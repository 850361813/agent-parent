package com.eden.agent.web.controller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.eden.agent.common.web.BaseResponse;
import com.eden.agent.dao.BaseInfoDao;
import com.eden.agent.dao.TaskInfoDao;
import com.eden.agent.domain.RequestEntity;
import com.eden.agent.domain.ResponseEntity;
import com.eden.agent.domain.TaskInfo;
import com.eden.agent.service.CollectService;
import com.eden.agent.service.PublishService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.print.BackgroundServiceLookup;

@Controller
@RequestMapping("/video")
public class PublishController {

    private static final Logger logger = LoggerFactory.getLogger(PublishController.class);

    @Autowired
    private PublishService publishService;

    @Autowired
    private CollectService collectService;

    @Autowired
    private TaskInfoDao taskInfoDao;

    @Autowired
    private BaseInfoDao baseInfoDao;

    private static ExecutorService executorService = Executors.newCachedThreadPool();

    @ResponseBody
    @RequestMapping(value = "/publish", method = RequestMethod.POST)
    public BaseResponse publish(@RequestBody RequestEntity requestEntity) {
        logger.info("begin publish: key word---" + requestEntity.getKeyWord());
        if (StringUtils.isNoneBlank(requestEntity.getKeyWord())) {
            publishService.setConfigKeyWord(requestEntity.getKeyWord());
        }
        executorService.submit(publishService);

        return BaseResponse.success();
    }

    @ResponseBody
    @RequestMapping(value = "/collect", method = RequestMethod.POST)
    public BaseResponse collect(@RequestBody RequestEntity requestEntity) {
        logger.info("begin collect: key word---" + requestEntity.getKeyWord());
        String pageNum = requestEntity.getPageNum();
        if (StringUtils.isBlank(pageNum)) {
            pageNum = "10";
        }
        if (StringUtils.isNoneBlank(requestEntity.getKeyWord())) {
            collectService.setConfigKeyWord(requestEntity.getKeyWord());
        }
        if (StringUtils.isNoneBlank(pageNum)) {
            collectService.setNumberPerDay(Integer.parseInt(pageNum));
        }
        executorService.submit(collectService);
        return BaseResponse.success();
    }

    @ResponseBody
    @RequestMapping(value = "/status/craw", method = RequestMethod.POST)
    public BaseResponse<TaskInfo> crawStatus() {
        logger.info("request craw status---");
        BaseResponse<TaskInfo> baseResponse = new BaseResponse<TaskInfo>(taskInfoDao.selectByTaskName("craw"));
        logger.info("response craw status---" + baseResponse.getData().getTaskDisp());
        return baseResponse;
    }

    @ResponseBody
    @RequestMapping(value = "/status/publish", method = RequestMethod.POST)
    public BaseResponse<TaskInfo> publishStatus() {
        logger.info("request publish status---");
        BaseResponse<TaskInfo> baseResponse = new BaseResponse<TaskInfo>(taskInfoDao.selectByTaskName("publish"));
        logger.info("response craw status---" + baseResponse.getData().getTaskDisp());
        return baseResponse;
    }

    @ResponseBody
    @RequestMapping(value = "/number/craw", method = RequestMethod.POST)
    public BaseResponse crawNumber(@RequestBody RequestEntity requestEntity) {
        System.out.println("keyword for craw : " + requestEntity.getKeyWord());
        long number = baseInfoDao.selectCrawNumber(requestEntity.getKeyWord());
        BaseResponse<Long> baseResponse = new BaseResponse<Long>(number);
        System.out.println("number for craw : " + number);
        return baseResponse;
    }

    @ResponseBody
    @RequestMapping(value = "/number/publish", method = RequestMethod.POST)
    public BaseResponse publishNumber(@RequestBody RequestEntity requestEntity) {
        System.out.println("keyword for craw : " + requestEntity.getKeyWord());
        long number = baseInfoDao.selectPublishNumber(requestEntity.getKeyWord());
        BaseResponse<Long> baseResponse = new BaseResponse<Long>(number);
        System.out.println("number for publish : " + number);
        return baseResponse;
    }
}
