/*
 * Copyright (C) ActiveViam 2024
 * ALL RIGHTS RESERVED. This material is the CONFIDENTIAL and PROPRIETARY
 * property of ActiveViam Limited. Any unauthorized use,
 * reproduction or transfer of this material is strictly prohibited
 */
package com.activeviam.aaosandbox.cfg.pivot;

import static com.activeviam.aaosandbox.cfg.pivot.ActivePivotManagerConfig.DOUBLE_FORMATTER;
import static com.activeviam.aaosandbox.constants.StoreAndFieldConstants.TRADES_NOTIONAL;

import org.springframework.stereotype.Component;

import com.activeviam.activepivot.copper.api.Copper;
import com.activeviam.activepivot.core.intf.api.copper.ICopperContext;

@Component
public class Measures {

    public void build(ICopperContext context) {
        Copper.sum(TRADES_NOTIONAL)
                .as(TRADES_NOTIONAL)
                .withFormatter(DOUBLE_FORMATTER)
                .publish(context);
    }
}
