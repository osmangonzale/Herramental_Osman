package Tags;

import controladores.TipoMaquinaJpaController;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class Tag_tipo_maquina extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        //<editor-fold defaultstate="collapsed" desc="SESION">
        HttpSession sesion = pageContext.getSession();
//</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="JPAS">
        TipoMaquinaJpaController jpa_tipoMaquina = new TipoMaquinaJpaController();
//</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="VARIABLES GLOBALES">
        List lst_tipo_maquina = null;
        List lst_areas = null;
        int estado, id_TipoM = 0;
//</editor-fold>
        try {
            try {
                id_TipoM = Integer.parseInt(pageContext.getRequest().getAttribute("Id_tipoMaquina").toString());
            } catch (NumberFormatException e) {
                id_TipoM = 0;
            }
            //<editor-fold defaultstate="collapsed" desc="MODIFICAR TIPO MAQUINA">
            if (id_TipoM > 0) {
                lst_tipo_maquina = jpa_tipoMaquina.ConsultaTipoMaquinasId(id_TipoM);
                if (lst_tipo_maquina != null) {
                    Object[] obj_tipoMaquina = (Object[]) lst_tipo_maquina.get(0);
                    out.print("<div class='sweet-local' tabindex='-1' id='Ventana2' style='opacity: 1.03; display:block;'>");
                    out.print("<div class='cont_reg'>");
                    out.print("<div style='display: flex; justify-content: space-between'>");
                    out.print("<h2>Editar Tipo Maquina " + obj_tipoMaquina[1].toString() + "</h2>");
                    out.print("<button class='btn btn-outline-secondary' onclick='mostrarConvencion(2)' style='height: 30px;padding: 3px;width: 30px;'><i class='fas fa-times'></i></button>");
                    out.print("</div>");
                    out.print("<div class='cont_form_user'>");
                    out.print("<form action='Tipo_maquina?opc=3&Id_tipoMaquina=" + obj_tipoMaquina[0] + "' method='post' class='needs-validation' novalidate=''>");

                    out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
                    out.print("<div class='col-12'>");
                    out.print("<input type='text' class='form-control' name='nombre' placeholder='Nombre' value='" + obj_tipoMaquina[1] + "' required='' data-toggle='tooltip' data-placement='top' title='Nombre'>");
                    out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                    out.print("</div>");

                    out.print("<div class='col-lg-12' style='margin-top:2.9%; margin-left:3%; margin-right:-3%; text-align:left;'>");
                    out.print("<select class='select2 form-control' name='Area' data-toggle='tooltip' data-placement='top'>");
                    lst_areas = jpa_tipoMaquina.ConsultaAreas();
                    if (lst_areas != null || lst_areas.size() != 0) {
                        for (int i = 0; i < lst_areas.size(); i++) {
                            Object[] obj_area = (Object[]) lst_areas.get(i);
                            String selected = obj_tipoMaquina[3].toString().equals(obj_area[0].toString()) ? "selected" : "";
                            out.print("<option value='" + obj_area[0] + "' " + selected + ">" + obj_area[1] + "</option>");
                        }
                    } else {
                        out.print("<option value='0'>Se ha producido un error</option>");
                    }
                    out.print("</select>");
                    out.print("</div>");
                    out.print("</div>");

                    out.print("<div class='' style='width: 100%; text-align:center; margin-top: 25px;'>");
                    out.print("<button class='btn btn-secondary btn-lg'>Editar</button>");
                    out.print("</div>");

                    out.print("</form>");
                    out.print("</div>");
                    out.print("</div>");
                    out.print("</div>");
                }
            }
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="REGISTRAR TIPO MAQUINA">
            out.print("<div class='sweet-local' tabindex='-1' id='Ventana1' style='opacity: 1.03; display:none;'>");
            out.print("<div class='cont_reg'>");
            out.print("<div style='display: flex; justify-content: space-between'>");
            out.print("<h2>Registrar Tipo Maquina</h2>");
            out.print("<button class='btn btn-outline-secondary' onclick='mostrarConvencion(1)' style='height: 30px;padding: 3px;width: 30px;'><i class='fas fa-times'></i></button>");
            out.print("</div>");
            out.print("<div class='cont_form_user'>");
            out.print("<form action='Tipo_maquina?opc=2' method='post' class='needs-validation' novalidate=''>");

            out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
            out.print("<div class='col-12'>");
            out.print("<input type='text' class='form-control' name='nombre' placeholder='Nombre' required data-toggle='tooltip' data-placemente='top' title='Nombre'>");
            out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
            out.print("</div>");

            lst_areas = jpa_tipoMaquina.ConsultaAreas();
            if (lst_areas != null) {
                out.print("<div class='col-lg-12' style='margin-top:2.9%; margin-left:3%; margin-right:-3%; text-align:left;'  data-toggle='tooltip' data-placemente='top' title='Seleccione Area'>");
                out.print("<select class='select2 form-control' name='Area' data-toggle='tooltip' data-placement='top' required>");
                out.print("<option value='' disabled selected>Seleccione una Area</option>");
                for (int i = 0; i < lst_areas.size(); i++) {
                    Object[] obj_area = (Object[]) lst_areas.get(i);
                    out.print("<option value='" + obj_area[0] + "'>" + obj_area[1] + "</option>");
                }
            } else {
                out.print("<option value='0'>Se ha producido un error</option>");
            }
            out.print("</select>");
            out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
            out.print("</div>");
            out.print("</div>");

            out.print("<div class='' style='width: 100%; text-align:center; margin-top: 25px;'>");
            out.print("<button class='btn btn-secondary btn-lg'>Registrar</button>");
            out.print("</div>");

            out.print("</form>");
            out.print("</div>");
            out.print("</div>");
            out.print("</div>");
            //</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="TABLA TIPO MAQUINA">
            out.print("<section class='section'>");
            out.print("<div class='section-header'>");
            out.print("<h1>Modulo Tipo Maquina</h1>");
            out.print("</div>");
            out.print("<div class='section-body'>");
            out.print("<div class='row'>");
            out.print("<div class='col-12'>");
            out.print("<div class='card'>");
            out.print("<div class='card-header' style='justify-content: space-between;'>");
            out.print("<h4>Tabla de Tipo Maquina</h4>");
            out.print("<button class='btn btn-secondary' style='border-radius: 4px;' onclick='mostrarConvencion(1)' data-toggle='tooltip' data-placement='top' title='Registrar Tipo Maquina'><i class='fas fa-plus'></i></button>");
            out.print("</div>");
            out.print("<div class='card-body'>");
            out.print("<div class='table-responsive'>");
            out.print("<table class='table table-hover table-bordered' id='table-1'>");
            out.print("<thead>");
            out.print("<tr>");
            out.print("<th>Tipo Maquina</th>");
            out.print("<th>Area</th>");
            out.print("<th>Estado</th>");
            out.print("<th>Modificar</th>");
            out.print("</tr>");
            out.print("</thead>");
            out.print("<tbody>");
            lst_tipo_maquina = jpa_tipoMaquina.ConsultaTipoMaquinas();
            if (lst_tipo_maquina != null) {
                for (int i = 0; i < lst_tipo_maquina.size(); i++) {
                    Object[] obj_tipo_maquina = (Object[]) lst_tipo_maquina.get(i);
                    if (Integer.parseInt(obj_tipo_maquina[2].toString()) == 1) {
                        estado = Integer.parseInt(obj_tipo_maquina[2].toString());
                        out.print("<tr>");
                        out.print("<td>" + obj_tipo_maquina[1] + "</td>");
                        out.print("<td>" + obj_tipo_maquina[4] + "</td>");
                        out.print("<td align='center'>");
                        out.print("<button type='button' onclick='DesactivarTipoMaquina(" + obj_tipo_maquina[0] + ")' class='btn btn-success btn-sm btn-icon' data-toggle='tooltip' data-placement='top' title='Desactivar Tipo Maquina'><i class='fas fa-check'></i></button>");
                        out.print("</td>");
                        out.print("<td align='center'>");
                        out.print("<button type='button' onclick=\"javascript:location.href='Tipo_maquina?opc=1&Id_tipoMaquina=" + obj_tipo_maquina[0] + "'\" class='btn btn-primary btn-sm btn-icon' data-toggle='tooltip' data-placement='top' title='Modificar Tipo Maquina'><i class='fas fa-pencil-alt'></i></button>");
                        out.print("</td>");
                        out.print("</tr>");
                    } else {
                        out.print("<tr style='background-color: #ffd6d4'>");
                        out.print("<td>" + obj_tipo_maquina[1] + "</td>");
                        out.print("<td>" + obj_tipo_maquina[4] + "</td>");
                        out.print("<td align='center'>");
                        out.print("<button type='button' onclick='ActivarTipoMaquina(" + obj_tipo_maquina[0] + ")' class='btn btn-danger btn-sm btn-icon' style='width: 28px;' data-toggle='tooltip' data-placement='top' title='Activar Tipo Maquina'><i class='fas fa-times'></i></button>");
                        out.print("</td>");
                        out.print("<td align='center'>");
                        out.print("<button class='btn btn-light btn-sm' style='border-radius: 4px; cursor: not-allowed;' data-toggle='tooltip' data-placement='top' title='No disponible'><i class='fas fa-pencil-alt'></i></button>");
                        out.print("</td>");
                        out.print("</tr>");
                    }
                }
            } else {
                out.print("<tr>");
                out.print("<td colspan='4' align='center'>");
                out.print("<h1 style='text-align: center;'>No se han encontrado resultados historial!</h1>");
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
        } catch (IOException ex) {
            Logger.getLogger(Tag_usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return super.doStartTag();
    }
}
