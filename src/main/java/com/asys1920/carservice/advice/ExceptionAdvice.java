package com.asys1920.carservice.advice;

import com.asys1920.carservice.exceptions.CarAlreadyExistsException;
import com.asys1920.carservice.exceptions.IllegalVehicleTypeException;
import com.asys1920.carservice.exceptions.ValidationException;
import lombok.Data;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.rest.core.RepositoryConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionAdvice extends ResponseEntityExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(ExceptionAdvice.class);

    @ExceptionHandler({RepositoryConstraintViolationException.class})
    public ResponseEntity<ErrorMessage> handleRepositoryConstraintViolationException(
            RepositoryConstraintViolationException ex) {
        List<String> errors = ex.getErrors().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
        LOG.error(ex.getMessage(), ex);
        return ResponseEntity.badRequest().body(new ErrorMessage(errors));
    }

    @ExceptionHandler(value = {EmptyResultDataAccessException.class, NoSuchElementException.class})
    @ResponseBody
    public ResponseEntity<String> handleNoSuchEntity(Exception ex) {
        LOG.error(ex.getMessage(), ex);
        return new ResponseEntity<>(jsonFromException(ex), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {IllegalVehicleTypeException.class})
    public ResponseEntity<String> handleIllegalVehicleTypeException(Exception ex) {
        LOG.error(ex.getMessage(), ex);
        return new ResponseEntity<>(jsonFromException(ex), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {CarAlreadyExistsException.class})
    public ResponseEntity<String> handleCarAlreadyExistsException(Exception ex) {
        LOG.error(ex.getMessage(), ex);
        return new ResponseEntity<>(jsonFromException(ex), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseBody
    public ResponseEntity<String> handleValidationException(Exception ex) {
        LOG.error(ex.getMessage(), ex);
        return new ResponseEntity<>(jsonFromException(ex), HttpStatus.BAD_REQUEST);
    }

    private String jsonFromException(Exception ex) {
        JSONObject response = new JSONObject();
        response.put("message", ex.getMessage());
        return response.toJSONString();
    }

    @Data
    private static class ErrorMessage {
        private final String cause = "VALIDATION FAILED";
        private List<String> description;

        ErrorMessage(List<String> description) {
            this.description = description;
        }
    }
}