package com.eden.agent.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.eden.agent.common.constants.SearchTypeEnum;
import com.eden.agent.domain.YoutubeEntity;

/**
 * Create by zhaoxianghui on 2017/11/23.
 */
public class YoutubeEntityDaoTest extends BaseTest {

    @Autowired
    private YoutubeEntityDao youtubeEntityDao;

    @Test
    public void insert() throws Exception {
        YoutubeEntity youtubeEntity = new YoutubeEntity();
        youtubeEntity.setPrevPageToken("test");
        youtubeEntity.setNextPageToken("test");
        youtubeEntity.setPageToken("test");
        youtubeEntity.setKeyWord("test");
        youtubeEntity.setVideoId("videoid");
        youtubeEntity.setVideoLink("link");
        youtubeEntity.setSearchType(SearchTypeEnum.SEARCH_BY_KEYWORD.getCode());
        youtubeEntity.setCrawStatus(1);
        youtubeEntity.setChannelName("");
        youtubeEntity.setChannelId("");
        youtubeEntity.setVideoTitle("");
        youtubeEntityDao.insert(youtubeEntity);
    }

}