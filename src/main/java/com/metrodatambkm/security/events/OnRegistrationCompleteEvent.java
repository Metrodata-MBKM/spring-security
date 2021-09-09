package com.metrodatambkm.security.events;

import com.metrodatambkm.security.dtos.RegisterResponse;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;
import java.util.Locale;


public class OnRegistrationCompleteEvent extends ApplicationEvent {

    public RegisterResponse getRegisterResponse() {
        return registerResponse;
    }

    public void setRegisterResponse(RegisterResponse registerResponse) {
        this.registerResponse = registerResponse;
    }

    private RegisterResponse registerResponse;

    public OnRegistrationCompleteEvent(RegisterResponse response){
        super(response);

        this.registerResponse = response;
    }
}
