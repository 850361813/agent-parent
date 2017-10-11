package com.eden.agent.batch.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eden.agent.batch.executer.Executor;
import com.eden.agent.service.PublishService;

@Component("publish")
public class PublishTask implements Executor {

    @Autowired
    private PublishService publishService;

    @Override
    public void start() {
        publishService.publish();
    }
}
