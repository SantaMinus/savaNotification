package com.sava.savaNotification.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpResponse;
import org.jose4j.lang.JoseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sava.savaNotification.vo.SubscriptionDTO;

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
    private static final int TTL = 255;
    private static final String WEB_PUSH_PRIVATE = "RVECh_zzCPqYP4341-orNGpFWfwE5LMIRmB2D8KGoDo=";
    private static final String WEB_PUSH_PUBLIC = "BJ9d0yTVFWzPhTk82J52ZNUZN_FBjkiEHSfDbGH4xpMsP2yxIqqk3PP77crU_k3en962b6YOYqpHGZpz5c5c6lc=";

    @Autowired
    public NotificationServiceImpl(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @Override
    public HttpResponse getNotification(String user) throws InterruptedException, GeneralSecurityException,
            JoseException, ExecutionException, IOException, ServiceException {
        List<Subscription> subscription = subscriptionService.getByUser(user);
        if (subscription == null) {
            throw new ServiceException(String.format("The user {} is not subscribed", user));
        }
        SubscriptionDTO sub = new SubscriptionDTO(subscription.get(0));

        return sendPushMessage(sub, "testSavaNotification");
////        // FCM notifications
//        Security.addProvider(new BouncyCastleProvider());
////        PushService pushService = new PushService();
////        Notification notification = new Notification(subscription, "sava");
////
////        return pushService.send(notification);
//        Notification notification = new Notification(subscription, "testSavaNotification");
//        webPush.send(notification);
    }

    private HttpResponse sendPushMessage(SubscriptionDTO sub, String payload) throws InterruptedException, GeneralSecurityException, JoseException, ExecutionException, IOException {

        // Figure out if we should use GCM for this notification somehow
//        boolean useGcm = shouldUseGcm(sub);
        Notification notification;
        PushService pushService;

//        if (!useGcm) {
        // Create a notification with the endpoint, userPublicKey from the subscription and a custom payload
        notification = new Notification(sub, payload);

        // Instantiate the push service, no need to use an API key for Push API
        pushService = new PushService();
//        } else {
//            // Or create a GcmNotification, in case of Google Cloud Messaging
//            notification = new Notification(
//                    sub.getEndpointUrl(),
//                    sub.getUserPublicKey(),
//                    sub.getAuthAsBytes(),
//                    payload.getBytes(),
//                    TTL
//            );
//
//            // Instantiate the push service with a GCM API key
//            pushService = new PushService("481214903913");
//        }

        pushService.setPrivateKey(WEB_PUSH_PRIVATE);
        pushService.setPublicKey(WEB_PUSH_PUBLIC);
        pushService.setGcmApiKey("481214903913");

        LOGGER.debug("PushService: {}; privateKey: {}, publicKey: {}", pushService, pushService.getPrivateKey(), pushService.getPublicKey());

        // Send the notification
        return pushService.send(notification);
    }

    private boolean shouldUseGcm(Subscription sub) {
        return false;
    }
}
