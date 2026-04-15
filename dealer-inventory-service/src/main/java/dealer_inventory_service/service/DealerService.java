package dealer_inventory_service.service;

import dealer_inventory_service.dto.DealerRequest;
import dealer_inventory_service.dto.DealerResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface DealerService {

    DealerResponse createDealer(DealerRequest request);

    DealerResponse getDealer(UUID id);

    Page<DealerResponse> getAllDealers(Pageable pageable);

    DealerResponse updateDealer(UUID id, DealerRequest request);

    void deleteDealer(UUID id);
}