import json

import scrapy

from crawler.items import CrawlerItem


class SegmentFaultSpider(scrapy.Spider):
    name = "segmentfault"
    allowed_domains = ["segmentfault.com"]
    start_urls = ["https://segmentfault.com/"]

    def parse(self, response):
        for sel in response.xpath("//*[@id='root']/div[4]/div/div[1]/div[1]/div//a"):
            url = response.urljoin(sel.xpath("@href").get())
            if len(url) == 0:
                continue
            item = CrawlerItem()
            item["channelName"] = sel.xpath("text()").extract()
            req = scrapy.Request(url, callback=self.parse_url, meta={"item": item})
            yield req

    def parse_url(self, response):
        print(response.request.url)
        for url in response.xpath(
                "//*[@id='root']/div[4]/div/div[1]/div[2]/ul//a[@class='text-body']//@href").extract():
            if len(url) == 0:
                continue

            url = response.urljoin(url)
            req = scrapy.Request(url, callback=self.parse_details, meta=response.meta)
            yield req

    def parse_details(self, response):
        item = response.meta["item"]
        item["url"] = response.request.url
        item["origin"] = "思否"
        item["title"] = response.xpath("//a[@class='text-body']/text()").get()
        item["content"] = response.xpath("//article[@class='article fmt article-content']//*").getall()
        item["labels"] = response.xpath("//a[@class='m-1 badge-tag']/text()").getall()
        item["publishTime"] = response.xpath("//time/@datetime").get()
        item["authorName"] = response.xpath("//strong[@ class='align-self-center']/text()").get()
        if len(item["channelName"]) == 0 and len(item["labels"]) > 0:
            item["channelName"] = item["labels"][0]
        yield item
