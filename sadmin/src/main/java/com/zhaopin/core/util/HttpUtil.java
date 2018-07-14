package com.zhaopin.core.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HttpUtil {
    private static Logger log = LoggerFactory.getLogger(HttpUtil.class);

    public static String get(String url, int time) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        try {
            HttpGet httpGet = new HttpGet(url);
            RequestConfig config = RequestConfig.custom().setSocketTimeout(time).setConnectTimeout(time).build();
            httpGet.setConfig(config);
            response = httpClient.execute(httpGet);
            int scode = response.getStatusLine().getStatusCode();
            if (HttpStatus.SC_OK == scode) {
                HttpEntity entity = response.getEntity();
                return EntityUtils.toString(entity);
            }else {
                HttpEntity entity = response.getEntity();
                System.out.printf( EntityUtils.toString(entity));
            }
        } finally {
            if (response != null) {
                response.close();
            }
            if (httpClient != null) {
                httpClient.close();
            }
        }
        return null;
    }

    public static String post(String url, String data, int time) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(time).setConnectTimeout(time).build();
            httpPost.setConfig(requestConfig);
            StringEntity myEntity = new StringEntity(data, "UTF-8");
            httpPost.addHeader("Content-Type", "text/json");
            httpPost.addHeader("charset", "utf-8");
            httpPost.setEntity(myEntity);
            response = httpclient.execute(httpPost);
            if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                HttpEntity entity = response.getEntity();
                return EntityUtils.toString(entity);
            }
            if (HttpStatus.SC_OK != response.getStatusLine().getStatusCode()) {
                HttpEntity entity = response.getEntity();
                log.error("请求结果异常{}，{},{}", url, data, EntityUtils.toString(entity));
            }
        } finally {
            if (response != null) {
                response.close();
            }
            if (httpclient != null) {
                httpclient.close();
            }
        }
        return "";
    }

}
