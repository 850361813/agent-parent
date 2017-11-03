# -*- coding: utf-8 -*-
import random
import urllib

import requests

import time

from bs4 import BeautifulSoup as bf
from common import log_config

session_requests = requests.session()

headers = {
            'accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8',
           'user-agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36'}

def sleep_random_second():
    """
    随机暂停一段时间（针对网站反爬虫机制）
    :return:
    """
    log_config.logger().info("request sleeping")
    sleep_time = random.randint(10, 30)
    time.sleep(sleep_time)
    log_config.logger().info('sleeping over for second:' + str(sleep_time))


def get_html(url):
    sleep_random_second()
    log_config.logger().info("request url: " + url)
    result = session_requests.get(url)
    return result.text


def get_soup(url):
    return get_soup_from_html(get_html(url))



def get_soup_from_html(html):
    return bf(html, "html.parser")


def download(url, path):
    urllib.urlretrieve(url, path)
