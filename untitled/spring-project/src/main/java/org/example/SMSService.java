package org.example;

import org.springframework.stereotype.Component;

@Component("smsService")
public class SMSService implements NotificationService{
    public void sendNotification(String message) {
        System.out.println("SMS sent: " + message);
    }
}
