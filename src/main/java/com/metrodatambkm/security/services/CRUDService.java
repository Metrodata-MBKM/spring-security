package com.metrodatambkm.security.services;

import com.metrodatambkm.security.dtos.EntityResponse;
import com.metrodatambkm.security.dtos.request.BaseRequest;
import com.metrodatambkm.security.exceptions.ResourceNotFoundException;
import com.metrodatambkm.security.services.interfaces.ConvertToEntity;
import com.metrodatambkm.security.services.interfaces.ICRUDService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public abstract class CRUDService<E, Req extends BaseRequest, Res, I> implements ICRUDService<E, Req, Res, I>, ConvertToEntity<E, Req> {

    JpaRepository<E, I> repository;
    EntityResponse<E, Res> response;

    @Override
    public List<Res> getAll() {
        List<Res> result = new ArrayList<>();
        System.out.println("================ Services ===================");
        for (E e: repository.findAll()) {
            result.add(response.create(e));
        }
        return result;
    }

    @Override
    public Res getById(I id) {
        E e = repository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("ID Not Found!"));

        return response.create(e);
    }

    @Override
    public E getOne(I id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ID Not Found!"));
    }

    @Override
    public Res create(Req req) {
        E e = repository.save(convert(req));

        return response.create(e);
    }

    @Override
    public Res update(I id, Req req) {
        if(!repository.findById(id).isPresent()){
            throw new ResourceNotFoundException("ID Not Found!");
        }
        req.setId(id);
        return response.create(repository.save(convert(req)));
    }

    @Override
    public void delete(I id) {
        if(!repository.findById(id).isPresent()){
            throw new ResourceNotFoundException("ID Not Found!");
        }

        E e = repository.findById(id).get();

        repository.delete(e);
    }

    @Override
    public abstract E convert(Req req);
}
