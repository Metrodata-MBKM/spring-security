package com.metrodatambkm.security.controllers;

import com.metrodatambkm.security.dtos.APIResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IBasicRestController<Req, Res, I>{

    public ResponseEntity<APIResponse<List<Res>>> getAll();
    public ResponseEntity<APIResponse<Res>> getById(@PathVariable I id);
    public ResponseEntity<APIResponse<Res>> create(@RequestBody Req req);
    public ResponseEntity<APIResponse<Res>> update(@PathVariable I id, @RequestBody Req req);
    public ResponseEntity<APIResponse<String>> delete(@PathVariable I id);

}
