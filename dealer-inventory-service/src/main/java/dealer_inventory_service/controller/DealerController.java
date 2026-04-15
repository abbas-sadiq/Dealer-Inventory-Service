package dealer_inventory_service.controller;

import dealer_inventory_service.dto.*;
import dealer_inventory_service.service.DealerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/dealers")
@RequiredArgsConstructor
public class DealerController {

    private final DealerService service;

    @PostMapping
    public ResponseEntity<ApiResponse<DealerResponse>> create(
            @Valid @RequestBody DealerRequest request) {

        return ResponseEntity.ok(
                ApiResponse.<DealerResponse>builder()
                        .success(true)
                        .message("Dealer created successfully")
                        .data(service.createDealer(request))
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DealerResponse>> get(
            @PathVariable UUID id) {

        return ResponseEntity.ok(
                ApiResponse.<DealerResponse>builder()
                        .success(true)
                        .data(service.getDealer(id))
                        .build()
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<DealerResponse>>> getAll(
            Pageable pageable) {

        return ResponseEntity.ok(
                ApiResponse.<Page<DealerResponse>>builder()
                        .success(true)
                        .data(service.getAllDealers(pageable))
                        .build()
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<DealerResponse>> update(
            @PathVariable UUID id,
            @RequestBody DealerRequest request) {

        return ResponseEntity.ok(
                ApiResponse.<DealerResponse>builder()
                        .success(true)
                        .message("Dealer updated successfully")
                        .data(service.updateDealer(id, request))
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(
            @PathVariable UUID id) {

        service.deleteDealer(id);

        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .message("Dealer deleted successfully")
                        .build()
        );
    }
}