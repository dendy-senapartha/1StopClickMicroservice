package com.microservice.auth.errors;


import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;



public class ForbiddenException extends AbstractThrowableProblem {

    private static final long serialVersionUID = 1L;

    private final String errorMessage;

    private final String errorKey;

    public ForbiddenException(String title, String errorMessage, String errorKey) {
        this(ErrorConstants.FORBIDDEN_TYPE, title, errorMessage, errorKey);
    }

    public ForbiddenException(URI type, String title, String errorMessage, String errorKey) {
        super(type, title, Status.FORBIDDEN, null, null, null, getAlertParameters(errorMessage, errorKey));
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
