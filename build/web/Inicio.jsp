<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/Inicio.tld" prefix="inicio"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>INICIO | HR</title>
        <link rel="stylesheet" href="Interfaz/Contenido/assets/modules/datatables/datatables.min.css">
        <link rel="stylesheet" href="Interfaz/Contenido/assets/modules/datatables/DataTables-1.10.16/css/dataTables.bootstrap4.min.css">
        <link rel="stylesheet" href="Interfaz/Contenido/assets/modules/datatables/Select-1.2.4/css/select.bootstrap4.min.css">
        <link rel="stylesheet" href="Interfaz/Contenido/assets/css/main.css">
        <link rel="stylesheet" href="Interfaz/Contenido/assets/css/grafico1.css">
        <link rel="stylesheet" href="Interfaz/Contenido/assets/modules/izitoast/css/iziToast.min.css">
        <link rel="stylesheet" href="Interfaz/Contenido/assets/modules/select2/dist/css/select2.min.css">
        <link rel="shortcut icon" href="Interfaz/Contenido/images/herramental.ico" type="image/x-icon" />
    </head>
    <body>
        <div id="app">
            <div class="main-wrapper main-wrapper-1">
                <jsp:include page="Menu.jsp"></jsp:include>
                    <div class="main-content" style="min-height: 694px;">
                    <inicio:inicio/>
                </div>
            </div>
        </div>
        <script type="text/javascript">
            function eliminarPendiente(id_familia) {
                Swal.fire({
                    title: "¡ Advertencia !",
                    html: "El estado de la familia de producto cambiará a <span style='color: #ff0000;'>Inactivo</span>. ¿Seguro desea cambiar el estado?",
                    icon: "warning",
                    showCancelButton: true,
                    cancelButtonText: "Cancelar",
                    confirmButtonText: "Cambiar estado",
                    confirmButtonColor: "#25c60a",
                    cancelButtonColor: "#d33",
                    reverseButtons: true
                }).then((result) => {
                    if (result.isConfirmed) {
                        var url = 'Familia_producto?opc=4&id_familia=' + id_familia + '&estado=0';
                        window.location.href = url;
                    }
                });
            }
        </script>
        <script src="Interfaz/Contenido/assets/modules/datatables/datatables.min.js"></script>
        <script src="Interfaz/Contenido/assets/modules/datatables/DataTables-1.10.16/js/dataTables.bootstrap4.min.js"></script>
        <script src="Interfaz/Contenido/assets/modules/datatables/Select-1.2.4/js/dataTables.select.min.js"></script>
        <script src="Interfaz/Contenido/assets/js/page/modules-datatables.js"></script>
        <script src="Interfaz/Contenido/assets/modules/izitoast/js/iziToast.min.js"></script>
        <script src="Interfaz/Contenido/assets/js/page/modules-toastr.js"></script>

        <script src="Interfaz/Contenido/assets/modules/chart.min.js"></script>
        <!--<script src="Interfaz/Contenido/assets/modules/components-statistic.js"></script>-->
        <script src="Interfaz/Contenido/assets/modules/jquery.sparkline.min.js"></script>
        <script src="Interfaz/Contenido/assets/modules/chart.min.js"></script>

        <script type="text/javascript" src="Interfaz/Alertas/dist/sweetalert.min.js"></script>
        <link href="Interfaz/Alertas/dist/sweetalert.css" rel="stylesheet" type="text/css"/>
        <script src="Interfaz/Contenido/assets/modules/select2/dist/js/select2.full.min.js"></script>
    </body>
</html>
