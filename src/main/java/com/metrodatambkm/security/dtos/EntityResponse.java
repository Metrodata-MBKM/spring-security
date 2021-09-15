package com.metrodatambkm.security.dtos;

public interface EntityResponse<E, R> {
    R create(E e);
}
