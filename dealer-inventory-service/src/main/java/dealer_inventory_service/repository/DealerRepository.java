package dealer_inventory_service.repository;
import dealer_inventory_service.model.Dealer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DealerRepository
        extends JpaRepository<Dealer, UUID> {

    Page<Dealer> findByTenantId(
            String tenantId,
            Pageable pageable
    );

   Optional<Dealer> findByIdAndTenantId(
           UUID id,
           String tenantId
   );
    @Query("""
            SELECT d.subscriptionType, COUNT(d)
            FROM Dealer d
            GROUP BY d.subscriptionType
            """)
    List<Object[]> countBySubscription();
}
