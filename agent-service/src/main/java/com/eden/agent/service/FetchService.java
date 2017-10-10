package com.eden.agent.service;

import com.eden.agent.common.http.DefaultHttpService;
import com.eden.agent.common.util.JSONObjectUtils;
import com.eden.agent.domain.BaseInfo;
import com.google.common.collect.Maps;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class FetchService {

    private static final Logger logger = LoggerFactory.getLogger(FetchService.class);

    @Value("#{configProperties['web.topbuzz.cookie']}")
    private String cookie;

    public BaseInfo fetch(BaseInfo baseInfo) {
        logger.info("fetch begin: " + baseInfo.getVideoLinks());
        DefaultHttpService httpService = new DefaultHttpService();
        String url = "https://topbuzz.com/pgc/article/video/fetch";
        Map<String, String> paramsMap = Maps.newHashMap();
        paramsMap.put("video_url", baseInfo.getVideoLinks());
        Map<String, String> headerMap = Maps.newHashMap();
        headerMap.put("Cookie", cookie);
        headerMap.put("Connection", "keep-alive");
        String json = httpService.get(url, paramsMap, headerMap);
        JSONObject data = JSONObjectUtils.getJsonObject(JSONObjectUtils.getJsonObject(json), "data");
        data = JSONObjectUtils.append(data, "video_file_type", "video");
        String videoTitle = JSONObjectUtils.getString(data, "video_title");
        System.out.println("video title: " + videoTitle);
        baseInfo.setVideoInfo(data.toString());
        baseInfo.setVideoTitle(videoTitle);
        logger.debug("fetch video info: " + data.toString());
        logger.info("fetch success");

        return baseInfo;

    }
}
