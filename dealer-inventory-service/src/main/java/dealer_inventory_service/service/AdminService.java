package dealer_inventory_service.service;

import dealer_inventory_service.dto.VehicleResponse;

import java.util.Map;
import java.util.UUID;

public interface AdminService {

    Map<String, Long> countDealersBySubscription();
    VehicleResponse approveVehicle(UUID id);
}