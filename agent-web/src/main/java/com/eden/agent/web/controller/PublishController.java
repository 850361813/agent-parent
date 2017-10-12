package com.eden.agent.web.controller;

import com.eden.agent.domain.BaseInfo;
import com.eden.agent.domain.RequestEntity;
import com.eden.agent.domain.ResponseEntity;
import com.eden.agent.service.CollectService;
import com.eden.agent.service.PublishService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/video")
public class PublishController {

    @Autowired
    private PublishService publishService;

    @Autowired
    private CollectService collectService;

    @ResponseBody
    @RequestMapping(value = "/publish", method = RequestMethod.POST)
    public ResponseEntity publish(@RequestBody RequestEntity requestEntity) {
        System.out.println(requestEntity.getKeyWord());
//        publishService.publish(requestEntity.getKeyWord());
        return ResponseEntity.success();
    }

    @ResponseBody
    @RequestMapping(value = "/collect", method = RequestMethod.POST)
    public ResponseEntity collect(@RequestBody RequestEntity requestEntity) {
        System.out.println(requestEntity.getKeyWord());
//        collectService.collect();
        return ResponseEntity.success();
    }
}
