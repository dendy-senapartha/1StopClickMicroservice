package com.microservice.auth.errors;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;
import org.zalando.problem.Problem;
import org.zalando.problem.spring.web.advice.ProblemHandling;
import org.zalando.problem.spring.web.advice.security.SecurityAdviceTrait;

import java.util.logging.Logger;

/*
 * Created by dendy-prtha on 07/01/2020.
 * exception translator
 */

@ControllerAdvice
public class ExceptionTranslator implements ProblemHandling, SecurityAdviceTrait {

    Logger log = Logger.getLogger(ExceptionTranslator.class.getSimpleName());

    @ExceptionHandler
    public ResponseEntity<Problem> handleUsernameNotFoundException(UsernameNotFoundException ex, NativeWebRequest request) {
        return create(ex, request);
    }

    @ExceptionHandler
    public ResponseEntity<Problem> handleBadRequestAlertException(ForbiddenException ex, NativeWebRequest request) {
        return create(ex, request, HeaderUtil.createFailureAlert("",
                true, ex.getErrorMessage(), ex.getErrorKey(), ex.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<Problem> handleNullPointerException(NullPointerException ex, NativeWebRequest request) {
        NullException exception = new NullException("NPE!", "Unknown null pointer exception", "10302");
        return create(exception, request);
    }

    @ExceptionHandler
    public ResponseEntity<Problem> handleInvalidPasswordException(InvalidPasswordException ex, NativeWebRequest request) {
        return create(new InvalidPasswordException(), request);
    }

    @ExceptionHandler
    public ResponseEntity<Problem> handleEmailAlreadyUsedException(EmailAlreadyUsedException ex, NativeWebRequest request) {
        EmailAlreadyUsedException problem = new EmailAlreadyUsedException();
        return create(problem, request, HeaderUtil.createFailureAlert("",  true,
                problem.getEntityName(), problem.getErrorKey(), problem.getMessage()));
    }

}
