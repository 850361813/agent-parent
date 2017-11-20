package com.eden.agent.common.http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Create by zhaoxianghui on 2017/11/20.
 */
public class OkhttpClientService {

    private final HashMap<HttpUrl, List<Cookie>> cookieStore = new HashMap<>();

    public static void main(String[] args) {
        OkhttpClientService service = new OkhttpClientService();
        service.get();
    }

    public void get() {
        String url = "https://www.baidu.com/";

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().cookieJar(new CookieJar() {
            @Override
            public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                cookieStore.put(url, cookies);
            }

            @Override
            public List<Cookie> loadForRequest(HttpUrl url) {
                List<Cookie> cookies = cookieStore.get(url);
                return cookies != null ? cookies : new ArrayList<>();
            }
        }).build();

        Request request = new Request.Builder()
                                  .url(url)
                                  .build();
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
