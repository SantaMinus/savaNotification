package com.sava.savaNotification.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import nl.martijndwars.webpush.Subscription;

/**
 * @author kateryna.savina
 */
@Repository
public class SubscriptionDaoImpl implements SubscriptionDao {
    private Logger LOGGER = LoggerFactory.getLogger(SubscriptionDaoImpl.class);

    /**
     * provided to speed up the development :) Should be changed to a real DB in future
     */
    //TODO use the real DB connection
    private static Map<String, List<Subscription>> subscriptionMap = new HashMap<>();

    public SubscriptionDaoImpl() {
        // temporarily not supported
    }

    @Override
    public Map<String, List<Subscription>> getAll() {
        return subscriptionMap;
    }

    @Override
    public List<Subscription> getByUser(String userId) {
        return subscriptionMap.get(userId);
    }

    @Override
    public void setSubscription(String userId, Subscription sub) {
        Validate.notNull(sub, "The subscription can't be null");
        LOGGER.debug("Setting a subscription..");

        subscriptionMap.put(userId, new ArrayList<>(Collections.singleton(sub)));
    }

    public void updateSubscription(String userId, Subscription sub) {
        List<Subscription> userSubscriptions = getByUser(userId);
        userSubscriptions.add(sub);
        subscriptionMap.replace(userId, userSubscriptions);
    }
}
