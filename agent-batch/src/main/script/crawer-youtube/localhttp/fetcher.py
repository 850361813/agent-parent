# -*- coding: utf-8 -*-
import random
import urllib

import requests

import time

from bs4 import BeautifulSoup as bf
from common import log_config

session_requests = requests.session()

headers = {
            'accept': '*/*'}


def sleep_random_second():
    """
    随机暂停一段时间（针对网站反爬虫机制）
    :return:
    """
    log_config.logger().info("system sleeping")
    sleep_time = random.randint(20, 60)
    time.sleep(sleep_time)
    log_config.logger().info('awake second:' + str(sleep_time))


def get_html(url):
    sleep_random_second()
    log_config.logger().info("request url: " + url)
    result = session_requests.get(url, headers=headers)
    return result.text


def get_json(url):
    sleep_random_second()
    log_config.logger().info("request url: " + url)
    result = session_requests.get(url)
    return result.json()


def get_soup(url):
    begin = time.time() * 1000
    soup = get_soup_from_html(get_html(url))
    now = time.time() * 1000
    print ('time spends: ' + str(now - begin) + 'ms for processing url:' + url)
    return soup


def get_soup_from_html(html):
    return bf(html, "html.parser")


def download(url, path):
    urllib.urlretrieve(url, path)
