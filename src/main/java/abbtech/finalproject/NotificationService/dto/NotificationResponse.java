package abbtech.finalproject.NotificationService.dto;

public record NotificationResponse(Long id,
                                   String userEmail,
                                   String message,
                                   String createdAt) {
}
