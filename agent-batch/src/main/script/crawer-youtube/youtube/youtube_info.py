# -*- coding: utf-8 -*-
class WebInfo:

    def __init__(self, key_word, page_num,page_url, input_url, input_tag, video_links,
                 valid, collect_time, publish_time, create_time, update_time, video_id, craw_status):
        self.key_word = key_word
        self.page_num = page_num
        self.page_url = page_url
        self.input_url = input_url
        self.input_tag = input_tag
        self.soup=None
        self.video_links = video_links
        self.valid = valid
        self.collect_time = collect_time
        self.publish_time = publish_time
        self.create_time = create_time
        self.update_time = update_time
        self.video_id=video_id
        self.craw_status = craw_status

    def __init__(self):
        self.key_word = ''
        self.page_num = 1
        self.page_url = ''
        self.input_url = ''
        self.input_tag = ''
        self.soup = None
        self.video_links = ''
        self.valid = 1 #默认有效
        self.collect_time = '1990-01-01 00:000:00'
        self.publish_time = '1990-01-01 00:000:00'
        self.create_time = '1990-01-01 00:000:00'
        self.update_time = '1990-01-01 00:000:00'
        self.video_id = ''
        self.craw_status = 0

    def gatherAttrs(self):
        return ",".join("{}={}"
                        .format(k, getattr(self, k))
                        for k in self.__dict__.keys())

    def __str__(self):
        return "[{}:{}]".format(self.__class__.__name__, self.gatherAttrs())


class TaskInfo:
    def __init__(self, task_name, task_status):
        self.task_name = task_name
        self.task_status = task_status

    def __init__(self):
        self.task_name = ''
        self.task_status = 0

    def gatherAttrs(self):
        return ",".join("{}={}"
                        .format(k, getattr(self, k))
                        for k in self.__dict__.keys())

    def __str__(self):
        return "[{}:{}]".format(self.__class__.__name__, self.gatherAttrs())
