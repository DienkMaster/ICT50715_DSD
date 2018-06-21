// ******************************************************************************
// * Copyright (c) 2010 Ford Motor Company. All Rights Reserved.
// * Original author: Ford Motor Company J2EE Center of Excellence
// *
// * $Workfile:   BARSPersistenceAS.java  $
// * $Revision:   1.0  $
// * $Author:   jburnard  $
// * $Date:   Oct 06 2010 13:37:50  $
// ******************************************************************************
package com.lpa.outbound.layer;

import com.ford.it.persistencecore.as.PcBaseLateBindingAS;

/**
 * This concrete class provides generalized Application Service methods for BARS.
 * 
 * @since v. 1.0
 */
public class BARSPersistenceAS extends PcBaseLateBindingAS {

    /** UID */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor - Useful if more than one persistence configuration is
     * present.
     * 
     * @param persistenceConfigurationKey Matches the persistence property name
     *        in the Persistence Core descriptor document.
     */

    public BARSPersistenceAS(final String persistenceConfigurationKey) {
        super(persistenceConfigurationKey);

    }
}