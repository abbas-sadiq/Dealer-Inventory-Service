package dealer_inventory_service.dto;

import dealer_inventory_service.enums.VehicleStatus;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record VehicleRequest(

        @NotNull
        UUID dealerId,

        String model,

        BigDecimal price,

        VehicleStatus status

) {}