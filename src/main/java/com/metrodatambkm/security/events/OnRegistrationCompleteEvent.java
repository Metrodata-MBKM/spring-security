/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodatambkm.security.events;

import com.metrodatambkm.security.dto.RegisterResponseDTO;
import java.util.Locale;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.context.ApplicationEvent;

/**
 *
 * @author gabri
 */

public class OnRegistrationCompleteEvent extends ApplicationEvent{
    private RegisterResponseDTO registerResponseDTO;

    public OnRegistrationCompleteEvent(RegisterResponseDTO response) {
        super(response);
        this.registerResponseDTO=response;
    }
    
    public RegisterResponseDTO getRegisterResponseDTO(){
        return registerResponseDTO;
    }

    public void setRegisterResponseDTO(RegisterResponseDTO registerResponseDTO) {
        this.registerResponseDTO = registerResponseDTO;
    }

   

 
    
    
}
