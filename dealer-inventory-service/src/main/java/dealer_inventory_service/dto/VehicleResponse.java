package dealer_inventory_service.dto;

import dealer_inventory_service.enums.VehicleStatus;

import java.math.BigDecimal;
import java.util.UUID;

public record VehicleResponse(

        UUID id,
        UUID dealerId,
        String model,
        BigDecimal price,
        VehicleStatus status

) {}