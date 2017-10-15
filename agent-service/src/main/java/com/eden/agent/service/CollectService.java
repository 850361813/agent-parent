package com.eden.agent.service;

import com.eden.agent.dao.TaskInfoDao;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Create by zhaoxianghui on 2017/10/12.
 */
@Service
public class CollectService {

    private static final Logger logger = LoggerFactory.getLogger(CollectService.class);

    @Value("${web.collect.python.home}")
    private String pythonHome;

    @Value("${web.publish.keyWord}")
    private String configKeyWord;

    @Value("${web.publish.number.perDay}")
    private Integer numberPerDay;

    @Autowired
    private TaskInfoDao taskInfoDao;

    public void collect(String keyWord, Integer numberDay) {

        taskInfoDao.updateTaskInfo(1, "运行中", "craw");

        String queryKeyWord = keyWord;

        if (StringUtils.isBlank(queryKeyWord)) {
            queryKeyWord = configKeyWord;
        }

        if (numberDay != null) {
            numberPerDay = numberDay;
        }

        logger.info("begin collect key word : " + queryKeyWord + "page num:" + numberPerDay);

        String executeScript = "python  " + pythonHome + "youtube_crawer_task.py" + " " + queryKeyWord + " " + numberPerDay;

        logger.info("execute python script : " + executeScript);

        try {
            Process proc = Runtime.getRuntime().exec(executeScript);
            proc.waitFor();
        } catch (Exception e) {
            logger.error("crawer execute exception", e);
        }
        taskInfoDao.updateTaskInfo(0, "未运行", "craw");

        logger.info("finished collect key word : " + queryKeyWord);

    }

}
