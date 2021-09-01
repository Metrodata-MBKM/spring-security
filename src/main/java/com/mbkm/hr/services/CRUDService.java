package com.mbkm.hr.services;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class CRUDService<T extends JpaRepository, Object, Key> {

    T repository;

    public List<Object> getAll(){
        return repository.findAll();
    }

    public Optional<Object> getById(Key id){
        return repository.findById(id);
    }

    public Object save(Object object){
        try{
            return (Object) repository.save(object);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public boolean delete(Key id){
        try{
            repository.deleteById(id);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}
