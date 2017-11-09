# -*- coding: utf-8 -*-
import re
import sys

from common import reader
from dao import youtube_info_dao
from youtube import config
from common import log_config
from localhttp import fetcher
from youtube.youtube_info import WebInfo


def craw_task(input_tag, input_url):
    video_list = []
    soup = fetcher.get_soup(input_url)
    video_detail_list = soup.find_all("h3", attrs={'class': 'yt-lockup-title '})
    if video_detail_list is not None:
        for video_detail in video_detail_list:
            pattern = re.compile(
                r'<h3([\s\S]*)data-sessionlink([\s\S]*)href="([\s\S]*)" title="([\s\S]*)</h3>')
            match = pattern.match(video_detail.encode("utf-8"))
            if match is not None:
                video_link = home_url + match.group(3)
                sub_info = WebInfo()
                sub_info.input_url = input_url
                sub_info.page_url = input_url
                sub_info.input_tag = input_tag
                sub_info.page_num = 1
                sub_info.craw_status=1
                sub_info.video_links = video_link
                sub_info.video_id = match.group(3)
                video_list.append(sub_info)
                youtube_info_dao.insert_base_info(sub_info, db_config)


if __name__ == '__main__':
    reload(sys)
    db_config = config.load_db_config()
    system_config = config.load_system_config()
    home_url = system_config.get('home_url')

    youtube_info_dao.update_task_info(0, db_config)
    file_data = reader.read(config.get_request_file())
    for f in file_data:
        input_tag = f.split(',')[0]
        input_url = f.split(',')[1]
        log_config.logger().info('begin--input tag: ' + input_tag + ';input url: ' + input_url)
        craw_task(input_tag=input_tag, input_url=input_url)
    log_config.logger().info('task finish')
    youtube_info_dao.update_task_info(1, db_config)

