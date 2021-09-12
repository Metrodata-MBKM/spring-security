/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodatambkm.security.exceptions;

import com.metrodatambkm.security.dto.ExceptionResponse;
import java.time.LocalDateTime;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @author gabri
 */

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> resourceNotFound(ResourceNotFoundException ex, HttpServletRequest request) {
        ExceptionResponse response = new ExceptionResponse();
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setError("NOT_FOUND");
        response.setMessage(ex.getMessage());
        response.setPath(request.getContextPath());

        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> resourceAlreadyExists(AlreadyExistsException ex, HttpServletRequest request) {
        ExceptionResponse response=new ExceptionResponse();
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(HttpStatus.CONFLICT.value());
        response.setError("CONFLICT");
        response.setMessage(ex.getMessage());
        response.setTimestamp(LocalDateTime.now());
        response.setPath(request.getContextPath());

        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ExceptionResponse> customException(CustomException ex, HttpServletRequest request) {
        ExceptionResponse response=new ExceptionResponse();
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setError("BAD_REQUEST");
        response.setMessage(ex.getMessage());
        response.setPath(request.getContextPath());

        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ExceptionResponse> unauthorizedException(UnauthorizedException ex, HttpServletRequest request) {
        ExceptionResponse response=new ExceptionResponse();
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setError("UNAUTHORIZED");
        response.setMessage(ex.getMessage());
        response.setPath(request.getContextPath());

        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.UNAUTHORIZED);
    }
}
