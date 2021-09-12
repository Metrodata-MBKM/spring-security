package com.metrodatambkm.security.controller;


import com.metrodatambkm.security.dto.RegionDTO;
import com.metrodatambkm.security.model.RegionModel;
import com.metrodatambkm.security.service.RegionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
@PreAuthorize("hasAnyRole('PROGRAMMER')")
public class RegionController {

    @Autowired
    private RegionServiceImpl regionService;

    @PostMapping("/addRegion")
    @PreAuthorize("hasAuthority('CREATE_REGION')")
    public ResponseEntity<String> addNewRegion (@RequestBody RegionDTO regionDTO){
        RegionModel newRegion = new RegionModel();
        if (regionDTO.getRegionName()!= null){
            if(regionService.getById(regionDTO.getId()).isPresent()){
                return ResponseEntity.badRequest().body("ID Has been used");
            }else {
                newRegion.setId(regionDTO.getId());
                newRegion.setName(regionDTO.getRegionName());
                regionService.add(newRegion);
                return ResponseEntity.ok("Region has been added");
            }
        }else {
            return ResponseEntity.badRequest().body("All region data must be filled");
        }
    }


    @GetMapping("/getAll")
    @PreAuthorize("hasAuthority('READ_REGION')")
    public List<RegionModel> regionList (){
        try {
            if (regionService.findAll() != null){
                return regionService.findAll();
            }else {
                throw new RuntimeException("Error Occurred");
            }
        }catch (RuntimeException e){
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT, "Region Has Empty Data"
            );
        }
    }


    @GetMapping("/getById/{id}")
    @PreAuthorize("hasAuthority('READ_REGION')")
    public RegionModel getbyid(@PathVariable("id") Long id){
        try {
            return regionService.getById(id).get();
        }catch (NoSuchElementException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Region with ID " + String.valueOf(id) + " doesn't exist!"
            );
        }
    }

    @PutMapping("/updateRegion")
    @PreAuthorize("hasAuthority('UPDATE_REGION')")
    public ResponseEntity<String> updateRegion(
            @RequestBody RegionModel region){
        try {
            if (regionService.getById(region.getId()) != null){
                regionService.change(region);
                return ResponseEntity.ok("Region has been updated");
            }else {
                throw new RuntimeException("Error Occurred");
            }
        }catch (RuntimeException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Region with ID " + String.valueOf(region.getId()) + " doesn't exist!"
            );
        }
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('DELETE_REGION')")
    public ResponseEntity<String> deleteRegion(
            @RequestParam ("id")
                    Long id){
        try {
            if (regionService.getById(id) != null){
                regionService.delete(id);
                return ResponseEntity.ok("Region has been deleted");
            }else {
                throw new RuntimeException("Error Occurred");
            }
        }catch (RuntimeException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Region with ID " + String.valueOf(id) + " doesn't exist!"
            );
        }
    }
}