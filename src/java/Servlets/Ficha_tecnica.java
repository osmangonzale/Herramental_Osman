package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import controladores.FichaTecnicaJpaController;
import controladores.UsuarioJpaController;

public class Ficha_tecnica extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        try {
            //<editor-fold defaultstate="collapsed" desc="SESION">
            HttpSession sesion = request.getSession();
            String Rol_usuario = sesion.getAttribute("Nombre_rol").toString();
            String usu_registro = sesion.getAttribute("Nombres").toString();
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="JPAS">
            FichaTecnicaJpaController JpaFichaTecnica = new FichaTecnicaJpaController();
            UsuarioJpaController JpaUsuario = new UsuarioJpaController();
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="VARIABLES GLOBALES">
            String referencia, nombre, herramental, firma1, firma2, firma3 = "";
            int version, id_fichaTec, temp, valorSeleccionado, id_usuario, codigo, cedula, id_area1, id_area2, id_area3, contador, estado = 0;
            int opc = Integer.parseInt(request.getParameter("opc"));
            boolean todosFirmados = true;
            boolean result = false;
//</editor-fold>
            switch (opc) {
                //<editor-fold defaultstate="collapsed" desc="CASO 1 ATRIBUTOS">
                case 1:
                    try {
                        id_fichaTec = Integer.parseInt(request.getParameter("id_fichaTec"));
                    } catch (Exception e) {
                        id_fichaTec = 0;
                    }
                    try {
                        estado = Integer.parseInt(request.getParameter("estado"));
                    } catch (Exception e) {
                        estado = 0;
                    }
                    try {
                        temp = Integer.parseInt(request.getParameter("temp"));
                    } catch (Exception e) {
                        temp = 0;
                    }
                    try {
                        valorSeleccionado = Integer.parseInt(request.getParameter("newEstado"));
                    } catch (Exception e) {
                        valorSeleccionado = 0;
                    }
                    try {
                        codigo = Integer.parseInt(request.getParameter("codigo"));
                    } catch (Exception e) {
                        codigo = 0;
                    }
                    try {
                        cedula = Integer.parseInt(request.getParameter("cedula"));
                    } catch (Exception e) {
                        cedula = 0;
                    }
                    request.setAttribute("id_fichaTec", id_fichaTec);
                    request.setAttribute("estado", estado);
                    request.setAttribute("temp", temp);
                    request.setAttribute("newEstado", valorSeleccionado);
                    request.setAttribute("codigo", codigo);
                    request.setAttribute("cedula", cedula);
                    request.getRequestDispatcher("Ficha_tecnica.jsp").forward(request, response);
                    break;
//</editor-fold> 
                //<editor-fold defaultstate="collapsed" desc="CASO 2 REGISTRAR FICHA">
                case 2:
                    try {
                        referencia = request.getParameter("referencia");
                    } catch (Exception e) {
                        referencia = null;
                    }
                    try {
                        version = Integer.parseInt(request.getParameter("version"));
                    } catch (Exception e) {
                        version = 0;
                    }
                    try {
                        nombre = request.getParameter("nombre");
                    } catch (Exception e) {
                        nombre = null;
                    }
                    try {
                        herramental = request.getParameter("herramental");
                    } catch (Exception e) {
                        herramental = "N/A";
                    }
                    try {
                        Rol_usuario = sesion.getAttribute("Nombre_rol").toString();
                    } catch (Exception e) {
                        Rol_usuario = null;
                    }
                    result = JpaFichaTecnica.Registrar_Ficha_Tecnica(referencia, version, nombre, herramental, Rol_usuario);
                    if (result) {
                        request.setAttribute("Registrar_f_tec", result);
                    }
                    request.getRequestDispatcher("Ficha_tecnica?opc=1").forward(request, response);
                    break;
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="CASO 3 MODIFICAR FICHA">
                case 3:
                    try {
                        id_fichaTec = Integer.parseInt(request.getParameter("id_fichaTec"));
                    } catch (Exception e) {
                        id_fichaTec = 0;
                    }
                    try {
                        referencia = request.getParameter("referencia");
                    } catch (Exception e) {
                        referencia = null;
                    }
                    try {
                        version = Integer.parseInt(request.getParameter("version"));
                    } catch (Exception e) {
                        version = 0;
                    }
                    try {
                        nombre = request.getParameter("nombre");
                    } catch (Exception e) {
                        nombre = null;
                    }
                    try {
                        herramental = request.getParameter("herramental");
                    } catch (Exception e) {
                        herramental = "N/A";
                    }
                    try {
                        usu_registro = sesion.getAttribute("Nombres").toString();
                    } catch (Exception e) {
                        usu_registro = null;
                    }
                    result = JpaFichaTecnica.Editar_FichaTecnica(id_fichaTec, referencia, version, nombre, herramental, usu_registro);
                    if (result) {
                        request.setAttribute("Modificar_f_tec", result);
                    }
                    request.getRequestDispatcher("Ficha_tecnica?opc=1").forward(request, response);
                    break;
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="CASO 4 CAMBIAR ESTADOS">
                case 4:
                    try {
                        id_fichaTec = Integer.parseInt(request.getParameter("id_fichaTec"));
                    } catch (Exception e) {
                        id_fichaTec = 0;
                    }
                    try {
                        valorSeleccionado = Integer.parseInt(request.getParameter("newEstado"));
                    } catch (Exception e) {
                        valorSeleccionado = 0;
                    }
                    result = JpaFichaTecnica.Editar_FichaTecnica_Estado(id_fichaTec, valorSeleccionado);
                    if (result) {
                        request.setAttribute("estado_f_tec", result);
                    }
                    request.getRequestDispatcher("Ficha_tecnica?opc=1&newEstado=0").forward(request, response);
                    break;
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="CASO 5 REGISTRAR FIRMAS">
                case 5:
                    try {
                        id_fichaTec = Integer.parseInt(request.getParameter("id_fichaTec"));
                    } catch (Exception e) {
                        id_fichaTec = 0;
                    }
                    try {
                        firma1 = request.getParameter("firma1").toString();
                    } catch (Exception e) {
                        firma1 = "";
                    }
                    try {
                        firma2 = request.getParameter("firma2").toString();
                    } catch (Exception e) {
                        firma2 = "";
                    }
                    try {
                        firma3 = request.getParameter("firma3").toString();
                    } catch (Exception e) {
                        firma3 = "";
                    }
                    try {
                        id_area1 = Integer.parseInt(request.getParameter("id_area1"));
                    } catch (Exception e) {
                        id_area1 = 0;
                    }
                    try {
                        id_area2 = Integer.parseInt(request.getParameter("id_area2"));
                    } catch (Exception e) {
                        id_area2 = 0;
                    }
                    try {
                        id_area3 = Integer.parseInt(request.getParameter("id_area3"));
                    } catch (Exception e) {
                        id_area3 = 0;
                    }
                    try {
                        valorSeleccionado = Integer.parseInt(request.getParameter("newEstado"));
                    } catch (Exception e) {
                        valorSeleccionado = 0;
                    }
                    try {
                        codigo = Integer.parseInt(request.getParameter("codigo"));
                    } catch (Exception e) {
                        codigo = 0;
                    }
                    try {
                        cedula = Integer.parseInt(request.getParameter("cedula"));
                    } catch (Exception e) {
                        cedula = 0;
                    }
                    try {
                        contador = Integer.parseInt(request.getParameter("contador"));
                    } catch (Exception e) {
                        contador = 0;
                    }

                    String todosFirmadosStr = request.getParameter("todosFirmados");

                    todosFirmados = Boolean.parseBoolean(todosFirmadosStr);

                    if (id_area1 == 6) {
                        result = JpaFichaTecnica.Registrar_firmas(id_fichaTec, firma1, id_area1);
                    } else if (id_area2 == 3) {
                        result = JpaFichaTecnica.Registrar_firmas(id_fichaTec, firma2, id_area2);
                    } else if (id_area3 == 10) {
                        result = JpaFichaTecnica.Registrar_firmas(id_fichaTec, firma3, id_area3);
                    }
                    if (todosFirmados) {
                        request.getRequestDispatcher("Ficha_tecnica?opc=4&newEstado=" + valorSeleccionado + "&id_fichaTec=" + id_fichaTec).forward(request, response);
                    } else {
                        request.getRequestDispatcher("Ficha_tecnica?opc=1&newEstado=" + valorSeleccionado + "&codigo=" + codigo + "&cedula=" + cedula).forward(request, response);
                    }
                    break;

//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="CASO 6">                    
                case 6:
                    break;
//</editor-fold>
            }
        } catch (Exception ex) {
            request.getRequestDispatcher("Ficha_tecnica.jsp").forward(request, response);
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
