package com.eden.agent.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.eden.agent.domain.YoutubeEntity;

/**
 * Create by zhaoxianghui on 2017/11/22.
 */
@Repository
@Transactional
public interface YoutubeEntityDao {

    int insert(YoutubeEntity youtubeEntity);
    String selectNextPageToken(@Param("keyWord") String keyWord);
    List<YoutubeEntity> selectByKeyWord(@Param("keyWord") String keyWord);
    YoutubeEntity selectById(@Param("id") long id);
    List<YoutubeEntity> selectNotPublish(@Param("channelName") String channelName);
    List<YoutubeEntity> selectPublish(@Param("channelName") String channelName);
    void update(@Param("youtubeEntity") YoutubeEntity youtubeEntity);
    long selectCrawNumber(@Param("keyWord") String keyWord);
    long selectPublishNumber(@Param("keyWord") String keyWord);
    List<String> selectAllChannel();

    String selectNextPageTokenByChannelName(@Param("channelName") String channelName);

}
