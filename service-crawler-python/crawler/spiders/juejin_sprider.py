import json

import scrapy

from crawler.items import CrawlerItem


class JuejinSpider(scrapy.Spider):
    name = "juejin"
    allowed_domains = ["juejin.cn"]
    start_urls = ["https://juejin.cn/"]

    def parse(self, response):
        cate_id_list = ["6809637773935378440",  # 算法
                        "6809637769959178254",  # 后端
                        "6809637767543259144",  # 前端
                        "6809635626879549454",  # 安卓
                        "6809635626661445640",  # ios
                        "6809637771511070734",  # 开发工具
                        "6809637776263217160",  # 代码人生
                        "6809637772874219534",  # 阅读
                        ]
        for cate_id in cate_id_list:
            headers = {
                'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36',
                'origin': 'https://juejin.cn',
                'referer': 'https://juejin.cn',
                'accept-language': 'zh-CN,zh;q=0.9',
                'content-type': 'application/json',
                'sec-fetch-dest': 'empty',
                'sec-fetch-mode': 'cors',
                'sec-fetch-site': 'same-site',
            }

            params = {
                'cate_id': cate_id,  # 文章类型
                'id_type': 2,
                'sort_type': 200,
                'cursor': str(0),  # 页码
                'limit': 20,
            }
            req = scrapy.Request(
                url="https://api.juejin.cn/recommend_api/v1/article/recommend_cate_feed?aid=2608&uuid=7124291808393315872&spider=0",
                body=json.dumps(params),
                method="POST",
                headers=headers,
                callback=self.parse_url,
                dont_filter=True)
            yield req

    def parse_url(self, response):
        res = json.loads(response.body.decode('UTF-8'))
        data_key = "data"
        if data_key not in res:
            return
        datas = res.get(data_key)
        url = "https://juejin.cn/post/"
        for data in datas:
            article_info_key = "article_info"
            author_user_info_key = "author_user_info"
            tags_key = "tags"
            category_key = "category"
            item = CrawlerItem()
            item["origin"] = "掘金"
            item["title"] = data[article_info_key]["title"]
            item["labels"] = get_tags(data[tags_key])
            item["url"] = url + data["article_id"]
            item["_id"] = data["article_id"]
            item["publishTime"] = int(data[article_info_key]["ctime"]) * 1000
            item["channelName"] = data[category_key]["category_name"]
            item["authorName"] = data[author_user_info_key]["user_name"]
            req = scrapy.Request(item["url"], callback=self.parse_details, meta={"item": item})
            yield req

    def parse_details(self, response):
        item = response.meta["item"]
        item["content"] = response.xpath("//div[@ class = 'article-content']//*").getall()
        yield item


def get_tags(params):
    results = []
    for param in params:
        results.append(param["tag_name"])
    return results
