package com.mbkm.hr.controllers;

import java.util.List;

public interface BaseController<Object, ID> {
    List<Object> getAll();
    Object getById(ID id);
    Object save(Object object);
    Object update(Object object);
    String delete(ID id);
}
