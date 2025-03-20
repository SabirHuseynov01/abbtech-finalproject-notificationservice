package abbtech.finalproject.NotificationService.feign;

import abbtech.finalproject.NotificationService.dto.BookingResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "booking-service", url = "http://localhost:8083/api/bookings")
public interface BookingServiceClient {

    @GetMapping("/{id}")
    BookingResponse getBooking(@PathVariable("id") Long id);
}
