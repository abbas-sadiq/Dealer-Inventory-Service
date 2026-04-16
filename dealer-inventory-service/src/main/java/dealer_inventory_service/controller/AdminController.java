package dealer_inventory_service.controller;

import dealer_inventory_service.dto.ApiResponse;
import dealer_inventory_service.dto.VehicleResponse;
import dealer_inventory_service.enums.ErrorCode;
import dealer_inventory_service.enums.SubscriptionType;
import dealer_inventory_service.enums.UserRole;
import dealer_inventory_service.exceptions.ApplicationException;
import dealer_inventory_service.security.SecurityContext;
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
    public Map<SubscriptionType, Long> countBySubscription() {

        if (SecurityContext.getRole() != UserRole.GLOBAL_ADMIN) {
            throw new ApplicationException(ErrorCode.ACCOUNT_NOT_VERIFIED);
        }

        return service.countDealersBySubscription();
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