package com.tommeng.simplejackexample.request;

import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class GetRequest extends BaseRequest {
    public GetRequest(@NonNull String url, Class<?> responseType) {
        super(url, Method.GET, responseType);
    }

    private Map<String, String> queryParams = new HashMap<>();

    @NonNull
    @Override
    public String getUrl() {
        StringBuilder builder = new StringBuilder(this.url);
        if (!queryParams.isEmpty()) {
            builder.append("?");
            Set<Map.Entry<String, String>> entries = queryParams.entrySet();
            int i = 0;
            int size = entries.size();
            for (Map.Entry<String, String> entry : entries) {
                builder.append(entry.getKey())
                        .append("=")
                        .append(entry.getValue());
                if (i < size - 1) {
                    builder.append("&");
                }
                i += 1;
            }
        }
        return builder.toString();
    }

    @NonNull
    public Map<String, String> getQueryParams() {
        return queryParams;
    }

    public GetRequest setQueryParams(@NonNull Map<String, String> queryParams) {
        this.queryParams = queryParams;
        return this;
    }

    public GetRequest addQueryParam(String key, String value) {
        this.queryParams.put(key, value);
        return this;
    }
}
