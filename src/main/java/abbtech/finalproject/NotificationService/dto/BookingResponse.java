package abbtech.finalproject.NotificationService.dto;

public record BookingResponse(Long id,
                              Long roomId,
                              String userEmail,
                              String confirmationCode,
                              String paymentMethod,
                              Double amount) {
}
