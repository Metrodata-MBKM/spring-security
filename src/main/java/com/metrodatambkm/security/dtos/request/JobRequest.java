package com.metrodatambkm.security.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class JobRequest extends BaseRequest<Integer>{
    private String name;
    private double minSalary;
    private double maxSalary;
}
