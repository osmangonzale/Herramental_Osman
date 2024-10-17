package Servlets;

import controladores.ClasificacionDefectoJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Clasificacion_defecto extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        try {
            //<editor-fold defaultstate="collapsed" desc="SESION">
            HttpSession sesion = request.getSession();
            String Rol_Usuario = sesion.getAttribute("Nombre_rol").toString();
            String nombre_Usuario = sesion.getAttribute("Nombres").toString();
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="JPAS">
            ClasificacionDefectoJpaController jpa_clasificacionD = new ClasificacionDefectoJpaController();
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="VARIABLES GLOBALES">
            String clasificacion, descripcion, convencion, descripcionHtml = "";
            int id_defecto = 0, estado = 0;
            String[] lineas = null;
            Boolean result = false;
            int opc = Integer.parseInt(request.getParameter("opc"));
//</editor-fold>
            switch (opc) {
                //<editor-fold defaultstate="collapsed" desc="CASO 1 ATRIBUTOS">
                case 1:
                    try {
                        id_defecto = Integer.parseInt(request.getParameter("Id_C_defecto"));
                    } catch (Exception e) {
                        id_defecto = 0;
                    }
                    request.setAttribute("Id_C_defecto", id_defecto);
                    request.getRequestDispatcher("Clasificacion_defecto.jsp").forward(request, response);
                    break;
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="CASO 2 REGISTRAR">
                case 2:
                    try {
                        clasificacion = request.getParameter("clasificacion");
                    } catch (Exception e) {
                        clasificacion = null;
                    }
                    try {
                        convencion = request.getParameter("convencion");
                    } catch (Exception e) {
                        convencion = null;
                    }
                    try {
                        descripcion = request.getParameter("descripcion");
                    } catch (Exception e) {
                        descripcion = null;
                    }
                    try {
                        nombre_Usuario = sesion.getAttribute("Nombres").toString();
                    } catch (Exception e) {
                        nombre_Usuario = null;
                    }
                    descripcionHtml = "<div><p>&nbsp;</p><ul>";
                    lineas = descripcion.split("\n");
                    for (String linea : lineas) {
                        if (linea.startsWith("-")) {
                            descripcionHtml += "<li>" + linea.substring(1).trim() + "</li>";
                        }
                    }
                    descripcionHtml += "</ul>";
                    for (String linea : lineas) {
                        if (linea.startsWith("Aceptacion:")) {
                            descripcionHtml += "<p><strong><font size=\"4\">" + linea.trim() + "</font></strong></p>";
                        }
                    }
                    descripcionHtml += "<p>&nbsp;</p></div>";
                    result = jpa_clasificacionD.RegistroClasificacion(clasificacion, descripcionHtml, convencion, nombre_Usuario);
                    if (result) {
                        request.setAttribute("registrar_C_defecto", result);
                    }
                    request.getRequestDispatcher("Clasificacion_defecto?opc=1").forward(request, response);
                    break;
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="CASO 3 MODIFICAR">

                case 3:
                    id_defecto = Integer.parseInt(request.getParameter("Id_C_defecto"));
                    try {
                        clasificacion = request.getParameter("clasificacion");
                    } catch (Exception e) {
                        clasificacion = null;
                    }
                    try {
                        convencion = request.getParameter("convencion");
                    } catch (Exception e) {
                        convencion = null;
                    }
                    try {
                        descripcion = request.getParameter("descripcion");
                    } catch (Exception e) {
                        descripcion = null;
                    }
                    descripcionHtml = "<div><p>&nbsp;</p><ul>";
                    lineas = descripcion.split("\n");
                    for (String linea : lineas) {
                        if (linea.startsWith("-")) {
                            descripcionHtml += "<li>" + linea.substring(1).trim() + "</li>";
                        }
                    }
                    descripcionHtml += "</ul>";
                    for (String linea : lineas) {
                        if (linea.startsWith("Aceptacion:")) {
                            descripcionHtml += "<p><strong><font size=\"4\">" + linea.trim() + "</font></strong></p>";
                        }
                    }
                    descripcionHtml += "<p>&nbsp;</p></div>";
                    result = jpa_clasificacionD.ModificarClasificacion(id_defecto, clasificacion, convencion, descripcionHtml);
                    if (result) {
                        request.setAttribute("modificar_C_defecto", result);
                    }
                    request.getRequestDispatcher("Clasificacion_defecto?opc=1&Id_C_defecto=0").forward(request, response);
                    break;
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="CASO 4 CAMBIAR ESTADO">
                case 4:
                    try {
                        id_defecto = Integer.parseInt(request.getParameter("Id_C_defecto"));
                    } catch (Exception e) {
                        id_defecto = 0;
                    }
                    try {
                        estado = Integer.parseInt(request.getParameter("estado"));
                    } catch (Exception e) {
                        estado = 0;
                    }
                    result = jpa_clasificacionD.cambiarEstado_C_defecto(id_defecto, estado);
                    if (result) {
                        request.setAttribute("cambiarEstado_C_defecto", result);
                    }
                    request.getRequestDispatcher("Clasificacion_defecto?opc=1&Id_C_defecto=0").forward(request, response);
                    break;
//</editor-fold>
            }
        } catch (Exception ex) {
            request.getRequestDispatcher("Clasificacion_defecto.jsp").forward(request, response);
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
