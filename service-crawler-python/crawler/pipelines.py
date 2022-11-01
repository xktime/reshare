# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: https://docs.scrapy.org/en/latest/topics/item-pipeline.html


# useful for handling different item types with a single interface

import pymongo
import customize_settings as setting


class CrawlerPipeline:
    def open_spider(self, spider):
        my_client = pymongo.MongoClient(
            setting.get_config("mongo_url"))
        self.my_db = my_client[setting.get_config("mongo_name")][setting.get_config("mongo_collect_name")]

    # def close_spider(self, spider):

    def process_item(self, item, spider):
        if self.my_db.find_one({'_id': item["_id"]}) is None:
            self.my_db.insert_one(item)
        return item
