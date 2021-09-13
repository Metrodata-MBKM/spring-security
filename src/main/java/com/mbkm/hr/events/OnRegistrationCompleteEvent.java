/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.events;

import com.mbkm.hr.DTO.RegisterResponse;
import java.util.Locale;
import org.springframework.context.ApplicationEvent;

/**
 *
 * @author Dony Tri P
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
