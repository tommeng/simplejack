package com.tommeng.simplejackexample.request;

import android.support.annotation.NonNull;

import okhttp3.RequestBody;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseRequest {
    public enum Method {
        GET("GET"),
        POST("POST");

        private String value;

        Method(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    protected String url;
    private Method method;
    private RequestBody requestBody;
    private Class<?> responseType;
    private Map<String, String> headers = new HashMap<>();

    public BaseRequest(@NonNull String url, @NonNull Method method, @NonNull Class<?> responseType) {
        this.url = url;
        this.method = method;
        this.responseType = responseType;
    }

    @NonNull
    public String getUrl() {
        return url;
    }

    public BaseRequest setUrl(@NonNull String url) {
        this.url = url;
        return this;
    }

    @NonNull
    public Method getMethod() {
        return method;
    }

    public BaseRequest setMethod(@NonNull Method method) {
        this.method = method;
        return this;
    }

    public RequestBody getRequestBody() {
        return requestBody;
    }

    public BaseRequest setRequestBody(RequestBody requestBody) {
        this.requestBody = requestBody;
        return this;
    }

    public Class<?> getResponseType() {
        return responseType;
    }

    public BaseRequest setResponseType(Class<?> responseType) {
        this.responseType = responseType;
        return this;
    }

    @NonNull
    public Map<String, String> getHeaders() {
        return headers;
    }

    public BaseRequest setHeaders(@NonNull Map<String, String> headers) {
        this.headers = headers;
        return this;
    }

    public BaseRequest addHeader(String key, String value) {
        this.headers.put(key, value);
        return this;
    }
}
