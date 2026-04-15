package dealer_inventory_service.controller;

import dealer_inventory_service.dto.*;
import dealer_inventory_service.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/vehicles")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService service;

    @PostMapping
    public ResponseEntity<ApiResponse<VehicleResponse>> create(
            @RequestBody VehicleRequest request) {

        return ResponseEntity.ok(
                ApiResponse.<VehicleResponse>builder()
                        .success(true)
                        .data(service.createVehicle(request))
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<VehicleResponse>> get(
            @PathVariable UUID id) {

        return ResponseEntity.ok(
                ApiResponse.<VehicleResponse>builder()
                        .success(true)
                        .data(service.getVehicle(id))
                        .build()
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<VehicleResponse>>> getAll(
            @RequestParam(required = false) String model,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Double priceMin,
            @RequestParam(required = false) Double priceMax,
            @RequestParam(required = false) String subscription,
            Pageable pageable) {

        return ResponseEntity.ok(
                ApiResponse.<Page<VehicleResponse>>builder()
                        .success(true)
                        .data(service.getVehicles(
                                model,
                                status,
                                priceMin,
                                priceMax,
                                subscription,
                                pageable))
                        .build()
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<VehicleResponse>> update(
            @PathVariable UUID id,
            @RequestBody VehicleRequest request) {

        return ResponseEntity.ok(
                ApiResponse.<VehicleResponse>builder()
                        .success(true)
                        .data(service.updateVehicle(id, request))
                        .build()
        );
    }
    @PatchMapping("/{id}/sold")
    public ResponseEntity<ApiResponse<VehicleResponse>>
    markAsSold(@PathVariable UUID id) {

        return ResponseEntity.ok(
                ApiResponse.<VehicleResponse>builder()
                        .success(true)
                        .data(service.markAsSold(id))
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(
            @PathVariable UUID id) {

        service.deleteVehicle(id);

        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .build()
        );
    }
}