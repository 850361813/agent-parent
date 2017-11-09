CREATE DATABASE trans_video;

USE trans_video;

CREATE TABLE `base_info` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `KEY_WORD` varchar(64) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '关键字',
  `PAGE_NUM` int(8) NOT NULL DEFAULT '0' COMMENT '页数',
  `PAGE_URL` varchar(256) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT 'YouTube地址',
  `INPUT_URL` varchar(256) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '输入网址',
  `INPUT_TAG` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '输入标签',
  `VIDEO_LINKS` varchar(4096) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '视频链接',
  `VALID` int(11) NOT NULL DEFAULT '0' COMMENT '是否有效，0无效，1有效',
  `COLLECT_TIME` timestamp NOT NULL DEFAULT '1990-01-01 00:00:00' COMMENT '采集时间',
  `PUBLISH_TIME` timestamp NOT NULL DEFAULT '1990-01-01 00:00:00' COMMENT '发布时间',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_TIME` timestamp NOT NULL DEFAULT '1990-01-01 00:00:00' COMMENT '更新时间',
  `VIDEO_ID` varchar(64) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT 'youtube video id',
  `CRAW_STATUS` int(11) NOT NULL DEFAULT '0' COMMENT '抓取状态',
  `FETCH_STATUS` int(11) NOT NULL DEFAULT '0' COMMENT '抓取状态：0初始状态，1成功，2失败',
  `POST_STATUS` int(11) NOT NULL DEFAULT '0' COMMENT 'post状态：0初始状态，1成功，2失败',
  `PUBLISH_STATUS` int(11) NOT NULL DEFAULT '0' COMMENT '发布状态，0未发布，1已发布，2失败',
  `VIDEO_TITLE` varchar(1024) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '视频名称',
  `VIDEO_INFO` varchar(4096) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT '视频信息',
  `VIDEO_ITEM_ID` varchar(64) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0' COMMENT 'topbuzz网视频ID',
  PRIMARY KEY (`ID`),
  KEY `video_id` (`VIDEO_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=313 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

create table trans_video.task_info
(
	ID bigint not null auto_increment comment '自增ID'
		primary key,
	TASK_NAME varchar(64) default '0' not null comment '任务名称',
	TASK_STATUS int default '0' not null comment '任务状态'
)
;

INSERT INTO trans_video.task_info (TASK_NAME, TASK_STATUS) VALUES ('craw', 0);
INSERT INTO trans_video.task_info (TASK_NAME, TASK_STATUS) VALUES ('publish', 0);
