package com.example.demo.controller;

import com.example.demo.model.Message;
import com.example.demo.service.MessageService;
import com.example.demo.service.UserService;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/message")
public class MessageController {

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> sendMessage(@RequestBody Message message) {
        try {
            Response response = MessageService.sendMessage(message);
            return ResponseEntity.status(response.getStatusLine().getStatusCode()).body(EntityUtils.toString(response.getEntity()));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(e);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/query")
    public ResponseEntity<?> query(@RequestBody() String query) {
        try {
            Response response = MessageService.query(query);
            return ResponseEntity.status(response.getStatusLine().getStatusCode()).body(EntityUtils.toString(response.getEntity()));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(e);
        }
    }
}
