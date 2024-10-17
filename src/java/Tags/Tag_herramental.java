package Tags;

import controladores.HerramentalJpaController;
import controladores.MaquinaJpaController;
import controladores.MovimientoInyectoraJpaController;
import controladores.TipoHerramentalJpaController;
import controladores.UsuarioJpaController;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class Tag_herramental extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        //<editor-fold defaultstate="collapsed" desc="SESION">
        HttpSession sesion = pageContext.getSession();
//        String area = sesion.getAttribute("Area").toString();
//</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="JPAS">
        HerramentalJpaController jpa_herramental = new HerramentalJpaController();
        MaquinaJpaController jpa_maquina = new MaquinaJpaController();
        UsuarioJpaController jpa_usuario = new UsuarioJpaController();
        TipoHerramentalJpaController jpa_tipoH = new TipoHerramentalJpaController();
        MovimientoInyectoraJpaController jpa_movimiento = new MovimientoInyectoraJpaController();
//</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="VARIABLES GLOBALES">
        List lst_herramentales = null;
        List lst_herramental = null;
        List lst_movimientos = null;
        List lst_pendientes = null;
        List lst_tipo = null;
        List lst_maquina = null;
        List lst_tipo_movi = null;
        int estado, maquina, id_herramental, temp = 0;
