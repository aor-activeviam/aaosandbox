/*
 * Copyright (C) ActiveViam 2024
 * ALL RIGHTS RESERVED. This material is the CONFIDENTIAL and PROPRIETARY
 * property of ActiveViam Limited. Any unauthorized use,
 * reproduction or transfer of this material is strictly prohibited
 */
package com.activeviam.aaosandbox.cfg;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.activeviam.activepivot.core.intf.api.cube.IActivePivotManager;
import com.activeviam.tech.core.api.agent.AgentException;

import lombok.RequiredArgsConstructor;

/**
 * Spring configuration of the ActivePivot Application services
 *
 * @author ActiveViam
 */
@Component
@RequiredArgsConstructor
public class ApplicationManagerService {
    private final IActivePivotManager activePivotManager;

    /**
     * Initialize and start the ActivePivot Manager, after performing all the injections into the ActivePivot plug-ins.
     *
     * @throws AgentException any exception that occurred during the injection, the initialization or the starting
     */
    @EventListener(ApplicationStartedEvent.class)
    public void startManager() throws AgentException {
        /* *********************************************** */
        /* Initialize the ActivePivot Manager and start it */
        /* *********************************************** */
        activePivotManager.init(null);
        activePivotManager.start();
    }
}
