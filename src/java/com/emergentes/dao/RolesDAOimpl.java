package com.emergentes.dao;

import com.emergentes.modelo.Roles;
import com.emergentes.utiles.ConexionBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RolesDAOimpl extends ConexionBD implements RolesDAO{

    @Override
    public void insert(Roles rol) throws Exception {
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("INSERT INTO roles (descripcion) values (?)");
            ps.setString(1, rol.getDescripcion());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void update(Roles rol) throws Exception {
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("UPDATE roles SET descripcion = ? where id = ?");
            ps.setString(1, rol.getDescripcion());
            ps.setInt(2, rol.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("DELETE FROM roles WHERE id = ?");
            ps.setInt(1,id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public Roles getById(int id) throws Exception {
               Roles ro = new Roles();
        try {
            this.conectar();
            
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM roles WHERE id = ?");
            ps.setInt(1,id);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()){
                ro.setId(rs.getInt("id"));
                ro.setDescripcion(rs.getString("descripcion"));
            }
            
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return ro;
    }

    @Override
    public List<Roles> getAll() throws Exception {
        List<Roles> lista = null;
        try {
            this.conectar();
            
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM roles");
            ResultSet rs = ps.executeQuery();
            
            lista = new ArrayList<Roles>();
            while (rs.next()){
                Roles ro =  new Roles();
                
                ro.setId(rs.getInt("id"));
                ro.setDescripcion(rs.getString("descripcion"));
                
                lista.add(ro);
            }
            rs.close();
            ps.close();
            
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return lista;
    }
    
}
