# -*- coding: utf-8 -*-
import urllib

import requests

from bs4 import BeautifulSoup as bf

session_requests = requests.session()

headers = {
           'accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8',
           'accept-encoding': 'gzip, deflate, br',
           'accept-language': 'zh-CN,zh;q=0.8,de;q=0.6,en;q=0.4,ko;q=0.2,zh-TW;q=0.2',
           'user-agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36',
           'x-chrome-uma-enabled:': '1'}


def get_html(url):
    result = session_requests.get(url, headers=headers)
    return result.text


def get_html_with_header(url, header):
    result = session_requests.get(url, headers=header)

    return result.text


def get_soup(url):
    return get_soup_from_html(get_html(url))


def get_soup_with_header(url, header):
    return get_soup_from_html(get_html_with_header(url, header))


def get_soup_from_html(html):
    return bf(html, "html.parser")


def download(url, path):
    urllib.urlretrieve(url, path)
