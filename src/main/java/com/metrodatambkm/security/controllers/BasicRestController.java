package com.metrodatambkm.security.controllers;

import com.metrodatambkm.security.dtos.APIResponse;
import com.metrodatambkm.security.services.interfaces.ICRUDService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

public class BasicRestController<E, Req, Res, I> implements IBasicRestController<Req, Res, I>{

    ICRUDService<E, Req, Res, I> service;

    @Override
    @GetMapping
    public List<Res> getAll() {

        List<Res> result = service.getAll();

        return result;
    }

    @Override
    @GetMapping("/{id}")
    public Res getById(@PathVariable  I id) {
        Res result = service.getById(id);

        return result;
    }

    @Override
    @PostMapping
    public Res create(@RequestBody Req req) {
        Res result = service.create(req);

        return result;
    }

    @Override
    @PutMapping("/{id}")
    public Res update(@PathVariable I id, @RequestBody Req req) {
        Res result = service.update(id, req);

        return result;
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable I id) {

        service.delete(id);
        return ResponseEntity.ok("Deleted!");
    }
}
