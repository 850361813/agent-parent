package com.eden.agent.common.http;

import com.google.common.collect.Maps;

import java.util.Map;

public class DemoHttpClient {

    private static String baseUrl = "http://219.129.165.68:8088";
    private static String loaderUrl = "/isis/getLoader"; // POST
    private static String getRealTime = "/isis/getRealtime"; // POST
    private static String versionUrl = "/isis/config/version.json"; // GET

    public static void main(String[] args) {
        login();
//        getLoader();
    }



    private static void login() {
        DefaultHttpService defaultHttpService = new DefaultHttpService();
        String result = defaultHttpService.get(baseUrl);
        System.out.println(result);
    }
    private static void getLoader() {
        Map<String, String> headerMap = Maps.newHashMap();
        headerMap.put("connection", "keep-alive");
        headerMap.put("Charsert", "UTF-8");
        headerMap.put("User-Agent", "Dalvik/2.1.0 (Linux; U; Android 5.1; PP5600 Build/PP56002D_REL_111I)");
        headerMap.put("Accept-Encoding", "gzip");
        headerMap.put("Host", "219.129.165.68:8088");
        headerMap.put("Content-Type", "application/x-www-form-urlencoded");

        Map<String, String> paramMap = Maps.newHashMap();
        paramMap.put("main.json", "");

        DefaultHttpService defaultHttpService = new DefaultHttpService();
        String result = defaultHttpService.post(baseUrl + getRealTime, null, headerMap);
        System.out.println(result);
    }
}
