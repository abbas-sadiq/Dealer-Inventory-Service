package dealer_inventory_service.service.impl;

import dealer_inventory_service.dto.VehicleRequest;
import dealer_inventory_service.dto.VehicleResponse;
import dealer_inventory_service.enums.ErrorCode;
import dealer_inventory_service.enums.SubscriptionType;
import dealer_inventory_service.enums.VehicleStatus;
import dealer_inventory_service.exceptions.ApplicationException;
import dealer_inventory_service.model.Vehicle;
import dealer_inventory_service.repository.VehicleRepository;
import dealer_inventory_service.service.VehicleService;
import dealer_inventory_service.tenant.TenantContext;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl
        implements VehicleService {

    private final VehicleRepository repository;

    @Override
    public VehicleResponse createVehicle(
            VehicleRequest request) {

        Vehicle vehicle = Vehicle.builder()
                .dealerId(request.dealerId())
                .model(request.model())
                .price(request.price())
                .status(VehicleStatus.PENDING)
                .tenantId(TenantContext.getTenant())
                .build();

        return map(repository.save(vehicle));
    }

    @Override
    public VehicleResponse getVehicle(UUID id) {

        Vehicle vehicle = repository.findByIdAndTenantId(
                        id,
                        TenantContext.getTenant()
                )
                .orElseThrow(() ->
                        new ApplicationException(
                                ErrorCode.RESOURCE_NOT_FOUND));

        return map(vehicle);
    }

    @Override
    public Page<VehicleResponse> getVehicles(
            String model,
            String status,
            Double priceMin,
            Double priceMax,
            String subscription,
            Pageable pageable) {

        if (subscription != null) {

            return repository
                    .findBySubscription(
                            TenantContext.getTenant(),
                            SubscriptionType.valueOf(subscription),
                            pageable
                    )
                    .map(this::map);
        }

        Page<Vehicle> vehicles =
                repository.findByTenantId(
                        TenantContext.getTenant(),
                        pageable
                );

        return vehicles.map(this::map);
    }

    @Override
    public VehicleResponse updateVehicle(
            UUID id,
            VehicleRequest request) {

        Vehicle vehicle = repository
                .findByIdAndTenantId(
                        id,
                        TenantContext.getTenant()
                )
                .orElseThrow(() ->
                        new ApplicationException(
                                ErrorCode.RESOURCE_NOT_FOUND));

        vehicle.setDealerId(request.dealerId());
        vehicle.setModel(request.model());
        vehicle.setPrice(request.price());
        vehicle.setStatus(request.status());

        return map(repository.save(vehicle));
    }

    @Override
    public void deleteVehicle(UUID id) {

        Vehicle vehicle = repository
                .findByIdAndTenantId(
                        id,
                        TenantContext.getTenant()
                )
                .orElseThrow(() ->
                        new ApplicationException(
                                ErrorCode.RESOURCE_NOT_FOUND));

        repository.delete(vehicle);
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
    public VehicleResponse markAsSold(UUID id) {

        Vehicle vehicle = repository
                .findByIdAndTenantId(
                        id,
                        TenantContext.getTenant()
                )
                .orElseThrow(() ->
                        new ApplicationException(
                                ErrorCode.RESOURCE_NOT_FOUND));

        vehicle.setStatus(VehicleStatus.SOLD);

        return map(repository.save(vehicle));
    }
}