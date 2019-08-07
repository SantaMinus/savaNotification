package com.sava.notification.service;

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

import com.sava.notification.vo.SubscriptionDTO;

import nl.martijndwars.webpush.Notification;
import nl.martijndwars.webpush.PushService;
import nl.martijndwars.webpush.Subscription;

/**
 * @author kateryna.savina
 */
@Service
public class NotificationServiceImpl implements NotificationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationServiceImpl.class);
    private SubscriptionService subscriptionService;
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
            throw new ServiceException(String.format("The user %s is not subscribed", user));
        }
        SubscriptionDTO sub = new SubscriptionDTO(subscription.get(0));

        return sendPushMessage(sub, "{\n" +
                "  \"notification\": {\n" +
                "    \"title\": \"Title\",\n" +
                "    \"body\": \"I am a body\",\n" +
                "    \"data\": \"\",\n" +
                "    \"actions\": [{\n" +
                "      \"action\": \"Do this!\",\n" +
                "      \"title\": \"Do this!\"\n" +
                "    }, {\n" +
                "      \"action\": \"Do that!\",\n" +
                "      \"title\": \"Do that!\"\n" +
                "    }]\n" +
                "  }\n" +
                "}");
    }

    private HttpResponse sendPushMessage(SubscriptionDTO sub, String payload) throws InterruptedException, GeneralSecurityException, JoseException, ExecutionException, IOException {
        Notification notification;
        PushService pushService;

        // Create a notification with the endpoint, userPublicKey from the subscription and a custom payload
        notification = new Notification(sub, payload);

        // Instantiate the push service, no need to use an API key for Push API
        pushService = new PushService();

        pushService.setPrivateKey(WEB_PUSH_PRIVATE);
        pushService.setPublicKey(WEB_PUSH_PUBLIC);

        LOGGER.debug("PushService: {}; privateKey: {}, publicKey: {}", pushService, pushService.getPrivateKey(), pushService.getPublicKey());

        // Send the notification
        return pushService.send(notification);
    }

}
