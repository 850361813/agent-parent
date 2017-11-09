package com.eden.agent.dao;

import com.eden.agent.domain.BaseInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface BaseInfoDao {
    List<BaseInfo> selectByTag(@Param("inputTag") String inputTag);
    List<BaseInfo> selectNotPublishedByInputTag(@Param("inputTag") String inputTag);
    BaseInfo selectById(@Param("id") long id);
    void update(@Param("baseInfo") BaseInfo baseInfo);
    long selectCrawNumber(@Param("inputTag") String inputTag);
    long selectPublishNumber(@Param("inputTag") String inputTag);
    List<String> selectAllTags();
}
