package com.emergentes.dao;

import com.emergentes.modelo.Usuarios;
import java.util.List;

public interface UsuariosDAO {
    
    public void insert(Usuarios usuario) throws Exception;
    public void update(Usuarios usuario) throws Exception;
    public void delete(int id) throws Exception;
    public Usuarios getById(int id) throws Exception;
    public List<Usuarios> getAll() throws Exception;
    
}
