package dealer_inventory_service.repository;

import dealer_inventory_service.enums.SubscriptionType;
import dealer_inventory_service.enums.VehicleStatus;
import dealer_inventory_service.model.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface VehicleRepository
        extends JpaRepository<Vehicle, UUID> {

    Page<Vehicle> findByTenantId(
            String tenantId,
            Pageable pageable
    );

    Page<Vehicle> findByTenantIdAndStatus(
            String tenantId,
            VehicleStatus status,
            Pageable pageable
    );

    Optional<Vehicle> findByIdAndTenantId(
            UUID id,
            String tenantId
    );

    @Query("""
            SELECT v
            FROM Vehicle v
            JOIN v.dealer d
            WHERE v.tenantId = :tenantId
            AND d.subscriptionType = :subscription
            """)
    Page<Vehicle> findBySubscription(
            @Param("tenantId") String tenantId,
            @Param("subscription") SubscriptionType subscription,
            Pageable pageable
    );
}