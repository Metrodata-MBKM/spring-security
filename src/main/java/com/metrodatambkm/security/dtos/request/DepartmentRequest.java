package com.metrodatambkm.security.dtos.request;

import com.metrodatambkm.security.dtos.response.DepartmentResponse;
import com.metrodatambkm.security.entities.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class DepartmentRequest extends BaseRequest<Integer>{
    private String name;
    private Integer location;
    private Integer manager;
}
