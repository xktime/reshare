# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: https://docs.scrapy.org/en/latest/topics/item-pipeline.html


# useful for handling different item types with a single interface

import pymongo
from crawler.util.snowflakeId_util import IdWorker


# import customize_settings as settings


class CrawlerPipeline:
    def open_spider(self, spider):
        my_client = pymongo.MongoClient("mongodb://root:1234@localhost:27017/?serverSelectionTimeoutMS=5000;connectTimeoutMS=10000")
        self.my_db = my_client["reshare"]["CrawlerVerifyArticle"]
        self.snowflake_worker = IdWorker(1, 2, 0)

    # def close_spider(self, spider):

    def process_item(self, item, spider):
        if self.my_db.find_one({'url': item["url"]}) is None:
            item["_id"] = self.snowflake_worker.get_id()
            self.my_db.insert_one(item)
        return item
