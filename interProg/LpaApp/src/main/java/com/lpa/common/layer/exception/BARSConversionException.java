// ******************************************************************************
// * Copyright (c) 2010 Ford Motor Company. All Rights Reserved.
// * Original author: Ford Motor Company J2EE Center of Excellence
// *
// * $Workfile:   BARSConversionException.java  $
// * $Revision:   1.0  $
// * $Author:   mtoll2  $
// * $Date:   Jun 14 2010 07:48:52  $
// ******************************************************************************
package com.lpa.common.layer.exception;

import com.ford.it.exception.FordExceptionAttributes;

/**
 * This class supports runtime exceptions.
 * 
 * @since v. 3.0
 */
public class BARSConversionException extends BARSBaseRuntimeException {

    /**
     * UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Construct the new exception with the specified detail message and
     * immutable attributes.
     * 
     * @param fordExceptionAttributes
     * @param message
     */
    public BARSConversionException(
            final FordExceptionAttributes fordExceptionAttributes,
            final String message) {

        super(fordExceptionAttributes, message);
    }

    /**
     * Construct the new exception with the specified detail message, immutable
     * attributes and cause.
     * 
     * @param fordExceptionAttributes Instance of immutable attributes
     * @param message Detail Logging message
     * @param cause Nested root cause exception
     */
    public BARSConversionException(
            final FordExceptionAttributes fordExceptionAttributes,
            final String message, final Throwable cause) {

        super(fordExceptionAttributes, message, cause);
    }
}