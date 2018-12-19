package com.sava.savaNotification.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        List<Subscription> userSubscriptions = dao.getByUser(userId);

        if (userSubscriptions == null) {
            dao.setSubscription(userId, subscription);
        } else if (userSubscriptions.stream()
                .filter(s -> s.endpoint.equals(subscription.endpoint))
                .collect(Collectors.toList())
                .isEmpty()) {
            dao.updateSubscription(userId, subscription);
        }
    }
}
