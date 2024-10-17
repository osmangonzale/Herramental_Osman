<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/tld_usuarios.tld" prefix="usuario"%>
<%@taglib uri="/WEB-INF/tlds/Alertas.tld" prefix="Alertas"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>USUARIOS | HR</title>
        <link rel="stylesheet" href="Interfaz/Contenido/assets/modules/datatables/datatables.min.css">
        <link rel="stylesheet" href="Interfaz/Contenido/assets/modules/datatables/DataTables-1.10.16/css/dataTables.bootstrap4.min.css">
        <link rel="stylesheet" href="Interfaz/Contenido/assets/modules/datatables/Select-1.2.4/css/select.bootstrap4.min.css">
        <link rel="stylesheet" href="Interfaz/Contenido/assets/css/main.css">
        <link rel="stylesheet" href="Interfaz/Contenido/assets/modules/izitoast/css/iziToast.min.css">
        <link rel="stylesheet" href="Interfaz/Contenido/assets/modules/select2/dist/css/select2.min.css">
        <link rel="shortcut icon" href="Interfaz/Contenido/images/herramental.ico" type="image/x-icon" />
    </head>
    <body>
        <div id="app">
            <div class="main-wrapper main-wrapper-1">
                <jsp:include page="Menu.jsp"></jsp:include>
                    <div class="main-content" style="min-height: 694px;">
                    <usuario:usuarios/>  
                </div>
            </div>
        </div>
        <Alertas:alertas/>
        <script type="text/javascript">
            function reinicializarTooltips() {
                if (typeof $ !== 'undefined' && typeof $.fn.tooltip !== 'undefined') {
                    $('[data-toggle="tooltip"]').tooltip();
                } else {
                    console.error("jQuery o Bootstrap no están cargados");
                }
            }

            $(document).ready(function () {
                var table = $('#table-1').DataTable();
                reinicializarTooltips();
                table.on('draw', function () {
                    reinicializarTooltips();
                });
            });
        </script>
        <script type="text/javascript">
            function DesactivarUsuario(id_usuario) {
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
                        var url = 'Usuario?opc=4&Id_usuario=' + id_usuario + '&estado=0';
                        window.location.href = url;
                    }
                });
            }
            function ActivarUsuario(id_usuario) {
                Swal.fire({
                    title: "¡ Advertencia !",
                    html: "El estado de la familia de producto cambiará a <span style='color: #2cb015;'>Activo</span>. ¿Seguro desea cambiar el estado?",
                    icon: "warning",
                    showCancelButton: true,
                    cancelButtonText: "Cancelar",
                    confirmButtonText: "Cambiar estado",
                    confirmButtonColor: "#25c60a",
                    cancelButtonColor: "#d33",
                    reverseButtons: true
                }).then((result) => {
                    if (result.isConfirmed) {
                        var url = 'Usuario?opc=4&Id_usuario=' + id_usuario + '&estado=1';
                        window.location.href = url;
                    }
                });
            }
        </script>
        <script type="text/javascript">
            function RestablecerPassword(id_usuario) {
                Swal.fire({
                    title: "¡ Advertencia !",
                    html: "Seguro que desea restablecer el password asignado a el usuario!",
                    icon: "warning",
                    showCancelButton: true,
                    cancelButtonText: "Cancelar",
                    confirmButtonText: "Cambiar estado",
                    confirmButtonColor: "#25c60a",
                    cancelButtonColor: "#d33",
                    reverseButtons: true
                }).then((result) => {
                    if (result.isConfirmed) {
                        var url = 'Usuario?opc=5&Id_usuario=' + id_usuario;
                        window.location.href = url;
                    }
                });
            }
        </script>
        <script src="Interfaz/Contenido/assets/js/Sweetalert2.js"></script>
        <script src="Interfaz/Contenido/assets/modules/datatables/datatables.min.js"></script>
        <script src="Interfaz/Contenido/assets/modules/datatables/DataTables-1.10.16/js/dataTables.bootstrap4.min.js"></script>
        <script src="Interfaz/Contenido/assets/modules/datatables/Select-1.2.4/js/dataTables.select.min.js"></script>
        <script src="Interfaz/Contenido/assets/js/page/modules-datatables.js"></script>
        <script src="Interfaz/Contenido/assets/modules/izitoast/js/iziToast.min.js"></script>
        <script src="Interfaz/Contenido/assets/js/page/modules-toastr.js"></script>

        <script type="text/javascript" src="Interfaz/Alertas/dist/sweetalert.min.js"></script>
        <link href="Interfaz/Alertas/dist/sweetalert.css" rel="stylesheet" type="text/css"/> 
        <script src="Interfaz/Contenido/assets/modules/select2/dist/js/select2.full.min.js"></script>
    </body>
</html>
