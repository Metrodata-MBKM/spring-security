/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.events;

import com.mbkm.hr.dtos.RegisterResponseDTO;
import java.util.Locale;
import org.springframework.context.ApplicationEvent;

/**
 *
 * @author hp
 */
public class OnRegistrationCompleteEvent extends ApplicationEvent {

    private String appUrl;
    private Locale locale;
    private RegisterResponseDTO registerResponseDTO;

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public RegisterResponseDTO getRegisterResponseDTO() {
        return registerResponseDTO;
    }

    public void setRegisterResponseDTO(RegisterResponseDTO registerResponseDTO) {
        this.registerResponseDTO = registerResponseDTO;
    }

    public OnRegistrationCompleteEvent(RegisterResponseDTO response, Locale locale, String appUrl){
        super(response);

        this.registerResponseDTO = response;
        this.locale = locale;
        this.appUrl = appUrl;
    }
}