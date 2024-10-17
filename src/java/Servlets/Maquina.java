package Servlets;

import Factory.Productos;
import Mail.Email;
import controladores.MaquinaJpaController;
import controladores.MovimientoInyectoraJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Maquina extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        try {
            //<editor-fold defaultstate="collapsed" desc="SESION">
            HttpSession sesion = request.getSession();
            String Usuario_registro = sesion.getAttribute("Nombres").toString();
            String usu_registro = sesion.getAttribute("Nombres/Rol").toString();
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="JPAS">
            MaquinaJpaController jpa_maquina = new MaquinaJpaController();
            MovimientoInyectoraJpaController jpa_movimientoIny = new MovimientoInyectoraJpaController();
            Email mail = new Email();
            Productos product = new Productos();
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="VARIABLES GLOBALES">
            String nombre, codigo = "", producto, fecha, lote, cavidades, movimiento, descripcion_tapada, descripcion, firma1, firma2, firma3, justificacion, parametro, nombre_maquina = "";
            int temp, tempo, tempora, id_tipo_M, total_cavi, cavi_desha, herramental, id_movimiento, valorSeleccionado, id_area1, id_area2, id_area3, codusu, cedula, contador, estado, id_maquina = 0;
            boolean result = false;
            boolean todosFirmados = true;
            int opc = Integer.parseInt(request.getParameter("opc"));
//</editor-fold>
            switch (opc) {
                //<editor-fold defaultstate="collapsed" desc="CASO 1 ATRIBUTOS">
                case 1:
                    try {
                        id_maquina = Integer.parseInt(request.getParameter("id_maquina"));
                    } catch (Exception e) {
                        id_maquina = 0;
                    }
                    try {
                        temp = Integer.parseInt(request.getParameter("temp"));
                    } catch (Exception e) {
                        temp = 0;
                    }
                    try {
                        nombre_maquina = request.getParameter("nombre_maquina").toString();
                    } catch (Exception e) {
                        nombre_maquina = "";
                    }
                    try {
                        codigo = request.getParameter("codigo").toString();
                    } catch (Exception e) {
                        codigo = "";
                    }
                    try {
                        tempo = Integer.parseInt(request.getParameter("tempo"));
                    } catch (Exception e) {
                        tempo = 0;
                    }
                    try {
                        tempora = Integer.parseInt(request.getParameter("tempora"));
                    } catch (Exception e) {
                        tempora = 0;
                    }
                    try {
                        id_movimiento = Integer.parseInt(request.getParameter("id_movimiento"));
                    } catch (Exception e) {
                        id_movimiento = 0;
                    }
                    try {
                        valorSeleccionado = Integer.parseInt(request.getParameter("newEstado"));
                    } catch (Exception e) {
                        valorSeleccionado = 0;
                    }
                    try {
                        codusu = Integer.parseInt(request.getParameter("codusu"));
                    } catch (Exception e) {
                        codusu = 0;
                    }
                    try {
                        cedula = Integer.parseInt(request.getParameter("cedula"));
                    } catch (Exception e) {
                        cedula = 0;
                    }
                    try {
                        parametro = request.getParameter("parametro").toString();
                    } catch (Exception e) {
                        parametro = "";
                    }
                    request.setAttribute("id_maquina", id_maquina);
                    request.setAttribute("temp", temp);
                    request.setAttribute("nombre_maquina", nombre_maquina);
                    request.setAttribute("codigo", codigo);
                    request.setAttribute("tempo", tempo);
                    request.setAttribute("id_movimiento", id_movimiento);
                    request.setAttribute("newEstado", valorSeleccionado);
                    request.setAttribute("codusu", codusu);
                    request.setAttribute("cedula", cedula);
                    request.setAttribute("tempora", tempora);
                    request.setAttribute("parametro", parametro);
                    request.getRequestDispatcher("maquina.jsp").forward(request, response);
                    break;
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="CASO 2 REGISTRAR MAQUINA">
                case 2:
                    try {
                        nombre = request.getParameter("nombre");
                    } catch (Exception e) {
                        nombre = null;
                    }
                    try {
                        id_tipo_M = Integer.parseInt(request.getParameter("tipo"));
                    } catch (Exception e) {
                        id_tipo_M = 0;
                    }
                    try {
                        Usuario_registro = sesion.getAttribute("Nombres").toString();
                    } catch (Exception e) {
                        Usuario_registro = null;
                    }
                    result = jpa_maquina.RegistroMaquina(nombre, id_tipo_M, Usuario_registro);
                    if (result) {
                        request.setAttribute("registro_maquina", result);
                    }
                    request.getRequestDispatcher("Maquina?opc=1").forward(request, response);
                    break;
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="CASO 3 MODIFICAR MAQUINA">
                case 3:
                    try {
                        id_maquina = Integer.parseInt(request.getParameter("id_maquina"));
                    } catch (Exception e) {
                        id_maquina = 0;
                    }
                    try {
                        nombre = request.getParameter("nombre");
                    } catch (Exception e) {
                        nombre = null;
                    }
                    try {
                        id_tipo_M = Integer.parseInt(request.getParameter("tipo"));
                    } catch (Exception e) {
                        id_tipo_M = 0;
                    }
                    result = jpa_maquina.ModificarMaquina(id_maquina, nombre, id_tipo_M);
                    if (result) {
                        request.setAttribute("modificar_maquina", result);
                    }
                    request.getRequestDispatcher("Maquina?opc=1").forward(request, response);
                    break;
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="CASO 4 REGISTRAR MOVIMIENTO">
                case 4:
                    try {
                        id_maquina = Integer.parseInt(request.getParameter("id_maquina"));
                    } catch (NumberFormatException e) {
                        id_maquina = 0;
                    }
                    try {
                        producto = request.getParameter("producto");
                    } catch (Exception e) {
                        producto = null;
                    }
                    try {
                        fecha = request.getParameter("fecha");
                    } catch (Exception e) {
                        fecha = null;
                    }
                    try {
                        lote = request.getParameter("lote");
                    } catch (Exception e) {
                        lote = null;
                    }
                    try {
                        total_cavi = Integer.parseInt(request.getParameter("total_cavi"));
                    } catch (NumberFormatException e) {
                        total_cavi = 0;
                    }
                    try {
                        cavi_desha = Integer.parseInt(request.getParameter("cavi_desha"));
                    } catch (NumberFormatException e) {
                        cavi_desha = 0;
                    }
                    try {
                        cavidades = request.getParameter("cavidades");
                    } catch (Exception e) {
                        cavidades = null;
                    }
                    try {
                        herramental = Integer.parseInt(request.getParameter("herramental"));
                    } catch (NumberFormatException e) {
                        herramental = 0;
                    }
                    try {
                        movimiento = request.getParameter("movimiento");
                    } catch (Exception e) {
                        movimiento = null;
                    }
                    descripcion_tapada = request.getParameter("descripcion_tapada");
                    if (descripcion_tapada == null) {
                        descripcion_tapada = "N/A";
                    }
                    descripcion = request.getParameter("descripcion");
                    if (descripcion == null) {
                        descripcion = "N/A";
                    }
                    try {
                        usu_registro = sesion.getAttribute("Nombres/Rol").toString();
                    } catch (Exception e) {
                        usu_registro = null;
                    }
                    result = jpa_movimientoIny.RegistroMovimiento(id_maquina, producto, fecha, lote, total_cavi, cavi_desha, cavidades, herramental, movimiento, descripcion_tapada, descripcion, "[" + usu_registro + "]");
                    if (result) {
                        request.setAttribute("regitrar_movimiento", result);
                    }
                    request.getRequestDispatcher("Maquina?opc=1&temp=2").forward(request, response);
                    break;
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="CASO 5 FIRMAS DESMONTADO">
                case 5:
                    try {
                        id_maquina = Integer.parseInt(request.getParameter("id_maquina"));
                    } catch (NumberFormatException e) {
                        id_maquina = 0;
                    }
                    try {
                        id_movimiento = Integer.parseInt(request.getParameter("id_movimiento"));
                    } catch (NumberFormatException e) {
                        id_movimiento = 0;
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
                    } catch (NumberFormatException e) {
                        id_area1 = 0;
                    }
                    try {
                        id_area2 = Integer.parseInt(request.getParameter("id_area2"));
                    } catch (NumberFormatException e) {
                        id_area2 = 0;
                    }
                    try {
                        id_area3 = Integer.parseInt(request.getParameter("id_area3"));
                    } catch (NumberFormatException e) {
                        id_area3 = 0;
                    }
                    try {
                        valorSeleccionado = Integer.parseInt(request.getParameter("newEstado"));
                    } catch (NumberFormatException e) {
                        valorSeleccionado = 0;
                    }
                    try {
                        codusu = Integer.parseInt(request.getParameter("codusu"));
                    } catch (NumberFormatException e) {
                        codusu = 0;
                    }
                    try {
                        cedula = Integer.parseInt(request.getParameter("cedula"));
                    } catch (NumberFormatException e) {
                        cedula = 0;
                    }
                    try {
                        contador = Integer.parseInt(request.getParameter("contador"));
                    } catch (NumberFormatException e) {
                        contador = 0;
                    }
                    try {
                        temp = Integer.parseInt(request.getParameter("temp"));
                    } catch (NumberFormatException e) {
                        temp = 0;
                    }
                    try {
                        tempo = Integer.parseInt(request.getParameter("tempo"));
                    } catch (NumberFormatException e) {
                        tempo = 0;
                    }
                    try {
                        tempora = Integer.parseInt(request.getParameter("tempora"));
                    } catch (NumberFormatException e) {
                        tempora = 0;
                    }

                    String todosFirmadosStr = request.getParameter("todosFirmados");

                    todosFirmados = Boolean.parseBoolean(todosFirmadosStr);

                    if (id_area1 == 6) {
                        result = jpa_movimientoIny.Registrar_firmas_movimiento(id_movimiento, firma1, id_area1);
                    } else if (id_area2 == 3) {
                        result = jpa_movimientoIny.Registrar_firmas_movimiento(id_movimiento, firma2, id_area2);
                    } else if (id_area3 == 10) {
                        result = jpa_movimientoIny.Registrar_firmas_movimiento(id_movimiento, firma3, id_area3);
                    }
                    if (todosFirmados) {
                        request.setAttribute("regitrar_firmas_movimiento", todosFirmados);
                        request.getRequestDispatcher("Maquina?opc=1&newEstado=" + valorSeleccionado + "&id_movimiento=" + id_movimiento + "&id_maquina=" + id_maquina + "&temp=2&tempo=1&tempora=0").forward(request, response);
                    } else {
                        request.getRequestDispatcher("Maquina?opc=1&newEstado=" + valorSeleccionado + "&codusu=" + codusu + "&cedula=" + cedula + "&id_movimiento=" + id_movimiento + "&id_maquina=" + id_maquina + "&temp=2&tempo=1&tempora=1").forward(request, response);
                    }
                    break;
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="CASO 6 MODIFICAR ULTIMO MOVIMIENTO">
                case 6:
                    try {
                        id_movimiento = Integer.parseInt(request.getParameter("id_movimiento"));
                    } catch (NumberFormatException e) {
                        id_movimiento = 0;
                    }
                    try {
                        producto = request.getParameter("producto");
                    } catch (Exception e) {
                        producto = null;
                    }
                    try {
                        fecha = request.getParameter("fecha");
                    } catch (Exception e) {
                        fecha = null;
                    }
                    try {
                        lote = request.getParameter("lote");
                    } catch (Exception e) {
                        lote = null;
                    }
                    try {
                        total_cavi = Integer.parseInt(request.getParameter("total_cavi"));
                    } catch (NumberFormatException e) {
                        total_cavi = 0;
                    }
                    try {
                        cavi_desha = Integer.parseInt(request.getParameter("cavi_desha"));
                    } catch (NumberFormatException e) {
                        cavi_desha = 0;
                    }
                    try {
                        cavidades = request.getParameter("cavidades");
                    } catch (Exception e) {
                        cavidades = null;
                    }
                    try {
                        herramental = Integer.parseInt(request.getParameter("herramental"));
                    } catch (NumberFormatException e) {
                        herramental = 0;
                    }
                    try {
                        movimiento = request.getParameter("movimiento");
                    } catch (Exception e) {
                        movimiento = null;
                    }
                    descripcion_tapada = request.getParameter("descripcion_tapada");
                    if (descripcion_tapada == null) {
                        descripcion_tapada = "N/A";
                    }
                    descripcion = request.getParameter("descripcion");
                    if (descripcion == null) {
                        descripcion = "N/A";
                    }
                    try {
                        usu_registro = sesion.getAttribute("Nombres/Rol").toString();
                    } catch (Exception e) {
                        usu_registro = null;
                    }
                    result = jpa_movimientoIny.ModificarMovimiento(id_movimiento, producto, fecha, lote, total_cavi, cavi_desha, cavidades, herramental, movimiento, descripcion_tapada, descripcion, "[" + usu_registro + "]");
                    if (result) {
                        request.setAttribute("modificar_movimiento", result);
                    }
                    request.getRequestDispatcher("Maquina?opc=1&tempo=0").forward(request, response);
                    break;
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="CASO 7 CAMBIO ESTADO MAQUINA">
                case 7: 
                    try {
                        id_maquina = Integer.parseInt(request.getParameter("id_maquina"));
                    } catch (Exception e) {
                        id_maquina = 0;
                    }
                    try {
                        estado = Integer.parseInt(request.getParameter("estado"));
                    } catch (Exception e) {
                        estado = 0;
                    }
                    try {
                        justificacion = request.getParameter("justificacion");
                    } catch (Exception e) {
                        justificacion = null;
                    }
                    result = jpa_maquina.ModificarEstadoMaquina(id_maquina, estado, justificacion);
                    if (result) {
                        request.setAttribute("cambiar_estado", result);
                    }
                    request.getRequestDispatcher("Maquina?opc=1").forward(request, response);
                    break;
//</editor-fold>
                    
//</editor-fold>
            }
        } catch (Exception ex) {
            request.getRequestDispatcher("menu.jsp").forward(request, response);
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
