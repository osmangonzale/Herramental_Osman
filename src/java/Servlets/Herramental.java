package Servlets;

import Mail.Email;
import controladores.HerramentalJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Herramental extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        try {
            //<editor-fold defaultstate="collapsed" desc="SESION">
            HttpSession sesion = request.getSession();
            String nombre_Usuario = sesion.getAttribute("Nombres").toString();
            String Rol_Usuario = sesion.getAttribute("Nombre_rol").toString();
            String Usuario_registro = sesion.getAttribute("Nombres").toString();

//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="JPAS">
            HerramentalJpaController jpa_herramental = new HerramentalJpaController();
            Email mail = new Email();
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="VARIABLES GLOBALES">
            String filtro = "";
            int tipo, id_herramental, estado, temp = 0;
            String codigo, descripcion, maquina = "";
            Boolean result = false;
            int opc = Integer.parseInt(request.getParameter("opc"));
            switch (opc) {
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="CASO 1 ATRIBUTOS">
                case 1:
                    try {
                        id_herramental = Integer.parseInt(request.getParameter("id_herramental").toString());
                    } catch (Exception e) {
                        id_herramental = 0;
                    }
                    try {
                        temp = Integer.parseInt(request.getParameter("temp").toString());
                    } catch (Exception e) {
                        temp = 0;
                    }
                    request.setAttribute("id_herramental", id_herramental);
                    request.setAttribute("temp", temp);
                    request.getRequestDispatcher("herramental.jsp").forward(request, response);
                    break;
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="CASO 2 REGISTRAR HERRAMENTAL">
                case 2:
                    try {
                        codigo = request.getParameter("codigo");
                    } catch (Exception e) {
                        codigo = null;
                    }
                    try {
                        descripcion = request.getParameter("descripcion");
                    } catch (Exception e) {
                        descripcion = null;
                    }
                    try {
                        tipo = Integer.parseInt(request.getParameter("tipo"));
                    } catch (NumberFormatException e) {
                        tipo = 0;
                    }
                    if (maquina.equals("") || (maquina == null)) {
                        maquina = "N/A";
                    } else {
                        maquina = request.getParameter("maquina");
                    }
                    try {
                        Usuario_registro = sesion.getAttribute("Nombres").toString();
                    } catch (Exception e) {
                        Usuario_registro = null;
                    }
                    result = jpa_herramental.RegistroHerramental(codigo, descripcion, tipo, maquina, Usuario_registro);
                    if (result) {
                        request.setAttribute("registro_herramental", result);
                        request.getRequestDispatcher("Herramental?opc=1").forward(request, response);
                    }
                    request.getRequestDispatcher("Herramental?opc=1").forward(request, response);
                    break;
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="CASO 3 MODIFICAR HERRAMENTAL">
                case 3:
                    try {
                        id_herramental = Integer.parseInt(request.getParameter("id_herramental").toString());
                    } catch (Exception e) {
                        id_herramental = 0;
                    }
                    try {
                        codigo = request.getParameter("codigo");
                    } catch (Exception e) {
                        codigo = null;
                    }
                    try {
                        descripcion = request.getParameter("descripcion");
                    } catch (Exception e) {
                        descripcion = null;
                    }
                    try {
                        tipo = Integer.parseInt(request.getParameter("tipo"));
                    } catch (NumberFormatException e) {
                        tipo = 0;
                    }
                    try {
                        maquina = request.getParameter("maquina");
                    } catch (Exception e) {
                        maquina = null;
                    }
                    try {
                        Usuario_registro = sesion.getAttribute("Nombres").toString();
                    } catch (Exception e) {
                        Usuario_registro = null;
                    }
                    result = jpa_herramental.ModificarHerramental(id_herramental, codigo, descripcion, tipo, maquina, Usuario_registro);
                    if (result) {
                        request.setAttribute("modificar_herramental", result);
                    }
                    request.getRequestDispatcher("Herramental?opc=1&id_herramental=0").forward(request, response);
                    break;
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="CASO 4 CAMBIAR ESTADO HERRAMENTAL">
                case 4:
                    try {
                        id_herramental = Integer.parseInt(request.getParameter("id_herramental").toString());
                    } catch (Exception e) {
                        id_herramental = 0;
                    }
                    try {
                        estado = Integer.parseInt(request.getParameter("estado").toString());
                    } catch (Exception e) {
                        estado = 0;
                    }
                    result = jpa_herramental.cambiarEstado_Herramental(id_herramental, estado);
                    if (result) {
                        request.setAttribute("cambiarEstado_herramental", result);
                    }
                    request.getRequestDispatcher("Herramental?opc=1&id_herramental=0").forward(request, response);
                    break;
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="CASOS">
//                case 5:
//                    filtro = request.getParameter("txt_bus");
//                    id_herramental = Integer.parseInt(request.getParameter("idH"));
//                    id_pendiente = Integer.parseInt(request.getParameter("idP"));
//                    id_movimiento = Integer.parseInt(request.getParameter("idMV"));
//                    solucion = Integer.parseInt(request.getParameter("idS"));
//                    request.setAttribute("Modulo_pendientes", 1);
//                    request.setAttribute("filtro", filtro);
//                    request.setAttribute("id_pendiente", id_pendiente);
//                    request.setAttribute("id_herramental", id_herramental);
//                    request.setAttribute("id_movimiento", id_movimiento);
//                    request.setAttribute("solucion", solucion);
//                    request.getRequestDispatcher("herramental.jsp").forward(request, response);
//                    break;
//                case 6:
//                    filtro = request.getParameter("txt_bus");
//                    id_herramental = Integer.parseInt(request.getParameter("idH"));
//                    fecha = request.getParameter("txt_fecha");
//                    descripcion = request.getParameter("txt_descripcionM");
//                    resultado = jpa_herramental.RegistroPendiente(id_herramental, fecha, descripcion, nombre_Usuario);
//                    if (resultado) {
//                        request.setAttribute("registro_pendiente", resultado);
//                    } else {
//                        request.setAttribute("registro_pendiente", resultado);
//                    }
//                    request.getRequestDispatcher("Herramental?opc=5&idH=" + id_herramental + "&idP=" + 0 + "&idMV=" + 0 + "&idS=" + 0 + "&txt_bus=" + filtro + "").forward(request, response);
//                    break;
//                case 7:
//                    filtro = request.getParameter("txt_bus");
//                    id_herramental = Integer.parseInt(request.getParameter("idH"));
//                    id_pendiente = Integer.parseInt(request.getParameter("idP"));
//                    fecha = request.getParameter("txt_fechaM");
//                    descripcion = request.getParameter("txt_descripcionM");
//                    resultado = jpa_herramental.ModificarPendiente(id_pendiente, fecha, descripcion);
//                    if (resultado) {
//                        request.setAttribute("modificar_pendiente", resultado);
//                    } else {
//                        request.setAttribute("modificar_pendiente", resultado);
//                    }
//                    request.getRequestDispatcher("Herramental?opc=5&idH=" + id_herramental + "&idP=" + 0 + "&idMV=" + 0 + "&idS=" + 0 + "&txt_bus=" + filtro + "").forward(request, response);
//                    break;
//                case 8:
//                    filtro = request.getParameter("txt_bus");
//                    id_herramental = Integer.parseInt(request.getParameter("idH"));
//                    id_pendiente = Integer.parseInt(request.getParameter("idP"));
//                    id_usuarios = request.getParameter("txt_usuarios");
//                    estado = Integer.parseInt(request.getParameter("est"));
//                    List lst_pendiente = jpa_herramental.ConsultaPendientesId(id_pendiente);
//                    jpa_herramental.EstadoPendienteHerramental(id_pendiente, estado);
//                    resultado = mail.mail_Pendiente_herramental(id_usuarios, lst_pendiente, nombre_Usuario);
//                    if (resultado) {
//                        request.setAttribute("Mail_pendiente_her", resultado);
//                    } else {
//                        request.setAttribute("Mail_pendiente_her", resultado);
//                    }
//                    request.setAttribute("estado", estado);
//                    request.getRequestDispatcher("Herramental?opc=5&idH=" + id_herramental + "&idP=" + 0 + "&idMV=" + 0 + "&idS=" + 0 + "&txt_bus=" + filtro + "").forward(request, response);
//                    break;
//                case 9:
//                    filtro = request.getParameter("txt_bus");
//                    id_herramental = Integer.parseInt(request.getParameter("idH"));
//                    id_pendiente = Integer.parseInt(request.getParameter("idP"));
//                    fecha = request.getParameter("txt_fecha");
//                    descripcion = request.getParameter("txt_solucion");
//                    resultado = jpa_herramental.SolucionPendienteHerramental(id_pendiente, fecha, descripcion);
//                    if (resultado) {
//                        request.setAttribute("solucionar_pendiente_herramental", resultado);
//                    } else {
//                        request.setAttribute("solucionar_pendiente_herramental", resultado);
//                    }
//                    request.getRequestDispatcher("Herramental?opc=5&idH=" + id_herramental + "&idP=" + 0 + "&idMV=" + 0 + "&idS=" + 0 + "&txt_bus=" + filtro + "").forward(request, response);
//                    break;
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
