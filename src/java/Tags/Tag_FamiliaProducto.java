package Tags;

import controladores.ClasificacionDefectoJpaController;
import controladores.DefectoJpaController;
import controladores.FamiliaProductoJpaController;
import controladores.TipoMaquinaJpaController;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class Tag_FamiliaProducto extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        //<editor-fold defaultstate="collapsed" desc="SESION">
        HttpSession sesion = pageContext.getSession();
//</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="JPAS">
        FamiliaProductoJpaController jpafamilia = new FamiliaProductoJpaController();
        TipoMaquinaJpaController jpatipomaquina = new TipoMaquinaJpaController();
        DefectoJpaController jpadefecto = new DefectoJpaController();
        ClasificacionDefectoJpaController jpaclasificacionD = new ClasificacionDefectoJpaController();
//</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="VARIABLES GLOBALES">
        List lst_familias = null;
        List lst_tipomaquina = null;
        List lst_defectos = null;
        List lst_clasificacionD = null;
        String familia, producto = "";
        int id_familia = 0, temp = 0, estado = 0;
//</editor-fold>
        try {
            //<editor-fold defaultstate="collapsed" desc="VARIABLES DE CONTEXTO">
            try {
                id_familia = Integer.parseInt(pageContext.getRequest().getAttribute("id_familia").toString());
            } catch (NumberFormatException e) {
                id_familia = 0;
            }
            try {
                temp = Integer.parseInt(pageContext.getRequest().getAttribute("temp").toString());
            } catch (NumberFormatException e) {
                temp = 0;
            }
            try {
                familia = pageContext.getRequest().getAttribute("familia").toString();
            } catch (Exception e) {
                familia = "";
            }
            try {
                producto = pageContext.getRequest().getAttribute("producto").toString();
            } catch (Exception e) {
                producto = "";
            }
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="MODIFICAR FAMILIA PRODUCTO">
            if (temp == 1) {
                lst_familias = jpafamilia.ConsultaFamiliaProductoId(id_familia);
                if (lst_familias != null) {
                    Object[] obj_familia = (Object[]) lst_familias.get(0);
                    out.print("<div class='sweet-local' tabindex='-1' id='Ventana2' style='opacity: 1.03; display:block;'>");
                    out.print("<div class='cont_reg'>");
                    out.print("<div style='display: flex; justify-content: space-between'>");
                    out.print("<h2>Editar Familia de Producto " + obj_familia[1].toString() + "</h2>");
                    out.print("<button class='btn btn-outline-secondary' onclick='mostrarConvencion(2)' style='height: 30px;padding: 3px;width: 30px;'><i class='fas fa-times'></i></button>");
                    out.print("</div>");
                    out.print("<div class='cont_form_user'>");
                    out.print("<form action='Familia_producto?opc=3&id_familia=" + obj_familia[0] + "' method='post' class='needs-validation' novalidate=''>");

                    out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
                    out.print("<div class='col-12'>");
                    out.print("<input type='text' class='form-control' name='nombre' placeholder='Nombre' value='" + obj_familia[1] + "' required data-toggle='tooltip' data-placemente='top' title='Nombre'>");
                    out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                    out.print("</div>");

                    out.print("<div class='col-lg-12' style='margin-top:2.5%; margin-left:3%; margin-right:-3%; text-align:left;' data-toggle='tooltip' data-placemente='top' title='Seleccione Tipo'>");
                    out.print("<select class='select2 form-control' name='tipomaquina' required>");
                    lst_tipomaquina = jpatipomaquina.ConsultaTipoMaquinas();
                    if (lst_tipomaquina != null) {
                        for (int i = 0; i < lst_tipomaquina.size(); i++) {
                            Object[] obj_tipomaquina = (Object[]) lst_tipomaquina.get(i);
                            String selected = obj_tipomaquina[0].toString().equals(obj_familia[3].toString()) ? "selected" : "";
                            out.print("<option value='" + obj_tipomaquina[0] + "' " + selected + ">" + obj_tipomaquina[1] + "</option>");
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
            //<editor-fold defaultstate="collapsed" desc="REGISTRAR FAMILIA PRODUCTO">
            out.print("<div class='sweet-local' tabindex='-1' id='Ventana1' style='opacity: 1.03; display:none;'>");
            out.print("<div class='cont_reg'>");
            out.print("<div style='display: flex; justify-content: space-between'>");
            out.print("<h2>Registrar Familia de producto</h2>");
            out.print("<button class='btn btn-outline-secondary' onclick='mostrarConvencion(1)' style='height: 30px;padding: 3px;width: 30px;'><i class='fas fa-times'></i></button>");
            out.print("</div>");
            out.print("<div class='cont_form_user'>");
            out.print("<form action='Familia_producto?opc=2' method='post' class='needs-validation' novalidate=''>");

            out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
            out.print("<div class='col-12'>");
            out.print("<input type='text' class='form-control' name='nombre' placeholder='Nombre' required data-toggle='tooltip' data-placemente='top' title='Nombre'>");
            out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
            out.print("</div>");

            out.print("<div class='col-lg-12' style='margin-top:2.5%; margin-left:3%; margin-right:-3%; text-align:left;' data-toggle='tooltip' data-placemente='top' title='Seleccione Tipo'>");
            out.print("<select class='select2 form-control' name='tipomaquina' required>");
            out.print("<option value='' disabled selected>Seleccione un Tipo de maquina</option>");
            lst_tipomaquina = jpatipomaquina.ConsultaTipoMaquinas();
            if (lst_tipomaquina != null) {
                for (int i = 0; i < lst_tipomaquina.size(); i++) {
                    Object[] obj_tipomaquina = (Object[]) lst_tipomaquina.get(i);
                    out.print("<option value='" + obj_tipomaquina[0] + "'>" + obj_tipomaquina[1] + "</option>");
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
            //<editor-fold defaultstate="collapsed" desc="REGISTRAR DEFECTO">
            out.print("<div class='sweet-local' tabindex='-1' id='Ventana3' style='opacity: 1.03; display:none;'>");
            out.print("<div class='cont_reg'>");
            out.print("<div style='display: flex; justify-content: space-between'>");
            out.print("<h2>Registrar Defecto</h2>");
            out.print("<button class='btn btn-outline-secondary' onclick='mostrarConvencion(3)' style='height: 30px;padding: 3px;width: 30px;'><i class='fas fa-times'></i></button>");
            out.print("</div>");
            out.print("<div class='cont_form_user'>");
            out.print("<form action='Familia_producto?opc=5&temp=2&id_familia=" + id_familia + "&familia=" + familia + "&producto=" + producto + "' method='post' class='needs-validation' novalidate=''>");

            out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
            out.print("<div class='col-12'>");
            out.print("<input type='text' class='form-control' name='codigo' placeholder='Codigo Defecto' required data-toggle='tooltip' data-placemente='top' title='Codigo Defecto'>");
            out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
            out.print("</div>");

            out.print("<div class='col-12'>");
            out.print("<input type='text' class='form-control' name='nombre' placeholder='Nombre Defecto' required data-toggle='tooltip' data-placemente='top' title='Nombre Defecto'>");
            out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
            out.print("</div>");
            out.print("</div>");

            out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
            out.print("<div class='col-lg-12' style='margin-top:2.5%; margin-left:3%; margin-right:-3%; text-align:left;' data-toggle='tooltip' data-placemente='top' title='Seleccione clasificacion de defecto'>");
            out.print("<select class='select2 form-control' name='clasificacionD' required>");
            out.print("<option value='' disabled selected>Seleccione una clasificacion</option>");
            lst_clasificacionD = jpaclasificacionD.ConsultaClasificacion();
            if (lst_clasificacionD != null) {
                for (int i = 0; i < lst_clasificacionD.size(); i++) {
                    Object[] obj_clasificacion = (Object[]) lst_clasificacionD.get(i);
                    out.print("<option value='" + obj_clasificacion[0] + "'>" + obj_clasificacion[1] + "</option>");
                }
            } else {
                out.print("<option value='0'>Se ha producido un error</option>");
            }
            out.print("</select>");
            out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
            out.print("</div>");

            out.print("<div class='col-12'>");
            out.print("<textarea class='form-control' name='descripcion' id='textareaRegistrar' style='width: 100%; margin-left: 2.6%; margin-top: 3%;' placeholder='Descripcion General' required data-toggle='tooltip' data-placemente='top' title='Descripcion General'></textarea>");
            out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor válido!</div>");
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
            //<editor-fold defaultstate="collapsed" desc="REGISTRO R-GC-211">
            if (temp == 2) {
                out.print("<section class='section'>");
                out.print("<div class='section-header'>");
                out.print("<h1>Modulo Catalogo Defectos</h1>");
                out.print("</div>");
                out.print("<div class='section-body'>");
                out.print("<div class='row'>");
                out.print("<div class='col-12'>");
                out.print("<div class='card'>");
                out.print("<div class='card-header' style='justify-content: space-between;'>");
                out.print("<button class='btn btn-secondary' style='border-radius: 4px; margin-right: 0%;' onclick=\"javascript:location.href='Familia_producto?opc=1'\" data-toggle='tooltip' data-placement='top' title='Volver a Catalogo Defectos'><i class='fas fa-arrow-left'></i></button>");
                out.print("<h4>Tabla Defectos | <span style='color: #f9c827;'>Familia:</span> " + familia + " | <span style='color: #f9c827;'>Producto:</span> " + producto + "</h4>");
                out.print("<button class='btn btn-warning' onclick='Imprimir()' style='position: fixed;bottom: 20px; height: 50px;width: 50px;font-size: 15px;z-index: 1000; right: 20px;' data-toggle='tooltip' data-placement='top' title='Imprimir R-GC-211'><i class='fas fa-print fa-lg'></i></button>");
                out.print("<button class='btn btn-secondary' style='border-radius: 4px;' onclick='mostrarConvencion(3)' data-toggle='tooltip' data-placement='top' title='Registrar Defecto'><i class='fas fa-plus'></i></button>");
                out.print("</div>");
                out.print("<div class='card-body'>");

                out.print("<div id='Imprimir'>");

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
                out.print("<td class='text-center title-cell' style='width: 50%; font-weight: bold;'> REGISTRO</td>");
                out.print("<td class='text-center code-version-cell' style='width: 25%; text-align: center; font-weight: bold;'>");
                out.print("<span style='color: #f9c827;'>CÓDIGO:</span> R-GC-211");
                out.print("</td>");
                out.print("</tr>");
                out.print("<tr>");
                out.print("<td class='text-center title-cell' style='width: 50%; font-weight: bold;'> CATALOGO DE DEFECTOS</td>");
                out.print("<td style='width: 25%; text-align: center; font-weight: bold;'>");
                out.print("<span style='color: #f9c827;'>VERSION:</span> 000");
                out.print("</td>");
                out.print("</tr>");
                out.print("</tbody>");
                out.print("</table>");

                out.print("<table class='table table-sm table-hover table-bordered' style='width: 100%; margin-top: -1%;'>");
                out.print("<thead>");
                out.print("<tr>");
                out.print("<td align='center' style='width: 12%;'><span style='font-weight: bold;'>CODIGO DEFECTO</span></td>");
                out.print("<td align='center' style='width: 13%;'><span style='font-weight: bold;'>NOMBRE DEL DEFECTO</span></td>");
                out.print("<td align='center' style='width: 35%;'><span style='font-weight: bold;'>IMAGEN</span></td>");
                out.print("<td align='center' style='width: 15%;'><span style='font-weight: bold;'>CLASIFICACION</span></td>");
                out.print("<td align='center'><span style='font-weight: bold;'>TRATAMIENTO</span></td>");
                out.print("</tr>");
                out.print("</thead>");

                lst_defectos = jpadefecto.ConsultaDefectos(id_familia);
                if (lst_defectos.size() > 0) {
                    for (int i = 0; i < lst_defectos.size(); i++) {
                        Object[] obj_defecto = (Object[]) lst_defectos.get(i);
                        out.print("<tbody class='body-table'>");
                        String Color = (Integer) obj_defecto[1] == 1 ? "#ffe0e0" : (Integer) obj_defecto[1] == 2 ? "#ffdcc7" : (Integer) obj_defecto[1] == 3 ? "#ffffc6" : (Integer) obj_defecto[1] == 4 ? "#def8d5" : "transparent";
                        out.print("<tr style='background-color: " + Color + ";'>");
                        out.print("<td align='center' style='width: 12%;'><span style='font-weight: bold; color: #ffc300;'>" + obj_defecto[7] + "</span></td>");
                        out.print("<td align='center' style='width: 13%;'><span style='font-weight: bold;'>" + obj_defecto[5] + "</span></td>");
                        out.print("<td align='center' style='width: 35%;'><span style='font-weight: bold;'>" + obj_defecto[6] + "</span></td>");
                        out.print("<td align='center' style='width: 15%;'><span style='font-weight: bold;'>" + obj_defecto[2] + "</span></td>");
                        out.print("<td align='center'><span style='font-weight: bold;'>" + obj_defecto[6] + "</span></td>");
                        out.print("</tr>");
                        out.print("</tbody>");
                    }
                } else {
                    out.print("<tr>");
                    out.print("<td colspan='5' align='center'>");
                    out.print("<h1 style='text-align: center;'><i class='fas fa-frown fa-lg' style='font-size: 100%;'></i> ¡ No se han encontrado resultados !</h1>");
                    out.print("</td>");
                    out.print("</tr>");
                }
                out.print("</table>");

                out.print("</div>");

                out.print("</div>");
                out.print("</div>");
                out.print("</div>");
                out.print("</div>");
                out.print("</div>");
                out.print("</div>");
                out.print("</section>");
            } else {
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="TABLA FAMILIA PRODUCTO">
                out.print("<section class='section'>");
                out.print("<div class='section-header'>");
                out.print("<h1>Modulo Catalogo Defectos</h1>");
                out.print("</div>");
                out.print("<div class='section-body'>");
                out.print("<div class='row'>");
                out.print("<div class='col-12'>");
                out.print("<div class='card'>");
                out.print("<div class='card-header' style='justify-content: space-between;'>");
                out.print("<h4>Tabla de Familia de Productos</h4>");
                out.print("<button class='btn btn-secondary' style='border-radius: 4px;' onclick='mostrarConvencion(1)' data-toggle='tooltip' data-placement='top' title='Registrar Familia de Producto'><i class='fas fa-plus'></i></button>");
                out.print("</div>");
                out.print("<div class='card-body'>");
                out.print("<div class='table-responsive'>");
                out.print("<table class='table table-hover table-bordered' id='table-1'>");
                out.print("<thead>");
                out.print("<tr>");
                out.print("<th>Nombre</th>");
                out.print("<th>Tipo</th>");
                out.print("<th>Estado</th>");
                out.print("<th>Modificar</th>");
                out.print("<th>R-GC-211</th>");
                out.print("</tr>");
                out.print("</thead>");
                out.print("<tbody>");
                lst_familias = jpafamilia.ConsultaFamiliaProducto();
                if (lst_familias != null) {
                    for (int i = 0; i < lst_familias.size(); i++) {
                        Object[] obj_familia = (Object[]) lst_familias.get(i);
                        if (Integer.parseInt(obj_familia[2].toString()) == 1) {
                            estado = Integer.parseInt(obj_familia[2].toString());
                            out.print("<tr>");
                            out.print("<td>" + obj_familia[1] + "</td>");
                            out.print("<td>" + obj_familia[4] + "</td>");
                            out.print("<td align='center'>");
                            out.print("<button type='button' onclick='DesactivarFamilia(" + obj_familia[0] + ")' class='btn btn-success btn-sm btn-icon' data-toggle='tooltip' data-placement='top' title='Desactivar Familia de Producto'><i class='fas fa-check'></i></button>");
                            out.print("</td>");
                            out.print("<td align='center'>");
                            out.print("<button type='button' onclick=\"javascript:location.href='Familia_producto?opc=1&temp=1&id_familia=" + obj_familia[0] + "'\" class='btn btn-primary btn-sm btn-icon' data-toggle='tooltip' data-placement='top' title='Modificar Familia de Producto'><i class='fas fa-pencil-alt'></i></button>");
                            out.print("</td>");
                            out.print("<td align='center'>");
                            out.print("<button type='button' onclick=\"javascript:location.href='Familia_producto?opc=1&temp=2&id_familia=" + obj_familia[0] + "&familia=" + obj_familia[4] + "&producto=" + obj_familia[1] + "'\" class='btn btn-warning btn-sm btn-icon' data-toggle='tooltip' data-placement='top' title='Registro R-GC-211'><i class='fas fa-scroll'></i></button>");
                            out.print("</td>");
                            out.print("</tr>");
                        } else {
                            out.print("<tr style='background-color: #ffd6d4'>");
                            out.print("<td>" + obj_familia[1] + "</td>");
                            out.print("<td>" + obj_familia[4] + "</td>");
                            out.print("<td align='center'>");
                            out.print("<button type='button' onclick='ActivarFamilia(" + obj_familia[0] + ")' class='btn btn-danger btn-sm btn-icon' style='width: 28px;' data-toggle='tooltip' data-placement='top' title='Activar Familia de Producto'><i class='fas fa-times'></i></button>");
                            out.print("</td>");
                            out.print("<td align='center'>");
                            out.print("<button class='btn btn-light btn-sm' style='border-radius: 4px; cursor: not-allowed;' data-toggle='tooltip' data-placement='top' title='No disponible'><i class='fas fa-pencil-alt'></i></button>");
                            out.print("</td>");
                            out.print("<td align='center'>");
                            out.print("<button class='btn btn-light btn-sm' style='border-radius: 4px; cursor: not-allowed;' data-toggle='tooltip' data-placement='top' title='No disponible'><i class='fas fa-key'></i></button>");
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
                //</editor-fold>
            }
        } catch (IOException ex) {
            Logger.getLogger(Tag_usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return super.doStartTag();
    }
}
