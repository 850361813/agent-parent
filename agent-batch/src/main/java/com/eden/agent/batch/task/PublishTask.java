package com.eden.agent.batch.task;

import com.eden.agent.service.PublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublishTask {

    @Autowired
    private PublishService publishService;

    public void publish() {
        publishService.publish();
    }


}
