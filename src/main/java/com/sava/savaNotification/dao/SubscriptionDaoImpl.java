package com.sava.savaNotification.dao;

import java.util.List;

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

    @Override
    public List<Subscription> getAll() {
        return null;
    }

    @Override
    public void setSubscription(Subscription sub) {
        Validate.notNull(sub, "The subscription can't be null");

        LOGGER.debug("Setting a subscription..");
    }
}
