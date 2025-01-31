/*
 * Copyright (C) ActiveViam 2024
 * ALL RIGHTS RESERVED. This material is the CONFIDENTIAL and PROPRIETARY
 * property of ActiveViam Limited. Any unauthorized use,
 * reproduction or transfer of this material is strictly prohibited
 */
package com.activeviam.aaosandbox.cfg.security.dsl.local;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import com.activeviam.springboot.atoti.server.starter.api.LoginLogoutUrls;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Sample of a custom {@link LogoutSuccessHandler} in case the client requires something specific
 */
@Component
@NoArgsConstructor
@Slf4j
public class UserLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {
        if (authentication != null) {
            log.info("User {} has logged out", authentication.getName());
        }
        response.sendRedirect(LoginLogoutUrls.LOGOUT_PAGE_URL);
    }
}
