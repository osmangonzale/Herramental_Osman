package Tags;

import Factory.Productos;
import controladores.HerramentalJpaController;
import controladores.MaquinaJpaController;
import controladores.MovimientoInyectoraJpaController;
import controladores.TipoMaquinaJpaController;
import controladores.UsuarioJpaController;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class Tag_maquina extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        //<editor-fold defaultstate="collapsed" desc="SESION">
        HttpSession sesion = pageContext.getSession();
//        String area = sesion.getAttribute("Area").toString();
//</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="JPAS">
        MaquinaJpaController jpa_maquina = new MaquinaJpaController();
        TipoMaquinaJpaController jpa_tipoM = new TipoMaquinaJpaController();
        MovimientoInyectoraJpaController jpa_movimiento = new MovimientoInyectoraJpaController();
        HerramentalJpaController jpa_Herramental = new HerramentalJpaController();
        UsuarioJpaController UsuarioJpa = new UsuarioJpaController();
        Productos product = new Productos();
//</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="VARIABLES GLOBALES">
        List lst_maquinas = null;
        List lst_maquina = null;
        List lst_movimientos = null;
        List lst_tipo_Maquina = null;
        List lst_detalle_movi = null;
        List lst_productos = null;
        List lst_herramentales = null;
        List lst_tipo_movimiento = null;
        List lst_usuarios = null;
        List lst_pendientes = null;
        String nombre_maquina, parametro = "", codigo = "";
        int tempo, id_movimiento, temp = 0, tempora, valorSeleccionado, cedula = 0, codusu = 0, id_maquina = 0;
        boolean todosFirmados = true;
