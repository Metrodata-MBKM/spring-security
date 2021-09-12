/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodatambkm.security.exceptions;

/**
 *
 * @author gabri
 */
public class UnauthorizedException extends RuntimeException{
      public UnauthorizedException(String message) {
        super(message);
    }
}
