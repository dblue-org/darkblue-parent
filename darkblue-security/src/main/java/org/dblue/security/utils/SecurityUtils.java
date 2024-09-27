package org.dblue.security.utils;

import org.dblue.common.error.CommonError;
import org.dblue.common.exception.ServiceException;
import org.dblue.security.user.SecurityUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

/**
 * 认证相关工具类，用户获取当前登录的用户信息
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
public class SecurityUtils {

    private SecurityUtils() {
    }

    public static boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.isAuthenticated() && !isAnonymousUser(authentication);
    }

    /**
     * 用于判断当前用户是否为匿名用户
     *
     * @param authentication 认证信息
     * @return true-匿名用户
     */
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
        if (authentication != null && isAuthenticated()) {
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

    public static SecurityUser getUserByAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && !isAnonymousUser(authentication)) {
            return (SecurityUser) authentication.getPrincipal();
        } else {
           return null;
        }
    }

    public static Boolean isAdmin() {
        return Boolean.TRUE.equals(getUser().isAdmin());
    }
}
