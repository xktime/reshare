import os

import configparser

CONFIG_FILE = "../scrapy.cfg"


def get_config(field_name):
    if os.path.exists(os.path.join(os.getcwd(), CONFIG_FILE)):
        config = configparser.ConfigParser()
        config.read(CONFIG_FILE)
        return config.get("customize", field_name)
