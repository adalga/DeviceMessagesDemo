package com.example.demo.util;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;

public class RestUtils {
    public RestUtils() {
    }

    private static RestClient restClient = RestClient.builder(new HttpHost("192.168.112.139", 9200, "http")).build();

    public static RestClient getRestClient() {
        return restClient;
    }
}
