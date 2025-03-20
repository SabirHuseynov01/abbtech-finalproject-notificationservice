package abbtech.finalproject.NotificationService.kafka.consumer;

import abbtech.finalproject.NotificationService.service.NotificationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationConsumer {

    private final NotificationService notificationService;
    private final ObjectMapper objectMapper;

    public NotificationConsumer(NotificationService notificationService, ObjectMapper objectMapper) {
        this.notificationService = notificationService;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = {"hotel-events", "booking-events"}, groupId = "notification-group")
    public void listen(String message) {
        try {

            notificationService.createNotification("user@example.com", "New event: " + message);
        } catch (Exception e) {
            throw new RuntimeException("Failed to process Kafka message: " + e.getMessage());
        }
    }
}
