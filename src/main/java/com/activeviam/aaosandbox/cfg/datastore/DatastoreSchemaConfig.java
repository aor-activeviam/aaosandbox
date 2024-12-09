/*
 * Copyright (C) ActiveViam 2024
 * ALL RIGHTS RESERVED. This material is the CONFIDENTIAL and PROPRIETARY
 * property of ActiveViam Limited. Any unauthorized use,
 * reproduction or transfer of this material is strictly prohibited
 */
package com.activeviam.aaosandbox.cfg.datastore;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.activeviam.activepivot.server.spring.api.config.IDatastoreSchemaDescriptionConfig;
import com.activeviam.database.datastore.api.description.IDatastoreSchemaDescription;
import com.activeviam.database.datastore.api.description.IReferenceDescription;
import com.activeviam.database.datastore.api.description.IStoreDescription;
import com.activeviam.database.datastore.api.description.impl.DatastoreSchemaDescription;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class DatastoreSchemaConfig implements IDatastoreSchemaDescriptionConfig {
    private final List<? extends IStoreDescription> storeDescriptions;
    private final List<? extends IReferenceDescription> referenceDescriptions;

    @Override
    @Bean
    public IDatastoreSchemaDescription datastoreSchemaDescription() {
        return new DatastoreSchemaDescription(storeDescriptions, referenceDescriptions);
    }
}
