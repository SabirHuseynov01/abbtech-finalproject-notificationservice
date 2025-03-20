package abbtech.finalproject.NotificationService.service.impl;

import abbtech.finalproject.NotificationService.dto.BookingResponse;
import abbtech.finalproject.NotificationService.dto.NotificationResponse;
import abbtech.finalproject.NotificationService.entity.Notification;
import abbtech.finalproject.NotificationService.feign.BookingServiceClient;
import abbtech.finalproject.NotificationService.repository.NotificationRepository;
import abbtech.finalproject.NotificationService.service.NotificationService;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final BookingServiceClient bookingServiceClient;

    public NotificationServiceImpl(NotificationRepository notificationRepository, BookingServiceClient bookingServiceClient) {
        this.notificationRepository = notificationRepository;
        this.bookingServiceClient = bookingServiceClient;
    }

    @Override
    public void createNotification(String userEmail, String message) {
        Long bookingId = extractBookingIdFromMessage(message);
        String detailedMessage = message;
        if (bookingId != null) {
            BookingResponse booking = bookingServiceClient.getBooking(bookingId);
            detailedMessage = "Booking confirmed for " + booking.roomId() + " with payment " + booking.paymentMethod();
        }

        Notification notification = new Notification();
        notification.setUserEmail(userEmail);
        notification.setMessage(message);
        notification.setCreatedAt(LocalDateTime.now());
        notificationRepository.save(notification);
    }

    private Long extractBookingIdFromMessage(String message) {
        try {
            return Long.parseLong(message.split(":")[1].trim());
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public Publisher<?> getNotificationByUser(String userEmail) {
        return null;
    }


    @Override
    public List<NotificationResponse> getNotificationsByUser(String userEmail) {
        List<Notification> notifications = notificationRepository.findByUserEmail(userEmail);
        return notifications.stream()
                .map(n -> new NotificationResponse(n.getId(), n.getUserEmail(), n.getMessage(), n.getCreatedAt().toString()))
                .collect(Collectors.toList());
    }
}
