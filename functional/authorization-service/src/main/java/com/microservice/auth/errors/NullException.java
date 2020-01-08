package com.microservice.auth.errors;


import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;


public class NullException extends AbstractThrowableProblem {

    private static final long serialVersionUID = 1L;

    private final String errorMessage;

    private final String errorKey;

    public NullException(String title, String errorMessage, String errorKey) {
        this(ErrorConstants.DEFAULT_TYPE, title, errorMessage, errorKey);
    }

    public NullException(URI type, String title, String errorMessage, String errorKey) {
        super(type, title, Status.INTERNAL_SERVER_ERROR, null, null, null, getAlertParameters(errorMessage, errorKey));
        this.errorMessage = errorMessage;
        this.errorKey = errorKey;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getErrorKey() {
        return errorKey;
    }

    private static Map<String, Object> getAlertParameters(String errorMessage, String errorKey) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("message", errorMessage);
        parameters.put("params",errorKey );
        return parameters;
    }
}
