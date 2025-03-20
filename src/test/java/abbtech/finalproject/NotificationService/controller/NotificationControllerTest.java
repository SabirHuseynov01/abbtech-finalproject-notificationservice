package abbtech.finalproject.NotificationService.controller;

import abbtech.finalproject.NotificationService.dto.NotificationResponse;
import abbtech.finalproject.NotificationService.service.NotificationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class NotificationControllerTest {

    private MockMvc mockMvc;

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private NotificationController notificationController;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(notificationController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testGetNotificationByUser_Success() throws Exception {
        List<NotificationResponse> notifications = List.of(
                new NotificationResponse(1L, "test@example.com", "Booking successful", "2025-03-15T10:00:00" )
        );
        when(notificationService.getNotificationsByUser("test@example.com")).thenReturn(notifications);

        mockMvc.perform(get("/api/notifications/test@example.com")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].userEmail").value("test@example.com"))
                .andExpect(jsonPath("$[0].message").value("Booking confirmed"))
                .andExpect(jsonPath("$[0].createdAt").value("2025-03-15T10:00:00"));

        verify(notificationService, times(1)).getNotificationsByUser("test@example.com");
    }
}
