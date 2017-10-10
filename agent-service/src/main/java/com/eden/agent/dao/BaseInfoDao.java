package com.eden.agent.dao;

import com.eden.agent.domain.BaseInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaseInfoDao {
    List<BaseInfo> selectByKeyWord(@Param("keyWord") String keyWord);
    BaseInfo selectById(@Param("id") long id);
    void update(@Param("baseInfo") BaseInfo baseInfo);
}
