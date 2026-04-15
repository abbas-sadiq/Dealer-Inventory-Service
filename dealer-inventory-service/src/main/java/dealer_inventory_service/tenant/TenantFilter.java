package dealer_inventory_service.tenant;
import dealer_inventory_service.enums.ErrorCode;
import dealer_inventory_service.exceptions.ApplicationException;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TenantFilter implements Filter {

    private static final String TENANT_HEADER = "X-Tenant-Id";

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest =
                (HttpServletRequest) request;

        String tenant =
                httpRequest.getHeader(TENANT_HEADER);

        if (tenant == null || tenant.isBlank()) {
            throw new ApplicationException(
                    ErrorCode.INVALID_REQUEST
            );
        }

        try {

            TenantContext.setTenant(tenant);

            chain.doFilter(request, response);

        } finally {

            TenantContext.clear();
        }
    }
}
