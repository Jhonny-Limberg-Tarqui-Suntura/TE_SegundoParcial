package com.emergentes.dao;

import com.emergentes.modelo.Roles;
import java.util.List;

public interface RolesDAO {
    
    public void insert(Roles rol) throws Exception;
    public void update(Roles rol) throws Exception;
    public void delete(int id) throws Exception;
    public Roles getById(int id) throws Exception;
    public List<Roles> getAll() throws Exception;
    
}
