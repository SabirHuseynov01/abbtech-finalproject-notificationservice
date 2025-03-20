package abbtech.finalproject.NotificationService.service;

import abbtech.finalproject.NotificationService.dto.NotificationResponse;
import org.reactivestreams.Publisher;

import java.util.List;

public interface NotificationService {
    void createNotification(String userEmail, String message);

    Publisher<?> getNotificationByUser(String userEmail);

    List<NotificationResponse> getNotificationsByUser(String userEmail);
}
