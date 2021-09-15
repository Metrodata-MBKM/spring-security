package com.metrodatambkm.security.services.interfaces;

import java.util.List;

public interface ICRUDService<E, Req, Res, I> {

    public List<Res> getAll();
    public Res getById(I id);
    public E getOne(I id);
    public Res create(Req req);
    public Res update(I id, Req req);
    public void delete(I id);

}
