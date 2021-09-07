/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodatambkm.security.controllers;

import java.util.List;

/**
 *
 * @author gabri
 */
public interface BaseController<Object, ID> {
    List<Object> getAll();
    Object getById(ID id);
    Object save(Object object);
    Object update(ID id, Object object);
    String delete(ID id);
}
