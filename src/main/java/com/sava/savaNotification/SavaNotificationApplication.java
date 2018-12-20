package com.sava.savaNotification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SavaNotificationApplication {

    public static Logger LOGGER = LoggerFactory.getLogger(SavaNotificationApplication.class);

    public static void main(String[] args) {
		SpringApplication.run(SavaNotificationApplication.class, args);
	}

}
