package dealer_inventory_service.dto;



import dealer_inventory_service.enums.SubscriptionType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DealerRequest(

        @NotBlank
        String name,

        @Email
        @NotBlank
        String email,

        @NotNull
        SubscriptionType subscriptionType

) {}
