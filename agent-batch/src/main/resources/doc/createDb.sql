create table trans_video.base_info
(
	ID bigint auto_increment comment '自增ID'
		primary key,
	KEY_WORD varchar(64) default '0' not null comment '关键字',
	PAGE_NUM int default '0' not null comment '页数',
	URL varchar(100) default '0' not null comment 'YouTube地址',
	VIDEO_LINKS varchar(4096) default '0' not null comment '视频链接',
	VALID int default '0' not null comment '是否有效，0无效，1有效',
	PUBLISH_STATUS int default '0' not null comment '发布状态，0未发布，1已发布',
	COLLECT_TIME timestamp default '1990-01-01 00:00:00' not null comment '采集时间',
	PUBLISH_TIME timestamp default '1990-01-01 00:00:00' not null comment '发布时间',
	CREATE_TIME timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
	UPDATE_TIME timestamp default '1990-01-01 00:00:00' not null comment '更新时间',
	INFO_HASH varchar(64) default '0' not null comment 'hash',
	FETCH_STATUS int default '0' not null comment '抓取状态：0初始状态，1抓取成功，2抓取失败',
	CRAW_STATUS int default '0' not null comment '抓取状态',
	VIDEO_TITLE varchar(1024) default '0' not null comment '视频名称',
	VIDEO_INFO varchar(2048) default '0' not null comment '获取视频信息',
	VIDEO_ITEM_ID varchar(64) default '0' not null comment 'topbuzz网视频ID',
	POST_STATUS int default '0' not null comment 'post状态：0初始状态，1抓取成功，2抓取失败'
)
;

create index info_hash
	on base_info (INFO_HASH)
;

create table trans_video.task_info
(
	ID bigint auto_increment comment '自增ID'
		primary key,
	TASK_NAME varchar(64) default '0' not null comment '任务名称',
	TASK_STATUS int default '0' not null comment '任务状态',
	TASK_DISP varchar(64) default '初始化' not null comment '任务状态显示'
)
;

INSERT INTO trans_video.task_info (TASK_NAME, TASK_STATUS, TASK_DISP) VALUES ('craw', 0, '未运行');
INSERT INTO trans_video.task_info (TASK_NAME, TASK_STATUS, TASK_DISP) VALUES ('publish', 0, '未运行');