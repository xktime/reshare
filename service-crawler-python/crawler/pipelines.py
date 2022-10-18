# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: https://docs.scrapy.org/en/latest/topics/item-pipeline.html


# useful for handling different item types with a single interface
from itemadapter import ItemAdapter

from crawler.items import CrawlerItem


class CrawlerPipeline:
    def open_spider(self, spider):
        print("=============open====================")
        self.fp = open( "data.txt", "w")

    def close_spider(self, spider):
        print("=============close====================")
        self.fp.close()

    def process_item(self, item, spider):
        print("write spider:", spider)
        url = str(item["url"])
        self.fp.write(url + "\n")
        return item
