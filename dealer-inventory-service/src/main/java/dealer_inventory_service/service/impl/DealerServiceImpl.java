package dealer_inventory_service.service.impl;

import dealer_inventory_service.dto.DealerRequest;
import dealer_inventory_service.dto.DealerResponse;
import dealer_inventory_service.enums.ErrorCode;
import dealer_inventory_service.exceptions.ApplicationException;
import dealer_inventory_service.model.Dealer;
import dealer_inventory_service.repository.DealerRepository;
import dealer_inventory_service.service.DealerService;
import dealer_inventory_service.tenant.TenantContext;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DealerServiceImpl implements DealerService {

    private final DealerRepository repository;

    @Override
    public DealerResponse createDealer(DealerRequest request) {

        Dealer dealer = Dealer.builder()
                .name(request.name())
                .email(request.email())
                .subscriptionType(request.subscriptionType())
                .tenantId(TenantContext.getTenant())
                .build();

        return map(repository.save(dealer));
    }

    @Override
    public DealerResponse getDealer(UUID id) {

        Dealer dealer = repository
                .findByIdAndTenantId(
                        id,
                        TenantContext.getTenant()
                )
                .orElseThrow(() ->
                        new ApplicationException(
                                ErrorCode.RESOURCE_NOT_FOUND));

        return map(dealer);
    }

    @Override
    public Page<DealerResponse> getAllDealers(Pageable pageable) {

        return repository
                .findByTenantId(
                        TenantContext.getTenant(),
                        pageable
                )
                .map(this::map);
    }

    @Override
    public DealerResponse updateDealer(
            UUID id,
            DealerRequest request) {

        Dealer dealer = repository
                .findByIdAndTenantId(
                        id,
                        TenantContext.getTenant()
                )
                .orElseThrow(() ->
                        new ApplicationException(
                                ErrorCode.RESOURCE_NOT_FOUND));

        dealer.setName(request.name());
        dealer.setEmail(request.email());
        dealer.setSubscriptionType(
                request.subscriptionType());

        return map(repository.save(dealer));
    }

    @Override
    public void deleteDealer(UUID id) {

        Dealer dealer = repository
                .findByIdAndTenantId(
                        id,
                        TenantContext.getTenant()
                )
                .orElseThrow(() ->
                        new ApplicationException(
                                ErrorCode.RESOURCE_NOT_FOUND));

        repository.delete(dealer);
    }

    private DealerResponse map(Dealer dealer) {

        return new DealerResponse(
                dealer.getId(),
                dealer.getName(),
                dealer.getEmail(),
                dealer.getSubscriptionType()
        );
    }
}