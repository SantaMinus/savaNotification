package com.sava.notification.dao;

import java.util.List;
import java.util.Map;

import nl.martijndwars.webpush.Subscription;

/**
 * @author kateryna.savina
 */
public interface SubscriptionDao {
    Map<String, List<Subscription>> getAll();

    List<Subscription> getByUser(String userId);

    void setSubscription(String userId, Subscription subscription);

    void updateSubscription(String userId, Subscription subscription);
}
