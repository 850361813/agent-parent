package com.eden.agent.web.controller;

import com.eden.agent.domain.BaseInfo;
import com.eden.agent.service.BaseInfoService;
import com.eden.agent.service.FetchService;
import com.eden.agent.service.PublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/video")
public class PublishController {

    @Autowired
    private BaseInfoService baseInfoService;

    @Autowired
    private FetchService fetchService;

    @Autowired
    private PublishService publishService;

    @ResponseBody
    @RequestMapping("/fetch")
    public List<BaseInfo> fetchVideoInfo() {
        List<BaseInfo> baseInfoList = baseInfoService.selectByKeyWord("美国");
        for (BaseInfo baseInfo : baseInfoList) {
            try {
                Thread.sleep(1000 * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            BaseInfo baseInfo2 = fetchService.fetch(baseInfo);
            baseInfoService.update(baseInfo2);
        }
        return baseInfoList;
    }

    @ResponseBody
    @RequestMapping("/post")
    public BaseInfo postVideoInfo() {
        BaseInfo baseInfo = publishService.post(baseInfoService.selectById(253));
        baseInfoService.update(baseInfo);
        return baseInfo;
    }

    @ResponseBody
    @RequestMapping("/publish")
    public String publishVideoInfo() {
        BaseInfo baseInfo = publishService.post(baseInfoService.selectById(253));
        String publishResult = publishService.publish(baseInfoService.selectById(253));
        return publishResult;
    }
}
