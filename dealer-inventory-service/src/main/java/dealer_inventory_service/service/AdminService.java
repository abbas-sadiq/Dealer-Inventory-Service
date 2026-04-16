package dealer_inventory_service.service;

import dealer_inventory_service.dto.VehicleResponse;
import dealer_inventory_service.enums.SubscriptionType;

import java.util.Map;
import java.util.UUID;

public interface AdminService {

    Map<SubscriptionType, Long> countDealersBySubscription();
    VehicleResponse approveVehicle(UUID id);
}