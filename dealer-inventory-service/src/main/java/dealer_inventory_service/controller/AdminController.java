package dealer_inventory_service.controller;

import dealer_inventory_service.dto.ApiResponse;
import dealer_inventory_service.dto.VehicleResponse;
import dealer_inventory_service.service.AdminService;
import dealer_inventory_service.tenant.TenantContext;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

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

    @PatchMapping("/vehicles/{id}/approve")
    public ResponseEntity<ApiResponse<VehicleResponse>>
    approveVehicle(@PathVariable UUID id) {

        return ResponseEntity.ok(
                ApiResponse.<VehicleResponse>builder()
                        .success(true)
                        .data(service.approveVehicle(id))
                        .build()
        );
    }
}