//</editor-fold>            
        try {
            //<editor-fold defaultstate="collapsed" desc="VARIABLES DE CONTEXTO">
            try {
                id_maquina = Integer.parseInt(pageContext.getRequest().getAttribute("id_maquina").toString());
            } catch (NumberFormatException e) {
                id_maquina = 0;
            }
            try {
                temp = Integer.parseInt(pageContext.getRequest().getAttribute("temp").toString());
            } catch (NumberFormatException e) {
                temp = 0;
            }
            try {
                nombre_maquina = pageContext.getRequest().getAttribute("nombre_maquina").toString();
            } catch (Exception e) {
                nombre_maquina = "";
            }
            try {
                codigo = pageContext.getRequest().getAttribute("codigo").toString();
            } catch (Exception e) {
                codigo = "";
            }
            try {
                tempo = Integer.parseInt(pageContext.getRequest().getAttribute("tempo").toString());
            } catch (NumberFormatException e) {
                tempo = 0;
            }
            try {
                id_movimiento = Integer.parseInt(pageContext.getRequest().getAttribute("id_movimiento").toString());
            } catch (NumberFormatException e) {
                id_movimiento = 0;
            }
            try {
                valorSeleccionado = Integer.parseInt(pageContext.getRequest().getAttribute("newEstado").toString());
            } catch (NumberFormatException e) {
                valorSeleccionado = 0;
            }
            try {
                cedula = Integer.parseInt(pageContext.getRequest().getAttribute("cedula").toString());
            } catch (NumberFormatException e) {
                cedula = 0;
            }
            try {
                codusu = Integer.parseInt(pageContext.getRequest().getAttribute("codusu").toString());
            } catch (NumberFormatException e) {
                codusu = 0;
            }
            try {
                tempora = Integer.parseInt(pageContext.getRequest().getAttribute("tempora").toString());
            } catch (NumberFormatException e) {
                tempora = 0;
            }
            try {
                parametro = pageContext.getRequest().getAttribute("parametro").toString();
            } catch (Exception e) {
                parametro = "";
            }
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="MODIFICAR MAQUINA">
            if (temp == 1) {
                lst_maquina = jpa_maquina.ConsultaMaquinaId(id_maquina);
                if (lst_maquina != null) {
                    Object[] obj_maquina = (Object[]) lst_maquina.get(0);
                    out.print("<div class='sweet-local' tabindex='-1' id='Ventana2' style='opacity: 1.03; display:block;'>");
                    out.print("<div class='cont_reg'>");
                    out.print("<div style='display: flex; justify-content: space-between'>");
                    out.print("<h2>Editar Maquina " + obj_maquina[1].toString() + "</h2>");
                    out.print("<button class='btn btn-outline-secondary' onclick='mostrarConvencion(2)' style='height: 30px;padding: 3px;width: 30px;'><i class='fas fa-times'></i></button>");
                    out.print("</div>");
                    out.print("<div class='cont_form_user'>");
                    out.print("<form action='Maquina?opc=3&id_maquina=" + obj_maquina[0] + "' method='post' class='needs-validation' novalidate=''>");

                    out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
                    out.print("<div class='col-12'>");
                    out.print("<input type='text' class='form-control' name='nombre' placeholder='Nombre' required='' value='" + obj_maquina[1] + "' data-toggle='tooltip' data-placemente='top' title='Nombre'>");
                    out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                    out.print("</div>");

                    out.print("<div class='col-lg-12' style='margin-top:2.9%; margin-left:3%; margin-right:-3%; text-align:left;' data-toggle='tooltip' data-placemente='top' title='Tipo de Maquina'>");
                    out.print("<select class='select2 form-control' name='tipo' required>");
                    lst_tipo_Maquina = jpa_tipoM.ConsultaTipoMaquinas();
                    if (lst_tipo_Maquina != null) {
                        for (int i = 0; i < lst_tipo_Maquina.size(); i++) {
                            Object[] obj_tipo_M = (Object[]) lst_tipo_Maquina.get(i);
                            String selected = obj_tipo_M[0].toString().equals(obj_maquina[3].toString()) ? "selected" : "";
                            out.print("<option value='" + obj_tipo_M[0] + "' " + selected + ">" + obj_tipo_M[1] + "</option>");
                        }
                    } else {
                        out.print("<option value='0'>Se ha producido un error</option>");
                    }
                    out.print("</select>");
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
            //<editor-fold defaultstate="collapsed" desc="REGISTRAR MAQUINA">
            out.print("<div class='sweet-local' tabindex='-1' id='Ventana1' style='opacity: 1.03; display:none;'>");
            out.print("<div class='cont_reg'>");
            out.print("<div style='display: flex; justify-content: space-between'>");
            out.print("<h2>Registrar Maquina</h2>");
            out.print("<button class='btn btn-outline-secondary' onclick='mostrarConvencion(1)' style='height: 30px;padding: 3px;width: 30px;'><i class='fas fa-times'></i></button>");
            out.print("</div>");
            out.print("<div class='cont_form_user'>");
            out.print("<form action='Maquina?opc=2' method='post' class='needs-validation' novalidate=''>");

            out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
            out.print("<div class='col-12'>");
            out.print("<input type='text' class='form-control' name='nombre' placeholder='Nombre' required='' data-toggle='tooltip' data-placemente='top' title='Nombre'>");
            out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
            out.print("</div>");

            out.print("<div class='col-lg-12' style='margin-top:2.9%; margin-left:3%; margin-right:-3%; text-align:left;' data-toggle='tooltip' data-placemente='top' title='Tipo de Maquina'>");
            out.print("<select class='select2 form-control' name='tipo' required>");
            out.print("<option value='' disabled selected>Seleccione Tipo de Maquina</option>");
            lst_tipo_Maquina = jpa_tipoM.ConsultaTipoMaquinas();
            if (lst_tipo_Maquina != null) {
                for (int i = 0; i < lst_tipo_Maquina.size(); i++) {
                    Object[] obj_tipo_M = (Object[]) lst_tipo_Maquina.get(i);
                    out.print("<option value='" + obj_tipo_M[0] + "'>" + obj_tipo_M[1] + "</option>");
                }
            } else {
                out.print("<option value='0'>Se ha producido un error</option>");
            }
            out.print("</select>");
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
            //<editor-fold defaultstate="collapsed" desc="PENDIENTES MAQUINA">
            if (temp == 3) {
                lst_maquina = jpa_maquina.ConsultaMaquinaId(id_maquina);
                if (lst_maquina != null) {
                    Object[] obj_maquina = (Object[]) lst_maquina.get(0);
                    out.print("<section class='section'>");
                    out.print("<div class='section-header'>");
                    out.print("<h1>Pendientes Maquina " + obj_maquina[1].toString() + "</h1>");
                    out.print("</div>");
                    out.print("<div class='section-body'>");
                    out.print("<div class='row'>");
                    out.print("<div class='col-12'>");
                    out.print("<div class='card'>");
                    out.print("<div class='card-header' style='justify-content: space-between;'>");
                    lst_pendientes = jpa_maquina.ConsultaPendientesMaquina(id_maquina);
                    if (lst_pendientes.size() > 0) {
                        out.print("<button onclick=\"javascript:location.href='Maquina?opc=1&parametro=" + parametro + "'\" class='btn btn-secondary' style='border-radius: 4px;'  data-toggle='tooltip' data-placement='right' title='Volver a Maquinas'><i class='fas fa-arrow-left'></i></button>");
                        out.print("<h4>Pendientes Maquina</h4>");
                        out.print("<button class='btn btn-secondary' style='border-radius: 4px;' onclick='mostrarConvencion(1)' data-toggle='tooltip' data-placement='top' title='Registrar Maquina'><i class='fas fa-plus'></i></button>");
                    } else {
                        out.print("<button onclick=\"javascript:location.href='Maquina?opc=1&parametro=" + parametro + "'\" class='btn btn-secondary' style='border-radius: 4px;'  data-toggle='tooltip' data-placement='right' title='Volver a Maquinas'><i class='fas fa-arrow-left'></i></button>");
                        out.print("<h4 style='margin-right: 42%;'>Pendientes Maquina</h4>");
                    }
                    out.print("</div>");
                    out.print("<div class='card-body'>");
                    out.print("<div class='table-responsive'>");
                    out.print("<table class='table table-hover table-bordered' id='table-3'>");
                    out.print("<thead>");
                    out.print("<tr>");
                    out.print("<th>Fecha</th>");
                    out.print("<th>Pendiente</th>");
                    out.print("<th>Usuario Registro</th>");
                    out.print("<th>Solucion</th>");
                    out.print("<th>Modificar</th>");
                    out.print("<th>Mail</th>");
                    out.print("</tr>");
                    out.print("</thead>");
                    out.print("<tbody>");
                    lst_pendientes = jpa_maquina.ConsultaPendientesMaquina(id_maquina);
                    if (lst_pendientes.size() > 0) {
                        for (int i = 0; i < lst_pendientes.size(); i++) {
                            Object[] obj_pendiente = (Object[]) lst_pendientes.get(i);
                            out.print("<tr>");
                            out.print("<td>" + obj_pendiente[3] + "</td>");
                            out.print("<td>" + obj_pendiente[4] + "</td>");
                            out.print("<td>" + obj_pendiente[5] + "</td>");
                            out.print("<td>" + obj_pendiente[8] + "</td>");
                            out.print("<td align='center'>");
                            out.print("<button type='button' onclick=\"javascript:location.href='Herramental?opc=1&id_herramental=" + obj_pendiente[0] + "'\" class='btn btn-primary btn-sm btn-icon' data-toggle='tooltip' data-placement='top' title='Modificar Movimiento'><i class='fas fa-pencil-alt'></i></button>");
                            out.print("</td>");
                            out.print("<td align='center'>");
                            out.print("<button type='button' onclick='RestablecerPassword(" + obj_pendiente[0] + ")' class='btn btn-success btn-sm btn-icon' style='width: 28px;' data-toggle='tooltip' data-placement='top' title='Enviar Movimiento'><i class='fas fa-envelope'></i></button>");
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
                    out.print("</section>");
                } else {
                    out.print("<tr>");
                    out.print("<td colspan='5' align='center'>");
                    out.print("<h1 style='text-align: center;'><i class='fas fa-frown fa-lg' style='font-size: 100%;'></i> ¡ No se han encontrado resultados !</h1>");
                    out.print("</td>");
                    out.print("</tr>");
                }
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="HISTORIAL MAQUINA">
            } else if (temp == 2) {
                out.print("<section class='section'>");
                out.print("<div class='section-header'>");
                out.print("<h1>Maquina " + nombre_maquina + "</h1>");
                out.print("</div>");
                out.print("<div class='section-body'>");
                out.print("<div class='row'>");
                out.print("<div class='col-12'>");
                out.print("<div class='card'>");
                lst_movimientos = jpa_movimiento.ConsultaMovimientosIdMaquina(id_maquina);
                if (lst_movimientos != null) {
                    Integer currentGroup = null;

                    List lst_ult_movi = jpa_movimiento.ConsultaUltMovimientoIdMaquina(id_maquina);
                    if (lst_ult_movi != null) {
                        Object[] obj_movimi = (Object[]) lst_ult_movi.get(0);
                        out.print("<div class='card-header' style='justify-content: space-between;'>");
                        out.print("<button onclick=\"javascript:location.href='Maquina?opc=1&parametro=" + parametro + "'\" class='btn btn-secondary' style='border-radius: 4px;'  data-toggle='tooltip' data-placement='right' title='Volver a Maquinas'><i class='fas fa-arrow-left'></i></button>");
                        out.print("<button type='button' onclick=\"javascript:location.href='Maquina?opc=1&temp=2&tempo=1&id_maquina=" + id_maquina + "&nombre_maquina=" + nombre_maquina + "'\" class='btn btn-secondary' style='border-radius: 4px;' data-toggle='tooltip' data-placement='top' title='Actualizar Ultimo Movimiento'><i class='fas fa-hammer'></i></button>");
                        out.print("<h4>Historial Maquina</h4>");
                        out.print("<button onclick=\"javascript:location.href='Maquina?opc=1'\" class='btn btn-secondary' style='border-radius: 4px;'  data-toggle='tooltip' data-placement='top' title='Enviar Correo'><i class='fas fa-envelope'></i></button>");
                        if (obj_movimi[5] != null && (Integer) obj_movimi[5] == 4) {
                            out.print("<button class='btn btn-secondary' style='border-radius: 4px;' onclick='molde_montado()' data-toggle='tooltip' data-placement='left' title='Registrar Movimiento'><i class='fas fa-plus'></i></button>");
                        } else {
                            out.print("<button class='btn btn-secondary' style='border-radius: 4px;' onclick='mostrarConvencion(3)' data-toggle='tooltip' data-placement='left' title='Registrar Movimiento'><i class='fas fa-plus'></i></button>");
                        }
                        out.print("</div>");
                    }

                    for (int i = 0; i < lst_movimientos.size(); i++) {
                        Object[] obj_movimiento = (Object[]) lst_movimientos.get(i);

                        int grupo = Integer.parseInt(obj_movimiento[19].toString());

                        if (currentGroup == null || !currentGroup.equals(grupo)) {
                            currentGroup = grupo;

                            out.print("<div class='card'>");
                            out.print("<div class='card-header' " + ((grupo == 1) ? "style='background-color: #f9c827;'" : "") + ">");
                            if (grupo == 1) {
                                out.print("<h4 style='color: #000000;'>" + obj_movimiento[4].toString() + " | " + obj_movimiento[7].toString() + " | MOLDE ACTUAL</h4>");
                            } else {
                                out.print("<h4>" + obj_movimiento[4].toString() + " | " + obj_movimiento[7].toString() + "</h4>");
                            }
                            out.print("<div class='card-header-action'>");
                            out.print("<a data-collapse='#mycard-collapse" + i + "' class='btn btn-icon btn-primary' href='#'>");
                            out.print("<i class='fas fa-plus'></i>");
                            out.print("</a>");
                            out.print("</div>");
                            out.print("</div>");
                            out.print("<div class='collapse' id='mycard-collapse" + i + "' style=''>");
                            out.print("<div class='card-body'>");
                            lst_detalle_movi = jpa_movimiento.Consulta_Movimientos_por_grupo(id_maquina, grupo);
                            if (!lst_detalle_movi.isEmpty()) {
                                out.print("<div id='accordion" + i + "' class='accordion'>");
                                for (int x = 0; x < lst_detalle_movi.size(); x++) {
                                    Object[] obj_detalle = (Object[]) lst_detalle_movi.get(x);

                                    String responsables = obj_detalle[13].toString();
                                    String resultadoFinal = responsables.replace("][", "<br>").replace("[", "").replace("]", "");

                                    out.print("<div class='accordion-item'>");
                                    if ((x == 0) && grupo == 1) {
                                        out.print("<div class='accordion-header' style='color: #f9c827;' role='button' data-toggle='collapse' data-target='#panel-body-" + i + "-" + x + "' aria-expanded='false'>");
                                        out.print("<h4>" + obj_detalle[14].toString() + " || " + obj_detalle[6].toString() + " || MOLDE ACTUAL</h4>");
                                    } else {
                                        out.print("<div class='accordion-header' role='button' data-toggle='collapse' data-target='#panel-body-" + i + "-" + x + "' aria-expanded='false'>");
                                        out.print("<h4>" + obj_detalle[14].toString() + " || " + obj_detalle[6].toString() + "</h4>");
                                    }
                                    out.print("</div>");

                                    out.print("<div class='accordion-body collapse' id='panel-body-" + i + "-" + x + "'>");

                                    //<editor-fold defaultstate="collapsed" desc="REGISTRO R-PI-019">
                                    out.print("<table class='table table-sm table-hover table-bordered border-primary' style='width: 100%'>");
                                    out.print("<thead>");
                                    out.print("<tr>");
                                    out.print("<th colspan='3' class='header-cell' style='text-align: center; background-color: #c1c1c1'>COPIA NO CONTROLADA</th>");
                                    out.print("</tr>");
                                    out.print("</thead>");
                                    out.print("<tbody class='body-table'>");
                                    out.print("<tr>");
                                    out.print("<td rowspan='2' class='logo-cell' style='width: 25%; text-align: center'>");
                                    out.print("<img src=\"Interfaz/Contenido/images/Logo.png\" alt=\"Logo\" class='logo-img' style='width: 200px'>");
                                    out.print("</td>");
                                    out.print("<td rowspan='2' class='text-center title-cell' style='width: 50%; font-weight: bold;'> REGISTRO DE ACTIVIDADES</td>");
                                    out.print("<td class='text-center code-version-cell' style='width: 25%; text-align: center; font-weight: bold;'>");
                                    out.print("<span style='color: #f9c827;'>CÓDIGO:</span> R-PI-019");
                                    out.print("</td>");
                                    out.print("</tr>");
                                    out.print("<tr>");
                                    out.print("<td style='width: 25%; text-align: center; font-weight: bold;'>");
                                    out.print("<span style='color: #f9c827;'>VERSION:</span> 003");
                                    out.print("</td>");
                                    out.print("</tr>");
                                    out.print("<tr>");
                                    out.print("<td colspan='3' style='width: 25%; font-weight: bold;'>");
                                    out.print("<span style='color: #f9c827;'>PRODUCTO:</span> " + obj_detalle[7].toString() + " | <span style='color: #f9c827;'>MOLDE:</span> " + obj_detalle[4].toString() + " | <span style='color: #f9c827;'>MAQUINA:</span> " + obj_detalle[2].toString() + "");
                                    out.print("</td>");
                                    out.print("</tr>");
                                    out.print("<tr>");
                                    out.print("<td align='center' style='width: 25%; font-weight: bold;'>");
                                    out.print("<span style='color: #f9c827;'>TOTAL CAVIDADES:</span> " + obj_detalle[9].toString() + "");
                                    out.print("</td>");
                                    out.print("<td align='center' style='width: 25%; font-weight: bold;'>");
                                    out.print("<span style='color: #f9c827;'>CAVIDADES DESHABILITADAS:</span> " + obj_detalle[10].toString() + "");
                                    out.print("</td>");
                                    out.print("<td align='center' style='width: 25%; font-weight: bold;'>");
                                    out.print("<span style='color: #f9c827;'>CAVIDADES:</span> " + obj_detalle[11].toString() + "");
                                    out.print("</td>");
                                    out.print("</tr>");
                                    out.print("<tr>");
                                    out.print("<td colspan='3' style='width: 25%; font-weight: bold;'>");
                                    out.print("<span style='color: #f9c827;'>Descripcion Cavidad(es) des-habilitadas:</span> " + obj_detalle[15].toString() + "");
                                    out.print("</td>");
                                    out.print("</tr>");
                                    out.print("<tr>");
                                    out.print("<td colspan='2' style='width: 25%; font-weight: bold;'>");
                                    out.print("<span></span> " + obj_detalle[12].toString() + "");
                                    out.print("</td>");
                                    out.print("<td style='width: 25%; font-weight: bold;'>");
                                    out.print("<span style='color: #f9c827;'>RESPONSABLES:</span> " + resultadoFinal + "<br>");
                                    out.print("<span style='color: #f9c827;'>CONFIRMAN:</span> " + obj_detalle[16] + "<br>" + obj_detalle[17] + "<br>" + obj_detalle[18]);
                                    out.print("</td>");
                                    out.print("</tr>");
                                    out.print("</tbody>");
                                    out.print("</table>");
                                    //</editor-fold>

                                    out.print("</div>");
                                    out.print("</div>");
                                }
                                out.print("</div>");
                            }

                            out.print("</div>");
                            out.print("</div>");
                            out.print("</div>");
                        }
                    }
                } else {
                    out.print("<div class='card-header' style='justify-content: space-between;'>");
                    out.print("<button onclick=\"javascript:location.href='Maquina?opc=1&parametro=" + parametro + "'\" class='btn btn-secondary' style='border-radius: 4px;'  data-toggle='tooltip' data-placement='right' title='Volver a Maquinas'><i class='fas fa-arrow-left'></i></button>");
                    out.print("<h4 style='margin-right: 47%;'>Historial Maquina</h4>");
                    out.print("</div>");
                    out.print("<h1 style='text-align: center;'><i class='fas fa-frown fa-lg' style='font-size: 100%;'></i> ¡ No se han encontrado resultados !</h1>");
                }

                //<editor-fold defaultstate="collapsed" desc="ACTUALIZAR MOVIMIENTO">
                if (tempo == 1) {
                    lst_movimientos = jpa_movimiento.ConsultaUltMovimientoIdMaquina(id_maquina);
                    if (lst_movimientos != null) {
                        Object[] obj_movimi = (Object[]) lst_movimientos.get(0);
                        out.print("<div class='sweet-local' tabindex='-1' id='Ventana4' style='opacity: 1.03; display: " + ((!codigo.equals("")) ? "none" : "block") + ";'>");
                        out.print("<div class='cont_reg'>");
                        out.print("<div style='display: flex; justify-content: space-between'>");
                        out.print("<h2>Actualizar Ultimo Movimiento</h2>");
                        out.print("<button class='btn btn-outline-secondary' onclick='mostrarConvencion(4)' style='height: 30px;padding: 3px;width: 30px;'><i class='fas fa-times'></i></button>");
                        out.print("</div>");
                        out.print("<div class='cont_form_user'>");
                        out.print("<form action='Maquina?opc=1' method='post' class='needs-validation' id='modificarform' novalidate=''>");

                        out.print("<input type='hidden' name='id_maquina' value='" + id_maquina + "'>");
                        out.print("<input type='hidden' name='temp' value='2'>");
                        out.print("<input type='hidden' name='tempo' value='1'>");
                        out.print("<input type='hidden' name='id_movimiento' value='" + obj_movimi[0] + "'>");

                        out.print("<div class='col-12'>");
                        out.print("<input type='text' class='form-control' name='codigo' style='margin-left: 2%; width: 95%;' onkeypress='enviar2()' placeholder='Codigo Factory' value='" + codigo + "' required='' data-toggle='tooltip' data-placemente='top' title='codigo'>");
                        out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                        out.print("</div>");
                        out.print("</form>");

                        if (codigo == "") {
                            out.print("<form action='Maquina?opc=6&id_movimiento=" + obj_movimi[0] + "' method='post' class='needs-validation' novalidate=''>");
                            out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
                            out.print("<div class='col-lg-12' style='margin-top:2.9%; margin-left:3%; margin-right:-3%; text-align:left;' data-toggle='tooltip' data-placemente='top' title='Seleccione Rol'>");
                            out.print("<select class='select2 form-control' name='producto' required style='width: 25% !important;'>");
                            lst_productos = product.Productos(codigo);
                            if (lst_productos != null && !lst_productos.isEmpty()) {
                                for (Object producto : lst_productos) {
                                    String productoStr = producto.toString();
                                    String selected = productoStr.equals(obj_movimi[7].toString()) ? "selected" : "";
                                    out.print("<option value='" + productoStr + "' " + selected + ">" + productoStr + "</option>");
                                }
                            } else {
                                out.print("<option value='0'>Se ha producido un error</option>");
                            }
                            out.print("</select>");
                            out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                            out.print("</div>");
                        } else {
                            out.print("<form action='Maquina?opc=4' method='post' class='needs-validation' novalidate=''>");
                            out.print("<input type='hidden' name='id_maquina' value='" + id_maquina + "'>");
                            out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
                            out.print("<div class='col-lg-12' style='margin-top:2.9%; margin-left:3%; margin-right:-3%; text-align:left;' data-toggle='tooltip' data-placemente='top' title='Seleccione Rol'>");
                            out.print("<select class='select2 form-control' name='producto' required style='width: 25% !important;'>");
                            lst_productos = product.Productos(codigo);
                            if (lst_productos != null && !lst_productos.isEmpty()) {
                                for (Object producto : lst_productos) {
                                    String productoStr = producto.toString();
                                    out.print("<option value='" + productoStr + "'>" + productoStr + "</option>");
                                }
                            } else {
                                out.print("<option value='0'>Se ha producido un error</option>");
                            }
                            out.print("</select>");
                            out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                            out.print("</div>");
                        }

                        out.print("<div class='col-12'>");
                        out.print("<input type='datetime-local' class='form-control' name='fecha' placeholder='Fecha' required='' data-toggle='tooltip' data-placemente='top' title='Fecha Modificacion'>");
                        out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                        out.print("</div>");
                        out.print("</div>");

                        out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
                        out.print("<div class='col-12'>");
                        out.print("<input type='text' class='form-control' name='lote' value='" + obj_movimi[8] + "' placeholder='Lote' required='' data-toggle='tooltip' data-placemente='top' title='Lote'>");
                        out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                        out.print("</div>");

                        out.print("<div class='col-12'>");
                        out.print("<input type='number' class='form-control' name='total_cavi' value='" + obj_movimi[9] + "' placeholder='Total Cavidades' required='' data-toggle='tooltip' data-placemente='top' title='Total Cavidades'>");
                        out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                        out.print("</div>");
                        out.print("</div>");

                        out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
                        out.print("<div class='col-12'>");
                        out.print("<input type='number' class='form-control' name='cavi_desha' value='" + obj_movimi[10] + "' placeholder='Cavidades Deshabilitadas' required='' data-toggle='tooltip' data-placemente='top' title='Cavidades Deshabilitadas'>");
                        out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                        out.print("</div>");

                        out.print("<div class='col-12'>");
                        out.print("<input type='text' class='form-control' name='cavidades' value='" + obj_movimi[11] + "' placeholder='Cavidades' required='' data-toggle='tooltip' data-placemente='top' title='Cavidades'>");
                        out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                        out.print("</div>");
                        out.print("</div>");

                        out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
                        out.print("<div class='col-lg-12' style='margin-top: 2.9%; margin-left: 2%; margin-right: -2%; margin-bottom: 6%' data-toggle='tooltip' data-placemente='top' title='Herramental'>");
                        out.print("<select class='select2 form-control' name='herramental' required style='width: 25% !important;'>");
                        lst_herramentales = jpa_Herramental.ConsultaHerramentales();
                        if (lst_herramentales != null && !lst_herramentales.isEmpty()) {
                            for (int i = 0; i < lst_herramentales.size(); i++) {
                                Object[] obj_herramental = (Object[]) lst_herramentales.get(i);
                                if (Integer.parseInt(obj_herramental[7].toString()) == 3) {
                                    out.print("<option value='" + obj_herramental[0] + "' disabled>" + obj_herramental[5] + " | En Reparacion</option>");
                                } else {
                                    String selected = obj_herramental[0].toString().equals(obj_movimi[3].toString()) ? "selected" : "";
                                    out.print("<option value='" + obj_herramental[0] + "' " + selected + ">" + obj_herramental[5] + "</option>");
                                }
                            }
                        } else {
                            out.print("<option value='0'>Se ha producido un error</option>");
                        }
                        out.print("</select>");
                        out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                        out.print("</div>");

                        if (valorSeleccionado == 2 || valorSeleccionado == 3) {
                            out.print("<input type='hidden' id='id_maquina' name='id_maquina' value='" + id_maquina + "'>");
                            out.print("<input type='hidden' id='temp' name='temp' value='2'>");
                            out.print("<input type='hidden' id='tempo' name='tempo' value='1'>");
                            out.print("<input type='hidden' id='tempora' name='tempora' value='1'>");
                            out.print("<input type='hidden' id='id_movimiento' name='id_movimiento' value='" + obj_movimi[0] + "'>");
                            out.print("<div class='col-lg-12' style='margin-top: 2.9%; margin-left: 2%; margin-right: -2%; margin-bottom: 6%' data-toggle='tooltip' data-placemente='top' title='Movimiento'>");
                            out.print("<select class='select2 form-control' name='movimiento' required style='width: 25% !important;' onchange='manejarCambio(this)'>");
                            lst_tipo_movimiento = jpa_movimiento.ConsultaMovimientos();
                            if (lst_tipo_movimiento != null && !lst_tipo_movimiento.isEmpty()) {
                                for (int i = 0; i < lst_tipo_movimiento.size(); i++) {
                                    Object[] obj_movimiento = (Object[]) lst_tipo_movimiento.get(i);
                                    String selected = obj_movimiento[0].toString().equals(String.valueOf(valorSeleccionado)) ? "selected" : "";
                                    out.print("<option value='" + obj_movimiento[0] + "' " + selected + ">" + obj_movimiento[1] + "</option>");
                                }
                            } else {
                                out.print("<option value='0'>Se ha producido un error</option>");
                            }
                            out.print("</select>");
                            out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                            out.print("</div>");
                            out.print("</div>");
                        } else {
                            out.print("<input type='hidden' id='id_maquina' name='id_maquina' value='" + id_maquina + "'>");
                            out.print("<input type='hidden' id='temp' name='temp' value='2'>");
                            out.print("<input type='hidden' id='tempo' name='tempo' value='1'>");
                            out.print("<input type='hidden' id='tempora' name='tempora' value='1'>");
                            out.print("<input type='hidden' id='id_movimiento' name='id_movimiento' value='" + obj_movimi[0] + "'>");
                            out.print("<div class='col-lg-12' style='margin-top: 2.9%; margin-left: 2%; margin-right: -2%; margin-bottom: 6%' data-toggle='tooltip' data-placemente='top' title='Movimiento'>");
                            out.print("<select class='select2 form-control' name='movimiento' required style='width: 25% !important;' onchange='manejarCambio(this)'>");
                            lst_tipo_movimiento = jpa_movimiento.ConsultaMovimientos();
                            if (lst_tipo_movimiento != null && !lst_tipo_movimiento.isEmpty()) {
                                for (int i = 0; i < lst_tipo_movimiento.size(); i++) {
                                    Object[] obj_movimiento = (Object[]) lst_tipo_movimiento.get(i);
                                    String selected = obj_movimiento[0].toString().equals(obj_movimi[5].toString()) ? "selected" : "";
                                    out.print("<option value='" + obj_movimiento[0] + "' " + selected + ">" + obj_movimiento[1] + "</option>");
                                }
                            } else {
                                out.print("<option value='0'>Se ha producido un error</option>");
                            }
                            out.print("</select>");
                            out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                            out.print("</div>");
                            out.print("</div>");
                        }

                        out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
                        out.print("<div class='col-12'>");
                        out.print("<textarea class='form-control' name='descripcion_tapada' id='textareaRegistrar' style='width: 207%; margin-left: 2%; margin-bottom: 6%' placeholder='Descripcion Cavidad Deshabilitada' required data-toggle='tooltip' data-placement='top' title='Descripcion Cavidad Deshabilitada'>" + obj_movimi[15] + "</textarea>");
                        out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor válido!</div>");
                        out.print("</div>");
                        out.print("</div>");

                        out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
                        out.print("<div class='col-12'>");
                        out.print("<textarea class='form-control' name='descripcion' id='textareaRegistrar' style='width: 207%; margin-left: 2%; margin-bottom: 6%' placeholder='Descripcion General' required data-toggle='tooltip' data-placemente='top' title='Descripcion General'>" + obj_movimi[12] + "</textarea>");
                        out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor válido!</div>");
                        out.print("</div>");
                        out.print("</div>");

                        out.print("<div class='' style='width: 100%; text-align:center;'>");
                        out.print("<button class='btn btn-green btn-lg'>Modificar</button>");
                        out.print("</div>");
                    }
                }
                out.print("</form>");
                out.print("</div>");
                out.print("</div>");
                out.print("</div>");
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="FIMAS CONFIRMAN">
                if (tempora == 1 && (valorSeleccionado == 2 || valorSeleccionado == 3)) {
                    lst_movimientos = jpa_movimiento.ConsultaUltMovimientoIdMaquina(id_maquina);
                    if (lst_movimientos != null) {
                        Object[] obj_movimien = (Object[]) lst_movimientos.get(0);
                        out.print("<div class='sweet-local' tabindex='-1' id='Ventana5' style='opacity: 1.03; display:block;'>");
                        out.print("<div class='cont_reg2'>");
                        out.print("<div style='display: flex; justify-content: space-between'>");
                        out.print("<h2>Confirmar Desmontado</h2>");
                        out.print("<button class='btn btn-outline-secondary' onclick='mostrarConvencion(5)' style='height: 30px;padding: 3px;width: 30px;'><i class='fas fa-times'></i></button>");
                        out.print("</div>");
                        out.print("<div class='cont_form_user'>");
                        out.print("<form action='Maquina?opc=5&id_movimiento=" + id_movimiento + "' method='post' class='needs-validation' novalidate=''>");
                        out.print("<input type='hidden' name='newEstado' value='" + valorSeleccionado + "'>");
                        out.print("<input type='hidden' name='contador' value='" + obj_movimien[20] + "'>");
                        out.print("<input type='hidden' name='nombre_maquina' value='" + obj_movimien[2] + "'>");

                        out.print("<table class='table'>");
                        out.print("<tbody style='font-size: 11px;'>");

                        out.print("<tr>");
                        out.print("<td>Firma Insumos</td>");
                        if (cedula == 0 || codusu == 0) {
                            out.print("<td align='center'>");
                            out.print("<span>Firmar</span>");
                            out.print("</td>");
                            todosFirmados = false;
                        } else if (!obj_movimien[16].equals("N/A")) {
                            out.print("<td align='center'>");
                            out.print("<span>" + obj_movimien[16] + "</span>");
                            out.print("</td>");
                        } else {
                            lst_usuarios = UsuarioJpa.Firma(cedula, codusu);
                            if (lst_usuarios != null) {
                                Object[] obj_usuario = (Object[]) lst_usuarios.get(0);
                                for (int i = 0; i < lst_usuarios.size(); i++) {
                                    if (obj_usuario[6].equals("PRODUCCION INSUMOS") || obj_usuario[7].toString().contains("AJUSTADOR")) {
                                        out.print("<td align='center'>");
                                        out.print("<span>" + obj_usuario[2] + "</span>");
                                        out.print("</td>");
                                        out.print("<input type='hidden' name='id_area1' value='" + obj_usuario[5] + "'>");
                                        out.print("<input type='hidden' name='firma1' value='" + obj_usuario[2] + "/" + obj_usuario[6] + "'>");
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
                        if (cedula == 0 || codusu == 0) {
                            out.print("<td align='center'>");
                            out.print("<span>Firmar</span>");
                            out.print("</td>");
                            todosFirmados = false;
                        } else if (!obj_movimien[17].equals("N/A")) {
                            out.print("<td align='center'>");
                            out.print("<span>" + obj_movimien[17] + "</span>");
                            out.print("</td>");
                        } else {
                            lst_usuarios = UsuarioJpa.Firma(cedula, codusu);
                            if (lst_usuarios != null) {
                                Object[] obj_usuario = (Object[]) lst_usuarios.get(0);
                                for (int i = 0; i < lst_usuarios.size(); i++) {
                                    if (obj_usuario[6].equals("GESTION DE CALIDAD") || obj_usuario[7].toString().contains("INSPECTORA")) {
                                        out.print("<td align='center'>");
                                        out.print("<span>" + obj_usuario[2] + "</span>");
                                        out.print("</td>");
                                        out.print("<input type='hidden' name='id_area2' value='" + obj_usuario[5] + "'>");
                                        out.print("<input type='hidden' name='firma2' value='" + obj_usuario[2] + "/" + obj_usuario[6] + "'>");
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
                        if (cedula == 0 || codusu == 0) {
                            out.print("<td align='center'>");
                            out.print("<span >Firmar</span>");
                            out.print("</td>");
                            todosFirmados = false;
                        } else if (!obj_movimien[18].equals("N/A")) {
                            out.print("<td align='center'>");
                            out.print("<span>" + obj_movimien[18] + "</span>");
                            out.print("</td>");
                        } else {
                            lst_usuarios = UsuarioJpa.Firma(cedula, codusu);
                            if (lst_usuarios != null) {
                                Object[] obj_usuario = (Object[]) lst_usuarios.get(0);
                                for (int i = 0; i < lst_usuarios.size(); i++) {
                                    if ((obj_usuario[6].equals("PROYECTOS") || obj_usuario[7].toString().contains("COORDINADOR"))) {
                                        out.print("<td align='center'>");
                                        out.print("<span>" + obj_usuario[2] + "</span>");
                                        out.print("</td>");
                                        out.print("<input type='hidden' name='id_area3' value='" + obj_usuario[5] + "'>");
                                        out.print("<input type='hidden' name='firma3' value='" + obj_usuario[2] + "/" + obj_usuario[6] + "'>");
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
                            out.print("<input type='number' class='form-control' name='codusu' id='codigo' placeholder='Codigo' readonly data-toggle='tooltip' data-placement='top' title='Codigo'>");
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
                            out.print("<input type='number' class='form-control' name='codusu' id='codigo' placeholder='Codigo' required data-toggle='tooltip' data-placement='top' title='Codigo'>");
                            out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                            out.print("</div>");

                            out.print("<div class='col-12'>");
                            out.print("<input type='number' class='form-control' name='cedula' id='cedula' placeholder='Cedula' required data-toggle='tooltip' data-placement='top' title='Cedula'>");
                            out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                            out.print("</div>");
                            out.print("</div>");

                        }
                        out.print("<input type='hidden' name='id_maquina' value='" + id_maquina + "'>");
                        out.print("<input type='hidden' name='temp' value='2'>");
                        out.print("<input type='hidden' name='tempo' value='1'>");
                        out.print("<input type='hidden' name='id_movimiento' value='" + id_movimiento + "'>");
                        out.print("<input type='hidden' name='todosFirmados' value='" + todosFirmados + "'>");

                        if (todosFirmados) {
                            out.print("<div class='' style='width: 100%; text-align:center;'>");
                            out.print("<button class='btn btn-green btn-lg'>Guardar</button>");
                            out.print("</div>");
                        } else {
                            out.print("<div class='' style='width: 100%; text-align:center;'>");
                            out.print("<button class='btn btn-green btn-lg'>Firmar</button>");
                            out.print("</div>");
                        }

                        out.print("</form>");
                        out.print("</div>");
                        out.print("</div>");
                        out.print("</div>");
                    }
                }
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="REGISTRAR MOVIMIENTO">
                out.print("<div class='sweet-local' tabindex='-1' id='Ventana3' style='opacity: 1.03; display:" + ((!codigo.equals("")) ? "block" : "none") + ";'>");
                out.print("<div class='cont_reg'>");
                out.print("<div style='display: flex; justify-content: space-between'>");
                out.print("<h2>Registrar Movimiento</h2>");
                out.print("<button class='btn btn-outline-secondary' onclick='mostrarConvencion(3)' style='height: 30px;padding: 3px;width: 30px;'><i class='fas fa-times'></i></button>");
                out.print("</div>");
                out.print("<div class='cont_form_user'>");
                out.print("<form action='Maquina?opc=1' method='post' class='needs-validation' id='registrarform' novalidate=''>");

                out.print("<input type='hidden' name='id_maquina' value='" + id_maquina + "'>");
                out.print("<input type='hidden' name='temp' value='2'>");

                out.print("<div class='col-12'>");
                out.print("<input type='text' class='form-control' name='codigo' style='margin-left: 2%; width: 95%;' onkeypress='enviar1()' placeholder='Codigo Factory' value='" + codigo + "' required='' data-toggle='tooltip' data-placemente='top' title='codigo'>");
                out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                out.print("</div>");

                out.print("</form>");

                if (!"".equals(codigo)) {
                    out.print("<form action='Maquina?opc=4' method='post' class='needs-validation' novalidate=''>");

                    out.print("<input type='hidden' name='id_maquina' value='" + id_maquina + "'>");
                    out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
                    out.print("<div class='col-lg-12' style='margin-top: 2.9%; margin-left: 2%; margin-right: -2%;' data-toggle='tooltip' data-placemente='top' title='Producto'>");
                    out.print("<select class='select2 form-control' name='producto' required style='width: 25% !important;'>");
                    out.print("<option value='' disabled selected>Seleccione Producto</option>");
                    lst_productos = product.Productos(codigo);
                    if (lst_productos != null && !lst_productos.isEmpty()) {
                        for (Object producto : lst_productos) {
                            String productoStr = producto.toString();
                            out.print("<option value='" + productoStr + "'>" + productoStr + "</option>");
                        }
                    } else {
                        out.print("<option value='0'>Se ha producido un error</option>");
                    }
                    out.print("</select>");
                    out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                    out.print("</div>");

                    out.print("<div class='col-12'>");
                    out.print("<input type='datetime-local' class='form-control' name='fecha' placeholder='Fecha' required='' data-toggle='tooltip' data-placemente='top' title='Fecha Registro'>");
                    out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                    out.print("</div>");
                    out.print("</div>");

                    out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
                    out.print("<div class='col-12'>");
                    out.print("<input type='text' class='form-control' name='lote' placeholder='Lote' required='' data-toggle='tooltip' data-placemente='top' title='Lote'>");
                    out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                    out.print("</div>");

                    out.print("<div class='col-12'>");
                    out.print("<input type='number' class='form-control' name='total_cavi' placeholder='Total Cavidades' required='' data-toggle='tooltip' data-placemente='top' title='Total Cavidades'>");
                    out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                    out.print("</div>");
                    out.print("</div>");

                    out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
                    out.print("<div class='col-12'>");
                    out.print("<input type='number' class='form-control' name='cavi_desha' placeholder='Cavidades Deshabilitadas' required='' data-toggle='tooltip' data-placemente='top' title='Cavidades Deshabilitadas'>");
                    out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                    out.print("</div>");

                    out.print("<div class='col-12'>");
                    out.print("<input type='text' class='form-control' name='cavidades' placeholder='Cavidades' required='' data-toggle='tooltip' data-placemente='top' title='Cavidades'>");
                    out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                    out.print("</div>");
                    out.print("</div>");

                    out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
                    out.print("<div class='col-lg-12' style='margin-top: 2.9%; margin-left: 2%; margin-right: -2%; margin-bottom: 6%' data-toggle='tooltip' data-placemente='top' title='Herramental'>");
                    out.print("<select class='select2 form-control' name='herramental' required style='width: 25% !important;'>");
                    out.print("<option value='' disabled selected>Seleccione Herramental</option>");
                    lst_herramentales = jpa_Herramental.ConsultaHerramentales();
                    if (lst_herramentales != null && !lst_herramentales.isEmpty()) {
                        for (int i = 0; i < lst_herramentales.size(); i++) {
                            Object[] obj_herramental = (Object[]) lst_herramentales.get(i);
                            if (Integer.parseInt(obj_herramental[7].toString()) == 3) {
                                out.print("<option value='" + obj_herramental[0] + "' disabled>" + obj_herramental[5] + " | En Reparacion</option>");
                            } else {
                                out.print("<option value='" + obj_herramental[0] + "'>" + obj_herramental[5] + "</option>");
                            }
                        }
                    } else {
                        out.print("<option value='0'>Se ha producido un error</option>");
                    }
                    out.print("</select>");
                    out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                    out.print("</div>");

                    out.print("<div class='col-lg-12' style='margin-top: 2.9%; margin-left: 2%; margin-right: -2%; margin-bottom: 6%' data-toggle='tooltip' data-placemente='top' title='Movimiento'>");
                    out.print("<select class='select2 form-control' name='movimiento' required style='width: 25% !important;'>");
                    out.print("<option value='' disabled selected>Seleccione Movimiento</option>");
                    lst_tipo_movimiento = jpa_movimiento.ConsultaMovimientos();
                    if (lst_tipo_movimiento != null && !lst_tipo_movimiento.isEmpty()) {
                        for (int i = 0; i < lst_tipo_movimiento.size(); i++) {
                            Object[] obj_movimiento = (Object[]) lst_tipo_movimiento.get(i);
                            if ((Integer) obj_movimiento[0] == 4) {
                                out.print("<option value='" + obj_movimiento[0] + "'>" + obj_movimiento[1] + "</option>");
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
                    out.print("<textarea class='form-control' name='descripcion_tapada' id='textareaRegistrar' style='width: 207%; margin-left: 2%; margin-bottom: 6%' placeholder='Descripcion Cavidad Deshabilitada' required data-toggle='tooltip' data-placemente='top' title='Descripcion Cavidad Deshabilitada'></textarea>");
                    out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor válido!</div>");
                    out.print("</div>");
                    out.print("</div>");

                    out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
                    out.print("<div class='col-12'>");
                    out.print("<textarea class='form-control' name='descripcion' id='textareaRegistrar' style='width: 207%; margin-left: 2%; margin-bottom: 6%' placeholder='Descripcion General' required data-toggle='tooltip' data-placemente='top' title='Descripcion General'></textarea>");
                    out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor válido!</div>");
                    out.print("</div>");
                    out.print("</div>");

                    out.print("<div class='' style='width: 100%; text-align:center;'>");
                    out.print("<button class='btn btn-green btn-lg'>Registrar</button>");
                    out.print("</div>");
                }
                out.print("</form>");
                out.print("</div>");
                out.print("</div>");
                out.print("</div>");
//</editor-fold>

                out.print("</div>");
                out.print("</div>");
                out.print("</div>");
                out.print("</section>");
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="TABLA MAQUINAS">
            } else {
                out.print("<section class='section'>");
                out.print("<div class='section-header'>");
                out.print("<h1>Modulo Maquinas</h1>");
                out.print("</div>");
                out.print("<div class='section-body'>");
                out.print("<div class='row'>");
                out.print("<div class='col-12'>");
                out.print("<div class='card'>");
                out.print("<div class='card-header' style='justify-content: space-between;'>");
                out.print("<h4>Tabla de Maquinas</h4>");

                out.print("<form action='Maquina?opc=1' method='post' class='needs-validation' novalidate=''>");
                out.print("<div class='d-flex align-items-center'>");
                out.print("<input type='text' class='form-control' name='parametro' placeholder='Buscar' value='" + (parametro != null ? parametro : "") + "' style='width: 190px; margin-right: 10px;'>");
                out.print("<button class='btn btn-secondary' style='border-radius: 4px;' data-toggle='tooltip' data-placement='top' title='Filtro General'><i class='fas fa-search'></i></button>");
                out.print("</div>");
                out.print("</form>");

                out.print("<button class='btn btn-secondary' style='border-radius: 4px;' onclick='mostrarConvencion(1)' data-toggle='tooltip' data-placement='top' title='Registrar Maquina'><i class='fas fa-plus'></i></button>");
                out.print("</div>");
                out.print("<div class='card-body'>");
                out.print("<div class='table-responsive'>");
                out.print("<table class='table table-hover table-bordered' id='table-1'>");
                out.print("<thead>");
                out.print("<tr>");
                out.print("<th>Nombre</th>");
                out.print("<th>Tipo</th>");
                out.print("<th>Molde Actual</th>");
                out.print("<th>Producto Actual</th>");
                out.print("<th>Estado</th>");
                out.print("<th>Modificar</th>");
                out.print("<th>Historial</th>");
                out.print("<th>Pendientes</th>");
                out.print("</tr>");
                out.print("</thead>");
                out.print("<tbody>");
                if (parametro != null && !parametro.isEmpty()) {
                    lst_maquinas = jpa_maquina.filtro_general(parametro);
                } else {
                    lst_maquinas = jpa_maquina.ConsultaMaquinas_U_movimientos();
                }
                if (lst_maquinas != null) {
                    for (int i = 0; i < lst_maquinas.size(); i++) {
                        Object[] obj_maquina = (Object[]) lst_maquinas.get(i);
                        out.print("<tr>");
                        out.print("<td>" + obj_maquina[1] + "</td>");
                        out.print("<td align='center'>" + obj_maquina[4] + "</td>");
                        out.print("<td align='center'>" + obj_maquina[7] + "</td>");
                        out.print("<td>" + obj_maquina[8] + "</td>");
                        out.print("<td align='center'>"
                                + (((Integer) obj_maquina[2] == 1) ? "<div class='status-tag positive-medium' onclick=\"maquina_parada(" + obj_maquina[0] + ")\"><p class='status-tag__txt bac-l-stack-xs' data-toggle='tooltip' data-placement='top' data-html='true' title='<span style=\"color: #007bff;\">Herramental:</span> " + obj_maquina[10] + "<br><span style=\"color: #28a745;\">Molde:</span> " + obj_maquina[7] + "<br><span style=\"color: #dc3545;\">Producto:</span> " + obj_maquina[8] + "'>En producción</p></div>"
                                        : (((Integer) obj_maquina[2] == 2) ? "<div class='status-tag attention-low' onclick=\"maquina_parada(" + obj_maquina[0] + ")\"><p class='status-tag__txt bac-l-stack-xs' data-toggle='tooltip' data-placement='top' data-html='true' title='<span style=\"color: #007bff;\">Herramental:</span> " + obj_maquina[10] + "<br><span style=\"color: #28a745;\">Molde:</span> " + obj_maquina[7] + "<br><span style=\"color: #dc3545;\">Producto:</span> " + obj_maquina[8] + "'>En producción Cavidad tapada</p></div>"
                                                : (((Integer) obj_maquina[2] == 3) ? "<div class='status-tag critical-low' onclick=\"maquina_parada(" + obj_maquina[0] + ")\"><p class='status-tag__txt bac-l-stack-xs' data-toggle='tooltip' data-placement='top' data-html='true' title='<span style=\"color: #007bff;\">Herramental:</span> " + obj_maquina[10] + "<br><span style=\"color: #28a745;\">Molde:</span> " + obj_maquina[7] + "<br><span style=\"color: #dc3545;\">Producto:</span> " + obj_maquina[8] + "'>Parada</p></div>"
                                                        : (((Integer) obj_maquina[2] == 0) ? "<div class='status-tag neutral-extrahigh' onclick=\"maquina_parada(" + obj_maquina[0] + ")\"><p class='status-tag__txt bac-l-stack-xs' data-toggle='tooltip' data-placement='top' data-html='true' title='<span style=\"color: #007bff;\">Herramental:</span> " + obj_maquina[10] + "<br><span style=\"color: #28a745;\">Molde:</span> " + obj_maquina[7] + "<br><span style=\"color: #dc3545;\">Producto:</span> " + obj_maquina[8] + "'>Disponible</p></div>" : ""))))
                                + "</td>");
                        out.print("<td align='center'>");
                        out.print("<button type='button' onclick=\"javascript:location.href='Maquina?opc=1&temp=1&id_maquina=" + obj_maquina[0] + "&parametro=" + parametro + "'\" class='btn btn-primary btn-sm btn-icon' data-toggle='tooltip' data-placement='top' title='Modificar Ficha'><i class='fas fa-pencil-alt'></i></button>");
                        out.print("</td>");
                        out.print("<td align='center'>");
                        out.print("<button type='button' onclick=\"javascript:location.href='Maquina?opc=1&temp=2&id_maquina=" + obj_maquina[0] + "&nombre_maquina=" + obj_maquina[1] + "&parametro=" + parametro + "'\" class='btn btn-warning btn-sm btn-icon' data-toggle='tooltip' data-placement='top' title='Movimientos'><i class='fas fa-heading'></i></button>");
                        out.print("</td>");
                        out.print("<td align='center'>");
                        out.print("<button type='button' onclick=\"javascript:location.href='Maquina?opc=1&temp=3&id_maquina=" + obj_maquina[0] + "&nombre_maquina=" + obj_maquina[1] + "&parametro=" + parametro + "'\" class='btn btn-dark btn-sm btn-icon' data-toggle='tooltip' data-placement='top' title='Movimientos'><i class='fas fa-tasks'></i></button>");
                        out.print("</td>");
                        out.print("</tr>");
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
            }
//</editor-fold>
        } catch (IOException ex) {
            Logger.getLogger(Tag_usuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Tag_maquina.class.getName()).log(Level.SEVERE, null, ex);
        }
        return super.doStartTag();
    }
}
