package com.sava.savaNotification.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sava.savaNotification.dao.SubscriptionDao;

import nl.martijndwars.webpush.Subscription;

/**
 * @author kateryna.savina
 */
@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    private SubscriptionDao dao;

    @Autowired
    public SubscriptionServiceImpl(SubscriptionDao subscriptionDao) {
        this.dao = subscriptionDao;
    }

    @Override
    public Map<String, List<Subscription>> getSubscriptions() {
        return dao.getAll();
    }

    @Override
    public List<Subscription> getByUser(String userId) {
        return dao.getByUser(userId);
    }


    @Override
    public void setSubscription(String userId, Subscription subscription) {
        dao.setSubscription(userId, subscription);
    }
}
