package com.eden.agent.batch.task;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eden.agent.common.executer.Executor;
import com.eden.agent.dao.YoutubeEntityDao;
import com.eden.agent.domain.YoutubeEntity;
import com.eden.agent.service.YoutubeService;
import com.google.common.collect.Lists;

/**
 * Create by zhaoxianghui on 2017/11/20.
 */
@Component("collect")
public class KeyWordCollectTask implements Executor {

    @Autowired
    private YoutubeService youtubeService;

    @Autowired
    private YoutubeEntityDao youtubeEntityDao;

    private static final int MAX_NUM = 1000;

    private int totalCount = 0;

    @Override
    public void start() {

        String keyWord = "entertainment";

        String nextPageToken = youtubeEntityDao.selectNextPageToken(keyWord);

        List<YoutubeEntity> entityList;

        if (StringUtils.isBlank(nextPageToken)) {
            entityList = doCollect("entertainment", null);
        } else {
            entityList = doCollect("entertainment", nextPageToken);
        }

        if (CollectionUtils.isNotEmpty(entityList)) {
            for (YoutubeEntity entity : entityList) {
                youtubeEntityDao.insert(entity);
            }
        }



    }

    private List<YoutubeEntity> doCollect(String keyWord, String pageToken) {

        List<YoutubeEntity> result = Lists.newArrayList();

        List<YoutubeEntity> entities = youtubeService.search(keyWord, pageToken);

        totalCount = totalCount + entities.size();

        if (CollectionUtils.isNotEmpty(entities)) {
            String nextPageToken = entities.get(0).getNextPageToken();
            result.addAll(entities);
            if (StringUtils.isNoneBlank(nextPageToken) && totalCount < MAX_NUM) {
                entities = doCollect(keyWord, nextPageToken);
                result.addAll(entities);
            }
        }

        return result;
    }
}
