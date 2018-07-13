package com.example.demo.controller;

import com.example.demo.model.Device;
import com.example.demo.service.DeviceService;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/device")
public class DeviceController {

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> registerDevice(@RequestBody() Device device) {
        try {
            Response response = DeviceService.registerDevice(device);
            return ResponseEntity.status(response.getStatusLine().getStatusCode()).body(EntityUtils.toString(response.getEntity()));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(e);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/query")
    public ResponseEntity<?> query(@RequestBody() String query) {
        try {
            Response response = DeviceService.query(query);
            return ResponseEntity.status(response.getStatusLine().getStatusCode()).body(EntityUtils.toString(response.getEntity()));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(e);
        }
    }
}
