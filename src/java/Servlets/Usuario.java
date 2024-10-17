package Servlets;

import controladores.UsuarioJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Usuario", urlPatterns = {"/Usuario"})
public class Usuario extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        //<editor-fold defaultstate="collapsed" desc="JPAS">
        UsuarioJpaController jpa_usuario = new UsuarioJpaController();
//</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="SESION">
        HttpSession sesion = request.getSession();
        String Nombre_Usuario = sesion.getAttribute("Nombres").toString();
        String Rol = sesion.getAttribute("Nombre_rol").toString();
        String Id_Rol = sesion.getAttribute("Id_rol").toString();
        String Usuario_registro = sesion.getAttribute("Rol/Nombres").toString();
//</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="VARIABLES GLOBALES">
        int documento, codigo, id_usuario, id_rol, id_area, menu, estado = 0;
        String nombre, apellido, usuario, correo, cargo = "";
        Boolean result = false;
        int opc = Integer.parseInt(request.getParameter("opc"));
//</editor-fold>
        try {
            switch (opc) {
                //<editor-fold defaultstate="collapsed" desc="CASO 1 ATRIBUTOS">
                case 1:
                    try {
                        id_usuario = Integer.parseInt(request.getParameter("Id_usuario").toString());
                    } catch (Exception e) {
                        id_usuario = 0;
                    }
                    request.setAttribute("Id_usuario", id_usuario);
                    request.setAttribute("Id_rol", Id_Rol);
                    request.getRequestDispatcher("Usuarios.jsp").forward(request, response);
                    break;
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="CASO 2 REGISTRAR">
                case 2:
                    try {
                        nombre = request.getParameter("Txt_nombre");
                    } catch (Exception e) {
                        nombre = null;
                    }
                    try {
                        apellido = request.getParameter("Txt_apellido");
                    } catch (Exception e) {
                        apellido = null;
                    }
                    try {
                        documento = Integer.parseInt(request.getParameter("Txt_documento"));
                    } catch (Exception e) {
                        documento = 0;
                    }
                    try {
                        codigo = Integer.parseInt(request.getParameter("Txt_codigo"));
                    } catch (Exception e) {
                        codigo = 0;
                    }
                    try {
                        usuario = request.getParameter("Txt_usuario");
                    } catch (Exception e) {
                        usuario = null;
                    }
                    try {
                        id_rol = Integer.parseInt(request.getParameter("Rol"));
                    } catch (Exception e) {
                        id_rol = 0;
                    }
                    try {
                        id_area = Integer.parseInt(request.getParameter("Area"));
                    } catch (Exception e) {
                        id_area = 0;
                    }
                    try {
                        cargo = request.getParameter("Cargo");
                    } catch (Exception e) {
                        cargo = null;
                    }
                    try {
                        correo = request.getParameter("txt_correo");
                    } catch (Exception e) {
                        correo = null;
                    }
                    try {
                        Usuario_registro = sesion.getAttribute("Rol/Nombres").toString();
                    } catch (Exception e) {
                        Usuario_registro = null;
                    }
                    result = jpa_usuario.RegistroUsuario(nombre, apellido, documento, codigo, usuario, id_rol, id_area, cargo, correo, Usuario_registro);
                    if (result) {
                        request.setAttribute("Registro_usuario", result);
                    }
                    request.getRequestDispatcher("Usuario?opc=1").forward(request, response);
                    break;
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="CASO 3 MODIFICAR">
                case 3:
                    try {
                        id_usuario = Integer.parseInt(request.getParameter("Id_usuario"));
                    } catch (Exception e) {
                        id_usuario = 0;
                    }
                    try {
                        nombre = request.getParameter("Txt_nombre");
                    } catch (Exception e) {
                        nombre = null;
                    }
                    try {
                        apellido = request.getParameter("Txt_apellido");
                    } catch (Exception e) {
                        apellido = null;
                    }
                    try {
                        documento = Integer.parseInt(request.getParameter("Txt_documento"));
                    } catch (Exception e) {
                        documento = 0;
                    }
                    try {
                        codigo = Integer.parseInt(request.getParameter("Txt_codigo"));
                    } catch (Exception e) {
                        codigo = 0;
                    }
                    try {
                        usuario = request.getParameter("Txt_usuario");
                    } catch (Exception e) {
                        usuario = null;
                    }
                    try {
                        id_rol = Integer.parseInt(request.getParameter("Rol"));
                    } catch (Exception e) {
                        id_rol = 0;
                    }
                    try {
                        id_area = Integer.parseInt(request.getParameter("Area"));
                    } catch (Exception e) {
                        id_area = 0;
                    }
                    try {
                        cargo = request.getParameter("Cargo");
                    } catch (Exception e) {
                        cargo = null;
                    }
                    try {
                        correo = request.getParameter("txt_correo");
                    } catch (Exception e) {
                        correo = null;
                    }
                    try {
                        Usuario_registro = sesion.getAttribute("Rol/Nombres").toString();
                    } catch (Exception e) {
                        Usuario_registro = null;
                    }
                    result = jpa_usuario.ModificarUsuario(id_usuario, nombre, apellido, documento, codigo, usuario, id_rol, id_area, cargo, correo, Usuario_registro);
                    if (result) {
                        request.setAttribute("Modificar_usuario", result);
                    }
                    request.getRequestDispatcher("Usuario?opc=1&Id_usuario=0").forward(request, response);
                    break;
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="CASO 4 INACTIVAR">
                case 4:
                    try {
                        id_usuario = Integer.parseInt(request.getParameter("Id_usuario"));
                    } catch (Exception e) {
                        id_usuario = 0;
                    }
                    try {
                        estado = Integer.parseInt(request.getParameter("estado"));
                    } catch (Exception e) {
                        estado = 0;
                    }
                    result = jpa_usuario.cambiarEstado_Usuario(id_usuario, estado);
                    if (result) {
                        request.setAttribute("cambiarEstado_Usuario", result);
                    }
                    request.getRequestDispatcher("Usuario?opc=1&Id_usuario=0").forward(request, response);
                    break;
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="CASO 5 REESTABLECER PASSWORD">
                case 5:
                    try {
                        menu = Integer.parseInt(request.getParameter("menu").toString());
                    } catch (Exception e) {
                        menu = 0;
                    }
                    try {
                        id_usuario = Integer.parseInt(request.getParameter("Id_usuario").toString());
                    } catch (Exception e) {
                        id_usuario = 0;
                    }
                    result = jpa_usuario.Restablecer_password(id_usuario);
                    if (result) {
                        request.setAttribute("Password_actualizado", result);
                    } else {
                        request.setAttribute("Error_app", result);
                    }
                    if (menu == 1) {
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                    } else {
                        request.getRequestDispatcher("Usuario?opc=1&Id_usuario=0").forward(request, response);
                    }
                    break;
//</editor-fold>
            }
        } catch (Exception ex) {
            request.setAttribute("Alerta", "Error_sesion");
            request.getRequestDispatcher("Usuario.jsp").forward(request, response);
        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
