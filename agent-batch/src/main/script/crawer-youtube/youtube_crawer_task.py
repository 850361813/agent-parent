# -*- coding: utf-8 -*-
import os
import re
import time
import urllib2
import hashlib
import copy

import datetime

import sys


from dao import youtube_info_dao
from http import http_fetcher
from youtube import config
from youtube.youtube_info import web_info
from common import log_config


def get_next_page_website(soup):
    '''
    获取下一页
    :param soup:
    :return: next page url
    '''
    page_info = soup.find("div", attrs={'class': 'branded-page-box search-pager spf-link '})
    if page_info is not None:
        pattern = re.compile( r'([\s\S]*)<a([\s\S]*)href="([\s\S]*)"><([\s\S]*)>Next([\s\S]*)a>')
        match = pattern.match(page_info.encode("utf-8"))
        if match is not None:
            print home_url + match.group(3)
            return home_url + match.group(3)
        else:
            return ''


def calc_info_hash(key_word, video_link):
    md5 = hashlib.md5()
    md5.update(key_word + video_link)
    return md5.hexdigest()


def get_detail_website(info, soup):
    '''
    获取列表中视频链接地址
    :param info: YoutubeInfo.WebInfo
    :return:
    '''
    list_info = []
    self_soup = soup
    if self_soup is None:
        self_soup = handle_single_url(info.url)
    detail_list = self_soup.find_all("h3", attrs={'class': 'yt-lockup-title '})
    if detail_list is not None:
        video_links = []
        for detail in detail_list:
            pattern = re.compile(
                r'<h3([\s\S]*)data-sessionlink([\s\S]*)href="([\s\S]*)" rel="spf-prefetch"([\s\S]*)</h3>')
            match = pattern.match(detail.encode("utf-8"))
            if match is not None:
                video_link = home_url + match.group(3)
                sub_info = copy.copy(info)
                sub_info.video_links = video_link
                sub_info.info_hash = calc_info_hash(key_words, video_link)
                list_info.append(sub_info)
    return list_info


def handle_single_url(url):
    """
    单个URL处理
    :param url:
    :return: BeautifulSoup
    """
    begin = time.time() * 1000
    soup = http_fetcher.get_soup(url)
    now = time.time() * 1000
    print ('time spends: ' + str(now - begin) + 'ms for processing url:' + url)
    return soup


if __name__ == '__main__':
    log_config.logger().info("crawer begin")
    reload(sys)
    db_config = config.load_db_config()
    system_config = config.load_system_config()
    key_words = ''
    if len(sys.argv) == 2:
        key_words = sys.argv[1]
    else:
        key_words = system_config.get('key_words')
    daily_fetch_page=int(system_config.get('daily_fetch_page'))
    home_url=system_config.get('home_url')
    lastest_web_info = youtube_info_dao.select_lastest_base_info(key_words, db_config)
    start_page = 1;
    if lastest_web_info.page_num is not None:
        start_page = lastest_web_info.page_num;

    now = datetime.datetime.now()

    # 已经爬取过的页面不重复爬取
    soup = None
    if start_page == 1:
        quoto_key_words = urllib2.quote(key_words, ':?=/')
        query_url = home_url + '/results?search_query=' + quoto_key_words
        soup = handle_single_url(query_url)
    else:
        soup = handle_single_url(lastest_web_info.url)

    for i in range(start_page-1, daily_fetch_page + start_page):
        print 'begin craw page : ' + str(i)
        request_url=get_next_page_website(soup)
        if request_url=='':
            print '关键字：' + key_words + '抓取完成'
            youtube_info_dao.update_base_info(1, key_words, db_config)
            sys.exit(0)
        soup = handle_single_url(request_url)
        info = web_info()
        info.key_word = key_words
        info.url=request_url
        info.page_num=i+1
        info.soup=soup
        info.create_time=now
        info.update_time=now
        list_info = get_detail_website(info, soup)
        if list_info is not None:
            for detail_info in list_info:
                youtube_info_dao.insert_base_info(detail_info, db_config)

    log_config.logger().info("crawer finish")

