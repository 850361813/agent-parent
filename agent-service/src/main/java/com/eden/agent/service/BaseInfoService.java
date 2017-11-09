package com.eden.agent.service;

import com.eden.agent.dao.BaseInfoDao;
import com.eden.agent.domain.BaseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaseInfoService {

    @Autowired
    private BaseInfoDao baseInfoDao;

    public List<BaseInfo> selectByKeyWord(String keyWord) {
        return baseInfoDao.selectByTag(keyWord);
    }

    public BaseInfo selectById(long id) {
        return baseInfoDao.selectById(id);
    }

    public void update(BaseInfo baseInfo) {
        baseInfoDao.update(baseInfo);
    }

}
