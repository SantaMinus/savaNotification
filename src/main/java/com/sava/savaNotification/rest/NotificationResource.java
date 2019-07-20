package com.sava.savaNotification.rest;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import org.jose4j.lang.JoseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sava.savaNotification.fcm.FcmClient;
import com.sava.savaNotification.service.NotificationService;
import com.sava.savaNotification.service.ServiceException;

/**
 * @author kateryna.savina
 */
@CrossOrigin
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

    @GetMapping("/{userId}")
    public void getNotification(@PathVariable String userId) throws InterruptedException, ExecutionException,
            JoseException, GeneralSecurityException, IOException, ServiceException {
        HashMap<String, String> data = new HashMap<String, String>() {{
            put("testKey", "testValue");
        }};
        service.getNotification(userId);
    }
}
