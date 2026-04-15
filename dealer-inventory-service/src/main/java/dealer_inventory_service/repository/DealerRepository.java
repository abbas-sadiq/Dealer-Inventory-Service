package dealer_inventory_service.repository;
import dealer_inventory_service.model.Dealer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

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
}
