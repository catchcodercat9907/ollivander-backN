package io.ollivander.ollivanderbackend.utils;

import io.ollivander.ollivanderbackend.model.entities.Account;
import io.ollivander.ollivanderbackend.model.entities.Role;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public final class SecurityContextHelper {
    private SecurityContextHelper() {

    }

    public static Account getCurrentAccount() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof Account) {
            return (Account) authentication.getPrincipal();
        }

        return null;
    }

    public static boolean hasRole(String role) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            for (GrantedAuthority authority : authentication.getAuthorities()) {
                if (authority.getAuthority().equalsIgnoreCase(role)) {
                    return true;
                }
            }
        }
        return false;
    }

//    public static AuthenticationDetails getCurrentDetails() {
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        if (authentication != null && authentication.isAuthenticated()) {
//            return (AuthenticationDetails) authentication.getDetails();
//        }
//
//        return null;
//
//    }
//
//    public static Account getCurrentStaff() {
//        if (hasRole(Role.ROLE_ADMIN.getAuthority())) {
//            AuthenticationDetails details = getCurrentDetails();
//            return details != null ? details.getAuthenticatedStaff() : null;
//        }
//        return null;
//    }
}
