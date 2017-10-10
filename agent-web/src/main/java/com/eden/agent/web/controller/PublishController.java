package com.eden.agent.web.controller;

import com.eden.agent.domain.BaseInfo;
import com.eden.agent.service.PublishService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/video")
public class PublishController {

    @Autowired
    private PublishService publishService;

    @ResponseBody
    @RequestMapping("/publish")
    public List<BaseInfo> publish() {
        List<BaseInfo> baseInfoList = Lists.newArrayList();
        publishService.publish();
        return baseInfoList;
    }
}
