package com.sava.savaNotification.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author kateryna.savina
 */
public class Subscription extends nl.martijndwars.webpush.Subscription {
    private String auth;
    private String key;
    private String endpointUrl;

    public Subscription(String auth, String key, String endpointUrl) {
        this.auth = auth;
        this.key = key;
        this.endpointUrl = endpointUrl;
    }

    public String getAuth() {
        return auth;
    }

    public String getKey() {
        return key;
    }

    public String getEndpointUrl() {
        return endpointUrl;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("auth", auth)
                .append("key", key)
                .append("endpointUrl", endpointUrl)
                .toString();
    }
}
