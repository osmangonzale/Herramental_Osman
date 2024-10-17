<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/tld_herramental.tld" prefix="herramental"%>
<%@taglib uri="/WEB-INF/tlds/Alertas.tld" prefix="Alertas"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HERRAMENTAL | HR</title>
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
                    <herramental:herramental/>
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
        <script>
            function actualizarMaquinasSeleccionadas() {
                var selectMaquinas = document.getElementById('selectMaquinas');
                var seleccionados = Array.from(selectMaquinas.selectedOptions).map(option => "[" + option.value + "]");
                var ids = seleccionados.join('');
                document.getElementById('id_maquinas').value = ids;
                console.log("IDs seleccionados:", ids);
            }
            window.onload = function () {
                actualizarMaquinasSeleccionadas();
            };
        </script>
        <script type="text/javascript">
            function DesactivarHerramental(id_herramental) {
                Swal.fire({
                    title: "¡ Advertencia !",
                    html: "El estado del herramental cambiará a <span style='color: #ff0000;'>Inactivo</span>. ¿Seguro desea cambiar el estado?",
                    icon: "warning",
                    showCancelButton: true,
                    cancelButtonText: "Cancelar",
                    confirmButtonText: "Cambiar estado",
                    confirmButtonColor: "#25c60a",
                    cancelButtonColor: "#d33",
                    reverseButtons: true
                }).then((result) => {
                    if (result.isConfirmed) {
                        var url = 'Herramental?opc=4&id_herramental=' + id_herramental + '&estado=0';
                        window.location.href = url;
                    }
                });
            }
            function ActivarHerramental(id_herramental) {
                Swal.fire({
                    title: "¡ Advertencia !",
                    html: "El estado del herramental cambiará a <span style='color: #2cb015;'>Activo</span>. ¿Seguro desea cambiar el estado?",
                    icon: "warning",
                    showCancelButton: true,
                    cancelButtonText: "Cancelar",
                    confirmButtonText: "Cambiar estado",
                    confirmButtonColor: "#25c60a",
                    cancelButtonColor: "#d33",
                    reverseButtons: true
                }).then((result) => {
                    if (result.isConfirmed) {
                        var url = 'Herramental?opc=4&id_herramental=' + id_herramental + '&estado=1';
                        window.location.href = url;
                    }
                });
            }
        </script>
        <script type="text/javascript">
            function Movimiento() {
                var htmleditor = document.getElementsByName("HTML_Editor").innerHTML;
                document.getElementsByName("txt_descripcion").value = htmleditor;
                document.getElementById("formDes").submit();
            }
            function MovimientoM() {
                var htmleditor = document.getElementsByName("HTML_Editor").innerHTML;
                document.getElementsByName("txt_descripcionM").value = htmleditor;
                document.formM.submit();
            }
            function SolucionP() {
                var htmleditor = document.getElementsByName("HTML_Editor").innerHTML;
                document.getElementsByName("txt_solucion").value = htmleditor;
                document.formS.submit();
            }
            function SolucionPM() {
                var htmleditor = document.getElementsByName("HTML_Editor").innerHTML;
                document.getElementsByName("txt_solucion").value = htmleditor;
                document.formSM.submit();
            }
            function CorreoM() {
                swal({
                    type: "warning",
                    title: "Enviando!",
                    text: "El correo se esta enviando por favor espere",
                    timer: 2000,
                    showConfirmButton: false
                },
                        function () {
                            document.formMailM.submit();
                        }
                );
            }
            function CorreoP() {
                swal({
                    type: "warning",
                    title: "Enviando!",
                    text: "El correo se esta enviando por favor espere",
                    timer: 2000,
                    showConfirmButton: false
                },
                        function () {
                            document.formMailP.submit();
                        }
                );
            }

        </script>
        <script type="text/javascript">
            function registroH() {
                document.getElementById("btsubmit").disabled = true;
                document.getElementById("btsubmit").value = "";
                document.getElementById("puntos").style.display = "block";
            }
            function registroM() {
                document.getElementById("botonM").disabled = true;
                document.getElementById("botonM").value = "";
                document.getElementById("puntosM").style.display = "block";
            }
            function MailM() {
                document.getElementById("botonMa").disabled = true;
                document.getElementById("botonMa").value = "";
                document.getElementById("puntosMa").style.display = "block";
            }
            function registroP() {
                document.getElementById("botonP").disabled = true;
                document.getElementById("botonP").value = "";
                document.getElementById("puntosP").style.display = "block";
            }
            function MailP() {
                document.getElementById("botonPe").disabled = true;
                document.getElementById("botonPe").value = "";
                document.getElementById("puntosPe").style.display = "block";
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
