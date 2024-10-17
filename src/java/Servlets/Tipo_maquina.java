package Servlets;

import controladores.TipoMaquinaJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Tipo_maquina extends HttpServlet {

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
            TipoMaquinaJpaController jpa_tipoMaquina = new TipoMaquinaJpaController();
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="VARIABLES GLOBALES">
            String nombre = "";
            int id_TipoM = 0, estado = 0, id_area = 0;
            Boolean result = false;      
            int opc = Integer.parseInt(request.getParameter("opc"));
//</editor-fold>
            switch (opc) {
                //<editor-fold defaultstate="collapsed" desc="CASO 1 ATRIBUTOS">
                case 1:
                    try {
                        id_TipoM = Integer.parseInt(request.getParameter("Id_tipoMaquina").toString());
                    } catch (Exception e) {
                        id_TipoM = 0;
                    }
                    request.setAttribute("Id_tipoMaquina", id_TipoM);
                    request.getRequestDispatcher("tipo_maquina.jsp").forward(request, response);
                    break;
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="CASO 2 REGISTRAR">
                case 2:
                    try {
                        nombre = request.getParameter("nombre");
                    } catch (Exception e) {
                        nombre = null;
                    }
                    try {
                        id_area = Integer.parseInt(request.getParameter("Area"));
                    } catch (Exception e) {
                        id_area = 0;
                    }
                    try {
                        nombre_Usuario = sesion.getAttribute("Nombres").toString();
                    } catch (Exception e) {
                        nombre_Usuario = null;
                    }
                    result = jpa_tipoMaquina.RegistroTipoMaquina(nombre, id_area, nombre_Usuario);
                    if (result) {
                        request.setAttribute("registro_tipo_maquina", result);
                        request.getRequestDispatcher("Tipo_maquina?opc=1").forward(request, response);
                    }
                    request.getRequestDispatcher("Tipo_maquina?opc=1").forward(request, response);
                    break;
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="CASO 3 MODIFICAR">
                case 3:
                    try {
                        id_TipoM = Integer.parseInt(request.getParameter("Id_tipoMaquina"));
                    } catch (Exception e) {
                        id_TipoM = 0;
                    }
                    try {
                        nombre = request.getParameter("nombre");
                    } catch (Exception e) {
                        nombre = null;
                    }
                    try {
                        id_area = Integer.parseInt(request.getParameter("Area"));
                    } catch (Exception e) {
                        id_area = 0;
                    }
                    try {
                        nombre_Usuario = sesion.getAttribute("Nombres").toString();
                    } catch (Exception e) {
                        nombre_Usuario = null;
                    }
                    result = jpa_tipoMaquina.ModificarTipoMaquina(id_TipoM, nombre, id_area, nombre_Usuario);
                    if (result) {
                        request.setAttribute("modificar_tipo_maquina", result);
                    }
                    request.getRequestDispatcher("Tipo_maquina?opc=1&Id_tipoMaquina=0").forward(request, response);
                    break;
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="CASO 4 CAMBIAR ESTADO">
                case 4:
                    try {
                        id_TipoM = Integer.parseInt(request.getParameter("Id_tipoMaquina"));
                    } catch (NumberFormatException e) {
                        id_TipoM = 0;
                    }
                    try {
                        estado = Integer.parseInt(request.getParameter("estado"));
                    } catch (NumberFormatException e) {
                        estado = 0;
                    }
                    result = jpa_tipoMaquina.cambiarEstado_tipoMaquina(id_TipoM, estado);
                    if (result) {
                        request.setAttribute("cambiarEstado_tipo_maquina", result);
                    }
                    request.getRequestDispatcher("Tipo_maquina?opc=1&Id_tipoMaquina=0").forward(request, response);
                    break;
//</editor-fold>
//</editor-fold>
            }
        } catch (Exception ex) {
            request.getRequestDispatcher("Inicio.jsp").forward(request, response);
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
