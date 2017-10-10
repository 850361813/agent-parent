# -*- coding: utf-8 -*-
import cookielib
import urllib
import urllib2

import requests

from bs4 import BeautifulSoup as bf
from youtube import config

session_requests = requests.session()


def get_html(url):
    result = session_requests.get(url)

    return result.text


def get_soup(url):
    return get_soup_from_html(get_html(url))


def get_soup_from_html(html):
    return bf(html, "html.parser")


def download(url, path):
    urllib.urlretrieve(url, path)
