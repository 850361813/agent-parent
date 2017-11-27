package com.eden.agent.service;

import java.util.List;

import com.eden.agent.domain.ChannelEntity;
import com.eden.agent.domain.YoutubeEntity;

/**
 * Create by zhaoxianghui on 2017/11/20.
 */
public interface YoutubeService {

    /**
     * search by keyword, set pageToken = null if first search
     * @param keyWord 关键字
     * @param pageToken youtube 返回的 页码token
     * @return PageableYoutubeEntity
     */
    List<YoutubeEntity> search(String keyWord, String pageToken);

    /**
     * 根据channel名称，如：DiscoveryNetworks 获取 ChannelEntity
     * @param channelName channel名称
     * @return channelName&channelId = ChannelEntity
     */
    List<ChannelEntity> getChannelId(String channelName);

    /**
     * search by channelId, set pageToken = null if first search
     * @param channelEntity channelEntity
     * @param pageToken youtube 返回的 页码token
     * @return PageableYoutubeEntity
     */
    List<YoutubeEntity> search(ChannelEntity channelEntity,String pageToken);

    List<YoutubeEntity> searchByChannelId(String channelId,String pageToken);


    void guideCategories();
}
