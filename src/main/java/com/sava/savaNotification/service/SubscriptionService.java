package com.sava.savaNotification.service;

import java.util.List;
import java.util.Map;

import nl.martijndwars.webpush.Subscription;

/**
 * @author kateryna.savina
 */
public interface SubscriptionService {
    Map<String, List<Subscription>> getSubscriptions();

    List<Subscription> getByUser(String userId);

    void setSubscription(String userId, Subscription subscription);
}
