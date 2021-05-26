package com.emergentes.controlador;

import com.emergentes.dao.UsuariosDAOimpl;
import com.emergentes.modelo.Usuarios;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.emergentes.dao.UsuariosDAO;


@WebServlet(name = "UsuarioControlador", urlPatterns = {"/UsuarioControlador"})
public class UsuarioControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            Usuarios usu = new Usuarios();
            int id;
            UsuariosDAO dao = new UsuariosDAOimpl();
            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";
            
            switch(action){
                case "add":
                    request.setAttribute("usuario", usu);
                    request.getRequestDispatcher("frmusuarios.jsp").forward(request, response);
                    break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    usu =  dao.getById(id);
                    request.setAttribute("usuario", usu);
                    request.getRequestDispatcher("frmusuarios.jsp").forward(request, response);
                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    response.sendRedirect("UsuarioControlador");
                    break;
                case "view":
                    // Obtener la lista de registros
                    List<Usuarios> lista = dao.getAll();
                    request.setAttribute("usuarios",lista);
                    request.getRequestDispatcher("usuarios.jsp").forward(request, response);
                    break;
            }
            
        }catch(Exception ex){
            System.out.println("Error " + ex.getMessage());
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        String usuario = request.getParameter("usuario");
        String correo =  request.getParameter("correo");
        String clave = request.getParameter("clave");
        
        Usuarios usu = new Usuarios();
        
        usu.setId(id);
        usu.setUsuario(usuario);
        usu.setCorreo(correo);
        usu.setClave(clave);
        
        UsuariosDAO dao = new UsuariosDAOimpl();
        if (id == 0){
            try {
                // Nuevo registro
                dao.insert(usu);
            } catch (Exception ex) {
                System.out.println("Error al insertar "+ex.getMessage());
            }
        }
        else{
            try {
                // Edicion de registro
                dao.update(usu);
            } catch (Exception ex) {
                System.out.println("Error al editar "+ex.getMessage());
            }
        }
        response.sendRedirect("UsuarioControlador");
    }

}
