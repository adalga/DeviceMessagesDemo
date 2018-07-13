package com.example.demo.service;

import com.example.demo.model.Message;
import com.example.demo.util.RestUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.elasticsearch.client.Response;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

public class MessageService {


    public static Response sendMessage(Message message) throws IOException {
        SimpleDateFormat indexDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeStampFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

        ObjectMapper objectMapper = new ObjectMapper();
        message.setDate(timeStampFormat.format(Date.from(Instant.ofEpochSecond(message.getTimestamp()))));
        String messageString = objectMapper.writeValueAsString(message);
        HttpEntity entity = new NStringEntity(messageString, ContentType.APPLICATION_JSON);

        Map<String, String> params = Collections.emptyMap();
        Response response = RestUtils.getRestClient().performRequest("POST", "/messages-" + indexDateFormat.format(Date.from(Instant.now())) + "/doc", params, entity);
        return response;

    }

    public static Response query(String query) throws IOException {
        HttpEntity entity = new NStringEntity(query, ContentType.APPLICATION_JSON);
        Map<String, String> params = Collections.emptyMap();
        Response response = RestUtils.getRestClient().performRequest("POST", "/messages-*/_search", params, entity);
        return response;
    }
}
