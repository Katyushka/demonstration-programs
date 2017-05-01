package com.demo.util;

import com.demo.domain.CurrentUser;
import com.demo.domain.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author Ekaterina Pyataeva on 24.04.2017.
 */
public class SecurityHelper {

    public static User getCurrentUser() {
        if (SecurityContextHolder.getContext().getAuthentication() == null){
            return null;
        }
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            CurrentUser currentUser = (CurrentUser) principal;
            return currentUser.getUser();
        }
        return null;
    }
}
