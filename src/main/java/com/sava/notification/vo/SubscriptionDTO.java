package com.sava.notification.vo;

import java.security.Security;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import nl.martijndwars.webpush.Subscription;

/**
 * @author kateryna.savina
 */
public class SubscriptionDTO extends Subscription {

    public SubscriptionDTO(Subscription subscription) {
        super(subscription.endpoint, subscription.keys);

        if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
            Security.addProvider(new BouncyCastleProvider());
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("auth", keys.auth)
                .append("key", keys.p256dh)
                .append("endpointUrl", endpoint)
                .toString();
    }
}
