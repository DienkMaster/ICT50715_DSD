package com.lpa.common.layer.exception;

public abstract class LpaBaseRuntimeException extends Exception {

    private static final long serialVersionUID = 1L;

    public LpaBaseRuntimeException(final String message) {
        super(message);
    }

    public LpaBaseRuntimeException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
