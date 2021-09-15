package com.metrodatambkm.security.dtos.response;

import com.metrodatambkm.security.dtos.EntityResponse;
import com.metrodatambkm.security.entities.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class DepartmentResponse implements EntityResponse<Department, DepartmentResponse> {
    private Integer id;
    private String name;
    private LocationResponse location;
    private String manager;

    public DepartmentResponse(Department department) {
        this.id = department.getId();
        this.name = department.getName();
        this.location = new LocationResponse(department.getLocation());
        this.manager = department.getManager().getEmployee().getFirstName() + " " + department.getManager().getEmployee().getFirstName();
    }

    @Override
    public DepartmentResponse create(Department department) {
        return new DepartmentResponse(department);
    }
}
