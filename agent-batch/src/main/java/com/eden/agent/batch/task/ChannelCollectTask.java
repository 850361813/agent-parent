package com.eden.agent.batch.task;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.eden.agent.common.executer.Executor;
import com.eden.agent.dao.YoutubeEntityDao;
import com.eden.agent.domain.ChannelEntity;
import com.eden.agent.domain.YoutubeEntity;
import com.eden.agent.service.YoutubeService;
import com.google.common.collect.Lists;

/**
 * Create by zhaoxianghui on 2017/11/27.
 */
@Component("channelCollect")
public class ChannelCollectTask implements Executor {

    private static final Logger logger = LoggerFactory.getLogger(ChannelCollectTask.class);

    @Autowired
    private YoutubeService youtubeService;

    @Autowired
    private YoutubeEntityDao youtubeEntityDao;

    private int totalCount = 0;

    @Value("${youtube.channel.config}")
    private String channelConfig;
    @Value("${youtube.channel.maxNum}")
    private int maxNum;

    private static final String SPLIT_CHAR = ";";

    private static final String SPLIT_COMMA = ",";


    @Override
    public void start() {

        List<ChannelEntity> entities = Lists.newArrayList();

        if (StringUtils.isBlank(channelConfig) || !channelConfig.contains(",")) {
            logger.info("channel config is not right");
            return;
        }

        if (channelConfig.contains(SPLIT_CHAR)) {
            String[] channels = channelConfig.split(SPLIT_CHAR);
            for (String chan : channels) {

                if (!chan.contains(",")) {
                    logger.info("channel config is not right: " + chan);
                    continue;
                }
                ChannelEntity entity = new ChannelEntity();
                entity.setChannelName(chan.split(SPLIT_COMMA)[0]);
                entity.setChannelId(chan.split(SPLIT_COMMA)[1]);
                entities.add(entity);
            }
        } else {
            ChannelEntity entity = new ChannelEntity();
            entity.setChannelName(channelConfig.split(SPLIT_COMMA)[0]);
            entity.setChannelId(channelConfig.split(SPLIT_COMMA)[1]);
            entities.add(entity);
        }

        for (ChannelEntity entity : entities) {

            logger.info("collect for channel: " + entity.getChannelName());

            String nextPageToken = youtubeEntityDao.selectNextPageTokenByChannelName(entity.getChannelName());

            List<YoutubeEntity> entityList = Lists.newArrayList();

            entityList.addAll(youtubeService.search(entity, nextPageToken));

            if (CollectionUtils.isEmpty(entityList)) {
                return;
            }

            nextPageToken = entityList.get(0).getNextPageToken();

            while (StringUtils.isNoneBlank(nextPageToken) && totalCount < maxNum) {
                List<YoutubeEntity> subList = youtubeService.search(entity, nextPageToken);
                if (CollectionUtils.isEmpty(subList)) {
                    nextPageToken = null;
                    continue;
                }
                entityList.addAll(subList);
                totalCount = entityList.size();
                nextPageToken = subList.get(0).getNextPageToken();
            }

            if (CollectionUtils.isNotEmpty(entityList)) {
                for (YoutubeEntity e : entityList) {
                    youtubeEntityDao.insert(e);
                }
            }
        }

    }
}
