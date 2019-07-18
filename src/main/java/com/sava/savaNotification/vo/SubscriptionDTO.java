package com.sava.savaNotification.vo;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECNamedCurveParameterSpec;
import org.bouncycastle.jce.spec.ECPublicKeySpec;
import org.bouncycastle.math.ec.ECPoint;

/**
 * @author kateryna.savina
 */
public class SubscriptionDTO extends nl.martijndwars.webpush.Subscription {
    private String auth;
    private String key;
    private String endpointUrl;

    public SubscriptionDTO(String auth, String key, String endpointUrl) {
        this.auth = auth;
        this.key = key;
        this.endpointUrl = endpointUrl;

        if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
            Security.addProvider(new BouncyCastleProvider());
        }
    }

    public SubscriptionDTO(nl.martijndwars.webpush.Subscription subscription) {
        super(subscription.endpoint, subscription.keys);
        this.auth = subscription.keys.auth;
        this.key = subscription.keys.p256dh;
        this.endpointUrl = subscription.endpoint;

        if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
            Security.addProvider(new BouncyCastleProvider());
        }
    }

    public String getAuth() {
        return auth;
    }

    /**
     * Returns the base64 encoded auth string as a byte[]
     */
    public byte[] getAuthAsBytes() {
        return Base64.getDecoder().decode(getAuth());
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    /**
     * Returns the base64 encoded public key string as a byte[]
     */
    public byte[] getKeyAsBytes() {
        return Base64.getDecoder().decode(getKey());
    }

    public String getEndpointUrl() {
        return endpointUrl;
    }

    /**
     * Returns the base64 encoded public key as a PublicKey object
     */
    public PublicKey getUserPublicKey() throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException {
        KeyFactory kf = KeyFactory.getInstance("ECDH", BouncyCastleProvider.PROVIDER_NAME);
        ECNamedCurveParameterSpec ecSpec = ECNamedCurveTable.getParameterSpec("secp256r1");
        ECPoint point = ecSpec.getCurve().decodePoint(getKeyAsBytes());
        ECPublicKeySpec pubSpec = new ECPublicKeySpec(point, ecSpec);

        return kf.generatePublic(pubSpec);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("auth", auth)
                .append("key", key)
                .append("endpointUrl", endpointUrl)
                .toString();
    }
}
