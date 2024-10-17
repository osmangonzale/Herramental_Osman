<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/Ficha_tecnica.tld" prefix="ficha_tecnica"%>
<%@taglib uri="/WEB-INF/tlds/Alertas.tld" prefix="Alertas"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FICHA TECNICA | HR</title>
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
                    <ficha_tecnica:Ficha_tecnica/>
                </div>
            </div>
        </div>
        <Alertas:alertas/>
        <script type="text/javascript">
            function enviar(event) {
                event.preventDefault();
                var form = document.getElementById('formcodigo');
                var selectEstado = document.getElementsByName('newEstado')[0];
                var id_fichaTec = document.getElementsByName('id_fichaTec')[0].value;
                var valorSeleccionado = selectEstado.value;
                if (valorSeleccionado === '1') {
                    form.action = 'Ficha_tecnica?opc=4&id_fichaTec=' + id_fichaTec + '&newEstado=' + valorSeleccionado;
                    form.submit();
                } else if (valorSeleccionado === '2') {
                    form.action = 'Ficha_tecnica?opc=1&id_fichaTec=' + id_fichaTec + '&newEstado=' + valorSeleccionado;
                    form.submit();
                } else if (valorSeleccionado === '3') {
                    inactivar(id_fichaTec, valorSeleccionado);
                } else if (valorSeleccionado === '4') {
                    form.action = 'Ficha_tecnica?opc=1&id_fichaTec=' + id_fichaTec + '&newEstado=' + valorSeleccionado;
                    form.submit();
                } else {
                    form.action = 'Ficha_tecnica?opc=default&id_fichaTec=' + id_fichaTec + '&newEstado=' + valorSeleccionado;
                    form.submit();
                }
            }
        </script>
        <script type="text/javascript">
            function inactivar(id_fichaTec, valorSeleccionado) {
                Swal.fire({
                    title: "¡ Advertencia !",
                    text: "Esta ficha contiene máquinas y moldes asociados. ¿Seguro desea inactivar?",
                    icon: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#25c60a",
                    cancelButtonColor: "#d33",
                    confirmButtonText: "Sí, inactivar!",
                    cancelButtonText: "Cancelar",
                    reverseButtons: true
                }).then((result) => {
                    if (result.isConfirmed) {
                        var form = document.getElementById('formcodigo');
                        form.action = 'Ficha_tecnica?opc=4&id_fichaTec=' + id_fichaTec + '&newEstado=' + valorSeleccionado;
                        form.submit();
                    }
                });
            }
        </script>
        <script type="text/javascript">
            function actualizarHerramenSeleccionadas() {
                var selectHerramental = document.getElementById('selectHerramental');
                var seleccionados = Array.from(selectHerramental.selectedOptions).map(option => "[" + option.value + "]");
                var ids = seleccionados.join('');
                document.getElementById('id_herramental').value = ids;
            }
            window.onload = function () {
                actualizarHerramenSeleccionadas();
            };
        </script>
        <script type="text/javascript">
            function toggleStatus(estado) {
                location.href = 'Ficha_tecnica?opc=1&estado=' + estado;
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
            function registroU() {
                document.getElementById("btsubmit").disabled = true;
                document.getElementById("btsubmit").value = "";
                document.getElementById("puntos").style.display = "block";
            }
        </script>
        <script>
            function maxLengthCheck(object)
            {
                if (object.value.length > object.maxLength)
                    object.value = object.value.slice(0, object.maxLength)
            }
        </script>
        <script type="text/javascript">
            function ConstruirEditor() {
                document.getElementById("Txt_instruccion_seguridad").value = document.getElementById("Txt_editor_html").innerHTML;
                document.getElementById("FormInstruccion").submit();
            }
        </script>
        <script>
            function MasivoCargos(ide) {
                var id = "[" + ide + "]";
                var content = document.getElementById("txt_cargs").value;
                if (content.includes(id)) {
                    document.getElementById("txt_cargs").value = content.replace(id, "
                } else {
                    document.getElementById("txt_cargs").value += id;
                }
            }
            function MasivoCorrep(ide) {
                var id = ide;
                var content = document.getElementById("prueba").value;
                if (content.includes(id)) {
                    document.getElementById("prueba").value = content.replace(id, "");
                } else {
                    document.getElementById("prueba").value += id;
                }
            }
            function Masivo(ide) {
                var id = ide;
                var content = document.getElementById("Txt_ids").value;
                if (content.includes(id)) {
                    document.getElementById("Txt_ids").value = content.replace(id, "");
                } else {
                    document.getElementById("Txt_ids").value += id;
                }
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
