/*
 * Copyright (C) ActiveViam 2024
 * ALL RIGHTS RESERVED. This material is the CONFIDENTIAL and PROPRIETARY
 * property of ActiveViam Limited. Any unauthorized use,
 * reproduction or transfer of this material is strictly prohibited
 */
package com.activeviam.aaosandbox.cfg.content;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.activeviam.aaosandbox.content.ConditionalOnEmbeddedContentServer;
import com.activeviam.aaosandbox.content.ContentServiceSecurityProperties;
import com.activeviam.aaosandbox.content.EmbeddedContentServerHibernateProperties;
import com.activeviam.activepivot.server.intf.api.entitlements.IActivePivotContentService;
import com.activeviam.activepivot.server.spring.api.config.IActivePivotContentServiceConfig;
import com.activeviam.activepivot.server.spring.api.content.ActivePivotContentServiceBuilder;
import com.activeviam.tech.contentserver.spring.api.config.AtotiUiContentServiceUtil;
import com.activeviam.tech.contentserver.storage.api.IContentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@ConditionalOnEmbeddedContentServer
@Configuration
@RequiredArgsConstructor
@Slf4j
public class EmbeddedContentServerConfig implements IActivePivotContentServiceConfig {
    private final ContentServiceSecurityProperties contentServerSecurityProperties;
    private final EmbeddedContentServerHibernateProperties embeddedContentServerProperties;

    @Override
    @Bean
    public IContentService contentService() {
        var configuration = embeddedContentServerProperties.toHibernateConfiguration();
        Optional.ofNullable(embeddedContentServerProperties.getProperty(
                        EmbeddedContentServerHibernateProperties.PERSISTENCE_ENTITY_MAPPING_PATH))
                .ifPresent(configuration::addResource);
        var contentService = IContentService.builder()
                .withPersistence()
                .configuration(configuration)
                .build();

        // initialize the ActiveUI structure required on the ContentService side
        AtotiUiContentServiceUtil.initialize(contentService);
        log.info("Initialized the contentServer with the required structure to work with ActiveUI.");
        return contentService;
    }

    @Override
    @Bean
    public IActivePivotContentService activePivotContentService() {
        return new ActivePivotContentServiceBuilder()
                .with(contentService())
                .withCacheForEntitlements(contentServerSecurityProperties
                        .getCacheEntitlementsTtl()
                        .toSeconds())
                .needInitialization(
                        contentServerSecurityProperties.getCalculatedMemberRole(),
                        contentServerSecurityProperties.getKpiRole())
                .build();
    }
}
