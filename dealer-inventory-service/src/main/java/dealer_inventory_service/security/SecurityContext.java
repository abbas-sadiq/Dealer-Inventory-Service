package dealer_inventory_service.security;

import dealer_inventory_service.enums.UserRole;

public class SecurityContext {

    private static final ThreadLocal<UserRole> ROLE = new ThreadLocal<>();

    public static void setRole(String role) {
        ROLE.set(UserRole.valueOf(role));
    }

    public static UserRole getRole() {
        return ROLE.get();
    }

    public static void clear() {
        ROLE.remove();
    }
}