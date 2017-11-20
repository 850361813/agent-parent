package com.eden.agent.service;

import java.io.IOException;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.eden.agent.common.api.google.Auth;
import com.eden.agent.common.constants.SearchTypeEnum;
import com.eden.agent.domain.ChannelEntity;
import com.eden.agent.domain.YoutubeEntity;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Channel;
import com.google.api.services.youtube.model.ChannelListResponse;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.common.collect.Lists;

/**
 * Create by zhaoxianghui on 2017/11/20.
 */
@Service
public class DefaultYoutubeService implements YoutubeService {

    private static final Logger logger = LoggerFactory.getLogger(DefaultYoutubeService.class);

    private static final long NUMBER_OF_VIDEOS_RETURNED = 50;

    private static final String VIDEO_BASE_LINK = "https://www.youtube.com/watch?v=";

    private static YouTube youtube;

    @Value("${youtube.apikey}")
    private String apiKey;

    static {
        youtube = new YouTube.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY, new HttpRequestInitializer() {
            public void initialize(HttpRequest request) throws IOException {
            }
        }).setApplicationName("youtube").build();
    }

    @Override
    public List<YoutubeEntity> search(String keyWord, String pageToken) {

        int searchType = SearchTypeEnum.SEARCH_BY_KEYWORD.getCode();

        List<YoutubeEntity> entities = Lists.newArrayList();

        try {
            YouTube.Search.List search = youtube.search().list("id,snippet");
            search.setKey(apiKey);
            search.setQ(keyWord);
            search.setType("video");
            // search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
            search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);

            if (StringUtils.isNoneBlank(pageToken)) {
                search.setPageToken(pageToken);
            }

            // Call the API and print results.
            SearchListResponse searchResponse = search.execute();
            List<SearchResult> searchResultList = searchResponse.getItems();

            if (CollectionUtils.isEmpty(searchResultList)) {
                return entities;
            }

            for (SearchResult searchResult : searchResultList) {
                ResourceId rId = searchResult.getId();
                if (rId.getKind().equals("youtube#video")) {
                    YoutubeEntity entity = new YoutubeEntity();
                    logger.info("video id: " + rId.getVideoId());
                    entity.setPageToken(pageToken);
                    entity.setNextPageToken(searchResponse.getNextPageToken());
                    entity.setPrevPageToken(searchResponse.getNextPageToken());
                    entity.setSearchType(searchType);
                    entity.setKeyWord(keyWord);
                    entity.setVideoId(rId.getVideoId());
                    entity.setVideoLink(VIDEO_BASE_LINK + rId.getVideoId());
                    entity.setVideoTitle(searchResult.getSnippet().getTitle());
                    entities.add(entity);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return entities;
    }

    @Override
    public List<YoutubeEntity> search(ChannelEntity channelEntity, String pageToken) {

        int searchType = SearchTypeEnum.SEARCH_BY_CHANNEL.getCode();
        String channelId = channelEntity.getChannelId();
        String channelName = channelEntity.getChannelName();

        List<YoutubeEntity> entities = Lists.newArrayList();

        try {
            YouTube.Search.List search = youtube.search().list("id,snippet");
            search.setKey(apiKey);
            search.setType("channel");
            search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);

            if (StringUtils.isNoneBlank(pageToken)) {
                search.setPageToken(pageToken);
            }

            // Call the API and print results.
            SearchListResponse searchResponse = search.execute();
            List<SearchResult> searchResultList = searchResponse.getItems();

            if (CollectionUtils.isEmpty(searchResultList)) {
                return entities;
            }

            for (SearchResult searchResult : searchResultList) {
                ResourceId rId = searchResult.getId();
                if (rId.getKind().equals("youtube#video")) {
                    YoutubeEntity entity = new YoutubeEntity();
                    logger.info("video id: " + rId.getVideoId());
                    entity.setPageToken(pageToken);
                    entity.setNextPageToken(searchResponse.getNextPageToken());
                    entity.setPrevPageToken(searchResponse.getNextPageToken());
                    entity.setSearchType(searchType);
                    entity.setChannelId(channelId);
                    entity.setChannelName(channelName);
                    entity.setVideoId(rId.getVideoId());
                    entity.setVideoLink(VIDEO_BASE_LINK + rId.getVideoId());
                    entity.setVideoTitle(searchResult.getSnippet().getTitle());
                    entities.add(entity);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return entities;
    }

    @Override
    public List<ChannelEntity> getChannelId(String channelName) {

        List<ChannelEntity> entities = Lists.newArrayList();

        if (StringUtils.isBlank(channelName)) {
            return entities;
        }

        try {
            YouTube.Channels.List search = youtube.channels().list("id,snippet");
            search.setKey(apiKey);
            search.setForUsername(channelName);
            search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);

            // Call the API and print results.
            ChannelListResponse channelListResponse = search.execute();
            List<Channel> searchResultList = channelListResponse.getItems();

            if (searchResultList != null) {
                for (Channel channel : searchResultList) {
                    ChannelEntity entity = new ChannelEntity();
                    entity.setChannelName(channelName);
                    entity.setChannelId(channel.getId());
                }
            }
        } catch (IOException e) {
            System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
        }
        return entities;
    }
}
