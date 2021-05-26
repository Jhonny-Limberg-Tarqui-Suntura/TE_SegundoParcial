package com.emergentes.dao;

import com.emergentes.modelo.Permisos;
import com.emergentes.utiles.ConexionBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PermisosDAOimpl extends ConexionBD implements PermisosDAO{

    @Override
    public void insert(Permisos permiso) throws Exception {
        try {
            this.conectar();
            String sql = "INSERT INTO permisos (id_usuario, id_rol) values (?,?)";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, permiso.getId_usuario());
            ps.setInt(2, permiso.getId_rol());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void update(Permisos permiso) throws Exception {
        try {
            this.conectar();
            String sql = "UPDATE permisos SET id_usuario = ?, id_rol = ? WHERE id = ?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, permiso.getId_usuario());
            ps.setInt(2, permiso.getId_rol());
            ps.setInt(3, permiso.getId());
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
            String sql = "DELETE FROM permisos WHERE id = ?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public Permisos getById(int id) throws Exception {
        Permisos p = new Permisos();
        try {
            this.conectar();
            String sql = "SELECT * FROM permisos WHERE id = ?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                p.setId(rs.getInt("id"));
                p.setId_usuario(rs.getInt("id_usuario"));
                p.setId_rol(rs.getInt("id_rol"));
            }

        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return p;
    }

    @Override
    public List<Permisos> getAll() throws Exception {
        List<Permisos> lista = null;
        try {
            this.conectar();
            String sql = "SELECT v.*,p.usuario as usuarios, c.descripcion as roles FROM permisos v ";
            sql += "LEFT JOIN usuarios p ON v.id_usuario = p.id ";
            sql += "LEFT JOIN roles c ON v.id_rol = c.id";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            lista = new ArrayList<Permisos>();
            while (rs.next()) {
                Permisos v = new Permisos();
                v.setId(rs.getInt("id"));
                v.setId_usuario(rs.getInt("id_usuario"));
                v.setId_rol(rs.getInt("id_rol"));
                v.setUsuario(rs.getString("usuarios"));
                v.setDescripcion(rs.getString("roles"));
                lista.add(v);
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
