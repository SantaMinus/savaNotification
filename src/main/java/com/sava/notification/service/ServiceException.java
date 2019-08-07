package com.sava.notification.service;

/**
 * @author kateryna.savina
 */
public class ServiceException extends Exception {
    ServiceException(String errorMessage) {
        super(errorMessage);
    }
}
