package com.sava.savaNotification.rest;

import static javax.ws.rs.core.Response.Status.OK;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sava.savaNotification.service.SubscriptionService;

import nl.martijndwars.webpush.Subscription;


/**
 * @author kateryna.savina
 */
@RestController
@RequestMapping("subscription")
public class SubscriptionResource {
    private SubscriptionService service;

    @Autowired
    public SubscriptionResource(SubscriptionService subscriptionService) {
        this.service = subscriptionService;
    }

    @PostMapping
    public Response subscribe(@RequestBody Subscription subscription) {
        service.setSubscription(subscription);

        return Response.status(OK).build();
    }
}
