package com.tommeng.simplejackexample.networking;

import android.util.Log;

import com.google.gson.Gson;
import com.tommeng.simplejackexample.request.BaseRequest;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class NetworkManager {
    private final static String TAG = NetworkManager.class.getSimpleName();

    private static NetworkManager instance;
    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();
    private final Map<String, String> defaultHeaders = new HashMap<>();

    private NetworkManager() {
    }

    public static NetworkManager get() {
        if (instance == null) {
            instance = new NetworkManager();
        }
        return instance;
    }

    public NetworkManager addDefaultHeader(String key, String value) {
        this.defaultHeaders.put(key, value);
        return this;
    }

    public NetworkManager removeDefaultHeader(String key) {
        this.defaultHeaders.remove(key);
        return this;
    }

    public <T> List<T> executeForList(final BaseRequest baseRequest) {
        Request request = buildRequest(baseRequest);
        try {
            final Response response = client.newCall(request).execute();
            Log.d(TAG, "Response Code: " + response.code());
            if (response.isSuccessful()) {
                return gson.fromJson(response.body().charStream(), new ParameterizedType() {
                    @Override
                    public Type[] getActualTypeArguments() {
                        return new Type[]{baseRequest.getResponseType()};
                    }

                    @Override
                    public Type getOwnerType() {
                        return null;
                    }

                    @Override
                    public Type getRawType() {
                        return List.class;
                    }
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T> T executeForObject(BaseRequest request) {
        Request.Builder builder = new Request.Builder();
        builder.url(request.getUrl())
                .method(request.getMethod().getValue(), request.getRequestBody());
        try {
            Response response = client.newCall(builder.build()).execute();
            Log.d(TAG, "Response Code: " + response.code());
            if (response.isSuccessful()) {
                return (T) gson.fromJson(response.body().charStream(), request.getResponseType());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Request buildRequest(BaseRequest baseRequest) {
        Request.Builder builder = new Request.Builder();
        builder.url(baseRequest.getUrl())
                .method(baseRequest.getMethod().getValue(), baseRequest.getRequestBody());

        if (!defaultHeaders.isEmpty()) {
            Set<Map.Entry<String, String>> entries = defaultHeaders.entrySet();
            for (Map.Entry<String, String> entry : entries) {
                builder.addHeader(entry.getKey(), entry.getValue());
            }
        }

        if (!baseRequest.getHeaders().isEmpty()) {
            Set<Map.Entry<String, String>> entries = baseRequest.getHeaders().entrySet();
            for (Map.Entry<String, String> entry : entries) {
                builder.addHeader(entry.getKey(), entry.getValue());
            }
        }
        return builder.build();
    }
}
