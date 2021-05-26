package com.emergentes.controlador;

import com.emergentes.dao.RolesDAOimpl;
import com.emergentes.modelo.Roles;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.emergentes.dao.RolesDAO;

@WebServlet(name = "RolControlador", urlPatterns = {"/RolControlador"})
public class RolControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            Roles ro = new Roles();
            int id;
            RolesDAO dao = new RolesDAOimpl();
            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";
            
            switch(action){
                case "add":
                    request.setAttribute("rol", ro);
                    request.getRequestDispatcher("frmroles.jsp").forward(request, response);
                    break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    ro =  dao.getById(id);
                    request.setAttribute("rol", ro);
                    request.getRequestDispatcher("frmroles.jsp").forward(request, response);
                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    response.sendRedirect("RolControlador");
                    break;
                case "view":
                    // Obtener la lista de registros
                    List<Roles> lista = dao.getAll();
                    request.setAttribute("roles",lista);
                    request.getRequestDispatcher("roles.jsp").forward(request, response);
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
        String descripcion = request.getParameter("descripcion");
        
        Roles ro = new Roles();
        ro.setId(id);
        ro.setDescripcion(descripcion);
        
        RolesDAO dao = new RolesDAOimpl();
        if (id == 0){
            try {
                // Nuevo registro
                dao.insert(ro);
            } catch (Exception ex) {
                System.out.println("Error al insertar "+ex.getMessage());
            }
        }
        else{
            try {
                // Edicion de registro
                dao.update(ro);
            } catch (Exception ex) {
                System.out.println("Error al editar "+ex.getMessage());
            }
        }
        response.sendRedirect("RolControlador");
    }
}
