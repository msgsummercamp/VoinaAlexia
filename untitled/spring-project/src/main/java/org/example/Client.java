package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Client {
    private final NotificationService notificationService;

    @Autowired
    public Client(@Qualifier("smsService") NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void processMessage(String message) {
        notificationService.sendNotification(message);
    }
}
