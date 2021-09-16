package com.metrodatambkm.security.controllers;

import com.metrodatambkm.security.dtos.APIResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IBasicRestController<Req, Res, I>{

    public List<Res> getAll();
    public Res getById(@PathVariable I id);
    public Res create(@RequestBody Req req);
    public Res update(@PathVariable I id, @RequestBody Req req);
    public ResponseEntity<String> delete(@PathVariable I id);

}
