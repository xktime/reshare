from scrapy.utils.project import get_project_settings
from scrapy.crawler import CrawlerProcess
import customize_settings as settings

setting = get_project_settings()
process = CrawlerProcess(setting)

for spider_name in process.spiders.list():
    if spider_name in settings.get_config("dont_work_spider"):
        continue
    print("Running spider %s" % (spider_name))
    process.crawl(spider_name)
process.start()
