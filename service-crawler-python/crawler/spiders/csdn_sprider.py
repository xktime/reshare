import scrapy
from scrapy.spiders import Rule
from scrapy.linkextractors import LinkExtractor
from crawler.items import CrawlerItem


class CSDNSpider(scrapy.Spider):
    name = "csdn"
    allowed_domains = ["blog.csdn.net"]
    start_urls = ["https://www.csdn.net/"]

    def parse(self, response):
        print("parse start")
        for sel in response.xpath('//ul/li'):
            link = sel.xpath('a/@href').extract()
            if len(link) == 0:
                continue
            req = scrapy.Request(str(link[0]), callback=self.parse_details)
            yield req

    def parse_details(self, response):
        print("parse_details start")
        for url in response.xpath("//a[@ class = 'blog']/@href").getall():
            item = CrawlerItem()
            item["url"] = url
            yield item

