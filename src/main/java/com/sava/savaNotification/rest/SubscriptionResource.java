package com.sava.savaNotification.rest;

import java.util.List;
import java.util.Map;

import com.sava.savaNotification.service.SubscriptionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nl.martijndwars.webpush.Subscription;

/**
 * @author kateryna.savina
 */
@CrossOrigin
@RestController
@RequestMapping("/subscription")
public class SubscriptionResource {
    private SubscriptionService service;

    @Autowired
    public SubscriptionResource(SubscriptionService subscriptionService) {
        this.service = subscriptionService;
    }

    @PostMapping("/{userId}")
    public void subscribe(@PathVariable String userId, @RequestBody Subscription subscription) {
        service.setSubscription(userId, subscription);
    }

    @GetMapping
    public Map<String, List<Subscription>> getSubscriptions() {
        return service.getSubscriptions();
    }
}
