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
    public ResponseEntity<APIResponse<List<Res>>> getAll() {

        List<Res> result = service.getAll();

        return ResponseEntity.ok(new APIResponse(
                LocalDateTime.now(),
                HttpStatus.OK.value(),
                result
        ));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<Res>> getById(@PathVariable  I id) {
        Res result = service.getById(id);

        return ResponseEntity.ok(new APIResponse(
                LocalDateTime.now(),
                HttpStatus.OK.value(),
                result
        ));
    }

    @Override
    @PostMapping
    public ResponseEntity<APIResponse<Res>> create(@RequestBody Req req) {
        Res result = service.create(req);

        return ResponseEntity.ok(new APIResponse(
                LocalDateTime.now(),
                HttpStatus.OK.value(),
                result
        ));
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<Res>> update(@PathVariable I id, @RequestBody Req req) {
        Res result = service.update(id, req);

        return ResponseEntity.ok(new APIResponse(
                LocalDateTime.now(),
                HttpStatus.OK.value(),
                result
        ));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<String>> delete(@PathVariable I id) {

        service.delete(id);

        return ResponseEntity.ok(new APIResponse(
                LocalDateTime.now(),
                HttpStatus.OK.value()
        ));
    }
}
