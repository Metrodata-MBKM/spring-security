package com.metrodatambkm.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseEmployee {
    private String name;
    private String manager;
    private String job;
    private String country;
    private String region;
}
