package com.sava.savaNotification.dao;

import java.util.List;
import java.util.Map;

import nl.martijndwars.webpush.Subscription;

/**
 * @author kateryna.savina
 */
public interface SubscriptionDao {
    Map<String, List<Subscription>> getAll();

    List<Subscription> getByUser(String userId);

    void setSubscription(String useId, Subscription sub);
}
