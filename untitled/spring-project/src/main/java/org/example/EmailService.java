package org.example;

import org.springframework.stereotype.Service;

@Component("emailService")
public class EmailService implements NotificationService {
    public void sendNotification(String message) {
        System.out.println("Email sent: " + message);
    }
}
