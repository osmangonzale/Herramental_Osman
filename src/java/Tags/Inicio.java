package Tags;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class Inicio extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        //<editor-fold defaultstate="collapsed" desc="JPAS">
        HttpSession sesion = pageContext.getSession();
//</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="VARIABLES GLOBALES">
        int bienvenido = 0;
//</editor-fold>
        try {
            //<editor-fold defaultstate="collapsed" desc="VARIABLES DE CONTEXTO">
            try {
                bienvenido = Integer.parseInt(pageContext.getRequest().getAttribute("bienvenido").toString());
            } catch (Exception e) {
                bienvenido = 0;
            }
//</editor-fold>
            out.print("<section class='section'>");
            out.print("<div class='section-header'>");
            out.print("<h1>Pendientes</h1>");
            out.print("<div class='section-header-breadcrumb'>");
            out.print("<div class='breadcrumb-item'>Bienvenido</div>");
            out.print("<div class='breadcrumb-item'>Actividades</div>");
            out.print("</div>");
            out.print("</div>");
            out.print("<div style='display: flex; justify-content: space-between;'>");

            //<editor-fold defaultstate="collapsed" desc="PENDIENTES HERRAMENTAL">
            out.print("<div class='section-body'>");
            out.print("<h2 class='section-title'>Herramentales</h2>");
            out.print("<div class='row'>");
            out.print("<div class='col-12'>");
            out.print("<div class='activities'>");

            out.print("<div class='activity'>");
            out.print("<div class='activity-icon bg-primary text-white shadow-primary'>");
            out.print("<i class='fas fa-comment-alt'></i>");
            out.print("</div>");
            out.print("<div class='activity-detail'>");
            out.print("<div class='mb-2'>");
            out.print("<span class='text-job text-primary'>Fecha</span>");
            out.print("<span class='bullet'></span>");
            out.print("<a class='text-job' href='#'>Molde</a>");
            out.print("<div class='float-right dropdown'>");
            out.print("<a href='#' data-toggle='dropdown'><i class='fas fa-ellipsis-h'></i></a>");
            out.print("<div class='dropdown-menu'>");
            out.print("<div class='dropdown-title'>Opciones</div>");
            out.print("<a href='#' class='dropdown-item has-icon'><i class='fas fa-eye'></i> Ver en modulo</a>");
            out.print("<a href='#' class='dropdown-item has-icon'><i class='fas fa-list'></i> Detalle</a>");
            out.print("<div class='dropdown-divider'></div>");
//            out.print("<a class='dropdown-item has-icon text-danger' onclick='eliminarPendiente(" + id_familia + ")'><i class='fas fa-trash-alt'></i> Eliminar</a>");
            out.print("<a class='dropdown-item has-icon text-danger'><i class='fas fa-trash-alt'></i> Eliminar</a>");
            out.print("</div>");
            out.print("</div>");
            out.print("</div>");
            out.print("<p>Detalle de pendiente herramental</p>");
            out.print("</div>");
            out.print("</div>");

            out.print("<div class='activity'>");
            out.print("<div class='activity-icon bg-primary text-white shadow-primary'>");
            out.print("<i class='fas fa-arrows-alt'></i>");
            out.print("</div>");
            out.print("<div class='activity-detail'>");
            out.print("<div class='mb-2'>");
            out.print("<span class='text-job'>Fecha</span>");
            out.print("<span class='bullet'></span>");
            out.print("<a class='text-job' href='#'>Molde</a>");
            out.print("<div class='float-right dropdown'>");
            out.print("<a href='#' data-toggle='dropdown'><i class='fas fa-ellipsis-h'></i></a>");
            out.print("<div class='dropdown-menu'>");
            out.print("<div class='dropdown-title'>Opciones</div>");
            out.print("<a href='#' class='dropdown-item has-icon'><i class='fas fa-eye'></i> Ver en modulo</a>");
            out.print("<a href='#' class='dropdown-item has-icon'><i class='fas fa-list'></i> Detalle</a>");
            out.print("<div class='dropdown-divider'></div>");
            out.print("<a class='dropdown-item has-icon text-danger'><i class='fas fa-trash-alt'></i> Eliminar</a>");
            out.print("</div>");
            out.print("</div>");
            out.print("</div>");
            out.print("<p>Detalle de pendiente herramental</p>");
            out.print("</div>");
            out.print("</div>");

            out.print("<div class='activity'>");
            out.print("<div class='activity-icon bg-primary text-white shadow-primary'>");
            out.print("<i class='fas fa-unlock'></i>");
            out.print("</div>");
            out.print("<div class='activity-detail'>");
            out.print("<div class='mb-2'>");
            out.print("<span class='text-job'>Fecha</span>");
            out.print("<span class='bullet'></span>");
            out.print("<a class='text-job' href='#'>Molde</a>");
            out.print("<div class='float-right dropdown'>");
            out.print("<a href='#' data-toggle='dropdown'><i class='fas fa-ellipsis-h'></i></a>");
            out.print("<div class='dropdown-menu'>");
            out.print("<div class='dropdown-title'>Opciones</div>");
            out.print("<a href='#' class='dropdown-item has-icon'><i class='fas fa-eye'></i> Ver en modulo</a>");
            out.print("<a href='#' class='dropdown-item has-icon'><i class='fas fa-list'></i> Detalle</a>");
            out.print("<div class='dropdown-divider'></div>");
            out.print("<a class='dropdown-item has-icon text-danger'><i class='fas fa-trash-alt'></i> Eliminar</a>");
            out.print("</div>");
            out.print("</div>");
            out.print("</div>");
            out.print("<p>Detalle de pendiente herramental</p>");
            out.print("</div>");
            out.print("</div>");

            out.print("<div class='activity'>");
            out.print("<div class='activity-icon bg-primary text-white shadow-primary'>");
            out.print("<i class='fas fa-sign-out-alt'></i>");
            out.print("</div>");
            out.print("<div class='activity-detail'>");
            out.print("<div class='mb-2'>");
            out.print("<span class='text-job'>Fecha</span>");
            out.print("<span class='bullet'></span>");
            out.print("<a class='text-job' href='#'>Molde</a>");
            out.print("<div class='float-right dropdown'>");
            out.print("<a href='#' data-toggle='dropdown'><i class='fas fa-ellipsis-h'></i></a>");
            out.print("<div class='dropdown-menu'>");
            out.print("<div class='dropdown-title'>Opciones</div>");
            out.print("<a href='#' class='dropdown-item has-icon'><i class='fas fa-eye'></i> Ver en modulo</a>");
            out.print("<a href='#' class='dropdown-item has-icon'><i class='fas fa-list'></i> Detalle</a>");
            out.print("<div class='dropdown-divider'></div>");
            out.print("<a class='dropdown-item has-icon text-danger'><i class='fas fa-trash-alt'></i> Eliminar</a>");
            out.print("</div>");
            out.print("</div>");
            out.print("</div>");
            out.print("<p>Detalle de pendiente herramental</p>");
            out.print("</div>");
            out.print("</div>");

            out.print("<div class='activity'>");
            out.print("<div class='activity-icon bg-primary text-white shadow-primary'>");
            out.print("<i class='fas fa-trash'></i>");
            out.print("</div>");
            out.print("<div class='activity-detail'>");
            out.print("<div class='mb-2'>");
            out.print("<span class='text-job'>Fecha</span>");
            out.print("<span class='bullet'></span>");
            out.print("<a class='text-job' href='#'>Molde</a>");
            out.print("<div class='float-right dropdown'>");
            out.print("<a href='#' data-toggle='dropdown'><i class='fas fa-ellipsis-h'></i></a>");
            out.print("<div class='dropdown-menu'>");
            out.print("<div class='dropdown-title'>Opciones</div>");
            out.print("<a href='#'class='dropdown-item has-icon'><i class='fas fa-eye'></i> Ver en modulo</a>");
            out.print("<a href='#' class='dropdown-item has-icon'><i class='fas fa-list'></i> Detalle</a>");
            out.print("<div class='dropdown-divider'></div>");
            out.print("<a class='dropdown-item has-icon text-danger'><i class='fas fa-trash-alt'></i> Eliminar</a>");
            out.print("</div>");
            out.print("</div>");
            out.print("</div>");
            out.print("<p>Detalle de pendiente herramental</p>");
            out.print("</div>");
            out.print("</div>");

            out.print("<div class='activity'>");
            out.print("<div class='activity-icon bg-primary text-white shadow-primary'>");
            out.print("<i class='fas fa-trash'></i>");
            out.print("</div>");
            out.print("<div class='activity-detail'>");
            out.print("<div class='mb-2'>");
            out.print("<span class='text-job'>Fecha</span>");
            out.print("<span class='bullet'></span>");
            out.print("<a class='text-job' href='#'>Molde</a>");
            out.print("<div class='float-right dropdown'>");
            out.print("<a href='#' data-toggle='dropdown'><i class='fas fa-ellipsis-h'></i></a>");
            out.print("<div class='dropdown-menu'>");
            out.print("<div class='dropdown-title'>Opciones</div>");
            out.print("<a href='#' class='dropdown-item has-icon'><i class='fas fa-eye'></i> Ver en modulo</a>");
            out.print("<a href='#' class='dropdown-item has-icon'><i class='fas fa-list'></i> Detalle</a>");
            out.print("<div class='dropdown-divider'></div>");
            out.print("<a class='dropdown-item has-icon text-danger'><i class='fas fa-trash-alt'></i> Eliminar</a>");
            out.print("</div>");
            out.print("</div>");
            out.print("</div>");
            out.print("<p>Detalle de pendiente herramental</p>");
            out.print("</div>");
            out.print("</div>");

            out.print("</div>");
            out.print("</div>");
            out.print("</div>");
            out.print("</div>");
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="PENDIENTES MAQUINAS">
            out.print("<div class='section-body'>");
            out.print("<h2 class='section-title'>Maquinas</h2>");
            out.print("<div class='row'>");
            out.print("<div class='col-12'>");
            out.print("<div class='activities'>");

            out.print("<div class='activity'>");
            out.print("<div class='activity-icon bg-primary text-white shadow-primary'>");
            out.print("<i class='fas fa-comment-alt'></i>");
            out.print("</div>");
            out.print("<div class='activity-detail'>");
            out.print("<div class='mb-2'>");
            out.print("<span class='text-job text-primary'>Fecha</span>");
            out.print("<span class='bullet'></span>");
            out.print("<a class='text-job' href='#'>Molde</a>");
            out.print("<div class='float-right dropdown'>");
            out.print("<a href='#' data-toggle='dropdown'><i class='fas fa-ellipsis-h'></i></a>");
            out.print("<div class='dropdown-menu'>");
            out.print("<div class='dropdown-title'>Opciones</div>");
            out.print("<a href='#' class='dropdown-item has-icon'><i class='fas fa-eye'></i> Ver en modulo</a>");
            out.print("<a href='#' class='dropdown-item has-icon'><i class='fas fa-list'></i> Detalle</a>");
            out.print("<div class='dropdown-divider'></div>");
            out.print("<a class='dropdown-item has-icon text-danger'><i class='fas fa-trash-alt'></i> Eliminar</a>");
            out.print("</div>");
            out.print("</div>");
            out.print("</div>");
            out.print("<p>Detalle de pendiente Maquinas</p>");
            out.print("</div>");
            out.print("</div>");

            out.print("<div class='activity'>");
            out.print("<div class='activity-icon bg-primary text-white shadow-primary'>");
            out.print("<i class='fas fa-arrows-alt'></i>");
            out.print("</div>");
            out.print("<div class='activity-detail'>");
            out.print("<div class='mb-2'>");
            out.print("<span class='text-job'>Fecha</span>");
            out.print("<span class='bullet'></span>");
            out.print("<a class='text-job' href='#'>Molde</a>");
            out.print("<div class='float-right dropdown'>");
            out.print("<a href='#' data-toggle='dropdown'><i class='fas fa-ellipsis-h'></i></a>");
            out.print("<div class='dropdown-menu'>");
            out.print("<div class='dropdown-title'>Opciones</div>");
            out.print("<a href='#' class='dropdown-item has-icon'><i class='fas fa-eye'></i> Ver en modulo</a>");
            out.print("<a href='#' class='dropdown-item has-icon'><i class='fas fa-list'></i> Detalle</a>");
            out.print("<div class='dropdown-divider'></div>");
            out.print("<a class='dropdown-item has-icon text-danger'><i class='fas fa-trash-alt'></i> Eliminar</a>");
            out.print("</div>");
            out.print("</div>");
            out.print("</div>");
            out.print("<p>Detalle de pendiente Maquinas</p>");
            out.print("</div>");
            out.print("</div>");

            out.print("<div class='activity'>");
            out.print("<div class='activity-icon bg-primary text-white shadow-primary'>");
            out.print("<i class='fas fa-unlock'></i>");
            out.print("</div>");
            out.print("<div class='activity-detail'>");
            out.print("<div class='mb-2'>");
            out.print("<span class='text-job'>Fecha</span>");
            out.print("<span class='bullet'></span>");
            out.print("<a class='text-job' href='#'>Molde</a>");
            out.print("<div class='float-right dropdown'>");
            out.print("<a href='#' data-toggle='dropdown'><i class='fas fa-ellipsis-h'></i></a>");
            out.print("<div class='dropdown-menu'>");
            out.print("<div class='dropdown-title'>Opciones</div>");
            out.print("<a href='#' class='dropdown-item has-icon'><i class='fas fa-eye'></i> Ver en modulo</a>");
            out.print("<a href='#' class='dropdown-item has-icon'><i class='fas fa-list'></i> Detalle</a>");
            out.print("<div class='dropdown-divider'></div>");
            out.print("<a class='dropdown-item has-icon text-danger'><i class='fas fa-trash-alt'></i> Eliminar</a>");
            out.print("</div>");
            out.print("</div>");
            out.print("</div>");
            out.print("<p>Detalle de pendiente Maquinas</p>");
            out.print("</div>");
            out.print("</div>");

            out.print("<div class='activity'>");
            out.print("<div class='activity-icon bg-primary text-white shadow-primary'>");
            out.print("<i class='fas fa-sign-out-alt'></i>");
            out.print("</div>");
            out.print("<div class='activity-detail'>");
            out.print("<div class='mb-2'>");
            out.print("<span class='text-job'>Fecha</span>");
            out.print("<span class='bullet'></span>");
            out.print("<a class='text-job' href='#'>Molde</a>");
            out.print("<div class='float-right dropdown'>");
            out.print("<a href='#' data-toggle='dropdown'><i class='fas fa-ellipsis-h'></i></a>");
            out.print("<div class='dropdown-menu'>");
            out.print("<div class='dropdown-title'>Opciones</div>");
            out.print("<a href='#' class='dropdown-item has-icon'><i class='fas fa-eye'></i> Ver en modulo</a>");
            out.print("<a href='#' class='dropdown-item has-icon'><i class='fas fa-list'></i> Detalle</a>");
            out.print("<div class='dropdown-divider'></div>");
            out.print("<a class='dropdown-item has-icon text-danger'><i class='fas fa-trash-alt'></i> Eliminar</a>");
            out.print("</div>");
            out.print("</div>");
            out.print("</div>");
            out.print("<p>Detalle de pendiente Maquinas</p>");
            out.print("</div>");
            out.print("</div>");

            out.print("<div class='activity'>");
            out.print("<div class='activity-icon bg-primary text-white shadow-primary'>");
            out.print("<i class='fas fa-trash'></i>");
            out.print("</div>");
            out.print("<div class='activity-detail'>");
            out.print("<div class='mb-2'>");
            out.print("<span class='text-job'>Fecha</span>");
            out.print("<span class='bullet'></span>");
            out.print("<a class='text-job' href='#'>Molde</a>");
            out.print("<div class='float-right dropdown'>");
            out.print("<a href='#' data-toggle='dropdown'><i class='fas fa-ellipsis-h'></i></a>");
            out.print("<div class='dropdown-menu'>");
            out.print("<div class='dropdown-title'>Opciones</div>");
            out.print("<a href='#'class='dropdown-item has-icon'><i class='fas fa-eye'></i> Ver en modulo</a>");
            out.print("<a href='#' class='dropdown-item has-icon'><i class='fas fa-list'></i> Detalle</a>");
            out.print("<div class='dropdown-divider'></div>");
            out.print("<a class='dropdown-item has-icon text-danger'><i class='fas fa-trash-alt'></i> Eliminar</a>");
            out.print("</div>");
            out.print("</div>");
            out.print("</div>");
            out.print("<p>Detalle de pendiente Maquinas</p>");
            out.print("</div>");
            out.print("</div>");

            out.print("<div class='activity'>");
            out.print("<div class='activity-icon bg-primary text-white shadow-primary'>");
            out.print("<i class='fas fa-trash'></i>");
            out.print("</div>");
            out.print("<div class='activity-detail'>");
            out.print("<div class='mb-2'>");
            out.print("<span class='text-job'>Fecha</span>");
            out.print("<span class='bullet'></span>");
            out.print("<a class='text-job' href='#'>Molde</a>");
            out.print("<div class='float-right dropdown'>");
            out.print("<a href='#' data-toggle='dropdown'><i class='fas fa-ellipsis-h'></i></a>");
            out.print("<div class='dropdown-menu'>");
            out.print("<div class='dropdown-title'>Opciones</div>");
            out.print("<a href='#' class='dropdown-item has-icon'><i class='fas fa-eye'></i> Ver en modulo</a>");
            out.print("<a href='#' class='dropdown-item has-icon'><i class='fas fa-list'></i> Detalle</a>");
            out.print("<div class='dropdown-divider'></div>");
            out.print("<a class='dropdown-item has-icon text-danger'><i class='fas fa-trash-alt'></i> Eliminar</a>");
            out.print("</div>");
            out.print("</div>");
            out.print("</div>");
            out.print("<p>Detalle de pendiente Maquinas</p>");
            out.print("</div>");
            out.print("</div>");

            out.print("</div>");
            out.print("</div>");
            out.print("</div>");
            out.print("</div>");
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="PENDIENTES FICHAS TECNICAS">
            out.print("<div class='section-body'>");
            out.print("<h2 class='section-title'>Fichas Tecnicas</h2>");
            out.print("<div class='row'>");
            out.print("<div class='col-12'>");
            out.print("<div class='activities'>");

            out.print("<div class='activity'>");
            out.print("<div class='activity-icon bg-primary text-white shadow-primary'>");
            out.print("<i class='fas fa-comment-alt'></i>");
            out.print("</div>");
            out.print("<div class='activity-detail'>");
            out.print("<div class='mb-2'>");
            out.print("<span class='text-job text-primary'>Fecha</span>");
            out.print("<span class='bullet'></span>");
            out.print("<a class='text-job' href='#'>Molde</a>");
            out.print("<div class='float-right dropdown'>");
            out.print("<a href='#' data-toggle='dropdown'><i class='fas fa-ellipsis-h'></i></a>");
            out.print("<div class='dropdown-menu'>");
            out.print("<div class='dropdown-title'>Opciones</div>");
            out.print("<a href='#' class='dropdown-item has-icon'><i class='fas fa-eye'></i> Ver en modulo</a>");
            out.print("<a href='#' class='dropdown-item has-icon'><i class='fas fa-list'></i> Detalle</a>");
            out.print("<div class='dropdown-divider'></div>");
            out.print("<a class='dropdown-item has-icon text-danger'><i class='fas fa-trash-alt'></i> Eliminar</a>");
            out.print("</div>");
            out.print("</div>");
            out.print("</div>");
            out.print("<p>Detalle de pendiente Fichas Tecnicas</p>");
            out.print("</div>");
            out.print("</div>");

            out.print("<div class='activity'>");
            out.print("<div class='activity-icon bg-primary text-white shadow-primary'>");
            out.print("<i class='fas fa-arrows-alt'></i>");
            out.print("</div>");
            out.print("<div class='activity-detail'>");
            out.print("<div class='mb-2'>");
            out.print("<span class='text-job'>Fecha</span>");
            out.print("<span class='bullet'></span>");
            out.print("<a class='text-job' href='#'>Molde</a>");
            out.print("<div class='float-right dropdown'>");
            out.print("<a href='#' data-toggle='dropdown'><i class='fas fa-ellipsis-h'></i></a>");
            out.print("<div class='dropdown-menu'>");
            out.print("<div class='dropdown-title'>Opciones</div>");
            out.print("<a href='#' class='dropdown-item has-icon'><i class='fas fa-eye'></i> Ver en modulo</a>");
            out.print("<a href='#' class='dropdown-item has-icon'><i class='fas fa-list'></i> Detalle</a>");
            out.print("<div class='dropdown-divider'></div>");
            out.print("<a class='dropdown-item has-icon text-danger'><i class='fas fa-trash-alt'></i> Eliminar</a>");
            out.print("</div>");
            out.print("</div>");
            out.print("</div>");
            out.print("<p>Detalle de pendiente Fichas Tecnicas</p>");
            out.print("</div>");
            out.print("</div>");

            out.print("<div class='activity'>");
            out.print("<div class='activity-icon bg-primary text-white shadow-primary'>");
            out.print("<i class='fas fa-unlock'></i>");
            out.print("</div>");
            out.print("<div class='activity-detail'>");
            out.print("<div class='mb-2'>");
            out.print("<span class='text-job'>Fecha</span>");
            out.print("<span class='bullet'></span>");
            out.print("<a class='text-job' href='#'>Molde</a>");
            out.print("<div class='float-right dropdown'>");
            out.print("<a href='#' data-toggle='dropdown'><i class='fas fa-ellipsis-h'></i></a>");
            out.print("<div class='dropdown-menu'>");
            out.print("<div class='dropdown-title'>Opciones</div>");
            out.print("<a href='#' class='dropdown-item has-icon'><i class='fas fa-eye'></i> Ver en modulo</a>");
            out.print("<a href='#' class='dropdown-item has-icon'><i class='fas fa-list'></i> Detalle</a>");
            out.print("<div class='dropdown-divider'></div>");
            out.print("<a class='dropdown-item has-icon text-danger'><i class='fas fa-trash-alt'></i> Eliminar</a>");
            out.print("</div>");
            out.print("</div>");
            out.print("</div>");
            out.print("<p>Detalle de pendiente Fichas Tecnicas</p>");
            out.print("</div>");
            out.print("</div>");

            out.print("<div class='activity'>");
            out.print("<div class='activity-icon bg-primary text-white shadow-primary'>");
            out.print("<i class='fas fa-sign-out-alt'></i>");
            out.print("</div>");
            out.print("<div class='activity-detail'>");
            out.print("<div class='mb-2'>");
            out.print("<span class='text-job'>Fecha</span>");
            out.print("<span class='bullet'></span>");
            out.print("<a class='text-job' href='#'>Molde</a>");
            out.print("<div class='float-right dropdown'>");
            out.print("<a href='#' data-toggle='dropdown'><i class='fas fa-ellipsis-h'></i></a>");
            out.print("<div class='dropdown-menu'>");
            out.print("<div class='dropdown-title'>Opciones</div>");
            out.print("<a href='#' class='dropdown-item has-icon'><i class='fas fa-eye'></i> Ver en modulo</a>");
            out.print("<a href='#' class='dropdown-item has-icon'><i class='fas fa-list'></i> Detalle</a>");
            out.print("<div class='dropdown-divider'></div>");
            out.print("<a class='dropdown-item has-icon text-danger'><i class='fas fa-trash-alt'></i> Eliminar</a>");
            out.print("</div>");
            out.print("</div>");
            out.print("</div>");
            out.print("<p>Detalle de pendiente Fichas Tecnicas</p>");
            out.print("</div>");
            out.print("</div>");

            out.print("<div class='activity'>");
            out.print("<div class='activity-icon bg-primary text-white shadow-primary'>");
            out.print("<i class='fas fa-trash'></i>");
            out.print("</div>");
            out.print("<div class='activity-detail'>");
            out.print("<div class='mb-2'>");
            out.print("<span class='text-job'>Fecha</span>");
            out.print("<span class='bullet'></span>");
            out.print("<a class='text-job' href='#'>Molde</a>");
            out.print("<div class='float-right dropdown'>");
            out.print("<a href='#' data-toggle='dropdown'><i class='fas fa-ellipsis-h'></i></a>");
            out.print("<div class='dropdown-menu'>");
            out.print("<div class='dropdown-title'>Opciones</div>");
            out.print("<a href='#'class='dropdown-item has-icon'><i class='fas fa-eye'></i> Ver en modulo</a>");
            out.print("<a href='#' class='dropdown-item has-icon'><i class='fas fa-list'></i> Detalle</a>");
            out.print("<div class='dropdown-divider'></div>");
            out.print("<a class='dropdown-item has-icon text-danger'><i class='fas fa-trash-alt'></i> Eliminar</a>");
            out.print("</div>");
            out.print("</div>");
            out.print("</div>");
            out.print("<p>Detalle de pendiente Fichas Tecnicas</p>");
            out.print("</div>");
            out.print("</div>");

            out.print("<div class='activity'>");
            out.print("<div class='activity-icon bg-primary text-white shadow-primary'>");
            out.print("<i class='fas fa-trash'></i>");
            out.print("</div>");
            out.print("<div class='activity-detail'>");
            out.print("<div class='mb-2'>");
            out.print("<span class='text-job'>Fecha</span>");
            out.print("<span class='bullet'></span>");
            out.print("<a class='text-job' href='#'>Molde</a>");
            out.print("<div class='float-right dropdown'>");
            out.print("<a href='#' data-toggle='dropdown'><i class='fas fa-ellipsis-h'></i></a>");
            out.print("<div class='dropdown-menu'>");
            out.print("<div class='dropdown-title'>Opciones</div>");
            out.print("<a href='#' class='dropdown-item has-icon'><i class='fas fa-eye'></i> Ver en modulo</a>");
            out.print("<a href='#' class='dropdown-item has-icon'><i class='fas fa-list'></i> Detalle</a>");
            out.print("<div class='dropdown-divider'></div>");
            out.print("<a class='dropdown-item has-icon text-danger'><i class='fas fa-trash-alt'></i> Eliminar</a>");
            out.print("</div>");
            out.print("</div>");
            out.print("</div>");
            out.print("<p>Detalle de pendiente Fichas Tecnicas</p>");
            out.print("</div>");
            out.print("</div>");

            out.print("</div>");
            out.print("</div>");
            out.print("</div>");
            out.print("</div>");
//</editor-fold>

            out.print("</div>");
            out.print("</section>");

            if (bienvenido == 1) {
                out.print("<script type='text/javascript'>");
                out.print("$(document).ready(function() {");
                out.print("iziToast.warning({");
                out.print("title: 'Bienvenido',");
                out.print("message: '¡Al aplicativo Herramental!',");
                out.print("position: 'bottomRight',");
                out.print("icon: 'fas fa-handshake'");
                out.print("});");
                out.print("});");
                out.print("</script>");
            } else {
                out.print("");
            }
        } catch (IOException ex) {
            Logger.getLogger(Inicio.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return super.doStartTag();
    }
}
