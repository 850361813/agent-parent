package com.eden.agent.batch.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eden.agent.common.executer.Executor;
import com.eden.agent.service.NewPublishService;

@Component("publish")
public class PublishTask implements Executor {

    @Autowired
    private NewPublishService newPublishService;

    @Override
    public void start() {
        newPublishService.publish();
    }
}
