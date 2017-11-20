package com.eden.agent.batch.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eden.agent.batch.executer.Executor;
import com.eden.agent.domain.YoutubeEntity;
import com.eden.agent.service.YoutubeService;

/**
 * Create by zhaoxianghui on 2017/11/20.
 */
@Component("collect")
public class CollectTask implements Executor {

    @Autowired
    private YoutubeService youtubeService;

    @Override
    public void start() {
        List<YoutubeEntity> entities = youtubeService.search("news", null);
    }
}
