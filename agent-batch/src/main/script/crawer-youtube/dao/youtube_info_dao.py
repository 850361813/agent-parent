# -*- coding: utf-8 -*-
import mysql.connector

from common import log_config


def insert_base_info(web_info, config):
    video_id = web_info.video_id
    if is_video_exist(video_id, config):
        log_config.logger().info('video: ' + video_id + 'already exist.')
        return

    sql = '''INSERT INTO BASE_INFO 
           (KEY_WORD, PAGE_NUM, PAGE_URL, INPUT_URL, INPUT_TAG, VIDEO_LINKS,VALID,VIDEO_ID,CRAW_STATUS,
           COLLECT_TIME, CREATE_TIME, UPDATE_TIME) 
           VALUES (
           %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)'''
    param = (web_info.key_word,
             web_info.page_num,
             web_info.page_url,
             web_info.input_url,
             web_info.input_tag,
             web_info.video_links,
             web_info.valid,
             web_info.video_id,
             web_info.craw_status,
             web_info.collect_time,
             web_info.create_time,
             web_info.update_time,
             )
    cnx = mysql.connector.connect(**config)
    cur = cnx.cursor()

    # Insert
    cur.execute(sql, param)
    cnx.commit()
    print ('insert data success, video id:' + web_info.video_id + 'input url: ' + str(web_info.input_url))


def is_video_exist(video_id, config):
    sql = "SELECT VIDEO_ID FROM BASE_INFO where VIDEO_ID= '%s'" % (video_id)
    cnx = mysql.connector.connect(**config)
    cur = cnx.cursor()
    cur.execute(sql)
    data_size = len(cur.fetchall())
    cnx.commit()
    if data_size > 0:
        return True

    return False


def update_task_info(status, config):
    task_name = 'craw'
    sql = "UPDATE TASK_INFO SET TASK_STATUS=%s WHERE TASK_NAME='%s'"\
          % (status, task_name)

    cnx = mysql.connector.connect(**config)
    cur = cnx.cursor()
    cur.execute(sql)
    cnx.commit()


