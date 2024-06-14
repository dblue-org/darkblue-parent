package org.dblue.security.utils;

import org.dblue.common.error.CommonError;
import org.dblue.common.exception.ServiceException;
import org.dblue.security.user.SecurityUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

/**
 * @author Wang Chengwei
 * @since 1.0.0 [2022/12/15 10:31]
 */
public class SecurityUtils {

    private SecurityUtils() {
    }

    public static boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.isAuthenticated() && !isAnonymousUser(authentication);
    }

    private static boolean isAnonymousUser(Authentication authentication) {
        return Objects.equals("anonymousUser", authentication.getPrincipal());
    }

    /**
     * 获取用户ID
     *
     * @return 用户ID
     */
    public static String getUserId() {
        SecurityUser securityUser = getUser();
        return securityUser.getUserId();
    }

    public static SecurityUser getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return (SecurityUser) authentication.getPrincipal();
        } else {
            throw new ServiceException(CommonError.UNAUTHORIZED);
        }
    }

    public static String getDeptId() {
        SecurityUser securityUser = getUser();
        if (securityUser == null) {
            throw new ServiceException(CommonError.UNAUTHORIZED);
        }
        return securityUser.getDeptId();
    }
}
