package com.sava.savaNotification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nl.martijndwars.webpush.Notification;

/**
 * @author kateryna.savina
 */
@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    public NotificationServiceImpl() {

    }

    @Override
    public Notification getNotification() {
        return null;
    }
}
