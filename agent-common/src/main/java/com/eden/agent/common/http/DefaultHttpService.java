package com.eden.agent.common.http;

import com.google.common.collect.Lists;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class DefaultHttpService implements HttpService {

    @Override
    public String get(String url, Map<String, Object> urlParams, Map<String, String> headers) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String responseBody = "";
        CloseableHttpResponse response = null;

        try {
            if (urlParams != null) {
                List<NameValuePair> params = Lists.newArrayList();
                for (String key : urlParams.keySet()) {
                    params.add(new BasicNameValuePair(key, (String) urlParams.get(key)));
                }
                String str = EntityUtils.toString(new UrlEncodedFormEntity(params, Consts.UTF_8));
                url = url + "?" + str;
            }

            HttpGet httpGet = new HttpGet(url);

            if (headers != null) {
                for (String key : headers.keySet()) {
                    httpGet.addHeader(key, headers.get(key));
                }
            }

            response = httpClient.execute(httpGet);
            System.out.println(response.toString());
            HttpEntity entity = response.getEntity();
            responseBody = EntityUtils.toString(entity);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return responseBody;
    }

    @Override
    public String get(String url, Map<String, Object> urlParams) {
        return get(url, urlParams, null);
    }

    @Override
    public String get(String url) {
        return get(url, null, null);
    }

    @Override
    public String post(String url, Map<String, Object> formParams) {
        return post(url, formParams, null);
    }

    @Override
    public String post(String url, Map<String, Object> formParams, Map<String, String> headers) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String responseBody = "";

        CloseableHttpResponse response = null;

        HttpPost httpPost = new HttpPost(url);

        if (headers != null) {
            for (String key : headers.keySet()) {
                httpPost.addHeader(key, headers.get(key));
            }
        }

        try {
            if (formParams != null) {
                MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create()
                        .setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
                ContentType contentType = ContentType.create(HTTP.PLAIN_TEXT_TYPE, HTTP.UTF_8);
                for (String key : formParams.keySet()) {
                    multipartEntityBuilder.addPart(key, new StringBody((String) formParams.get(key), contentType));
                }
                httpPost.setEntity(multipartEntityBuilder.build());
            }
            response = httpClient.execute(httpPost);

            System.out.println(response.getStatusLine());
            HttpEntity entity = response.getEntity();
            System.out.println(entity.getContent().toString());
            responseBody = EntityUtils.toString(entity);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return responseBody;
    }

    @Override
    public String post(String url, String json, Map<String, String> headers) {
        return null;
    }
}
