package com.eden.agent.batch.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eden.agent.batch.executer.Executor;
import com.eden.agent.service.CollectService;

/**
 * Create by zhaoxianghui on 2017/11/1.
 */
@Component("collect")
public class CollectTask implements Executor {

    @Autowired
    private CollectService collectService;

    @Override
    public void start() {
        collectService.collect("china", 10);
    }
}
