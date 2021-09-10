/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.exceptions;

import com.mbkm.hr.dtos.ExceptionResponseDTO;
import java.time.LocalDateTime;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @author hp
 */
@ControllerAdvice
public class BaseExceptions {
    @ExceptionHandler(NotFoundExceptions.class)
    public ResponseEntity<ExceptionResponseDTO> resourceNotFound(NotFoundExceptions ex, HttpServletRequest request) {
        ExceptionResponseDTO response = new ExceptionResponseDTO();
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setError("NOT_FOUND");
        response.setMessage(ex.getMessage());
        response.setPath(request.getContextPath());

        return new ResponseEntity<ExceptionResponseDTO>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AlreadyExistExceptions.class)
    public ResponseEntity<ExceptionResponseDTO> resourceAlreadyExists(AlreadyExistExceptions ex, HttpServletRequest request) {
        ExceptionResponseDTO response=new ExceptionResponseDTO();
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(HttpStatus.CONFLICT.value());
        response.setError("CONFLICT");
        response.setMessage(ex.getMessage());
        response.setTimestamp(LocalDateTime.now());
        response.setPath(request.getContextPath());

        return new ResponseEntity<ExceptionResponseDTO>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CustomExceptions.class)
    public ResponseEntity<ExceptionResponseDTO> customException(CustomExceptions ex, HttpServletRequest request) {
        ExceptionResponseDTO response=new ExceptionResponseDTO();
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setError("BAD_REQUEST");
        response.setMessage(ex.getMessage());
        response.setPath(request.getContextPath());

        return new ResponseEntity<ExceptionResponseDTO>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedExceptions.class)
    public ResponseEntity<ExceptionResponseDTO> unauthorizedException(UnauthorizedExceptions ex, HttpServletRequest request) {
        ExceptionResponseDTO response=new ExceptionResponseDTO();
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setError("UNAUTHORIZED");
        response.setMessage(ex.getMessage());
        response.setPath(request.getContextPath());

        return new ResponseEntity<ExceptionResponseDTO>(response, HttpStatus.UNAUTHORIZED);
    }
}
