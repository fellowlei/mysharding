package com.mark.sharding.util;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.commons.collections4.MapUtils;

import java.io.IOException;
import java.util.Map;

/**
 * @Auther: lulei
 * @Date: 2019/9/21 15:46
 * @Description:
 */
@Slf4j
public class HttpUtil {
    public static final OkHttpClient client = new OkHttpClient();

    public static String get(String url) throws Exception {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
//            System.out.println(response.body().string());
            return response.body().string();
        }
    }

    public static String postForm(String url, Map<String,String> params) throws Exception {
        FormBody.Builder builder = new FormBody.Builder();
        if(MapUtils.isNotEmpty(params)){
            for (Map.Entry<String, String> entry : params.entrySet()) {
                builder.add(entry.getKey(),entry.getValue());
            }
        }
        FormBody formBody = builder.build();

        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            return response.body().string();
        }
    }

    public static final MediaType JSON  = MediaType.get("application/json; charset=utf-8");

    public static String postJson(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON,json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }


    public static void main(String[] args) throws Exception {
        for(int i=0; i<10; i++){
            String result = get("http://localhost:8080/api/order/query");
            System.out.println(result);
        }
    }
}
