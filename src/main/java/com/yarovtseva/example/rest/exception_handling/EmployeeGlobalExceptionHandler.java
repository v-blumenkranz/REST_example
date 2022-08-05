package com.yarovtseva.example.rest.exception_handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmployeeGlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<EmployeeBadData> handleException(NoSuchEmployeeException exception) {
        EmployeeBadData badData = new EmployeeBadData();
        badData.setInfo(exception.getMessage());
        return new ResponseEntity<>(badData, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<EmployeeBadData> handleException(Exception exception) {
        EmployeeBadData badData = new EmployeeBadData();
        badData.setInfo(exception.getMessage());
        return new ResponseEntity<>(badData, HttpStatus.BAD_REQUEST);
    }
}
