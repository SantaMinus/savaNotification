package com.sava.savaNotification.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sava.savaNotification.fcm.FcmClient;
import com.sava.savaNotification.service.SubscriptionService;

import nl.martijndwars.webpush.Subscription;

/**
 * @author kateryna.savina
 */
@CrossOrigin
@RestController
@RequestMapping("/subscription")
public class SubscriptionResource {
    private SubscriptionService service;
    private FcmClient fcmClient;

    @Autowired
    public SubscriptionResource(SubscriptionService subscriptionService, FcmClient fcmClient) {
        this.service = subscriptionService;
        this.fcmClient = fcmClient;
    }

    @PostMapping("/{userId}")
    public void subscribe(@PathVariable String userId, @RequestBody Subscription subscription) {
        service.setSubscription(userId, subscription);
    }

    @GetMapping
    public Map<String, List<Subscription>> getSubscriptions() {
        return service.getSubscriptions();
    }

    @PostMapping
    public void subscribeTutorial(@RequestBody String token) {
        fcmClient.subscribe("sava", token);
    }
}
