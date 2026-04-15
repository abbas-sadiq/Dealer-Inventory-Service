package dealer_inventory_service.dto;
import dealer_inventory_service.enums.SubscriptionType;
import java.util.UUID;
public record DealerResponse(

        UUID id,
        String name,
        String email,
        SubscriptionType subscriptionType

) {}
