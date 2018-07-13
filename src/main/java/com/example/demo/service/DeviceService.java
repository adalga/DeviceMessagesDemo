package com.example.demo.service;

import com.example.demo.model.Device;
import com.example.demo.util.RestUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.elasticsearch.client.Response;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

public class DeviceService {

    public static Response registerDevice(Device device) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        String userString = objectMapper.writeValueAsString(device);

        HttpEntity entity = new NStringEntity(userString, ContentType.APPLICATION_JSON);

        Map<String, String> params = Collections.emptyMap();
        Response response = RestUtils.getRestClient().performRequest("PUT", "/devices" + "/doc/" + device.getD_id(), params, entity);
        return response;
    }

    public static Response query(String query) throws IOException {
        HttpEntity entity = new NStringEntity(query, ContentType.APPLICATION_JSON);
        Map<String, String> params = Collections.emptyMap();
        Response response = RestUtils.getRestClient().performRequest("POST", "/devices/_search", params, entity);
        return response;
    }
}
