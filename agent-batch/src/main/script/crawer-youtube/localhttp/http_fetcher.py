# -*- coding: utf-8 -*-
import random
import urllib

import requests
import urllib2

import time

from bs4 import BeautifulSoup as bf

session_requests = requests.session()

headers = {
            'accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8',
           'user-agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36'}

def sleep_random_second():
    """
    随机暂停一段时间（针对网站反爬虫机制）
    :return:
    """
    print 'sleeping'
    sleep_time = random.randint(10, 30)
    time.sleep(sleep_time)
    print 'sleep for second:' + str(sleep_time)


def get_html(url):
    result = session_requests.get(url, headers=headers)
    sleep_random_second()
    # request = urllib2.Request(url, headers=headers)
    # response = urllib2.urlopen(request)
    print result.text
    return result.text


def get_soup(url):
    return get_soup_from_html(get_html(url))



def get_soup_from_html(html):
    return bf(html, "html.parser")


def download(url, path):
    urllib.urlretrieve(url, path)
