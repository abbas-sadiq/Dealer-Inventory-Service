package dealer_inventory_service.service;

import dealer_inventory_service.dto.VehicleRequest;
import dealer_inventory_service.dto.VehicleResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface VehicleService {

    VehicleResponse createVehicle(VehicleRequest request);

    VehicleResponse getVehicle(UUID id);

    Page<VehicleResponse> getVehicles(
            String model,
            String status,
            Double priceMin,
            Double priceMax,
            String subscription,
            Pageable pageable
    );

    VehicleResponse updateVehicle(
            UUID id,
            VehicleRequest request);
    VehicleResponse markAsSold(UUID id);

    void deleteVehicle(UUID id);
}