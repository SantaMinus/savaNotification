package com.sava.savaNotification.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sava.savaNotification.service.NotificationService;

/**
 * @author kateryna.savina
 */
@RestController
@RequestMapping("notification")
public class NotificationResource {
    private NotificationService service;

    @Autowired
    public NotificationResource(NotificationService service) {
        this.service = service;
    }

    @GetMapping
    public void getNotification() {
        service.getNotification();
    }
}
