package com.stream.video.catalog.domain.exceptions;

public class NoStackTraceException extends RuntimeException {

    /*
     Important to suppress stack traces to avoid performance issues.
    * */
    public NoStackTraceException(String message, Throwable cause) {
        super(message, cause, true, false);
    }

    public NoStackTraceException(String message) {
        this(message, null);
    }
}
