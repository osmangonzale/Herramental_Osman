package Tags;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.util.List;
import controladores.FichaTecnicaJpaController;
import controladores.HerramentalJpaController;
import controladores.MaquinaJpaController;
import controladores.UsuarioJpaController;

public class Tag_ficha_tecnica extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        //<editor-fold defaultstate="collapsed" desc="SESION">
        HttpSession sesion = pageContext.getSession();
        String area = sesion.getAttribute("Area").toString();
        String nombres = sesion.getAttribute("Nombres").toString();
//</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="JPAS">
        FichaTecnicaJpaController fichaTecnicaJpa = new FichaTecnicaJpaController();
        HerramentalJpaController HerramentalJpa = new HerramentalJpaController();
        MaquinaJpaController MaquinaJpa = new MaquinaJpaController();
        UsuarioJpaController UsuarioJpa = new UsuarioJpaController();
//</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="VARIABLES GLOBALES">
        List lst_fichaTec = null;
        List lst_Herramentales = null;
        List lst_estados = null;
        List lst_usuarios = null;
        List lst_historial = null;
        List lst_pendientes = null;
        int id_fichaTec, temp, valorSeleccionado, cedula, codigo, estado = 0;
        boolean todosFirmados = true;
//</editor-fold>
        try {
            //<editor-fold defaultstate="collapsed" desc="VARIABLES DE CONTEXTO">
            try {
                id_fichaTec = Integer.parseInt(pageContext.getRequest().getAttribute("id_fichaTec").toString());
            } catch (NumberFormatException e) {
                id_fichaTec = 0;
            }
            try {
                estado = Integer.parseInt(pageContext.getRequest().getAttribute("estado").toString());
            } catch (NumberFormatException e) {
                estado = 0;
            }
            try {
                temp = Integer.parseInt(pageContext.getRequest().getAttribute("temp").toString());
            } catch (NumberFormatException e) {
                temp = 0;
            }
            try {
                valorSeleccionado = Integer.parseInt(pageContext.getRequest().getAttribute("newEstado").toString());
            } catch (NumberFormatException e) {
                valorSeleccionado = 0;
            }
            try {
                codigo = Integer.parseInt(pageContext.getRequest().getAttribute("codigo").toString());
            } catch (NumberFormatException e) {
                codigo = 0;
            }
            try {
                cedula = Integer.parseInt(pageContext.getRequest().getAttribute("cedula").toString());
            } catch (NumberFormatException e) {
                cedula = 0;
            }
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="CAMBIAR ESTADO DE FICHA">
            if (temp == 2) {
                lst_fichaTec = fichaTecnicaJpa.ConsultaFichaTecnica_id(id_fichaTec);
                if (lst_fichaTec != null) {
                    Object[] obj_ficha_t = (Object[]) lst_fichaTec.get(0);
                    out.print("<div class='sweet-local' tabindex='-1' id='Ventana3' style='opacity: 1.03; display:block;'>");
                    out.print("<div class='cont_reg2'>");
                    out.print("<div style='display: flex; justify-content: space-between'>");
                    out.print("<h2>Cambiar Estado " + obj_ficha_t[1].toString() + "</h2>");
                    out.print("<button class='btn btn-outline-secondary' onclick='mostrarConvencion(3)' style='height: 30px;padding: 3px;width: 30px;'><i class='fas fa-times'></i></button>");
                    out.print("</div>");
                    out.print("<div class='cont_form_user'>");
                    out.print("<form method='post' class='needs-validation' novalidate='' id='formcodigo'>");
                    out.print("<input type='hidden' name='id_fichaTec' value='" + obj_ficha_t[0] + "'>");

                    out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
                    out.print("<div class='col-lg-12' style='margin-top: 4%; margin-bottom: 4%;' data-toggle='tooltip' data-placemente='top' title='Seleccione Estado'>");
                    out.print("<select class='select2 form-control' name='newEstado' required style='width: 380px !important'>");
                    out.print("<option value='" + obj_ficha_t[5] + "'>" + obj_ficha_t[12].toString() + "</option>");
                    lst_estados = fichaTecnicaJpa.ConsultarParametros_xEstado("estado");
                    if (lst_estados != null && !lst_estados.isEmpty()) {
                        for (int i = 0; i < lst_estados.size(); i++) {
                            Object[] obj_estado = (Object[]) lst_estados.get(i);
                            out.print("<option value='" + obj_estado[2] + "'>" + obj_estado[3] + "</option>");
                        }
                    } else {
                        out.print("<option value='0'>Se ha producido un error</option>");
                    }
                    out.print("</select>");
                    out.print("</div>");
                    out.print("</div>");

                    out.print("<div class='' style='width: 100%; text-align:center;'>");
                    out.print("<button type='button' class='btn btn-secondary btn-lg' onclick='enviar(event)'>Editar</button>");
                    out.print("</div>");

                    out.print("</form>");

                    out.print("</div>");
                    out.print("</div>");
                    out.print("</div>");
                }
            }
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="FIRMAS CAMBIO ESTADO">
            if (valorSeleccionado == 2 || valorSeleccionado == 4) {
                lst_fichaTec = fichaTecnicaJpa.ConsultaFichaTecnica_id(id_fichaTec);
                if (lst_fichaTec != null) {
                    Object[] obj_ficha_t = (Object[]) lst_fichaTec.get(0);
                    out.print("<div class='sweet-local' tabindex='-1' id='Ventana4' style='opacity: 1.03; display:block;'>");
                    out.print("<div class='cont_reg2'>");
                    out.print("<div style='display: flex; justify-content: space-between'>");
                    out.print("<h2>Confirmar Cambio " + obj_ficha_t[1].toString() + "</h2>");
                    out.print("<button class='btn btn-outline-secondary' onclick='mostrarConvencion(4)' style='height: 30px;padding: 3px;width: 30px;'><i class='fas fa-times'></i></button>");
                    out.print("</div>");
                    out.print("<div class='cont_form_user'>");
                    out.print("<form action='Ficha_tecnica?opc=5&id_fichaTec=" + obj_ficha_t[0] + "' method='post' class='needs-validation' novalidate=''>");
                    out.print("<input type='hidden' name='newEstado' value='" + valorSeleccionado + "'>");
                    out.print("<input type='hidden' name='contador' value='" + obj_ficha_t[13] + "'>");

                    out.print("<table class='table'>");
                    out.print("<tbody style='font-size: 11px;'>");

                    out.print("<tr>");
                    out.print("<td>Firma Insumos</td>");
                    if (cedula == 0 || codigo == 0) {
                        out.print("<td align='center'>");
                        out.print("<span>Firmar</span>");
                        out.print("</td>");
                        todosFirmados = false;
                    } else if (obj_ficha_t[6] != null) {
                        out.print("<td align='center'>");
                        out.print("<span>" + obj_ficha_t[6] + "</span>");
                        out.print("</td>");
                    } else {
                        lst_usuarios = UsuarioJpa.Firma(cedula, codigo);
                        if (lst_usuarios != null) {
                            Object[] obj_usuario = (Object[]) lst_usuarios.get(0);
                            for (int i = 0; i < lst_usuarios.size(); i++) {
                                if (obj_usuario[6].equals("PRODUCCION INSUMOS") || obj_usuario[7].toString().contains("AJUSTADOR")) {
                                    out.print("<td align='center'>");
                                    out.print("<span>" + obj_usuario[2] + "</span>");
                                    out.print("</td>");
                                    out.print("<input type='hidden' name='id_area1' value='" + obj_usuario[5] + "'>");
                                    out.print("<input type='hidden' name='firma1' value='" + obj_usuario[2] + "'>");
                                } else {
                                    out.print("<td align='center'>");
                                    out.print("<span>Firmar</span>");
                                    out.print("</td>");
                                    todosFirmados = false;
                                }
                            }
                        } else {
                            out.print("<td align='center'>");
                            out.print("<span>El usuario ingresado no pertenece a ninguna de las areas solicitadas</span>");
                            out.print("</td>");
                            todosFirmados = false;
                        }
                    }
                    out.print("</tr>");

                    out.print("<tr>");
                    out.print("<td>Firma Calidad</td>");
                    if (cedula == 0 || codigo == 0) {
                        out.print("<td align='center'>");
                        out.print("<span>Firmar</span>");
                        out.print("</td>");
                        todosFirmados = false;
                    } else if (obj_ficha_t[7] != null) {
                        out.print("<td align='center'>");
                        out.print("<span>" + obj_ficha_t[7] + "</span>");
                        out.print("</td>");
                    } else {
                        lst_usuarios = UsuarioJpa.Firma(cedula, codigo);
                        if (lst_usuarios != null) {
                            Object[] obj_usuario = (Object[]) lst_usuarios.get(0);
                            for (int i = 0; i < lst_usuarios.size(); i++) {
                                if (obj_usuario[6].equals("GESTION DE CALIDAD") || obj_usuario[7].toString().contains("INSPECTORA")) {
                                    out.print("<td align='center'>");
                                    out.print("<span>" + obj_usuario[2] + "</span>");
                                    out.print("</td>");
                                    out.print("<input type='hidden' name='id_area2' value='" + obj_usuario[5] + "'>");
                                    out.print("<input type='hidden' name='firma2' value='" + obj_usuario[2] + "'>");
                                } else {
                                    out.print("<td align='center'>");
                                    out.print("<span>Firmar</span>");
                                    out.print("</td>");
                                    todosFirmados = false;
                                }
                            }
                        } else {
                            out.print("<td align='center'>");
                            out.print("<span>El usuario ingresado no pertenece a ninguna de las areas solicitadas</span>");
                            out.print("</td>");
                            todosFirmados = false;
                        }
                    }
                    out.print("</tr>");

                    out.print("<tr>");
                    out.print("<td>Firma Poyectos</td>");
                    if (cedula == 0 || codigo == 0) {
                        out.print("<td align='center'>");
                        out.print("<span >Firmar</span>");
                        out.print("</td>");
                        todosFirmados = false;
                    } else if (obj_ficha_t[8] != null) {
                        out.print("<td align='center'>");
                        out.print("<span>" + obj_ficha_t[8] + "</span>");
                        out.print("</td>");
                    } else {
                        lst_usuarios = UsuarioJpa.Firma(cedula, codigo);
                        if (lst_usuarios != null) {
                            Object[] obj_usuario = (Object[]) lst_usuarios.get(0);
                            for (int i = 0; i < lst_usuarios.size(); i++) {
                                if ((obj_usuario[6].equals("PROYECTOS") || obj_usuario[7].toString().contains("COORDINADOR"))) {
                                    out.print("<td align='center'>");
                                    out.print("<span>" + obj_usuario[2] + "</span>");
                                    out.print("</td>");
                                    out.print("<input type='hidden' name='id_area3' value='" + obj_usuario[5] + "'>");
                                    out.print("<input type='hidden' name='firma3' value='" + obj_usuario[2] + "'>");
                                } else {
                                    out.print("<td align='center'>");
                                    out.print("<span>Firmar</span>");
                                    out.print("</td>");
                                    todosFirmados = false;
                                }
                            }
                        } else {
                            out.print("<td align='center'>");
                            out.print("<span>El usuario ingresado no pertenece a ninguna de las areas solicitadas</span>");
                            out.print("</td>");
                            todosFirmados = false;
                        }
                    }
                    out.print("</tr>");

                    out.print("</tbody>");
                    out.print("</table>");

                    if (todosFirmados) {
                        out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
                        out.print("<div class='col-12'>");
                        out.print("<input type='number' class='form-control' name='codigo' id='codigo' placeholder='Codigo' readonly data-toggle='tooltip' data-placement='top' title='Codigo'>");
                        out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                        out.print("</div>");

                        out.print("<div class='col-12'>");
                        out.print("<input type='number' class='form-control' name='cedula' id='cedula' placeholder='Cedula' readonly data-toggle='tooltip' data-placement='top' title='Cedula'>");
                        out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                        out.print("</div>");
                        out.print("</div>");
                    } else {
                        out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
                        out.print("<div class='col-12'>");
                        out.print("<input type='number' class='form-control' name='codigo' id='codigo' placeholder='Codigo' required data-toggle='tooltip' data-placement='top' title='Codigo'>");
                        out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                        out.print("</div>");

                        out.print("<div class='col-12'>");
                        out.print("<input type='number' class='form-control' name='cedula' id='cedula' placeholder='Cedula' required data-toggle='tooltip' data-placement='top' title='Cedula'>");
                        out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                        out.print("</div>");
                        out.print("</div>");
                    }

                    out.print("<input type='hidden' name='todosFirmados' value='" + todosFirmados + "'>");

                    if (todosFirmados) {
                        out.print("<div class='' style='width: 100%; text-align:center;'>");
                        out.print("<button class='btn btn-secondary btn-lg'>Guardar</button>");
                        out.print("</div>");
                    } else {
                        out.print("<div class='' style='width: 100%; text-align:center;'>");
                        out.print("<button class='btn btn-secondary btn-lg'>Firmar</button>");
                        out.print("</div>");
                    }

                    out.print("</form>");
                    out.print("</div>");
                    out.print("</div>");
                    out.print("</div>");
                }
            }
//</editor-fold>           
            //<editor-fold defaultstate="collapsed" desc="MODIFICAR FICHA TECNICA">
            if (temp == 1) {
                lst_fichaTec = fichaTecnicaJpa.ConsultaFichaTecnica_id(id_fichaTec);
                if (lst_fichaTec != null) {
                    Object[] obj_ficha_t = (Object[]) lst_fichaTec.get(0);
                    out.print("<div class='sweet-local' tabindex='-1' id='Ventana2' style='opacity: 1.03; display:block;'>");
                    out.print("<div class='cont_reg'>");
                    out.print("<div style='display: flex; justify-content: space-between'>");
                    out.print("<h2>Editar Ficha Tecnica " + obj_ficha_t[1].toString() + "</h2>");
                    out.print("<button class='btn btn-outline-secondary' onclick='mostrarConvencion(2)' style='height: 30px;padding: 3px;width: 30px;'><i class='fas fa-times'></i></button>");
                    out.print("</div>");
                    out.print("<div class='cont_form_user'>");
                    out.print("<form action='Ficha_tecnica?opc=3&id_fichaTec=" + obj_ficha_t[0] + "' method='post' class='needs-validation' novalidate=''>");

                    out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
                    out.print("<div class='col-12'>");
                    out.print("<input type='text' class='form-control' name='referencia' placeholder='Referencia Ficha' value='" + obj_ficha_t[1] + "' required data-toggle='tooltip' data-placemente='top' title='Referencia Ficha'>");
                    out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                    out.print("</div>");

                    out.print("<div class='col-12'>");
                    out.print("<input type='number' class='form-control' name='version' placeholder='Version' value='" + obj_ficha_t[11] + "' required data-toggle='tooltip' data-placemente='top' title='Version'>");
                    out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                    out.print("</div>");
                    out.print("</div>");

                    out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
                    out.print("<div class='col-12'>");
                    out.print("<input type='text' class='form-control' name='nombre' placeholder='Nombre' value='" + obj_ficha_t[4] + "' required data-toggle='tooltip' data-placemente='top' title='Nombre'>");
                    out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                    out.print("</div>");

                    out.print("<div class='col-lg-12' style='margin-top:2.9%; margin-bottom: 2.9%; margin-left:3%; margin-right:-3%;' data-toggle='tooltip' data-placement='top' title='Seleccione Molde'>");
                    out.print("<select id='selectHerramental' class='select2 form-control' name='moldes' multiple='' required onchange='actualizarHerramenSeleccionadas()'>");
                    lst_Herramentales = HerramentalJpa.ConsultaHerramentales();
                    if (lst_Herramentales != null && lst_Herramentales.size() != 0) {
                        for (int i = 0; i < lst_Herramentales.size(); i++) {
                            Object[] obj_herramental = (Object[]) lst_Herramentales.get(i);
                            String selected = obj_ficha_t[2].toString().contains("[" + obj_herramental[0].toString() + "]") ? "selected" : "";
                            out.print("<option value='" + obj_herramental[0] + "' " + selected + ">" + obj_herramental[5] + "</option>");
                        }
                    } else {
                        out.print("<option value='0'>Se ha producido un error</option>");
                    }
                    out.print("</select>");
                    out.print("<input type='hidden' id='id_herramental' name='herramental' value=''>");
                    out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                    out.print("</div>");
                    out.print("</div>");

                    out.print("<div class='' style='width: 100%; text-align:center;'>");
                    out.print("<button class='btn btn-secondary btn-lg'>Editar</button>");
                    out.print("</div>");

                    out.print("</form>");
                    out.print("</div>");
                    out.print("</div>");
                    out.print("</div>");
                }
            }
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="REGISTRAR FICHA TECNICA">
            out.print("<div class='sweet-local' tabindex='-1' id='Ventana1' style='opacity: 1.03; display:none;'>");
            out.print("<div class='cont_reg'>");
            out.print("<div style='display: flex; justify-content: space-between'>");
            out.print("<h2>Registrar Ficha Tecnica</h2>");
            out.print("<button class='btn btn-outline-secondary' onclick='mostrarConvencion(1)' style='height: 30px;padding: 3px;width: 30px;'><i class='fas fa-times'></i></button>");
            out.print("</div>");
            out.print("<div class='cont_form_user'>");
            out.print("<form action='Ficha_tecnica?opc=2' method='post' class='needs-validation' novalidate=''>");

            out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
            out.print("<div class='col-12'>");
            out.print("<input type='text' class='form-control' name='referencia' placeholder='Referencia Ficha' required='' data-toggle='tooltip' data-placemente='top' title='Referencia Ficha'>");
            out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
            out.print("</div>");

            out.print("<div class='col-12'>");
            out.print("<input type='number' class='form-control' name='version' placeholder='Version' required='' data-toggle='tooltip' data-placemente='top' title='Version'>");
            out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
            out.print("</div>");
            out.print("</div>");

            out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
            out.print("<div class='col-12'>");
            out.print("<input type='text' class='form-control' name='nombre' placeholder='Nombre' required='' data-toggle='tooltip' data-placemente='top' title='Nombre'>");
            out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
            out.print("</div>");

            out.print("<div class='col-lg-12' style='margin-top:2.9%; margin-bottom: 2.9%; margin-left:3%; margin-right:-3%;' data-toggle='tooltip' data-placement='top' title='Seleccione Molde'>");
            out.print("<select id='selectHerramental' class='select2 form-control' name='moldes' multiple='' required onchange='actualizarHerramenSeleccionadas()'>");
            out.print("<option value='' disabled selected>N/A</option>");
            lst_Herramentales = HerramentalJpa.ConsultaHerramentales();
            if (lst_Herramentales != null && lst_Herramentales.size() != 0) {
                for (int i = 0; i < lst_Herramentales.size(); i++) {
                    Object[] obj_herramental = (Object[]) lst_Herramentales.get(i);
                    out.print("<option value='" + obj_herramental[0] + "'>" + obj_herramental[5] + "</option>");
                }
            } else {
                out.print("<option value='0'>Se ha producido un error</option>");
            }
            out.print("</select>");
            out.print("<input type='hidden' id='id_herramental' name='herramental' value=''>");
            out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
            out.print("</div>");
            out.print("</div>");

            out.print("<div class='' style='width: 100%; text-align:center;'>");
            out.print("<button class='btn btn-secondary btn-lg'>Registrar</button>");
            out.print("</div>");

            out.print("</form>");
            out.print("</div>");
            out.print("</div>");
            out.print("</div>");
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="PENDIENTES FICHA TECNICA">
            if (temp == 4) {
                lst_fichaTec = fichaTecnicaJpa.ConsultaFichaTecnica_id(id_fichaTec);
                if (lst_fichaTec != null) {
                    Object[] obj_ficha_t = (Object[]) lst_fichaTec.get(0);
                    out.print("<section class='section'>");
                    out.print("<div class='section-header'>");
                    out.print("<h1>Pendientes Ficha Tecnica de Proceso " + obj_ficha_t[4].toString() + "</h1>");
                    out.print("</div>");
                    out.print("<div class='section-body'>");
                    out.print("<div class='row'>");
                    out.print("<div class='col-12'>");
                    out.print("<div class='card'>");
                    out.print("<div class='card-header' style='justify-content: space-between;'>");
                    lst_pendientes = fichaTecnicaJpa.Consultar_Pendiente_FichaT_Id(id_fichaTec);
                    if (lst_pendientes.size() > 0) {
                        out.print("<button class='btn btn-secondary' style='border-radius: 4px; margin-right: 0%;' onclick=\"javascript:location.href='Ficha_tecnica?opc=1'\" data-toggle='tooltip' data-placement='top' title='Volver a Ficha Tecnica'><i class='fas fa-arrow-left'></i></button>");
                        out.print("<h4>Tabla de Pendientes Ficha Tecnica de Proceso</h4>");
                        out.print("<button class='btn btn-secondary' style='border-radius: 4px;' onclick='mostrarConvencion(4)' data-toggle='tooltip' data-placement='top' title='Registrar Pendiente'><i class='fas fa-plus'></i></button>");
                    } else {
                        out.print("<button class='btn btn-secondary' style='border-radius: 4px; margin-right: 0%;' onclick=\"javascript:location.href='Ficha_tecnica?opc=1'\" data-toggle='tooltip' data-placement='top' title='Volver a Ficha Tecnica'><i class='fas fa-arrow-left'></i></button>");
                        out.print("<h4 style='margin-right: 39%;'>Tabla de Pendientes Ficha Tecnica de Proceso</h4>");
                    }
                    out.print("</div>");
                    out.print("<div class='card-body'>");
                    out.print("<div class='table-responsive'>");
                    out.print("<table class='table table-hover table-bordered' id='table-2'>");
                    out.print("<thead>");
                    out.print("<tr>");
                    out.print("<th>Fecha Registro</th>");
                    out.print("<th>Causas</th>");
                    out.print("<th>Sugerencias</th>");
                    out.print("<th>Fecha Solucion</th>");
                    out.print("<th>Solucion</th>");
                    out.print("<th>Usuario Registro</th>");
                    out.print("</tr>");
                    out.print("</thead>");
                    out.print("<tbody>");
                    lst_pendientes = fichaTecnicaJpa.Consultar_Pendiente_FichaT_Id(id_fichaTec);
                    if (lst_pendientes.size() > 0) {
                        for (int i = 0; i < lst_pendientes.size(); i++) {
                            Object[] obj_pendiente = (Object[]) lst_pendientes.get(i);
                            out.print("<tr>");
                            out.print("<td>" + obj_pendiente[2] + "</td>");
                            out.print("<td>" + obj_pendiente[3] + "</td>");
                            out.print("<td>" + obj_pendiente[3] + "</td>");
                            out.print("<td>" + obj_pendiente[7] + "</td>");
                            out.print("<td>" + obj_pendiente[8] + "</td>");
                            out.print("<td>" + obj_pendiente[5] + "</td>");
                            out.print("</tr>");
                        }
                    } else {
                        out.print("<tr>");
                        out.print("<td colspan='6' align='center'>");
                        out.print("<h1 style='text-align: center;'><i class='fas fa-frown fa-lg' style='font-size: 100%;'></i> ¡ No se han encontrado resultados !</h1>");
                        out.print("</td>");
                        out.print("</tr>");
                    }
                } else {
                    out.print("<tr>");
                    out.print("<td colspan='6' align='center'>");
                    out.print("<h1 style='text-align: center;'><i class='fas fa-frown fa-lg' style='font-size: 100%;'></i> ¡ No se han encontrado resultados !</h1>");
                    out.print("</td>");
                    out.print("</tr>");
                }
                out.print("</tbody>");
                out.print("</table>");
                out.print("</div>");
                out.print("</div>");
                out.print("</div>");
                out.print("</div>");
                out.print("</div>");
                out.print("</div>");
                out.print("</section>");
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="HISTORIAL DE FICHA TECNICA">
            } else if (temp == 3) {
                lst_fichaTec = fichaTecnicaJpa.ConsultaFichaTecnica_id(id_fichaTec);
                if (lst_fichaTec != null) {
                    Object[] obj_ficha_t = (Object[]) lst_fichaTec.get(0);
                    out.print("<section class='section'>");
                    out.print("<div class='section-header'>");
                    out.print("<h1>Historial Ficha Tecnica de Proceso " + obj_ficha_t[4].toString() + "</h1>");
                    out.print("</div>");
                    out.print("<div class='section-body'>");
                    out.print("<div class='row'>");
                    out.print("<div class='col-12'>");
                    out.print("<div class='card'>");
                    out.print("<div class='card-header' style='justify-content: space-between;'>");
                    lst_historial = fichaTecnicaJpa.consultar_FichaTecnica_log(id_fichaTec);
                    if (lst_historial.size() > 0) {
                        out.print("<button class='btn btn-secondary' style='border-radius: 4px; margin-right: 0%;' onclick=\"javascript:location.href='Ficha_tecnica?opc=1'\" data-toggle='tooltip' data-placement='top' title='Volver a Ficha Tecnica'><i class='fas fa-arrow-left'></i></button>");
                        out.print("<h4 style='margin-right: 42%;'>Tabla de Historial Tecnica de Proceso</h4>");
                    } else {
                        out.print("<button class='btn btn-secondary' style='border-radius: 4px; margin-right: 0%;' onclick=\"javascript:location.href='Ficha_tecnica?opc=1'\" data-toggle='tooltip' data-placement='top' title='Volver a Ficha Tecnica'><i class='fas fa-arrow-left'></i></button>");
                        out.print("<h4 style='margin-right: 42%;'>Tabla de Historial Tecnica de Proceso</h4>");
                    }
                    out.print("</div>");
                    out.print("<div class='card-body'>");
                    out.print("<div class='table-responsive'>");
                    out.print("<table class='table table-hover table-bordered' id='table-2'>");
                    out.print("<thead>");
                    out.print("<tr>");
                    out.print("<th>Referencia</th>");
                    out.print("<th>Version</th>");
                    out.print("<th>Molde</th>");
                    out.print("<th>Nombre</th>");
                    out.print("<th>Estado</th>");
                    out.print("<th>Usuario Registro</th>");
                    out.print("<th>Fecha Actualizacion</th>");
                    out.print("</tr>");
                    out.print("</thead>");
                    out.print("<tbody>");
                    lst_historial = fichaTecnicaJpa.consultar_FichaTecnica_log(id_fichaTec);
                    if (lst_historial.size() > 0) {
                        for (int i = 0; i < lst_historial.size(); i++) {
                            Object[] obj_ficha_t_l = (Object[]) lst_historial.get(i);
                            out.print("<tr>");
                            out.print("<td>" + obj_ficha_t_l[3] + "</td>");
                            out.print("<td align='center'>" + obj_ficha_t_l[12] + "</td>");
                            out.print("<td align='center'>" + obj_ficha_t_l[4] + "</td>");
                            out.print("<td>" + obj_ficha_t_l[5] + "</td>");
                            out.print("<td align='center'>" + obj_ficha_t_l[6] + "</td>");
                            out.print("<td>" + obj_ficha_t_l[10] + "</td>");
                            out.print("<td>" + obj_ficha_t_l[11] + "</td>");
                            out.print("</tr>");
                        }
                    } else {
                        out.print("<tr>");
                        out.print("<td colspan='7' align='center'>");
                        out.print("<h1 style='text-align: center;'><i class='fas fa-frown fa-lg' style='font-size: 100%;'></i> ¡ No se han encontrado resultados !</h1>");
                        out.print("</td>");
                        out.print("</tr>");
                    }
                } else {
                    out.print("<tr>");
                    out.print("<td colspan='7' align='center'>");
                    out.print("<h1 style='text-align: center;'><i class='fas fa-frown fa-lg' style='font-size: 100%;'></i> ¡ No se han encontrado resultados !</h1>");
                    out.print("</td>");
                    out.print("</tr>");
                }
                out.print("</tbody>");
                out.print("</table>");
                out.print("</div>");
                out.print("</div>");
                out.print("</div>");
                out.print("</div>");
                out.print("</div>");
                out.print("</div>");
                out.print("</section>");
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="TABLA FICHA TECNICA">
            } else {
                out.print("<section class='section'>");
                out.print("<div class='section-header'>");
                out.print("<h1>Modulo Ficha Tecnica de Proceso</h1>");
                out.print("</div>");
                out.print("<div class='section-body'>");
                out.print("<div class='row'>");
                out.print("<div class='col-12'>");
                out.print("<div class='card'>");
                out.print("<div class='card-header' style='justify-content: space-between;'>");
                out.print("<h4>Tabla de Ficha Tecnica de Proceso</h4>");
                //<editor-fold defaultstate="collapsed" desc="FILTRO FICHAS ESTADO">
                if (estado == 5 || estado == 0) {
                    out.print("<div class='status-tag informative-medium' id='status-todas' style='text-align: center; height: 30px;'>");
                    out.print("<i class='highlight informative-medium' style='--iteration-count: infinite'></i>");
                    out.print("<p class='status-tag__txt bac-l-stack-xs' style='margin: 0; line-height: 30px;'>Todas</p>");
                    out.print("</div>");
                } else {
                    out.print("<div class='status-tag informative-medium' id='status-todas-active' onclick='toggleStatus(5)'style='text-align: center; height: 30px;'>");
                    out.print("<p class='status-tag__txt bac-l-stack-xs' style='margin: 0; line-height: 30px;'>Todas</p>");
                    out.print("</div>");
                }

                if (estado == 1) {
                    out.print("<div class='status-tag positive-medium' id='status-vigente' style='text-align: center; height: 30px;'>");
                    out.print("<i class='highlight positive-medium' style='--iteration-count: infinite'></i>");
                    out.print("<p class='status-tag__txt bac-l-stack-xs' style='margin: 0; line-height: 30px;'>Vigente</p>");
                    out.print("</div>");
                } else if (estado != 1) {
                    out.print("<div class='status-tag positive-medium' id='status-vigente-active' onclick='toggleStatus(1)' style='text-align: center; height: 30px;'>");
                    out.print("<p class='status-tag__txt bac-l-stack-xs' style='margin: 0; line-height: 30px;'>Vigente</p>");
                    out.print("</div>");
                }

                if (estado == 2) {
                    out.print("<div class='status-tag attention-low' id='status-proceso' style='text-align: center; height: 30px;'>");
                    out.print("<i class='highlight attention-low' style='--iteration-count: infinite'></i>");
                    out.print("<p class='status-tag__txt bac-l-stack-xs' style='margin: 0; line-height: 30px;'>Proceso</p>");
                    out.print("</div>");
                } else if (estado != 2) {
                    out.print("<div class='status-tag attention-low' id='status-proceso-active' onclick='toggleStatus(2)' style='text-align: center; height: 30px;'>");
                    out.print("<p class='status-tag__txt bac-l-stack-xs' style='margin: 0; line-height: 30px;'>Proceso</p>");
                    out.print("</div>");
                }

                if (estado == 3) {
                    out.print("<div class='status-tag critical-low' id='status-inactivo' style='text-align: center; height: 30px;'>");
                    out.print("<i class='highlight critical-low' style='--iteration-count: infinite'></i>");
                    out.print("<p class='status-tag__txt bac-l-stack-xs' style='margin: 0; line-height: 30px;'>Inactivo</p>");
                    out.print("</div>");
                } else if (estado != 3) {
                    out.print("<div class='status-tag critical-low' id='status-inactivo-active' onclick='toggleStatus(3)' style='text-align: center; height: 30px;'>");
                    out.print("<p class='status-tag__txt bac-l-stack-xs' style='margin: 0; line-height: 30px;'>Inactivo</p>");
                    out.print("</div>");
                }

                if (estado == 4) {
                    out.print("<div class='status-tag neutral-extrahigh' id='status-obsoleto' style='text-align: center; height: 30px;'>");
                    out.print("<i class='highlight neutral-extrahigh' style='--iteration-count: infinite'></i>");
                    out.print("<p class='status-tag__txt bac-l-stack-xs' style='margin: 0; line-height: 30px;'>Obsoleto</p>");
                    out.print("</div>");
                } else if (estado != 4) {
                    out.print("<div class='status-tag neutral-extrahigh' id='status-obsoleto-active' onclick='toggleStatus(4)' style='text-align: center; height: 30px;'>");
                    out.print("<p class='status-tag__txt bac-l-stack-xs' style='margin: 0; line-height: 30px;'>Obsoleto</p>");
                    out.print("</div>");
                }
//</editor-fold>
                out.print("<button class='btn btn-secondary' style='border-radius: 4px;' onclick='mostrarConvencion(1)' data-toggle='tooltip' data-placement='top' title='Registrar Ficha Tecnica'><i class='fas fa-plus'></i></button>");
                out.print("</div>");
                out.print("<div class='card-body'>");
                out.print("<div class='table-responsive'>");
                out.print("<table class='table table-hover table-bordered' id='table-1'>");
                out.print("<thead>");
                out.print("<tr>");
                out.print("<th>Referencia</th>");
                out.print("<th>Version</th>");
                out.print("<th>Molde</th>");
                out.print("<th>Nombre</th>");
                out.print("<th>Estado</th>");
                out.print("<th>Modificar</th>");
                out.print("<th>Historial</th>");
                out.print("<th>Pendientes</th>");
                out.print("</tr>");
                out.print("</thead>");
                out.print("<tbody>");
                if (estado != 0) {
                    lst_fichaTec = fichaTecnicaJpa.ConsultaEstado_FichaTecnica(estado);
                } else {
                    lst_fichaTec = fichaTecnicaJpa.ConsultaFichaTecnica();
                }
                if (lst_fichaTec.size() > 0) {
                    for (int i = 0; i < lst_fichaTec.size(); i++) {
                        Object[] obj_ficha_t = (Object[]) lst_fichaTec.get(i);
                        out.print("<tr>");
                        out.print("<td>" + obj_ficha_t[1] + "</td>");
                        out.print("<td align='center'>" + obj_ficha_t[11] + "</td>");
                        out.print("<td align='center'>" + obj_ficha_t[3] + "</td>");
                        out.print("<td>" + obj_ficha_t[4] + "</td>");
                        out.print("<td align='center'>"
                                + (((Integer) obj_ficha_t[5] == 1) ? "<div class='status-tag positive-medium' onclick=\"javascript:location.href='Ficha_tecnica?opc=1&temp=2&id_fichaTec=" + obj_ficha_t[0] + "'\"><p class='status-tag__txt bac-l-stack-xs'>Vigente</p></div>"
                                        : (((Integer) obj_ficha_t[5] == 2) ? "<div class='status-tag attention-low' onclick=\"javascript:location.href='Ficha_tecnica?opc=1&temp=2&id_fichaTec=" + obj_ficha_t[0] + "'\"><p class='status-tag__txt bac-l-stack-xs'>Proceso</p></div>"
                                                : (((Integer) obj_ficha_t[5] == 3) ? "<div class='status-tag critical-low' onclick=\"javascript:location.href='Ficha_tecnica?opc=1&temp=2&id_fichaTec=" + obj_ficha_t[0] + "'\"><p class='status-tag__txt bac-l-stack-xs'>Inactivo</p></div>"
                                                        : (((Integer) obj_ficha_t[5] == 4) ? "<div class='status-tag neutral-extrahigh' onclick=\"javascript:location.href='Ficha_tecnica?opc=1&temp=2&id_fichaTec=" + obj_ficha_t[0] + "'\"><p class='status-tag__txt bac-l-stack-xs'>Obsoleto</p></div>" : ""))))
                                + "</td>");
                        out.print("<td align='center'>");
                        out.print("<button type='button' onclick=\"javascript:location.href='Ficha_tecnica?opc=1&temp=1&id_fichaTec=" + obj_ficha_t[0] + "'\" class='btn btn-primary btn-sm btn-icon' data-toggle='tooltip' data-placement='top' title='Modificar Ficha'><i class='fas fa-pencil-alt'></i></button>");
                        out.print("</td>");
                        out.print("<td align='center'>");
                        out.print("<button type='button' onclick=\"javascript:location.href='Ficha_tecnica?opc=1&temp=3&id_fichaTec=" + obj_ficha_t[0] + "'\" class='btn btn-warning btn-sm btn-icon' data-toggle='tooltip' data-placement='top' title='Historial'><i class='fas fa-heading'></i></button>");
                        out.print("</td>");
                        out.print("<td align='center'>");
                        out.print("<button type='button' onclick=\"javascript:location.href='Ficha_tecnica?opc=1&temp=4&id_fichaTec=" + obj_ficha_t[0] + "'\" class='btn btn-dark btn-sm btn-icon' data-toggle='tooltip' data-placement='top' title='Pendientes'><i class='fas fa-tasks'></i></button>");
                        out.print("</td>");
                        out.print("</tr>");
                    }
                } else {
                    out.print("<tr>");
                    out.print("<td colspan='9' align='center'>");
                    out.print("<h1 style='text-align: center;'>No se han encontrado resultados!</h1>");
                    out.print("</td>");
                    out.print("</tr>");
                }
                out.print("</tbody>");
                out.print("</table>");
                out.print("</div>");
                out.print("</div>");
                out.print("</div>");
                out.print("</div>");
                out.print("</div>");
                out.print("</div>");
                out.print("</section>");
            }
//</editor-fold>

        } catch (IOException ex) {
            Logger.getLogger(Tag_ficha_tecnica.class.getName()).log(Level.SEVERE, null, ex);
        }
        return super.doStartTag();
    }
}
