/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.events;

import com.mbkm.hr.dtos.RegisterResponse;
import org.springframework.context.ApplicationEvent;
/**
 *
 * @author Kevitha
 */
public class OnRegistrationCompleteEvent extends ApplicationEvent {
    
    private RegisterResponse registerResponse;
    
    public OnRegistrationCompleteEvent(RegisterResponse response){
        super(response);
        this.registerResponse = response;
    }

    public RegisterResponse getRegisterResponse() {
        return registerResponse;
    }

    public void setRegisterResponse(RegisterResponse registerResponse) {
        this.registerResponse = registerResponse;
    }
}