package com.eden.agent.service;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    public void collect(String keyWord) {

        String queryKeyWord = keyWord;

        if (StringUtils.isBlank(queryKeyWord)) {
            queryKeyWord = configKeyWord;
        }

        String pythonScript = pythonHome + "youtube_crawer_task.py";
        try {
            Process proc = Runtime.getRuntime().exec("python  " + pythonScript + " " + queryKeyWord);
            proc.waitFor();
        } catch (Exception e) {
            logger.error("crawer execute exception", e);
        }
    }

    public void collect() {
        collect(null);
    }

}
