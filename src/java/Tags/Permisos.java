package Tags;

import controladores.RolJpaController;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;


public class Permisos extends TagSupport {
    
    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        HttpSession sesion = pageContext.getSession();
        //<editor-fold defaultstate="collapsed" desc="JPAS">
        RolJpaController jparol = new RolJpaController();
//</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="VARIABLES GLOBALES">
        List lst_roles = null;
        int estado = 0;
//</editor-fold>
        try {
            //<editor-fold defaultstate="collapsed" desc="TABLA ROLES">
            out.print("<section class='section'>");
            out.print("<div class='section-header'>");
            out.print("<h1>Modulo Permisos</h1>");
            out.print("</div>");
            out.print("<div class='section-body'>");
            out.print("<div class='row'>");
            out.print("<div class='col-12'>");
            out.print("<div class='card'>");
            out.print("<div class='card-header' style='justify-content: space-between;'>");
            out.print("<h4>Tabla de Permisos</h4>");
            out.print("<button class='btn btn-secondary' style='border-radius: 4px;' onclick='mostrarConvencion(1)' data-toggle='tooltip' data-placement='top' title='Registrar Rol'><i class='fas fa-plus'></i></button>");
            out.print("</div>");
            out.print("<div class='card-body'>");
            out.print("<div class='table-responsive'>");
            out.print("<table class='table table-hover table-bordered' id='table-1'>");
            out.print("<thead>");
            out.print("<tr>");
            out.print("<th>ID</th>");
            out.print("<th>Nombre</th>");
            out.print("<th>Estado</th>");
            out.print("<th>Permisos</th>");
            out.print("<th>Modificar</th>");
            out.print("<th>Opciones</th>");
            out.print("</tr>");
            out.print("</thead>");
            out.print("<tbody>");
            lst_roles = jparol.Roles();
            if (lst_roles != null) {
                for (int i = 0; i < lst_roles.size(); i++) {
                    Object[] obj_rol = (Object[]) lst_roles.get(i);
                    if (Integer.parseInt(obj_rol[3].toString()) == 1) {
                        estado = Integer.parseInt(obj_rol[3].toString());
                        out.print("<tr>");
                        out.print("<td align='center'>" + obj_rol[0] + "</td>");
                        out.print("<td align='center'>" + obj_rol[1] + "</td>");
                        out.print("<td class='text-center'>");
                        out.print("<div class='badge badge-success'>Activo</div>");
                        out.print("</td>");
                        out.print("<td align='center'>");
                        out.print("<button type='button' onclick=\"javascript:location.href='Rol?opc=1&id_rol_permission=" + obj_rol[0] + "'\" class='btn btn-sm btn-primary btn-icon' data-toggle='tooltip' data-placement='top' title='Asignar permisos'><i class='fas fa-shield-alt'></i></button>");
                        out.print("</td>");
                        out.print("<td align='center'>");
                        out.print("<button type='button' onclick=\"javascript:location.href='Rol?opc=1&id_rol_permission=" + obj_rol[0] + "'\" class='btn btn-warning btn-sm btn-icon' data-toggle='tooltip' data-placement='top' title='Modificar Rol'><i class='fas fa-pencil-alt'></i></button>");
                        out.print("</td>");
                        out.print("<td align='center'>");
                        out.print("<button type='button' onclick='RestablecerPassword(" + obj_rol[0] + ")' class='btn btn-success btn-sm btn-icon' data-toggle='tooltip' data-placement='top' title='Cambiar Estado Rol'><i class='fas fa-check'></i></button>");
                        out.print("</td>");
                        out.print("</tr>");
                    } else {
                        out.print("<tr style='background-color: #ffd6d4'>");
                        out.print("<td align='center'>" + obj_rol[0] + "</td>");
                        out.print("<td align='center'>" + obj_rol[1] + "</td>");
                        out.print("<td class='text-center'>");
                        out.print("<div class='badge badge-danger'>Inactivo</div>");
                        out.print("</td>");
                        out.print("<td align='center'>");
                        out.print("<button class='btn btn-light btn-sm' style='border-radius: 4px; cursor: not-allowed;' data-toggle='tooltip' data-placement='top' title='No disponible'><i class='fas fa-shield-alt'></i></button>");
                        out.print("</td>");
                        out.print("<td align='center'>");
                        out.print("<button class='btn btn-light btn-sm' style='border-radius: 4px; cursor: not-allowed;' data-toggle='tooltip' data-placement='top' title='No disponible'><i class='fas fa-pencil-alt'></i></button>");
                        out.print("</td>");
                        out.print("<td align='center'>");
                        out.print("<button type='button' onclick='RestablecerPassword(" + obj_rol[0] + ")' class='btn btn-danger btn-sm btn-icon' data-toggle='tooltip' data-placement='top' title='Cambiar Estado Rol'><i class='fas fa-check'></i></button>");
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
