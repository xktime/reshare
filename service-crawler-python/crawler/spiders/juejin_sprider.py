import json

import scrapy

from crawler.items import CrawlerItem


class JuejinSpider(scrapy.Spider):
    name = "juejin"
    # allowed_domains = ["juejin.cn/"]
    start_urls = ["https://juejin.cn/"]

    def parse(self, response):
        for url in response.xpath("//a[@ class = 'nav-item']/@href").getall():
            if len(url) == 0:
                continue

            headers = {
                'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36',
                'origin': 'https://juejin.cn',
                'referer': response.urljoin(url),
                'accept-language': 'zh-CN,zh;q=0.9',
                'content-type': 'application/json',
                'sec-fetch-dest': 'empty',
                'sec-fetch-mode': 'cors',
                'sec-fetch-site': 'same-site',
            }

            params = {
                'cate_id': "6809637769959178254",
                'IdType': 2,
                'SortType': 200,
                'Cursor': "0",
                'Limit': 20,
            }
            req = scrapy.Request(
                url="https://api.juejin.cn/recommend_api/v1/article/recommend_cate_feed?aid=2608&uuid=7124291808393315872&spider=0",
                body=json.dumps(params),
                method="POST",
                headers=headers,
                callback=self.parse_url,
                dont_filter=True)

            print(req.headers)
            yield req

    def parse_url(self, response):
        res = json.loads(response.body.decode('UTF-8'))
        data_key = "data"
        if data_key not in res:
            return
        datas = res.get(data_key)
        print("url:", response.request.url)
        print("url:", datas[0]["article_id"])
        url = "https://juejin.cn/post/"
        for data in datas:
            article_info_key = "article_info"
            author_user_info_key = "author_user_info"
            tags_key = "tags"
            category_key = "category"
            item = CrawlerItem()
            # item["origin"] = "掘金"
            # item["title"] = data[article_info_key]["title"]
            # item["labels"] = get_tags(data[tags_key])
            item["url"] = url + data["article_id"]
            # item["publishTime"] = int(data[article_info_key]["ctime"]) * 1000
            # item["channelName"] = data[category_key]["category_name"]
            # item["authorName"] = data[author_user_info_key]["user_name"]
            req = scrapy.Request(item["url"], callback=self.parse_details, meta={"item": item})
            yield req

    def parse_details(self, response):
        item = response.meta["item"]
        # item["content"] = response.xpath("//div[@ class = 'article-content']//*").getall()
        yield item


def get_tags(params):
    results = []
    for param in params:
        results.append(param["tag_name"])
    return results
