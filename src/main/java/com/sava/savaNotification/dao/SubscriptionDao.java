package com.sava.savaNotification.dao;

import java.util.List;

import nl.martijndwars.webpush.Subscription;

/**
 * @author kateryna.savina
 */
public interface SubscriptionDao {
    List<Subscription> getAll();

    void setSubscription(Subscription sub);
}
