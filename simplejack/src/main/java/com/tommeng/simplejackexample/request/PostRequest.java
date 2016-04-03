package com.tommeng.simplejackexample.request;

import android.support.annotation.NonNull;

import okhttp3.FormBody;
import okhttp3.RequestBody;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PostRequest extends BaseRequest {
    private Map<String, String> formParams = new HashMap<>();

    public PostRequest(@NonNull String url, @NonNull Class<?> responseType) {
        super(url, Method.POST, responseType);
    }

    @NonNull
    public Map<String, String> getFormParams() {
        return formParams;
    }

    public PostRequest setFormParams(@NonNull Map<String, String> formParams) {
        this.formParams = formParams;
        return this;
    }

    public PostRequest addFormParam(String key, String value) {
        this.formParams.put(key, value);
        return this;
    }

    @Override
    public RequestBody getRequestBody() {
        if (!formParams.isEmpty()) {
            FormBody.Builder builder = new FormBody.Builder();
            Set<Map.Entry<String, String>> entries = formParams.entrySet();
            for (Map.Entry<String, String> entry : entries) {
                builder.add(entry.getKey(), entry.getValue());
            }
            return builder.build();
        }
        return null;
    }
}
