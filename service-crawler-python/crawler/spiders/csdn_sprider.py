import scrapy

from crawler.items import CrawlerItem
import datetime


class CSDNSpider(scrapy.Spider):
    name = "csdn"
    allowed_domains = ["blog.csdn.net"]
    start_urls = ["https://www.csdn.net/"]

    def parse(self, response):
        for sel in response.xpath('//ul/li'):
            link = sel.xpath('a/@href').extract()
            if len(link) == 0:
                continue
            req = scrapy.Request(str(link[0]), callback=self.parse_url)
            yield req

    def parse_url(self, response):
        for url in response.xpath("//a[@ class = 'blog']/@href").getall():
            if len(url) == 0:
                continue
            req = scrapy.Request(str(url), callback=self.parse_details)
            yield req

    def parse_details(self, response):
        item = CrawlerItem()
        item["url"] = response.request.url
        item["origin"] = "CSDN博客"
        item["title"] = response.xpath("//h1[@ id = 'articleContentId']/text()").get()
        item["channelName"] = response.xpath("//a[@ class = 'tag-link']/text()").get()
        item["content"] = response.xpath("//div[@ id = 'content_views']//*").getall()
        item["labels"] = response.xpath("//a[@ class = 'tag-link']/text()").getall()
        item["publishTime"] = datetime.datetime.now()
        item["authorName"] = response.xpath("//a[@ class = 'follow-nickName']/text()").get()
        yield item
