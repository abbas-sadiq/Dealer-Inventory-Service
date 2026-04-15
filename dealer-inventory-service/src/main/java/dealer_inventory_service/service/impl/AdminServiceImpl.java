package dealer_inventory_service.service.impl;

import dealer_inventory_service.repository.DealerRepository;
import dealer_inventory_service.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl
        implements AdminService {

    private final DealerRepository repository;

    @Override
    public Map<String, Long>
    countDealersBySubscription() {

        List<Object[]> results =
                repository.countBySubscription();

        Map<String, Long> response =
                new HashMap<>();

        for (Object[] row : results) {

            response.put(
                    row[0].toString(),
                    (Long) row[1]
            );
        }

        return response;
    }
}