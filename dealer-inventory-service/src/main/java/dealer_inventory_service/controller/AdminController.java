package dealer_inventory_service.controller;

import dealer_inventory_service.dto.ApiResponse;
import dealer_inventory_service.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService service;

    @GetMapping("/dealers/countBySubscription")
    public ResponseEntity<
            ApiResponse<Map<String, Long>>
            > count() {

        return ResponseEntity.ok(
                ApiResponse
                        .<Map<String, Long>>builder()
                        .success(true)
                        .data(
                                service
                                        .countDealersBySubscription()
                        )
                        .build()
        );
    }
}