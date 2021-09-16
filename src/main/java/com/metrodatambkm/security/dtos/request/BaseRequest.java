package com.metrodatambkm.security.dtos.request;

import lombok.Data;

@Data
public class BaseRequest<I> {
    private I id;
}
