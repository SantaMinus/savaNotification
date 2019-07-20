package com.sava.savaNotification.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpResponse;
import org.jose4j.lang.JoseException;

/**
 * @author kateryna.savina
 */
public interface NotificationService {
    HttpResponse getNotification(String user) throws InterruptedException, GeneralSecurityException, JoseException,
            ExecutionException, IOException, ServiceException;
}
