package com.sava.savaNotification.rest;

import static javax.ws.rs.core.Response.Status.OK;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @PostMapping("{userId}")
    public Response subscribe(@PathVariable String userId, @RequestBody Subscription subscription) {
        service.setSubscription(userId, subscription);

        return Response.status(OK).build();
    }

    @GetMapping
    public Response getSubscriptions() {
        return Response.status(OK).entity(service.getSubscriptions()).build();
    }
}
