package com.sava.savaNotification.rest;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sava.savaNotification.fcm.FcmClient;
import com.sava.savaNotification.service.NotificationService;

/**
 * @author kateryna.savina
 */
@RestController
@RequestMapping("/notification")
public class NotificationResource {
    private NotificationService service;
    private FcmClient fcmClient;

    @Autowired
    public NotificationResource(NotificationService service, FcmClient fcmClient) {
        this.service = service;
        this.fcmClient = fcmClient;
    }

    @GetMapping
    public void getNotification()
            throws InterruptedException, ExecutionException {
        HashMap<String, String> data = new HashMap<String, String>() {{
            put("testKey", "testValue");
        }};
        fcmClient.send(data);
    }
}
