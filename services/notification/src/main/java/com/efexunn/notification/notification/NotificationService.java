package com.efexunn.notification.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public void saveNotification(Notification notification){
        this.notificationRepository.save(notification);
    }
}
