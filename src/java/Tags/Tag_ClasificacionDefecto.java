package Tags;

import controladores.ClasificacionDefectoJpaController;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class Tag_ClasificacionDefecto extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        //<editor-fold defaultstate="collapsed" desc="SESION">
        HttpSession sesion = pageContext.getSession();
//</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="JPAS">
        ClasificacionDefectoJpaController jps_clasificacionD = new ClasificacionDefectoJpaController();
//</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="VARIABLES GLOBALES">
        List lst_clasificacionD = null;
        int estado, id_defecto = 0;
//</editor-fold>
        try {
            try {
                id_defecto = Integer.parseInt(pageContext.getRequest().getAttribute("Id_C_defecto").toString());
            } catch (NumberFormatException e) {
                id_defecto = 0;
            }
            //<editor-fold defaultstate="collapsed" desc="MODIFICAR CLISIFICACION DE DEFECTO">
            if (id_defecto > 0) {
                lst_clasificacionD = jps_clasificacionD.ConsultaClasificacionId(id_defecto);
                if (lst_clasificacionD != null) {
                    Object[] obj_clasificacion = (Object[]) lst_clasificacionD.get(0);
                    out.print("<div class='sweet-local' tabindex='-1' id='Ventana2' style='opacity: 1.03; display:block;'>");
                    out.print("<div class='cont_reg'>");
                    out.print("<div style='display: flex; justify-content: space-between'>");
                    out.print("<h2>Editar Clasificacion de Defecto " + obj_clasificacion[1].toString() + "</h2>");
                    out.print("<button class='btn btn-outline-secondary' onclick='mostrarConvencion(2)' style='height: 30px;padding: 3px;width: 30px;'><i class='fas fa-times'></i></button>");
                    out.print("</div>");
                    out.print("<div class='cont_form_user'>");
                    out.print("<form action='Clasificacion_defecto?opc=3&Id_C_defecto=" + obj_clasificacion[0] + "' method='post' class='needs-validation' novalidate=''>");

                    out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
                    out.print("<div class='col-12'>");
                    out.print("<input type='text' class='form-control' name='clasificacion' placeholder='Clasificacion' value='" + obj_clasificacion[1] + "' required='' data-toggle='tooltip' data-placement='top' title='Clasificacion'>");
                    out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                    out.print("</div>");

                    out.print("<div class='col-12'>");
                    out.print("<input type='text' class='form-control' name='convencion' placeholder='Convencion' value='" + obj_clasificacion[3] + "' required='' data-toggle='tooltip' data-placement='top' title='Convencion'>");
                    out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                    out.print("</div>");
                    out.print("</div>");

                    out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
                    out.print("<div class='col-12'>");
                    out.print("<textarea class='form-control' name='descripcion' id='textareaModificar' style='width: 207%; height: 200px; margin-left: 3%;' placeholder='Descripcion Clasificacion de Defecto' required='' data-toggle='tooltip' data-placemente='top' title='Descripcion Clasificacion de Defecto'>");
                    String descripcionLimpia = convertirHtmlATexto(obj_clasificacion[2].toString());
                    out.print(descripcionLimpia);
                    out.print("</textarea>");
                    out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                    out.print("</div>");
                    out.print("</div>");

                    out.print("<div class='' style='width: 100%; text-align:center; margin-top: 25px;'>");
                    out.print("<button class='btn btn-secondary btn-lg' id='btnModificar'>Editar</button>");
                    out.print("</div>");

                    out.print("</form>");
                    out.print("</div>");
                    out.print("</div>");
                    out.print("</div>");
                }
            }
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="REGISTRAR CLASIFICACION DEFECTO">
            out.print("<div class='sweet-local' tabindex='-1' id='Ventana1' style='opacity: 1.03; display:none;'>");
            out.print("<div class='cont_reg'>");
            out.print("<div style='display: flex; justify-content: space-between'>");
            out.print("<h2>Registrar Clasificacion de Defecto</h2>");
            out.print("<button class='btn btn-outline-secondary' onclick='mostrarConvencion(1)' style='height: 30px;padding: 3px;width: 30px;'><i class='fas fa-times'></i></button>");
            out.print("</div>");
            out.print("<div class='cont_form_user'>");
            out.print("<form action='Clasificacion_defecto?opc=2' method='post' class='needs-validation' novalidate=''>");

            out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
            out.print("<div class='col-12'>");
            out.print("<input type='text' class='form-control' name='clasificacion' placeholder='Clasificacion' required data-toggle='tooltip' data-placemente='top' title='Clasificacion'>");
            out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
            out.print("</div>");

            out.print("<div class='col-12'>");
            out.print("<input type='text' class='form-control' name='convencion' placeholder='Convencion' required data-toggle='tooltip' data-placemente='top' title='Convencion'>");
            out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
            out.print("</div>");
            out.print("</div>");

            out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
            out.print("<div class='col-12'>");
            out.print("<textarea class='form-control' name='descripcion' id='textareaRegistrar' style='width: 207%; margin-left: 3%;' placeholder='Descripcion Clasificacion de Defecto' required data-toggle='tooltip' data-placemente='top' title='Descripcion Clasificacion de Defecto'></textarea>");
            out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor válido!</div>");
            out.print("</div>");
            out.print("</div>");

            out.print("<div class='' style='width: 100%; text-align:center; margin-top: 25px;'>");
            out.print("<button class='btn btn-secondary btn-lg' id='btnRegistrar'>Registrar</button>");
            out.print("</div>");

            out.print("</form>");
            out.print("</div>");
            out.print("</div>");
            out.print("</div>");
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="TABLA CLASIFICACION DEFECTO">
            out.print("<section class='section'>");
            out.print("<div class='section-header'>");
            out.print("<h1>Modulo Clasificacion Defecto</h1>");
            out.print("</div>");
            out.print("<div class='section-body'>");
            out.print("<div class='row'>");
            out.print("<div class='col-12'>");
            out.print("<div class='card'>");
            out.print("<div class='card-header' style='justify-content: space-between;'>");
            out.print("<h4>Tabla de Clasificacion Defecto</h4>");
            out.print("<button class='btn btn-secondary' style='border-radius: 4px;' onclick='mostrarConvencion(1)' data-toggle='tooltip' data-placement='top' title='Registrar Clasificacion Defecto'><i class='fas fa-plus'></i></button>");
            out.print("</div>");
            out.print("<div class='card-body'>");
            out.print("<div class='table-responsive'>");
            out.print("<table class='table table-hover table-bordered' id='table-1'>");
            out.print("<thead>");
            out.print("<tr>");
            out.print("<th>Convencion</th>");
            out.print("<th>Defecto</th>");
            out.print("<th>Descripcion</th>");
            out.print("<th>Estado</th>");
            out.print("<th>Modificar</th>");
            out.print("</tr>");
            out.print("</thead>");
            out.print("<tbody>");
            lst_clasificacionD = jps_clasificacionD.ConsultaClasificacion();
            if (lst_clasificacionD != null) {
                for (int i = 0; i < lst_clasificacionD.size(); i++) {
                    Object[] obj_clasificacion = (Object[]) lst_clasificacionD.get(i);
                    if (Integer.parseInt(obj_clasificacion[4].toString()) == 1) {
                        estado = Integer.parseInt(obj_clasificacion[4].toString());
                        out.print("<tr>");
                        out.print("<td>" + obj_clasificacion[3] + "</td>");
                        out.print("<td>" + obj_clasificacion[1] + "</td>");
                        out.print("<td>" + obj_clasificacion[2] + "</td>");
                        out.print("<td align='center'>");
                        out.print("<button type='button' onclick='InactivarClasificacion(" + obj_clasificacion[0] + ")' class='btn btn-success btn-sm btn-icon' data-toggle='tooltip' data-placement='top' title='Desactivar Clasificacion Defecto'><i class='fas fa-check'></i></button>");
                        out.print("</td>");
                        out.print("<td align='center'>");
                        out.print("<button type='button' onclick=\"javascript:location.href='Clasificacion_defecto?opc=1&Id_C_defecto=" + obj_clasificacion[0] + "'\" class='btn btn-primary btn-sm btn-icon' data-toggle='tooltip' data-placement='top' title='Modificar Clasificacion Defecto'><i class='fas fa-pencil-alt'></i></button>");
                        out.print("</td>");
                        out.print("</tr>");
                    } else {
                        out.print("<tr style='background-color: #ffd6d4'>");
                        out.print("<td>" + obj_clasificacion[3] + "</td>");
                        out.print("<td>" + obj_clasificacion[1] + "</td>");
                        out.print("<td>" + obj_clasificacion[2] + "</td>");
                        out.print("<td align='center'>");
                        out.print("<button type='button' onclick='ActivarClasificacion(" + obj_clasificacion[0] + ")' class='btn btn-danger btn-sm btn-icon' style='width: 28px;' data-toggle='tooltip' data-placement='top' title='Activar Clasificacion Defecto'><i class='fas fa-times'></i></button>");
                        out.print("</td>");
                        out.print("<td align='center'>");
                        out.print("<button class='btn btn-light btn-sm' style='border-radius: 4px; cursor: not-allowed;' data-toggle='tooltip' data-placement='top' title='No disponible'><i class='fas fa-pencil-alt'></i></button>");
                        out.print("</td>");
                        out.print("</tr>");
                    }
                }
            } else {
                out.print("<tr>");
                out.print("<td colspan='8' align='center'>");
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
    public String convertirHtmlATexto(String descripcionHtml) {
        if (descripcionHtml == null || descripcionHtml.isEmpty()) {
            return "";
        }

        String descripcionTexto = descripcionHtml
                .replaceAll("<div>", "")
                .replaceAll("</div>", "")
                .replaceAll("<li>", "- ")
                .replaceAll("</li>", "\n")
                .replaceAll("<ul>", "")
                .replaceAll("</ul>", "")
                .replaceAll("<p>", "\n")
                .replaceAll("</p>", "")
                .replaceAll("<strong>", "")
                .replaceAll("</strong>", "")
                .replaceAll("<font[^>]*>", "")
                .replaceAll("</font>", "")
                .replaceAll("&nbsp;", " ")
                .replaceAll("\n+Aceptación:", " Aceptación:")
                .replaceAll("Aceptación:", "\nAceptación:")
                .replaceAll("\n+", "\n");

        return descripcionTexto.trim();
    }
}
