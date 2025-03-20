package abbtech.finalproject.NotificationService.controller;

import abbtech.finalproject.NotificationService.dto.NotificationResponse;
import abbtech.finalproject.NotificationService.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/{userEmail}")
    public ResponseEntity<List<NotificationResponse>> getNotificationsByUser(@PathVariable String userEmail){
        return ResponseEntity.ok(notificationService.getNotificationByUser(userEmail));
    }
}
