package Tags;

import controladores.RolJpaController;
import controladores.UsuarioJpaController;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class Tag_usuario extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        //<editor-fold defaultstate="collapsed" desc="JPAS">
        UsuarioJpaController jpa_usuario = new UsuarioJpaController();
        RolJpaController jpacrol = new RolJpaController();
//</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="VARIABLES GLOBALES">
        List lst_usuarios = null;
        List lst_roles = null;
        List lst_areas = null;
        List lst_cargos = null;
        int Id_usuario = 0;
        int estado = 0;
//</editor-fold>
        try {
            //<editor-fold defaultstate="collapsed" desc="VARIABLES DE CONTEXTO">
            try {
                Id_usuario = Integer.parseInt(pageContext.getRequest().getAttribute("Id_usuario").toString());
            } catch (NumberFormatException e) {
                Id_usuario = 0;
            }
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="MODIFICAR">
            if (Id_usuario > 0) {
                lst_usuarios = jpa_usuario.ConsultaUsuarioId(Id_usuario);
                if (lst_usuarios != null) {
                    Object[] obj_usuarios = (Object[]) lst_usuarios.get(0);
                    out.print("<div class='sweet-local' tabindex='-1' id='Ventana2' style='opacity: 1.03; display:block;'>");
                    out.print("<div class='cont_reg'>");
                    out.print("<div style='display: flex; justify-content: space-between'>");
                    out.print("<h2>Editar Usuario " + obj_usuarios[1].toString() + "</h2>");
                    out.print("<button class='btn btn-outline-secondary' onclick='mostrarConvencion(2)' style='height: 30px;padding: 3px;width: 30px;'><i class='fas fa-times'></i></button>");
                    out.print("</div>");
                    out.print("<div class='cont_form_user'>");
                    out.print("<form action='Usuario?opc=3&Id_usuario=" + obj_usuarios[0] + "' method='post' class='needs-validation' novalidate=''>");

                    out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
                    out.print("<div class='col-12'>");
                    out.print("<input type='text' class='form-control' name='Txt_nombre' placeholder='Nombre' value='" + obj_usuarios[1] + "' required='' data-toggle='tooltip' data-placement='top' title='Nombre'>");
                    out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                    out.print("</div>");

                    out.print("<div class='col-12'>");
                    out.print("<input type='text' class='form-control' name='Txt_apellido' placeholder='Apellido' value='" + obj_usuarios[2] + "' required='' data-toggle='tooltip' data-placement='top' title='Apellido'>");
                    out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                    out.print("</div>");
                    out.print("</div>");

                    out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
                    out.print("<div class='col-12'>");
                    out.print("<input type='number' class='form-control' name='Txt_documento' placeholder='Documento' value='" + obj_usuarios[3] + "' required data-toggle='tooltip' data-placement='top' title='Documento'>");
                    out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                    out.print("</div>");

                    out.print("<div class='col-12'>");
                    out.print("<input type='number' class='form-control' name='Txt_codigo' placeholder='Codigo' value='" + obj_usuarios[4] + "' required data-toggle='tooltip' data-placement='top' title='Codigo'>");
                    out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                    out.print("</div>");
                    out.print("</div>");

                    out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
                    out.print("<div class='col-12'>");
                    out.print("<input type='text' class='form-control' name='Txt_usuario' placeholder='Usuario' value='" + obj_usuarios[5] + "' required data-toggle='tooltip' data-placement='top' title='Usuario'>");
                    out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                    out.print("</div>");

                    out.print("<div class='col-lg-12' style='margin-top:2.9%; margin-left:3%; margin-right:-3%; text-align:left;' data-toggle='tooltip' data-placemente='top' title='Seleccione Rol'>");
                    out.print("<select class='select2 form-control' name='Rol' required>");
                    lst_roles = jpacrol.Roles();
                    if (lst_roles != null || lst_roles.size() != 0) {
                        for (int i = 0; i < lst_roles.size(); i++) {
                            Object[] obj_rol = (Object[]) lst_roles.get(i);
                            String selected = obj_rol[0].toString().equals(obj_usuarios[8].toString()) ? "selected" : "";
                            out.print("<option value='" + obj_rol[0] + "' " + selected + ">" + obj_rol[1] + "</option>");
                        }
                    } else {
                        out.print("<option value='0'>Se ha producido un error</option>");
                    }
                    out.print("</select>");
                    out.print("</div>");
                    out.print("</div>");

                    out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
                    out.print("<div class='col-lg-12' style='margin-top:2.9%; margin-left:3%; margin-right:-3%; text-align:left;' data-toggle='tooltip' data-placemente='top' title='Seleccione Area'>");
                    out.print("<select class='select2 form-control' name='Area' required>");
                    lst_areas = jpa_usuario.Areas();
                    if (lst_roles != null || lst_areas.size() != 0) {
                        for (int i = 0; i < lst_areas.size(); i++) {
                            Object[] obj_area = (Object[]) lst_areas.get(i);
                            String selected = obj_area[0].toString().equals(obj_usuarios[13].toString()) ? "selected" : "";
                            out.print("<option value='" + obj_area[0] + "' " + selected + ">" + obj_area[1] + "</option>");
                        }
                    } else {
                        out.print("<option value='0'>Se ha producido un error</option>");
                    }
                    out.print("</select>");
                    out.print("</div>");

                    out.print("<div class='col-lg-12' style='margin-top:2.9%; margin-left:3%; margin-right:-3%; text-align:left;' data-toggle='tooltip' data-placemente='top' title='Seleccione Cargo'>");
                    out.print("<select class='select2' name='Cargo' required>");
                    out.print("<option value='" + obj_usuarios[13] + "'>" + obj_usuarios[13].toString() + "</option>");
                    lst_cargos = jpa_usuario.ConsultarParametros_xCargo("cargo");
                    if (lst_cargos != null && !lst_cargos.isEmpty()) {
                        for (int i = 0; i < lst_cargos.size(); i++) {
                            Object[] obj_cargo = (Object[]) lst_cargos.get(i);
                            out.print("<option value='" + obj_cargo[3] + "'>" + obj_cargo[3] + "</option>");
                        }
                    } else {
                        out.print("<option value='0'>Se ha producido un error</option>");
                    }
                    out.print("</select>");
                    out.print("</div>");
                    out.print("</div>");

                    out.print("<div class='col-12' style='max-width: 94%; margin-left: 17px; margin-top: 27px;'>");
                    out.print("<input type='email' value='" + obj_usuarios[7] + "'class='form-control' name='txt_correo' placeholder='Correo' required='' data-toggle='tooltip' data-placemente='top' title='Correo'>");
                    out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
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
            //<editor-fold defaultstate="collapsed" desc="REGISTRAR">
            out.print("<div class='sweet-local' tabindex='-1' id='Ventana1' style='opacity: 1.03; display:none;'>");
            out.print("<div class='cont_reg'>");
            out.print("<div style='display: flex; justify-content: space-between'>");
            out.print("<h2>Registrar Usuario</h2>");
            out.print("<button class='btn btn-outline-secondary' onclick='mostrarConvencion(1)' style='height: 30px;padding: 3px;width: 30px;'><i class='fas fa-times'></i></button>");
            out.print("</div>");
            out.print("<div class='cont_form_user'>");
            out.print("<form action='Usuario?opc=2' method='post' class='needs-validation' novalidate=''>");

            out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
            out.print("<div class='col-12'>");
            out.print("<input type='text' class='form-control' name='Txt_nombre' placeholder='Nombre' required data-toggle='tooltip' data-placemente='top' title='Nombre'>");
            out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
            out.print("</div>");

            out.print("<div class='col-12'>");
            out.print("<input type='text' class='form-control' name='Txt_apellido' placeholder='Apellido' required data-toggle='tooltip' data-placemente='top' title='Apellido'>");
            out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
            out.print("</div>");
            out.print("</div>");

            out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
            out.print("<div class='col-12'>");
            out.print("<input type='number' class='form-control' name='Txt_documento' placeholder='Documento' required data-toggle='tooltip' data-placemente='top' title='Documento'>");
            out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
            out.print("</div>");

            out.print("<div class='col-12'>");
            out.print("<input type='number' class='form-control' name='Txt_codigo' placeholder='Codigo' required data-toggle='tooltip' data-placemente='top' title='Codigo'>");
            out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
            out.print("</div>");
            out.print("</div>");

            out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
            out.print("<div class='col-12'>");
            out.print("<input type='text' class='form-control' name='Txt_usuario' placeholder='Usuario' required data-toggle='tooltip' data-placemente='top' title='Usuario'>");
            out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
            out.print("</div>");

            out.print("<div class='col-lg-12' style='margin-top:2.9%; margin-left:3%; margin-right:-3%; text-align:left;' data-toggle='tooltip' data-placemente='top' title='Seleccione Rol'>");
            out.print("<select class='select2 form-control' name='Rol' required>");
            out.print("<option value='' disabled selected>Seleccione un Rol</option>");
            lst_roles = jpacrol.Roles();
            if (lst_roles != null) {
                for (int i = 0; i < lst_roles.size(); i++) {
                    Object[] obj_rol = (Object[]) lst_roles.get(i);
                    out.print("<option value='" + obj_rol[0] + "'>" + obj_rol[1] + "</option>");
                }
            } else {
                out.print("<option value='0'>Se ha producido un error</option>");
            }
            out.print("</select>");
            out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
            out.print("</div>");
            out.print("</div>");

            out.print("<div class='col-lg-6 col-md-6' style='text-align: center; display: flex;'>");
            out.print("<div class='col-lg-12' style='margin-top:2.9%; margin-left:3%; margin-right:-3%; text-align:left;'  data-toggle='tooltip' data-placemente='top' title='Seleccione Area'>");
            out.print("<select class='select2 form-control' name='Area' required>");
            out.print("<option value='' disabled selected>Seleccione una Area</option>");
            lst_areas = jpa_usuario.Areas();
            if (lst_areas != null) {
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

            out.print("<div class='col-lg-12' style='margin-top:2.9%; margin-left:3%; margin-right:-3%; text-align:left;'  data-toggle='tooltip' data-placemente='top' title='Seleccione Cargo'>");
            out.print("<select class='select2 form-control' name='Cargo' required>");
            out.print("<option value='' disabled selected>Seleccione un Cargo</option>");
            lst_cargos = jpa_usuario.ConsultarParametros_xCargo("cargo");
            if (lst_cargos != null) {
                for (int i = 0; i < lst_cargos.size(); i++) {
                    Object[] obj_cargo = (Object[]) lst_cargos.get(i);
                    out.print("<option value='" + obj_cargo[3] + "'>" + obj_cargo[3] + "</option>");
                }
            } else {
                out.print("<option value='0'>Se ha producido un error</option>");
            }
            out.print("</select>");
            out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
            out.print("</div>");
            out.print("</div>");

            out.print("<div class='col-12' style='max-width: 94%; margin-left: 17px; margin-top: 27px;'>");
            out.print("<input type='email' class='form-control' name='txt_correo' placeholder='Correo' required='' data-toggle='tooltip' data-placemente='top' title='Correo'>");
            out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
            out.print("</div>");

            out.print("<div class='' style='width: 100%; text-align:center; margin-top: 25px;'>");
            out.print("<button class='btn btn-secondary btn-lg'>Registrar</button>");
            out.print("</div>");

            out.print("</form>");
            out.print("</div>");
            out.print("</div>");
            out.print("</div>");
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="TABLA USUARIOS">
            out.print("<section class='section'>");
            out.print("<div class='section-header'>");
            out.print("<h1>Modulo Usuarios</h1>");
            out.print("</div>");
            out.print("<div class='section-body'>");
            out.print("<div class='row'>");
            out.print("<div class='col-12'>");
            out.print("<div class='card'>");
            out.print("<div class='card-header' style='justify-content: space-between;'>");
            out.print("<h4>Tabla de Usuarios</h4>");
//            if (txtPermisos.contains("[1]")) {
            out.print("<button class='btn btn-secondary' style='border-radius: 4px;' onclick='mostrarConvencion(1)' data-toggle='tooltip' data-placement='top' title='Registrar Usuario'><i class='fas fa-plus'></i></button>");
//            } else {
//                out.print("<button class='btn btn-green' style='border-radius: 4px;opacity: 0.5;' data-toggle='tooltip' data-placement='top' title='No tiene permisos'><i class='fas fa-plus'></i></button>");
//            }
            out.print("</div>");
            out.print("<div class='card-body'>");
            out.print("<div class='table-responsive'>");
            out.print("<table class='table table-hover table-bordered' id='table-1'>");
            out.print("<thead>");
            out.print("<tr>");
            out.print("<th>Nombre</th>");
            out.print("<th>Documento</th>");
            out.print("<th>Código</th>");
            out.print("<th>Usuario</th>");
            out.print("<th>Rol</th>");
            out.print("<th>Estado</th>");
            out.print("<th>Modificar</th>");
            out.print("<th>Restablecer</th>");
            out.print("</tr>");
            out.print("</thead>");
            out.print("<tbody>");
            lst_usuarios = jpa_usuario.Usuarios();
            if (lst_usuarios != null) {
                for (int i = 0; i < lst_usuarios.size(); i++) {
                    Object[] obj_usuarios = (Object[]) lst_usuarios.get(i);
                    if (Integer.parseInt(obj_usuarios[6].toString()) == 1) {
                        estado = Integer.parseInt(obj_usuarios[6].toString());
                        out.print("<tr>");
                        out.print("<td>" + obj_usuarios[1] + "</td>");
                        out.print("<td>" + obj_usuarios[2] + "</td>");
                        out.print("<td>" + obj_usuarios[3] + "</td>");
                        out.print("<td>" + obj_usuarios[4] + "</td>");
                        out.print("<td>" + obj_usuarios[8] + "</td>");
                        out.print("<td align='center'>");
//                        if (txtPermisos.contains("[3]")) {
                        out.print("<button type='button' onclick='DesactivarUsuario(" + obj_usuarios[0] + ")' class='btn btn-success btn-sm btn-icon' data-toggle='tooltip' data-placement='top' title='Desactivar Usuario'><i class='fas fa-check'></i></button>");
//                        } else {
//                            out.print("<button class='btn btn-green btn-sm' style='border-radius: 4px;opacity: 0.5;' data-toggle='tooltip' data-placement='top' title='No tiene permisos'><i class='fas fa-check'></i></i></button>");
//                        }
                        out.print("</td>");
                        out.print("<td align='center'>");
//                        if (txtPermisos.contains("[2]")) {
                        out.print("<button type='button' onclick=\"javascript:location.href='Usuario?opc=1&Id_usuario=" + obj_usuarios[0] + "'\" class='btn btn-primary btn-sm btn-icon' data-toggle='tooltip' data-placement='top' title='Modificar Usuario'><i class='fas fa-pencil-alt'></i></button>");
//                        } else {
//                            out.print("<button class='btn btn-green btn-sm' style='border-radius: 4px;opacity: 0.5;' data-toggle='tooltip' data-placement='top' title='No tiene permisos'><i class='fas fa-pencil-alt'></i></i></button>");
//                        }
                        out.print("</td>");
                        out.print("<td align='center'>");
//                        if (txtPermisos.contains("[4]")) {
                        out.print("<button type='button' onclick='RestablecerPassword(" + obj_usuarios[0] + ")' class='btn btn-warning btn-sm btn-icon' data-toggle='tooltip' data-placement='top' title='Restablecer Password'><i class='fas fa-key'></i></button>");
//                        } else {
//                            out.print("<button class='btn btn-green btn-sm' style='border-radius: 4px;opacity: 0.5;' data-toggle='tooltip' data-placement='top' title='No tiene permisos'><i class='fas fa-key'></i></i></button>");
//                        }
                        out.print("</td>");
                        out.print("</tr>");
                    } else {
                        out.print("<tr style='background-color: #ffd6d4'>");
                        out.print("<td>" + obj_usuarios[1] + "</td>");
                        out.print("<td>" + obj_usuarios[2] + "</td>");
                        out.print("<td>" + obj_usuarios[3] + "</td>");
                        out.print("<td>" + obj_usuarios[4] + "</td>");
                        out.print("<td>" + obj_usuarios[8] + "</td>");
                        out.print("<td align='center'>");
//                        if (txtPermisos.contains("[91]")) {
                        out.print("<button type='button' onclick='ActivarUsuario(" + obj_usuarios[0] + ")' class='btn btn-danger btn-sm btn-icon' style='width: 28px;' data-toggle='tooltip' data-placement='top' title='Activar Usuario'><i class='fas fa-times'></i></button>");
//                        } else {
//                            out.print("<button class='btn btn-green btn-sm' style='border-radius: 4px;opacity: 0.5;' data-toggle='tooltip' data-placement='top' title='No tiene permisos'><i class='fas fa-times'></i></button>");
//                        }
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
//</editor-fold>
        } catch (IOException ex) {
            Logger.getLogger(Tag_usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return super.doStartTag();
    }
}
