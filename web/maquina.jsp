<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/tld_maquina.tld" prefix="maquina"%>
<%@taglib uri="/WEB-INF/tlds/Alertas.tld" prefix="Alertas"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MAQUINAS | HR</title>
        <link rel="stylesheet" href="Interfaz/Contenido/assets/modules/datatables/datatables.min.css">
        <link rel="stylesheet" href="Interfaz/Contenido/assets/modules/datatables/DataTables-1.10.16/css/dataTables.bootstrap4.min.css">
        <link rel="stylesheet" href="Interfaz/Contenido/assets/modules/datatables/Select-1.2.4/css/select.bootstrap4.min.css">
        <link rel="stylesheet" href="Interfaz/Contenido/assets/css/main.css">
        <link rel="stylesheet" href="Interfaz/Contenido/assets/modules/izitoast/css/iziToast.min.css">
        <link rel="stylesheet" href="Interfaz/Contenido/assets/modules/select2/dist/css/select2.min.css">
        <link rel="shortcut icon" href="Interfaz/Contenido/images/herramental.ico" type="image/x-icon" />
        <link rel="stylesheet" href="Interfaz/Contenido/Css/status.css" />
    </head>
    <body>
        <div id="app">
            <div class="main-wrapper main-wrapper-1">
                <jsp:include page="Menu.jsp"></jsp:include>
                    <div class="main-content" style="min-height: 694px;">
                    <maquina:Maquina/>  
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
            function enviar1(event) {
                if (event.key === 'Enter') {
                    event.preventDefault();
                    document.getElementById("registrarform").submit();
                }
            }
        </script>
        <script type="text/javascript">
            function enviar2(event) {
                if (event.key === 'Enter') {
                    event.preventDefault();
                    document.getElementById("modificarform").submit();
                }
            }
        </script>
        <script type="text/javascript">
            function manejarCambio(selectElement) {
                var valorSeleccionado = selectElement.value;
                var idMaquina = document.getElementById("id_maquina").value;
                var temp = document.getElementById("temp").value;
                var tempo = document.getElementById("tempo").value;
                var tempora = document.getElementById("tempora").value;
                var id_movimiento = document.getElementById("id_movimiento").value;
                if (valorSeleccionado === "2" || valorSeleccionado === "3") {
                    window.location.href = "Maquina?opc=1&newEstado=" + valorSeleccionado + "&id_maquina=" + idMaquina + "&temp=" + temp + "&tempo=" + tempo + "&tempora=" + tempora + "&id_movimiento=" + id_movimiento;
                } else if (valorSeleccionado === "1" || valorSeleccionado === "4" || valorSeleccionado === "5") {
                    console.log("El valor es 1, 4 o 5, no se realiza ninguna acción.");
                } else {
                    console.log("Valor no manejado.");
                }
            }
        </script>
        <script type="text/javascript">
            function molde_montado() {
                Swal.fire({
                    title: "¡ No es posible !",
                    text: "Asegurate de que el molde actual este desmotado para poder registrar un nuevo movimiento.",
                    icon: "warning",
                    confirmButtonText: "Aceptar",
                    confirmButtonColor: "#3085d6"
                });
            }
        </script>
        <script type="text/javascript">
            function maquina_parada(id_maquina) {
                Swal.fire({
                    title: "¡ Advertencia !",
                    html: "El estado de la máquina cambiará a <span style='color: #ff0000;'>Parada</span>. ¿Seguro desea cambiar el estado?",
                    icon: "warning",
                    input: "textarea",
                    inputPlaceholder: "Justificación del cambio de estado",
                    showCancelButton: true,
                    cancelButtonText: "Cancelar",
                    confirmButtonText: "Cambiar estado",
                    confirmButtonColor: "#25c60a",
                    cancelButtonColor: "#d33",
                    reverseButtons: true
                }).then((result) => {
                    if (result.isConfirmed) {
                        var justificacion = result.value;
                        var url = 'Maquina?opc=7&id_maquina=' + id_maquina + '&estado=3&justificacion=' + encodeURIComponent(justificacion);
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