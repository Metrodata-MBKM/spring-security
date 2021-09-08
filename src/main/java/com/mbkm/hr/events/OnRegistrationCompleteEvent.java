/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.events;

import com.mbkm.hr.dtos.RegisterResponse;
import java.util.Locale;
import org.springframework.context.ApplicationEvent;

/**
 *
 * @author rebel
 */
public class OnRegistrationCompleteEvent extends ApplicationEvent {

    private String appUrl;
    private Locale locale;
    private RegisterResponse registerResponse;

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

    public RegisterResponse getRegisterResponse() {
        return registerResponse;
    }

    public void setRegisterResponse(RegisterResponse registerResponse) {
        this.registerResponse = registerResponse;
    }

    public OnRegistrationCompleteEvent(RegisterResponse response, Locale locale, String appUrl){
        super(response);

        this.registerResponse = response;
        this.locale = locale;
        this.appUrl = appUrl;
    }
    
}
