package com.eden.agent.common.http;

import java.util.Map;

public interface HttpService {

    String get(String url, Map<String, String> paramMap);

    String get(String url, Map<String, String> paramMap, Map<String, String> header);

    String get(String url);

    String getHtml(String url);

    String post(String url, Map<String, String> paramMap);

    String post(String url, Map<String, String> paramMap, Map<String, String> header);

}
