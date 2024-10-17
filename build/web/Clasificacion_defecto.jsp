<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/tld_clasificacion_defectos.tld" prefix="clasificacion_defecto"%>
<%@taglib uri="/WEB-INF/tlds/Alertas.tld" prefix="Alertas"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>CLASIFICACION DEFECTO | HR</title>
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
                    <clasificacion_defecto:ClasificacionDefecto/>  
                </div>
            </div>
        </div>
        <Alertas:alertas/>
        <script type="text/javascript">
            function registroF() {
                document.getElementById("btsubmit").disabled = true;
                document.getElementById("btsubmit").value = "";
                document.getElementById("puntos").style.display = "block";
            }
        </script>
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
            function InactivarClasificacion(id_defecto) {
                Swal.fire({
                    title: "¡ Advertencia !",
                    html: "El estado de la clasificacion de defecto cambiará a <span style='color: #ff0000;'>Inactivo</span>. ¿Seguro desea cambiar el estado?",
                    icon: "warning",
                    showCancelButton: true,
                    cancelButtonText: "Cancelar",
                    confirmButtonText: "Cambiar estado",
                    confirmButtonColor: "#25c60a",
                    cancelButtonColor: "#d33",
                    reverseButtons: true
                }).then((result) => {
                    if (result.isConfirmed) {
                        var url = 'Clasificacion_defecto?opc=4&Id_C_defecto=' + id_defecto + '&estado=0';
                        window.location.href = url;
                    }
                });
            }
            function ActivarClasificacion(id_defecto) {
                Swal.fire({
                    title: "¡ Advertencia !",
                    html: "El estado de la clasificacion de defecto cambiará a <span style='color: #2cb015;'>Activo</span>. ¿Seguro desea cambiar el estado?",
                    icon: "warning",
                    showCancelButton: true,
                    cancelButtonText: "Cancelar",
                    confirmButtonText: "Cambiar estado",
                    confirmButtonColor: "#25c60a",
                    cancelButtonColor: "#d33",
                    reverseButtons: true
                }).then((result) => {
                    if (result.isConfirmed) {
                        var url = 'Clasificacion_defecto?opc=4&Id_C_defecto=' + id_defecto + '&estado=1';
                        window.location.href = url;
                    }
                });
            }
        </script>
        <script>
            document.getElementById('btnRegistrar').addEventListener('click', function (event) {
                const textarea = document.getElementById('textareaRegistrar');
                if (!validarEntrada(textarea)) {
                    event.preventDefault();
                }
            });

            document.getElementById('btnModificar').addEventListener('click', function (event) {
                const textarea = document.getElementById('textareaModificar');
                if (!validarEntrada(textarea)) {
                    event.preventDefault();
                }
            });
            function validarEntrada(textarea) {
                const lines = textarea.value.split('\n');
                let valid = true;
                let tieneAceptacion = false;
                for (let i = 0; i < lines.length; i++) {
                    const line = lines[i].trim();
                    if (line) {
                        if (line.startsWith("Aceptacion:")) {
                            const porcentaje = line.split('Aceptacion:')[1].trim();
                            if (!/^\d+(\.\d+)?%$/.test(porcentaje)) {
                                valid = false;
                                console.log("Porcentaje incorrecto: " + porcentaje);
                                break;
                            }
                            tieneAceptacion = true;
                        } else if (line.startsWith('-')) {
                            continue;
                        } else {
                            valid = false;
                            console.log("Línea inválida: " + line);
                            break;
                        }
                    }
                }
                if (!tieneAceptacion) {
                    valid = false;
                    console.log("Falta la línea de Aceptacion.");
                }
                if (!valid) {
                    iziToast.warning({
                        title: 'Atención',
                        message: 'Cada línea debe comenzar con un guion, Excepto línea de aceptacion (ej: Aceptacion: 0.075%).',
                        position: 'bottomRight',
                        icon: 'fas fa-exclamation-triangle'
                    });
                }
                return valid;
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