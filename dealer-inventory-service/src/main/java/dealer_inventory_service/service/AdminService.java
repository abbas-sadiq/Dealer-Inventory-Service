package dealer_inventory_service.service;

import java.util.Map;

public interface AdminService {

    Map<String, Long> countDealersBySubscription();
}