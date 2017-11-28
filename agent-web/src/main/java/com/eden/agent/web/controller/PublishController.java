package com.eden.agent.web.controller;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.eden.agent.common.constants.TaskStatus;
import com.eden.agent.common.web.BaseResponse;
import com.eden.agent.dao.BaseInfoDao;
import com.eden.agent.dao.TaskInfoDao;
import com.eden.agent.dao.YoutubeEntityDao;
import com.eden.agent.domain.RequestEntity;
import com.eden.agent.domain.ResponseEntity;
import com.eden.agent.domain.TaskInfo;
import com.eden.agent.domain.YoutubeEntity;
import com.eden.agent.service.CollectService;
import com.eden.agent.service.NewPublishService;
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
    private NewPublishService newPublishService;

    @Autowired
    private TaskInfoDao taskInfoDao;

    @Autowired
    private YoutubeEntityDao youtubeEntityDao;

    private static ExecutorService executorService = Executors.newCachedThreadPool();

    @ResponseBody
    @RequestMapping(value = "/publish", method = RequestMethod.POST)
    public BaseResponse publish(@RequestBody RequestEntity requestEntity) {
        logger.info("begin publish: key word---" + requestEntity.getKeyWord());
        if (StringUtils.isNoneBlank(requestEntity.getKeyWord())) {
            newPublishService.setConfigKeyWord(requestEntity.getKeyWord());
        }
        executorService.submit(newPublishService);

        return BaseResponse.success();
    }

    @ResponseBody
    @RequestMapping(value = "/collect", method = RequestMethod.POST)
    public BaseResponse collect(@RequestBody RequestEntity requestEntity) {
        logger.info("nothing to do");
        return BaseResponse.success();
    }

    @ResponseBody
    @RequestMapping(value = "/status/craw", method = RequestMethod.POST)
    public BaseResponse<TaskInfo> crawStatus() {
        logger.info("request craw status---");
        BaseResponse<TaskInfo> baseResponse = new BaseResponse<TaskInfo>(taskInfoDao.selectByTaskName("craw"));
        logger.info("response craw status---" + TaskStatus.getDesc(baseResponse.getData().getTaskStatus()));
        return baseResponse;
    }

    @ResponseBody
    @RequestMapping(value = "/status/publish", method = RequestMethod.POST)
    public BaseResponse<TaskInfo> publishStatus() {
        logger.info("request publish status---");
        BaseResponse<TaskInfo> baseResponse = new BaseResponse<TaskInfo>(taskInfoDao.selectByTaskName("publish"));
        logger.info("response craw status---" + TaskStatus.getDesc(baseResponse.getData().getTaskStatus()));
        return baseResponse;
    }

    @ResponseBody
    @RequestMapping(value = "/status/publish/stop", method = RequestMethod.GET)
    public BaseResponse<String> stopPublish() {
        logger.info("stopPublish---");
        taskInfoDao.updateTaskInfo(0, "publish");
        BaseResponse<String> baseResponse = new BaseResponse<String>("success");
        return baseResponse;
    }

    @ResponseBody
    @RequestMapping(value = "/number/notpublish", method = RequestMethod.POST)
    public BaseResponse crawNumber(@RequestBody RequestEntity requestEntity) {
        System.out.println("keyword for craw : " + requestEntity.getKeyWord());
        long number = youtubeEntityDao.selectNotPublish(requestEntity.getKeyWord()).size();
        BaseResponse<Long> baseResponse = new BaseResponse<Long>(number);
        System.out.println("number for craw : " + number);
        return baseResponse;
    }

    @ResponseBody
    @RequestMapping(value = "/tags", method = RequestMethod.GET)
    public BaseResponse findAllTags() {
        List<String> tags = youtubeEntityDao.selectAllChannel();
        BaseResponse<List<String>> baseResponse = new BaseResponse<>(tags);
        return baseResponse;
    }

    @ResponseBody
    @RequestMapping(value = "/number/publish", method = RequestMethod.POST)
    public BaseResponse publishNumber(@RequestBody RequestEntity requestEntity) {
        System.out.println("keyword for craw : " + requestEntity.getKeyWord());
        long number = youtubeEntityDao.selectPublish(requestEntity.getKeyWord()).size();
        BaseResponse<Long> baseResponse = new BaseResponse<Long>(number);
        System.out.println("number for publish : " + number);
        return baseResponse;
    }
}
