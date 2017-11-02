# -*- coding: utf-8 -*-
import mysql.connector
from youtube import config

from youtube.youtube_info import web_info


def insert_base_info(web_info, config):
    info_hash = web_info.info_hash
    data_size = select_base_info(info_hash, config)
    if data_size > 0:
        delete_base_info(info_hash, config)
    default_int = '0'
    default_str = ''
    sql = '''INSERT INTO BASE_INFO 
           (KEY_WORD, PAGE_NUM, URL, VIDEO_LINKS, PUBLISH_STATUS,
           COLLECT_TIME,PUBLISH_TIME, CREATE_TIME, UPDATE_TIME,INFO_HASH) 
           VALUES (
           %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)'''
    param = (web_info.key_word,
             web_info.page_num,
             web_info.url,
             web_info.video_links,
             web_info.publish_status,
             web_info.collect_time,
             web_info.publish_time,
             web_info.create_time,
             web_info.update_time,
             web_info.info_hash
             )
    cnx = mysql.connector.connect(**config)
    cur = cnx.cursor()

    # Insert
    cur.execute(sql, param)
    cnx.commit()
    print ('insert data success, title:' + web_info.key_word +'--'+ str(web_info.page_num))


def delete_base_info(info_hash, config):
    sql = "DELETE FROM BASE_INFO where INFO_HASH='%s'" % (info_hash)
    cnx = mysql.connector.connect(**config)
    cur = cnx.cursor()
    cur.execute(sql)
    cnx.commit()
    print ('delete data success, info_hash:' + info_hash)


def select_base_info(info_hash, config):
    sql = "SELECT KEY_WORD, PAGE_NUM, URL, VIDEO_LINKS, " \
          "PUBLISH_STATUS,COLLECT_TIME,PUBLISH_TIME, CREATE_TIME, UPDATE_TIME,INFO_HASH FROM BASE_INFO where INFO_HASH= '%s'" % (info_hash)
    cnx = mysql.connector.connect(**config)
    cur = cnx.cursor()
    cur.execute(sql)
    data_size = len(cur.fetchall())
    cnx.commit()
    return data_size


def select_lastest_base_info(key_word, config):
    lastest_base_info = web_info()
    sql = "SELECT KEY_WORD, PAGE_NUM, URL, VIDEO_LINKS,CRAW_STATUS,INFO_HASH FROM BASE_INFO where KEY_WORD= '%s' order by PAGE_NUM desc limit 1" % (key_word)
    cnx = mysql.connector.connect(**config)
    cur = cnx.cursor()
    cur.execute(sql)
    result = cur.fetchone()
    cnx.commit()
    if result is None:
        pass
    else:
        data = list(result)
        lastest_base_info.key_word = data[0].encode("UTF-8")
        lastest_base_info.page_num = data[1]
        lastest_base_info.url = data[2].encode("UTF-8")
        lastest_base_info.craw_status = data[4]
    return lastest_base_info


def update_base_info(craw_status, key_word, config):
    sql = "UPDATE BASE_INFO SET CRAW_STATUS=%s, WHERE KEY_WORD='%s'"\
          % (craw_status, key_word)
    cnx = mysql.connector.connect(**config)
    cur = cnx.cursor()
    cur.execute(sql)
    cnx.commit()


