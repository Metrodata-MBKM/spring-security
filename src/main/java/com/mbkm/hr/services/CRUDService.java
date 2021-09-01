package com.mbkm.hr.services;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class CRUDService<T extends JpaRepository, Object, K> {

    T repository;

    public List<Object> getAll(){
        return repository.findAll();
    }

    public Optional<T> getById(K id){
        return repository.findById(id);
    }

    public boolean save(T object){
        try{
            repository.save(object);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean delete(K id){
        try{
            repository.delete(id);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}
