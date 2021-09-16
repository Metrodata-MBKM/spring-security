/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.events;

import com.mbkm.hr.dtos.response.RegisterResponseDTO;
import java.util.Locale;
import org.springframework.context.ApplicationEvent;

/**
 *
 * @author hp
 */
public class OnRegistrationCompleteEvent extends ApplicationEvent {
    
    private RegisterResponseDTO registerResponseDTO;

    public RegisterResponseDTO getRegisterResponseDTO() {
        return registerResponseDTO;
    }

    public void setRegisterResponseDTO(RegisterResponseDTO registerResponseDTO) {
        this.registerResponseDTO = registerResponseDTO;
    }

    public OnRegistrationCompleteEvent(RegisterResponseDTO response){
        super(response);

        this.registerResponseDTO = response;
    }
}