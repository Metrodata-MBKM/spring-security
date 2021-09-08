package com.metrodatambkm.security.events;

import com.metrodatambkm.security.dtos.RegisterResponse;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;
import java.util.Locale;

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
