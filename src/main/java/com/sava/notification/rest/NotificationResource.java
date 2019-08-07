package com.sava.notification.rest;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.concurrent.ExecutionException;

import com.sava.notification.service.NotificationService;
import com.sava.notification.service.ServiceException;

import org.jose4j.lang.JoseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kateryna.savina
 */
@CrossOrigin
@RestController
@RequestMapping("/notification")
public class NotificationResource {
    private NotificationService service;

    @Autowired
    public NotificationResource(NotificationService service) {
        this.service = service;
    }

    @GetMapping("/{userId}")
    public void getNotification(@PathVariable String userId) throws InterruptedException, ExecutionException,
            JoseException, GeneralSecurityException, IOException, ServiceException {
        service.getNotification(userId);
    }
}
