package com.eden.agent.dao;

import com.eden.agent.domain.BaseInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface BaseInfoDao {
    List<BaseInfo> selectByKeyWord(@Param("keyWord") String keyWord);
    List<BaseInfo> selectNotPublishedByKeyWord(@Param("keyWord") String keyWord);
    BaseInfo selectById(@Param("id") long id);
    void update(@Param("baseInfo") BaseInfo baseInfo);
    long selectCrawNumber(@Param("keyWord") String keyWord);
    long selectPublishNumber(@Param("keyWord") String keyWord);
}
