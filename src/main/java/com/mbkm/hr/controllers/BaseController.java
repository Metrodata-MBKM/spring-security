package com.mbkm.hr.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

public interface BaseController<Object, ID> {
    List<Object> getAll();
    Object getById(ID id);
    Object save(Object object);
    Object update(ID id, Object object);
    String delete(ID id);
}
