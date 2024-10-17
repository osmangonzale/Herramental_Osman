package Servlets;

import controladores.DefectoJpaController;
import controladores.FamiliaProductoJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Familia_producto extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        try {
            //<editor-fold defaultstate="collapsed" desc="SESION">
            HttpSession sesion = request.getSession();
            String usu_registro = sesion.getAttribute("Nombres").toString();
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="JPAS">
            FamiliaProductoJpaController jpafamilia = new FamiliaProductoJpaController();
            DefectoJpaController jpadefecto = new DefectoJpaController();
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="VARIABLES GLOBALES">
            String nombre, familia, producto, descripcion, codigo = "";
            int tipomaquina, id_familia, estado, clasificacionD, temp = 0;
            boolean result = false;
            int opc = Integer.parseInt(request.getParameter("opc"));
//</editor-fold>
            switch (opc) {
                //<editor-fold defaultstate="collapsed" desc="CASO 1 ATRIBUTOS">
                case 1:
                    try {
                        id_familia = Integer.parseInt(request.getParameter("id_familia"));
                    } catch (NumberFormatException e) {
                        id_familia = 0;
                    }
                    try {
                        temp = Integer.parseInt(request.getParameter("temp"));
                    } catch (NumberFormatException e) {
                        temp = 0;
                    }
                    try {
                        familia = request.getParameter("familia").toString();
                    } catch (Exception e) {
                        familia = "";
                    }
                    try {
                        producto = request.getParameter("producto").toString();
                    } catch (Exception e) {
                        producto = "";
                    }
                    request.setAttribute("id_familia", id_familia);
                    request.setAttribute("temp", temp);
                    request.setAttribute("familia", familia);
                    request.setAttribute("producto", producto);
                    request.getRequestDispatcher("familia_producto.jsp").forward(request, response);
                    break;
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="CASO 2 REGISTRAR FAMILIA">
                case 2:
                    try {
                        nombre = request.getParameter("nombre");
                    } catch (Exception e) {
                        nombre = "";
                    }
                    try {
                        tipomaquina = Integer.parseInt(request.getParameter("tipomaquina"));
                    } catch (NumberFormatException e) {
                        tipomaquina = 0;
                    }
                    try {
                        usu_registro = sesion.getAttribute("Nombres").toString();
                    } catch (Exception e) {
                        usu_registro = "";
                    }
                    result = jpafamilia.RegistroFamiliaProducto(nombre, tipomaquina, usu_registro);
                    if (result) {
                        request.setAttribute("Registrar_familia", result);
                    }
                    request.getRequestDispatcher("Familia_producto?opc=1").forward(request, response);
                    break;
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="CASO 3 MODIFICAR">
                case 3:
                    try {
                        id_familia = Integer.parseInt(request.getParameter("id_familia"));
                    } catch (NumberFormatException e) {
                        id_familia = 0;
                    }
                    try {
                        nombre = request.getParameter("nombre");
                    } catch (Exception e) {
                        nombre = "";
                    }
                    try {
                        tipomaquina = Integer.parseInt(request.getParameter("tipomaquina"));
                    } catch (NumberFormatException e) {
                        tipomaquina = 0;
                    }
                    result = jpafamilia.ModificarFamiliaProducto(id_familia, nombre, tipomaquina);
                    if (result) {
                        request.setAttribute("modificar_familia", result);
                    }
                    request.getRequestDispatcher("Familia_producto?opc=1&temp=0").forward(request, response);
                    break;
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="CASO 4 CAMBIAR ESTADO">
                case 4:
                    try {
                        id_familia = Integer.parseInt(request.getParameter("id_familia"));
                    } catch (NumberFormatException e) {
                        id_familia = 0;
                    }
                    try {
                        estado = Integer.parseInt(request.getParameter("estado"));
                    } catch (NumberFormatException e) {
                        estado = 0;
                    }
                    result = jpafamilia.ModificarFamiliaProductoEstado(id_familia, estado);
                    if (result) {
                        request.setAttribute("cambio_estado_familia", result);
                    }
                    request.getRequestDispatcher("Familia_producto?opc=1").forward(request, response);
                    break;
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="CASO 5 REGISTRAR DEFECTO">
                case 5:
                    try {
                        id_familia = Integer.parseInt(request.getParameter("id_familia"));
                    } catch (NumberFormatException e) {
                        id_familia = 0;
                    }
                    try {
                        codigo = request.getParameter("codigo");
                    } catch (Exception e) {
                        codigo = "";
                    }
                    try {
                        nombre = request.getParameter("nombre");
                    } catch (Exception e) {
                        nombre = "";
                    }
                    try {
                        clasificacionD = Integer.parseInt(request.getParameter("clasificacionD"));
                    } catch (NumberFormatException e) {
                        clasificacionD = 0;
                    }
                    try {
                        descripcion = request.getParameter("descripcion");
                    } catch (Exception e) {
                        descripcion = "";
                    }
                    try {
                        usu_registro = sesion.getAttribute("Nombres").toString();
                    } catch (Exception e) {
                        usu_registro = "";
                    }
                    result = jpadefecto.RegistroDefecto(id_familia, codigo, nombre, clasificacionD, descripcion, usu_registro);
                    if (result) {
                        request.setAttribute("Registrar_defecto", result);
                    }
                    request.getRequestDispatcher("Familia_producto?opc=1&temp=2").forward(request, response);
                    break;
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
