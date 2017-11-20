package com.eden.agent.common.http;

import java.util.Map;

public interface HttpService {

    /**
     * simple http get
     * @param url
     * @return
     */
    String get(String url);

    String get(String url,
               Map<String, Object> urlParams);

    String get(String url,
               Map<String, Object> urlParams,
               Map<String, String> headers);

    String post(String url,
                Map<String, Object> formParams);

    String post(String url,
                Map<String, Object> formParams,
                Map<String, String> headers);

    String post(String url,
                String json,
                Map<String, String> headers);

}
