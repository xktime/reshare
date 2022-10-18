import scrapy

from crawler.items import CrawlerItem

class CSDNSpider(scrapy.Spider):
    name = "csdn"
    allowed_domains = ["dmoz.org"]
    start_urls = [
        "https://www.csdn.net/"
    ]

    def parse(self, response):
        for sel in response.xpath('//ul/li'):
            link = sel.xpath('a/@href').extract()
            if len(link) == 0:
                continue
            item = CrawlerItem()
            item["url"] = link
            yield item