package com.sava.savaNotification.service;

import java.util.List;

import nl.martijndwars.webpush.Subscription;

/**
 * @author kateryna.savina
 */
public interface SubscriptionService {
    List<Subscription> getSubscriptions();

    void setSubscription(Subscription subscription);
}
