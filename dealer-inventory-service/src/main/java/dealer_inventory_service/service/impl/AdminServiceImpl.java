package dealer_inventory_service.service.impl;

import dealer_inventory_service.dto.VehicleResponse;
import dealer_inventory_service.enums.ErrorCode;
import dealer_inventory_service.enums.SubscriptionType;
import dealer_inventory_service.enums.VehicleStatus;
import dealer_inventory_service.exceptions.ApplicationException;
import dealer_inventory_service.model.Vehicle;
import dealer_inventory_service.repository.DealerRepository;
import dealer_inventory_service.repository.VehicleRepository;
import dealer_inventory_service.service.AdminService;
import dealer_inventory_service.tenant.TenantContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl
        implements AdminService {

    private final DealerRepository repository;
    private final VehicleRepository vehicleRepository;

    @Override
    public Map<SubscriptionType, Long>
    countDealersBySubscription() {

        List<Object[]> results =
                repository.countBySubscription();

        Map<SubscriptionType, Long> response =
                new HashMap<>();

        for (Object[] row : results) {

            response.put(
                    (SubscriptionType) row[0],
                    (Long) row[1]
            );
        }

        return response;
    }

    @Override
    public VehicleResponse approveVehicle(UUID id){
        Vehicle vehicle = vehicleRepository
                .findByIdAndTenantId(id, TenantContext.getTenant())
                .orElseThrow(() -> new ApplicationException(ErrorCode.RESOURCE_NOT_FOUND));
        vehicle.setStatus(VehicleStatus.AVAILABLE);
        return map(vehicleRepository.save(vehicle));
    }
    private VehicleResponse map(Vehicle vehicle) {

        return new VehicleResponse(
                vehicle.getId(),
                vehicle.getDealerId(),
                vehicle.getModel(),
                vehicle.getPrice(),
                vehicle.getStatus()
        );
    }
}