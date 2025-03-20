package abbtech.finalproject.NotificationService.service;

import abbtech.finalproject.NotificationService.dto.NotificationResponse;
import abbtech.finalproject.NotificationService.entity.Notification;
import abbtech.finalproject.NotificationService.repository.NotificationRepository;
import abbtech.finalproject.NotificationService.service.impl.NotificationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class NotificationServiceTest {

	private NotificationServiceImpl notificationService;
	private NotificationRepository notificationRepository;

	@BeforeEach
	public void setUp() {
		notificationRepository = mock(NotificationRepository.class);
		notificationService = new NotificationServiceImpl(notificationRepository);
	}

	@Test
	public void testCreateNotification_Success() {
		Notification notification = new Notification();
		notification.setId(1L);
		notification.setUserEmail("test@example.com");
		notification.setMessage("Booking successfully");
		notification.setCreatedAt(LocalDateTime.parse("2025-03-15T10:00:00"));

		when(notificationRepository.save(any(Notification.class))).thenReturn(notification);

		notificationService.createNotification("test@example.com", "Booking successfully");

		verify(notificationRepository, times(1)).save(any(Notification.class));
	}

	@Test
	public void testGetNotificationsByUser_Success() {
		Notification notification = new Notification();
		notification.setId(1L);
		notification.setUserEmail("test@example.com");
		notification.setMessage("Booking successfully");
		notification.setCreatedAt(LocalDateTime.parse("2025-03-15T10:00:00"));

		when(notificationRepository.findByUserEmail("test@example.com")).thenReturn(List.of(notification));

		List<NotificationResponse> responses = notificationService.getNotificationsByUser("test@example.com");

		assertEquals(1, responses.size());
		assertEquals(1L, responses.getFirst().id());
		assertEquals("test@example.com", responses.getFirst().userEmail());
		assertEquals("Booking successfully", responses.getFirst().message());
		assertEquals("2025-03-15T10:00:00", responses.getFirst().createdAt());
	}


}
