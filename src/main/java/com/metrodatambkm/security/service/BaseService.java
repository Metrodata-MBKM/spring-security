package com.metrodatambkm.security.service;

import com.metrodatambkm.security.dto.UserDTO;
import com.metrodatambkm.security.model.UserModel;

import java.util.List;
import java.util.Optional;

public interface BaseService<T,key> {

    void add(T object);

    List<T> findAll();

    Optional<T> getById(key id);

    void delete(key id);

    T change(T object);
}