//</editor-fold>
        try {
            //<editor-fold defaultstate="collapsed" desc="VARIABLES DE CONTEXTO">
            try {
                id_herramental = Integer.parseInt(pageContext.getRequest().getAttribute("id_herramental").toString());
            } catch (NumberFormatException e) {
                id_herramental = 0;
            }
            try {
                temp = Integer.parseInt(pageContext.getRequest().getAttribute("temp").toString());
            } catch (NumberFormatException e) {
                temp = 0;
            }
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="MODIFICAR HERRAMENTAL">
            if (temp == 2) {
                lst_herramental = jpa_herramental.ConsultaHerramentalId(id_herramental);
                if (lst_herramental != null) {
                    Object[] obj_herramental = (Object[]) lst_herramental.get(0);
                    out.print("<div class='sweet-local' tabindex='-1' id='Ventana2' style='opacity: 1.03; display:block;'>");
                    out.print("<div class='cont_reg'>");
                    out.print("<div style='display: flex; justify-content: space-between'>");
                    out.print("<h2>Editar Herramental " + obj_herramental[4].toString() + "</h2>");
                    out.print("<button class='btn btn-outline-secondary' onclick='mostrarConvencion(2)' style='height: 30px;padding: 3px;width: 30px;'><i class='fas fa-times'></i></button>");
                    out.print("</div>");
                    out.print("<div class='cont_form_user'>");
                    out.print("<form action='Herramental?opc=3&id_herramental=" + obj_herramental[0] + "' method='post' class='needs-validation' novalidate=''>");

                    out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
                    out.print("<div class='col-12'>");
                    out.print("<input type='text' class='form-control' name='codigo' placeholder='Codigo Herramental' value='" + obj_herramental[4] + "' required='' data-toggle='tooltip' data-placemente='top' title='Codigo Herramental'>");
                    out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                    out.print("</div>");

                    out.print("<div class='col-12'>");
                    out.print("<input type='text' class='form-control' name='descripcion' placeholder='Descripcion Herramental' value='" + obj_herramental[9] + "' required='' data-toggle='tooltip' data-placemente='top' title='Descripcion Herramental'>");
                    out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                    out.print("</div>");
                    out.print("</div>");

                    out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
                    out.print("<div class='col-lg-12' style='margin-top:2.9%; margin-bottom: 2.9%; margin-left:3%; margin-right:-3%; margin-:2.9%;' data-toggle='tooltip' data-placemente='top' title='Seleccione Tipo'>");
                    out.print("<select class='select2 form-control' name='tipo' data-toggle='tooltip' data-placement='top' required>");
                    lst_tipo = jpa_tipoH.ConsultaTipoHerramental();
                    if (lst_tipo != null || lst_tipo.size() != 0) {
                        for (int i = 0; i < lst_tipo.size(); i++) {
                            Object[] obj_tipo = (Object[]) lst_tipo.get(i);
                            String selected = obj_herramental[1].toString().equals(obj_tipo[0].toString()) ? "selected" : "";
                            out.print("<option value='" + obj_tipo[0] + "' " + selected + ">" + obj_tipo[1] + "</option>");
                        }
                    } else {
                        out.print("<option value='0'>Se ha producido un error</option>");
                    }
                    out.print("</select>");
                    out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                    out.print("</div>");

                    out.print("<div class='col-lg-12' style='margin-top:2.9%; margin-bottom: 2.9%; margin-left:3%; margin-right:-3%; margin-:2.9%;' data-toggle='tooltip' data-placemente='top' title='Seleccione Maquina'>");
                    out.print("<select id='selectMaquinas' class='select2 form-control' name='maquinas' multiple='' required onchange='actualizarMaquinasSeleccionadas()'>");
                    lst_maquina = jpa_maquina.ConsultaMaquinas();
                    if (lst_maquina != null && !lst_maquina.isEmpty()) {
                        for (int i = 0; i < lst_maquina.size(); i++) {
                            Object[] obj_maquina = (Object[]) lst_maquina.get(i);
                            String selected = obj_herramental[3].toString().contains("[" + obj_maquina[0].toString() + "]") ? "selected" : "";
                            out.print("<option value='" + obj_maquina[0] + "' " + selected + ">" + obj_maquina[1] + "</option>");
                        }
                    } else {
                        out.print("<option value='0'>Se ha producido un error</option>");
                    }
                    out.print("</select>");
                    out.print("<input type='hidden' id='id_maquinas' name='maquina' value=''>");
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
            //<editor-fold defaultstate="collapsed" desc="REGISTRAR HERRAMENTAL">
            out.print("<div class='sweet-local' tabindex='-1' id='Ventana1' style='opacity: 1.03; display:none;'>");
            out.print("<div class='cont_reg'>");
            out.print("<div style='display: flex; justify-content: space-between'>");
            out.print("<h2>Registrar Herramental</h2>");
            out.print("<button class='btn btn-outline-secondary' onclick='mostrarConvencion(1)' style='height: 30px;padding: 3px;width: 30px;'><i class='fas fa-times'></i></button>");
            out.print("</div>");
            out.print("<div class='cont_form_user'>");
            out.print("<form action='Herramental?opc=2' method='post' class='needs-validation' novalidate=''>");

            out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
            out.print("<div class='col-12'>");
            out.print("<input type='text' class='form-control' name='codigo' placeholder='Codigo Herramental' required='' data-toggle='tooltip' data-placemente='top' title='Codigo Herramental'>");
            out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
            out.print("</div>");

            out.print("<div class='col-12'>");
            out.print("<input type='text' class='form-control' name='descripcion' placeholder='Descripcion Herramental' required='' data-toggle='tooltip' data-placemente='top' title='Descripcion Herramental'>");
            out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
            out.print("</div>");
            out.print("</div>");

            out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
            out.print("<div class='col-lg-12' style='margin-top:2.9%; margin-bottom: 2.9%; margin-left:3%; margin-right:-3%; margin-:2.9%;' data-toggle='tooltip' data-placemente='top' title='Seleccione Tipo'>");
            out.print("<select class='select2 form-control' name='tipo' data-toggle='tooltip' data-placement='top' required>");
            out.print("<option value='' disabled selected>Seleccione Tipo</option>");
            lst_tipo = jpa_tipoH.ConsultaTipoHerramental();
            if (lst_tipo != null || lst_tipo.size() != 0) {
                for (int i = 0; i < lst_tipo.size(); i++) {
                    Object[] obj_tipo = (Object[]) lst_tipo.get(i);
                    out.print("<option value='" + obj_tipo[0] + "'>" + obj_tipo[1] + "</option>");
                }
            } else {
                out.print("<option value='0'>Se ha producido un error</option>");
            }
            out.print("</select>");
            out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
            out.print("</div>");

            out.print("<div class='col-lg-12' style='margin-top:2.9%; margin-bottom: 2.9%; margin-left:3%; margin-right:-3%; margin-:2.9%;' data-toggle='tooltip' data-placemente='top' title='Seleccione Maquina'>");
            out.print("<select id='selectMaquinas' class='select2 form-control' name='maquinas' multiple='' required onchange='actualizarMaquinasSeleccionadas()'>");
            out.print("<option value='' disabled selected>N/A</option>");
            lst_maquina = jpa_maquina.ConsultaMaquinas();
            if (lst_maquina != null || lst_maquina.size() != 0) {
                for (int i = 0; i < lst_maquina.size(); i++) {
                    Object[] obj_maquina = (Object[]) lst_maquina.get(i);
                    out.print("<option value='" + obj_maquina[0] + "'>" + obj_maquina[1] + "</option>");
                }
            } else {
                out.print("<option value='0'>Se ha producido un error</option>");
            }
            out.print("</select>");
            out.print("<input type='hidden' id='id_maquinas' name='maquina' value=''>");
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
            //<editor-fold defaultstate="collapsed" desc="REGISTRAR MOVIMIENTO">
            out.print("<div class='sweet-local' tabindex='-1' id='Ventana3' style='opacity: 1.03; display:none;'>");
            out.print("<div class='cont_reg'>");
            out.print("<div style='display: flex; justify-content: space-between'>");
            out.print("<h2>Registrar Movimiento</h2>");
            out.print("<button class='btn btn-outline-secondary' onclick='mostrarConvencion(3)' style='height: 30px;padding: 3px;width: 30px;'><i class='fas fa-times'></i></button>");
            out.print("</div>");
            out.print("<div class='cont_form_user'>");
            out.print("<form action='Movimiento?opc=2' method='post' class='needs-validation' novalidate=''>");

            out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
            out.print("<div class='col-12'>");
            out.print("<input type='date' class='form-control' name='fecha' placeholder='Fecha' required='' data-toggle='tooltip' data-placemente='top' title='Fecha'>");
            out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
            out.print("</div>");

            out.print("<div class='col-lg-12' style='margin-top:2.9%; margin-bottom: 2.9%; margin-left:3%; margin-right:-3%; margin-:2.9%;' data-toggle='tooltip' data-placemente='top' title='Seleccione Movimiento'>");
            out.print("<select class='select2 form-control' name='movimiento' data-toggle='tooltip' data-placement='top' required>");
            out.print("<option value='' disabled selected>Seleccione Movimiento</option>");
            lst_tipo_movi = jpa_movimiento.ConsultaMovimientos();
            if (lst_tipo_movi != null || lst_tipo_movi.size() != 0) {
                for (int i = 0; i < lst_tipo_movi.size(); i++) {
                    Object[] obj_movimientos = (Object[]) lst_tipo_movi.get(i);
                    if ((Integer) obj_movimientos[0] != 1) {
                        out.print("<option value='" + obj_movimientos[0] + "'>" + obj_movimientos[1] + "</option>");
                    }
                }
            } else {
                out.print("<option value='0'>Se ha producido un error</option>");
            }
            out.print("</select>");
            out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
            out.print("</div>");
            out.print("</div>");

            out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
            out.print("<div class='col-12'>");
            out.print("<input type='textarea' class='form-control' name='descripcion' style='width: 207%;' placeholder='Descripcion Movimiento' required='' data-toggle='tooltip' data-placemente='top' title='Descripcion Movimiento'>");
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
            //<editor-fold defaultstate="collapsed" desc="HISTORIAL HERRAMENTAL">
            if (temp == 1) {
                lst_herramental = jpa_herramental.ConsultaHerramentalId(id_herramental);
                if (lst_herramental != null) {
                    Object[] obj_herramental = (Object[]) lst_herramental.get(0);
                    out.print("<section class='section'>");
                    out.print("<div class='section-header'>");
                    out.print("<h1>Historial de Herramental " + obj_herramental[4].toString() + "</h1>");
                    out.print("</div>");
                    out.print("<div class='section-body'>");
                    out.print("<div class='row'>");
                    out.print("<div class='col-12'>");
                    out.print("<div class='card'>");
                    out.print("<div class='card-header' style='justify-content: space-between;'>");
                    lst_movimientos = jpa_herramental.ConsultaMovimientosIdHerramental(id_herramental);
                    if (lst_movimientos.size() > 0) {
                        out.print("<button class='btn btn-secondary' style='border-radius: 4px; margin-right: 0%;' onclick=\"javascript:location.href='Herramental?opc=1'\" data-toggle='tooltip' data-placement='top' title='Volver a Herramentales'><i class='fas fa-arrow-left'></i></button>");
                        out.print("<h4>Tabla de Historial</h4>");
                        out.print("<button class='btn btn-secondary' style='border-radius: 4px;' onclick='mostrarConvencion(3)' data-toggle='tooltip' data-placement='top' title='Registrar Movimiento'><i class='fas fa-plus'></i></button>");
                    } else {
                        out.print("<button class='btn btn-secondary' style='border-radius: 4px; margin-right: 0%;' onclick=\"javascript:location.href='Herramental?opc=1'\" data-toggle='tooltip' data-placement='top' title='Volver a Herramentales'><i class='fas fa-arrow-left'></i></button>");
                        out.print("<h4 style='margin-right: 47%;'>Tabla de Historial</h4>");
                    }
                    out.print("</div>");
                    out.print("<div class='card-body'>");
                    out.print("<div class='table-responsive'>");
                    out.print("<table class='table table-hover table-bordered' id='table-3'>");
                    out.print("<thead>");
                    out.print("<tr>");
                    out.print("<th>Fecha / Maquina</th>");
                    out.print("<th>Producto / Lote</th>");
                    out.print("<th>Cavidades / Deshabilitadas</th>");
                    out.print("<th>Descripcion</th>");
                    out.print("<th>Estado</th>");
                    out.print("<th>Responsable</th>");
                    out.print("<th>Modificar</th>");
                    out.print("<th>Enviar</th>");
                    out.print("</tr>");
                    out.print("</thead>");
                    out.print("<tbody>");
                    lst_movimientos = jpa_herramental.ConsultaMovimientosIdHerramental(id_herramental);
                    if (lst_movimientos.size() > 0) {
                        for (int i = 0; i < lst_movimientos.size(); i++) {
                            Object[] obj_movimiento = (Object[]) lst_movimientos.get(i);
                            if (obj_movimiento[13] != "N/A") {
                                out.print("<tr>");
                                out.print("<td>" + obj_movimiento[7] + " - " + obj_movimiento[2] + "</td>");
                                out.print("<td>" + obj_movimiento[8] + " - " + obj_movimiento[9] + "</td>");
                                out.print("<td>" + obj_movimiento[10] + " - " + obj_movimiento[11] + " - " + obj_movimiento[12] + " - " + obj_movimiento[16] + "</td>");
                                out.print("<td>" + obj_movimiento[13] + "</td>");
                                out.print("<td>" + obj_movimiento[6] + "</td>");
                                out.print("<td>" + obj_movimiento[14] + "</td>");
                                out.print("<td align='center'>");
                                out.print("<button type='button' onclick=\"javascript:location.href='Herramental?opc=1&id_herramental=" + obj_movimiento[0] + "'\" class='btn btn-primary btn-sm btn-icon' data-toggle='tooltip' data-placement='top' title='Modificar Movimiento'><i class='fas fa-pencil-alt'></i></button>");
                                out.print("</td>");
                                out.print("<td align='center'>");
                                out.print("<button type='button' onclick='RestablecerPassword(" + obj_movimiento[0] + ")' class='btn btn-success btn-sm btn-icon' style='width: 28px;' data-toggle='tooltip' data-placement='top' title='Enviar Movimiento'><i class='fas fa-envelope'></i></button>");
                                out.print("</td>");
                                out.print("</tr>");
                            } else {
                                out.print("<tr>");
                                out.print("<td colspan='8' align='center'>");
                                out.print("<h1 style='text-align: center;'><i class='fas fa-frown fa-lg' style='font-size: 100%;'></i> ¡ No se han encontrado resultados !</h1>");
                                out.print("</td>");
                                out.print("</tr>");
                            }
                        }
                    } else {
                        out.print("<tr>");
                        out.print("<td colspan='8' align='center'>");
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
                } else {
                    out.print("<tr>");
                    out.print("<td colspan='8' align='center'>");
                    out.print("<h1 style='text-align: center;'><i class='fas fa-frown fa-lg' style='font-size: 100%;'></i> ¡ No se han encontrado resultados !</h1>");
                    out.print("</td>");
                    out.print("</tr>");
                }
            } else if (temp == 3) {
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="PENDIENTES HERRAMENTAL">
                lst_herramental = jpa_herramental.ConsultaHerramentalId(id_herramental);
                if (lst_herramental != null) {
                    Object[] obj_herramental = (Object[]) lst_herramental.get(0);
                    out.print("<section class='section'>");
                    out.print("<div class='section-header'>");
                    out.print("<h1>Pendientes de Herramental " + obj_herramental[4].toString() + "</h1>");
                    out.print("</div>");
                    out.print("<div class='section-body'>");
                    out.print("<div class='row'>");
                    out.print("<div class='col-12'>");
                    out.print("<div class='card'>");
                    out.print("<div class='card-header' style='justify-content: space-between;'>");
                    lst_pendientes = jpa_herramental.ConsultaPendientesIdHerramental(id_herramental);
                    if (lst_pendientes.size() > 0) {
                        out.print("<button class='btn btn-secondary' style='border-radius: 4px; margin-right: 0%;' onclick=\"javascript:location.href='Herramental?opc=1'\" data-toggle='tooltip' data-placement='top' title='Volver a Herramentales'><i class='fas fa-arrow-left'></i></button>");
                        out.print("<h4>Tabla de Pendientes</h4>");
                        out.print("<button class='btn btn-secondary' style='border-radius: 4px;' onclick='mostrarConvencion(4)' data-toggle='tooltip' data-placement='top' title='Registrar Pendiente'><i class='fas fa-plus'></i></button>");
                    } else {
                        out.print("<button class='btn btn-secondary' style='border-radius: 4px; margin-right: 0%;' onclick=\"javascript:location.href='Herramental?opc=1'\" data-toggle='tooltip' data-placement='top' title='Volver a Herramentales'><i class='fas fa-arrow-left'></i></button>");
                        out.print("<h4 style='margin-right: 47%;'>Tabla de Pendientes</h4>");
                    }
                    out.print("</div>");
                    out.print("<div class='card-body'>");
                    out.print("<div class='table-responsive'>");
                    out.print("<table class='table table-hover table-bordered' id='table-2'>");
                    out.print("<thead>");
                    out.print("<tr>");
                    out.print("<th>Fecha</th>");
                    out.print("<th>Cavidades</th>");
                    out.print("<th>Causas</th>");
                    out.print("<th>Sugerencias</th>");
                    out.print("<th>Solucion</th>");
                    out.print("<th>Modificar</th>");
                    out.print("<th>Mail</th>");
                    out.print("</tr>");
                    out.print("</thead>");
                    out.print("<tbody>");
                    lst_pendientes = jpa_herramental.ConsultaPendientesIdHerramental(id_herramental);
                    if (lst_pendientes.size() > 0) {
                        for (int i = 0; i < lst_pendientes.size(); i++) {
                            Object[] obj_pendiente = (Object[]) lst_pendientes.get(i);
                            out.print("<tr>");
                            out.print("<td>" + obj_pendiente[3] + "</td>");
                            out.print("<td>" + obj_pendiente[4] + "</td>");
                            out.print("<td>" + obj_pendiente[4] + "</td>");
                            out.print("<td>" + obj_pendiente[4] + "</td>");
                            out.print("<td>" + obj_pendiente[8] + "</td>");
                            out.print("<td align='center'>");
                            out.print("<button type='button' onclick='RestablecerPassword(" + obj_pendiente[0] + ")' class='btn btn-primary btn-sm btn-icon' style='width: 28px;' data-toggle='tooltip' data-placement='top' title='Informe'><i class='fas fa-pencil-alt'></i></button>");
                            out.print("</td>");
                            out.print("<td align='center'>");
                            out.print("<button type='button' onclick=\"javascript:location.href='Herramental?opc=1&id_herramental=" + obj_pendiente[0] + "'\" class='btn btn-success btn-sm btn-icon' data-toggle='tooltip' data-placement='top' title='Modificar Herramental'><i class='fas fa-envelope'></i></button>");
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
            } else {
////</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="TABLA HERRAMENTALES">
                out.print("<section class='section'>");
                out.print("<div class='section-header'>");
                out.print("<h1>Modulo Herramental</h1>");
                out.print("</div>");
                out.print("<div class='section-body'>");
                out.print("<div class='row'>");
                out.print("<div class='col-12'>");
                out.print("<div class='card'>");
                out.print("<div class='card-header' style='justify-content: space-between;'>");
                out.print("<h4>Tabla de Herramentales</h4>");
                out.print("<button class='btn btn-secondary' style='border-radius: 4px;' onclick='mostrarConvencion(1)' data-toggle='tooltip' data-placement='top' title='Registrar Herramental'><i class='fas fa-plus'></i></button>");
                out.print("</div>");
                out.print("<div class='card-body'>");
                out.print("<div class='table-responsive'>");
                out.print("<table class='table table-hover table-bordered' id='table-1'>");
                out.print("<thead>");
                out.print("<tr>");
                out.print("<th>Nombre</th>");
                out.print("<th>Descripcion</th>");
                out.print("<th>Tipo</th>");
                out.print("<th>Maquina</th>");
                out.print("<th>Movimiento</th>");
                out.print("<th>Estado</th>");
                out.print("<th>Modificar</th>");
                out.print("<th>Informe</th>");
                out.print("<th>Historial</th>");
                out.print("<th>Pendientes</th>");
                out.print("</tr>");
                out.print("</thead>");
                out.print("<tbody>");
                lst_herramentales = jpa_herramental.ConsultaHerramentales();
                if (lst_herramentales != null) {
                    for (int i = 0; i < lst_herramentales.size(); i++) {
                        Object[] obj_herramental = (Object[]) lst_herramentales.get(i);
                        if (Integer.parseInt(obj_herramental[6].toString()) == 1) {
                            estado = Integer.parseInt(obj_herramental[6].toString());
                            out.print("<tr>");
                            out.print("<td>" + obj_herramental[5] + "</td>");
                            out.print("<td>" + obj_herramental[10] + "</td>");
                            out.print("<td>" + obj_herramental[2] + "</td>");
                            out.print("<td>" + obj_herramental[4] + "</td>");
                            out.print("<td>" + obj_herramental[9] + "</td>");
                            out.print("<td align='center'>");
                            out.print("<button type='button' onclick='DesactivarHerramental(" + obj_herramental[0] + ")' class='btn btn-success btn-sm btn-icon' data-toggle='tooltip' data-placement='top' title='Desactivar Herramental'><i class='fas fa-check'></i></button>");
                            out.print("</td>");
                            out.print("<td align='center'>");
                            out.print("<button type='button' onclick=\"javascript:location.href='Herramental?opc=1&temp=2&id_herramental=" + obj_herramental[0] + "'\" class='btn btn-primary btn-sm btn-icon' data-toggle='tooltip' data-placement='top' title='Modificar Herramental'><i class='fas fa-pencil-alt'></i></button>");
                            out.print("</td>");
                            out.print("<td align='center'>");
                            out.print("<button type='button' onclick='RestablecerPassword(" + obj_herramental[0] + ")' class='btn btn-info btn-sm btn-icon' style='width: 28px;' data-toggle='tooltip' data-placement='top' title='Informe'><i class='fas fa-info'></i></button>");
                            out.print("</td>");
                            out.print("<td align='center'>");
                            out.print("<button type='button' onclick=\"javascript:location.href='Herramental?opc=1&temp=1&id_herramental=" + obj_herramental[0] + "'\" class='btn btn-warning btn-sm btn-icon' data-toggle='tooltip' data-placement='top' title='Historial'><i class='fas fa-heading'></i></button>");
                            out.print("</td>");
                            out.print("<td align='center'>");
                            out.print("<button type='button' onclick=\"javascript:location.href='Herramental?opc=1&temp=3&id_herramental=" + obj_herramental[0] + "'\" class='btn btn-dark btn-sm btn-icon' data-toggle='tooltip' data-placement='top' title='Pendientes'><i class='fas fa-tasks'></i></button>");
                            out.print("</td>");
                            out.print("</tr>");
                        } else {
                            out.print("<tr style='background-color: #ffd6d4'>");
                            out.print("<td>" + obj_herramental[5] + "</td>");
                            out.print("<td>" + obj_herramental[10] + "</td>");
                            out.print("<td>" + obj_herramental[2] + "</td>");
                            out.print("<td>" + obj_herramental[4] + "</td>");
                            out.print("<td>" + obj_herramental[9] + "</td>");
                            out.print("</td>");
                            out.print("<td align='center'>");
                            out.print("<button type='button' onclick='ActivarHerramental(" + obj_herramental[0] + ")' class='btn btn-danger btn-sm btn-icon' style='width: 28px;' data-toggle='tooltip' data-placement='top' title='Activar Herramental'><i class='fas fa-times'></i></button>");
                            out.print("</td>");
                            out.print("<td align='center'>");
                            out.print("<button class='btn btn-light btn-sm' style='border-radius: 4px; cursor: not-allowed;' data-toggle='tooltip' data-placement='top' title='No disponible'><i class='fas fa-pencil-alt'></i></button>");
                            out.print("</td>");
                            out.print("<td align='center'>");
                            out.print("<button class='btn btn-light btn-sm' style='border-radius: 4px; cursor: not-allowed; width: 28px;' data-toggle='tooltip' data-placement='top' title='No disponible'><i class='fas fa-info'></i></button>");
                            out.print("</td>");
                            out.print("<td align='center'>");
                            out.print("<button class='btn btn-light btn-sm' style='border-radius: 4px; cursor: not-allowed;' data-toggle='tooltip' data-placement='top' title='No disponible'><i class='fas fa-heading'></i></button>");
                            out.print("</td>");
                            out.print("<td align='center'>");
                            out.print("<button class='btn btn-light btn-sm' style='border-radius: 4px; cursor: not-allowed;' data-toggle='tooltip' data-placement='top' title='No disponible'><i class='fas fa-tasks'></i></button>");
                            out.print("</td>");
                            out.print("</tr>");
                        }
                    }
                } else {
                    out.print("<tr>");
                    out.print("<td colspan='5' align='center'>");
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
            }
//</editor-fold>    

        } catch (IOException ex) {
            Logger.getLogger(Tag_herramental.class.getName()).log(Level.SEVERE, null, ex);
        }
        return super.doStartTag();
    }
}
