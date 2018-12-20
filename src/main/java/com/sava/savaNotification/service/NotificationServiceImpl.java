package com.sava.savaNotification.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.Security;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpResponse;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.jose4j.lang.JoseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nl.martijndwars.webpush.Notification;
import nl.martijndwars.webpush.PushService;
import nl.martijndwars.webpush.Subscription;

/**
 * @author kateryna.savina
 */
@Service
public class NotificationServiceImpl implements NotificationService {
    Logger LOGGER = LoggerFactory.getLogger(NotificationServiceImpl.class);
    private SubscriptionService subscriptionService;
    private static final String PUBLIC_KEY = "AAAAcAqktmk:APA91bEvZpnvwumlxjdKUKaforTjREYGyY1CDBi7ohYkGDXD4IG5QqMQt6tal9LcTT055eqXmrSsK_EZ6fjFh6CBdsmZZx1oHIzjBntevcFeR85asYnHlDvHFgKkKrCrITVGNJi_bwjm";

    @Autowired
    public NotificationServiceImpl(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @Override
    public HttpResponse getNotification(String user)
            throws InterruptedException, GeneralSecurityException, JoseException, ExecutionException, IOException {
        Subscription subscription = subscriptionService.getByUser(user).get(0);

        Security.addProvider(new BouncyCastleProvider());
        PushService pushService = new PushService();
        Notification notification = new Notification(subscription, "sava");

        return pushService.send(notification);
    }
}
