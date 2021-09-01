/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 *
 * @author loisceka
 */
public interface BaseController<Object, ID> {

    List<Object> getAll();
    Object getById(ID id);
    Object save(Object object);
    Object update(ID id, Object object);
    String delete(ID id);
}
