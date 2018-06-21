//******************************************************************************
//* Copyright (c) 2012 Ford Motor Company. All Rights Reserved.
//******************************************************************************
package com.lpa.business.layer;

import com.ford.it.exception.FordExceptionAttributes;
import com.ford.bars.common.layer.exception.BARSBaseRuntimeException;

/**
 * BARS exception to be thrown when Business processing problems occur.
 */
public class BARSBusinessException extends BARSBaseRuntimeException {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;

    /**
     * Construct a BARSBusinessException instance
     * 
     * @param fordExceptionAttributes
     * @param message
     */
    public BARSBusinessException(
            final FordExceptionAttributes fordExceptionAttributes,
            final String message) {
        super(fordExceptionAttributes, message);

    }

    /**
     * Construct a BARSBusinessException instance
     * 
     * @param fordExceptionAttributes
     * @param message
     * @param cause
     */
    public BARSBusinessException(
            final FordExceptionAttributes fordExceptionAttributes,
            final String message, final Throwable cause) {
        super(fordExceptionAttributes, message, cause);

    }
}
