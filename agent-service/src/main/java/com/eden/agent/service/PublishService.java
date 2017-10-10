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
public class PublishService {

    private static final Logger logger = LoggerFactory.getLogger(PublishService.class);

    @Value("#{configProperties['web.topbuzz.cookie']}")
    private String cookie;

    public BaseInfo post(BaseInfo baseInfo) {
        logger.info("post begin : video title-" + baseInfo.getVideoTitle());
        DefaultHttpService httpService = new DefaultHttpService();
        String url = "https://topbuzz.com/pgc/article/publish/post";
        Map<String, String> paramsMap = Maps.newHashMap();
        paramsMap.put("item_id", "");
        paramsMap.put("publish", "0");
        paramsMap.put("article_type", "1");
        paramsMap.put("title", baseInfo.getVideoTitle());
        paramsMap.put("tags", "");
        paramsMap.put("content", "");
        paramsMap.put("has_video", "0");
        paramsMap.put("video_info", baseInfo.getVideoInfo());
        paramsMap.put("paste_url_count", "1");
        Map<String, String> headerMap = Maps.newHashMap();
        headerMap.put("Cookie", cookie);
        headerMap.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36");
        headerMap.put("Accept", "*/*");
        headerMap.put("Accept-Encoding", "gzip, deflate, br");
        headerMap.put("Accept-Language", "zh-CN,zh;q=0.8");
        headerMap.put("scheme", "https");
        headerMap.put("Connection", "keep-alive");
        String json = httpService.post(url, paramsMap, headerMap);
        System.out.println(json);
        JSONObject data = JSONObjectUtils.getJsonObject(JSONObjectUtils.getJsonObject(json), "data");
        String itemId = JSONObjectUtils.getString(data, "item_id");
        baseInfo.setVideoItemId(itemId);
        logger.info("post finish : video item Id-" + itemId);
        return baseInfo;
    }

    public String publish(BaseInfo baseInfo) {
        logger.info("publish begin : video title-" + baseInfo.getVideoTitle());
        DefaultHttpService httpService = new DefaultHttpService();
        String url = "https://topbuzz.com/pgc/article/publish/post";
        Map<String, String> paramsMap = Maps.newHashMap();
        paramsMap.put("item_id", baseInfo.getVideoItemId());
        paramsMap.put("publish", "1");
        paramsMap.put("article_type", "1");
        paramsMap.put("title", baseInfo.getVideoTitle());
        paramsMap.put("tags", "");
        paramsMap.put("content", "");
        paramsMap.put("has_video", "0");
        paramsMap.put("video_info", baseInfo.getVideoInfo());
        paramsMap.put("paste_url_count", "1");
        Map<String, String> headerMap = Maps.newHashMap();
        headerMap.put("Cookie", cookie);
        headerMap.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36");
        headerMap.put("Accept", "*/*");
        headerMap.put("Accept-Encoding", "gzip, deflate, br");
        headerMap.put("Accept-Language", "zh-CN,zh;q=0.8");
        headerMap.put("scheme", "https");
        headerMap.put("Connection", "keep-alive");
        return httpService.post(url, paramsMap, headerMap);
    }

    private static boolean isSuccessMessage(String json) {
        JSONObject data = JSONObjectUtils.getJsonObject(json);
        if (data != null && JSONObjectUtils.getString(data, "message").equals("success")) {
            return true;
        }
        return false;
    }

}
