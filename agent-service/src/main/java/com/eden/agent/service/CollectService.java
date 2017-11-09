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
public class CollectService implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(CollectService.class);

    @Value("${web.collect.python.home}")
    private String pythonHome;

    @Value("${web.publish.keyWord}")
    private String configKeyWord;

    @Value("${web.publish.number.perDay}")
    private Integer numberPerDay;

    @Autowired
    private TaskInfoDao taskInfoDao;

    @Override
    public void run() {
        collect();
    }

    public void collect() {

        taskInfoDao.updateTaskInfo(1, "craw");


        logger.info("begin collect key word : " + configKeyWord + "page num:" + numberPerDay);

        String executeScript = "python  " + pythonHome + "youtube_crawer_task.py" + " " + configKeyWord + " " + numberPerDay;

        logger.info("execute python script : " + executeScript);

        try {
            Process proc = Runtime.getRuntime().exec(executeScript);
            proc.waitFor();
        } catch (Exception e) {
            logger.error("crawer execute exception", e);
        }
        taskInfoDao.updateTaskInfo(0, "craw");

        logger.info("finished collect key word : " + configKeyWord);

    }

    public String getConfigKeyWord() {
        return configKeyWord;
    }

    public void setConfigKeyWord(String configKeyWord) {
        this.configKeyWord = configKeyWord;
    }

    public Integer getNumberPerDay() {
        return numberPerDay;
    }

    public void setNumberPerDay(Integer numberPerDay) {
        this.numberPerDay = numberPerDay;
    }
}
