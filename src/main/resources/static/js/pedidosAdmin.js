const tbody = document.querySelector("tbody");


function formatFechaHora(fechaHora) {
    const fecha = new Date(fechaHora);
    return fecha.toLocaleString('es-ES', { // Cambia 'es-ES' por el código de idioma que prefieras
        year: 'numeric',
        month: '2-digit',
        day: '2-digit',
        hour: '2-digit',
        minute: '2-digit',
        second: '2-digit',
        hour12: false // Cambia a true si prefieres el formato 12 horas
    });
}

function getPedidos() {
    axios.get("/pedido/listarPedidos")
        .then(function (response) {
            const pedidos = response.data;
            console.log(pedidos);
            let htmlContent = '';

            // Ordena los pedidos por fechaHora en forma descendente
            pedidos.sort((a, b) => new Date(b.fechaHora) - new Date(a.fechaHora));

            pedidos.forEach(pedido => {
                // Llama a getDetallesPedido para cada pedido y pasa el id
                getDetallesPedido(pedido.id).then(detallesHtml => {

                    //creo un objeto para pasarle los datos a la impresion
                    let pedidoImpresion = {
                        id: pedido.id,
                        nombreCuenta: pedido.cuenta.nombre,
                        fechaHora: pedido.fechaHora,
                        detalles: detallesHtml,
                        estado: pedido.estado.nombre,
                        total: pedido.total
                    };

                    htmlContent += `
                        <tr>
                            <td>${pedido.id}</td>
                            <td>${pedido.cuenta.nombre}</td>
                            <td>${formatFechaHora(pedido.fechaHora)}</td>
                            <td>${detallesHtml}</td>
                            <td>${pedido.estado.nombre}</td> 
                            <td>$${formatPrecio(pedido.total)}</td>
                            <td><button type="button" class="btn btn-primary" data-pedido='${JSON.stringify(pedidoImpresion)}' onclick="imprimirPedido(this)">Factura</button></td>
                        </tr>
                    `;


                    tbody.innerHTML = htmlContent; // Actualiza el contenido de la tabla
                });
            });
        })
        .catch((err) => console.error(err));
}

function getDetallesPedido(pedidoId) {
    return axios.get(`/detallePedido/listarDetallesPedidoPorPedido/${pedidoId}`)
        .then(function (response) {
            const detalles = response.data;
            let htmlContent = '';

            detalles.forEach(detalle => {
                htmlContent += `
                    <span>${detalle.producto.nombre} - Cantidad: ${detalle.cantidad} - Subtotal: ${detalle.subtotal}</span>
                `;
            });

            return htmlContent; // Devuelve el contenido de los detalles
        })
        .catch((err) => console.error(err));
}

function imprimirPedido(pedidoImpresion) {
    const pedido = JSON.parse(pedidoImpresion.dataset.pedido);

    // const divFactura = document.getElementById('factura');
    const facturaModal = crearFactura(pedido);
    document.getElementById('facturaModalBody').innerHTML = facturaModal;

    // Muestra el modal usando Bootstrap
    var myModal = new bootstrap.Modal(document.getElementById('facturaModal'));
    myModal.show();
}

function crearFactura(pedido) {
    console.log(pedido);

    const fechaPedido = parseFecha(pedido.fechaHora);

    const detallesPedido = parseDetalles(pedido.detalles);
    const filasDetalles = detallesPedido.map(detalle => `
        <tr class="service">
            <td class="tableitem"><p class="invoce-p itemtext">${detalle.item}</p></td>
            <td class="tableitem"><p class="invoce-p itemtext">${detalle.cantidad}</p></td>
            <td class="tableitem"><p class="invoce-p itemtext">$${formatPrecio(detalle.subtotal)}</p></td>
        </tr>
    `).join('');

    pedido.nombreCuenta = pedido.nombreCuenta.toUpperCase();


    //creo un html con una plantilla de factura, y le paso los datos del pedido
    const facturaHtml = `
    <div class="invoce-background">
    
        <div class="topFactura">
            <div class="divlogo">
                <img src="/imgs/albahaca.png" class="logoAlbahaca" alt="Logo Albahaca">
                <h1 class="invoiceh1">Albahaca Suplementos</h1>
            </div>

            <div class="infoFactura">
                <h1 class="invoiceh1">Factura Nro. ${pedido.id}</h1>
                <p class="invoce-p">Fecha: ${fechaPedido}</p>
            </div><!--End Title-->
            
        </div>
       
            <div class="infoCliente">
                <div class="clientlogo"></div>
                <p class="invoce-p"><b>CLIENTE:</b> ${pedido.nombreCuenta}</p>
            </div><!--End Info-->
    
        <div id="invoice-bot">
        
        <div id="table" class="tableinvoce">
            <table>
            <tr class="tabletitle">
                <td class="titleItem">Item</td>
                <td class="titleItem">Cantidad</td>
                <td class="titleItem">Subtotal</td>
            </tr>
            
            ${filasDetalles}
            
                
            <tr class="tabletitle">
                <td></td>
                <td class="totalItem"><b>Total</b></td>
                <td class="totalItem">$${formatPrecio(pedido.total)}</td>
            </tr>
            
            </table>
        </div><!--End Table-->
       

    </div><!--End Invoce background-->
    
    `;

    return facturaHtml;
}

function parseDetalles(detallesHtml) {
    // Crear un elemento temporal para trabajar con el HTML
    const tempDiv = document.createElement('div');
    tempDiv.innerHTML = detallesHtml.trim(); // Limpia espacios innecesarios

    // Seleccionar todos los <span> y convertirlos en un array
    const spans = tempDiv.querySelectorAll('span');
    const detallesArray = Array.from(spans).map(span => {
        // Extraer el texto del <span>
        const text = span.textContent.trim();

        // Usar una expresión regular para extraer los valores
        const match = text.match(/^(.*) - Cantidad: (\d+) - Subtotal: (\d+)$/);

        if (match) {
            return {
                item: match[1].trim(),         // Extraer el nombre del producto
                cantidad: parseInt(match[2]), // Convertir la cantidad a número
                subtotal: parseFloat(match[3]) // Convertir el subtotal a número
            };
        }
        return null; // Si no coincide, devolver null (puedes manejar esto si quieres)
    });

    // Filtrar nulos (por si hay líneas que no coinciden con el formato esperado)
    return detallesArray.filter(detalle => detalle !== null);
}

function parseFecha(fechaISO) {
    // Convertir la cadena en un objeto Date
    const date = new Date(fechaISO);

    // Extraer día, mes y año
    const dia = String(date.getDate()).padStart(2, '0'); // Asegura que el día tenga 2 dígitos
    const mes = String(date.getMonth() + 1).padStart(2, '0'); // Los meses en JavaScript son base 0
    const anio = date.getFullYear(); // Obtiene el año completo

    // Retornar el formato deseado
    return `${dia}/${mes}/${anio}`;
}

function formatPrecio(number) {
    return number.toLocaleString('es-ES', {
        minimumFractionDigits: 2,
        maximumFractionDigits: 2
    });
}

function descargarFactura() {
    const { jsPDF } = window.jspdf;
    const doc = new jsPDF();
    const element = document.getElementById("facturaModalBody");
   
    html2canvas(element, { scale: 6 }).then(function (canvas) {
        // Convertir la imagen capturada en datos URI
        const imgData = canvas.toDataURL("image/jpeg");

        // Agregar la imagen al PDF

        // En base a A4
        var anchoPagina = 210; // Ancho de la página en unidades (por ejemplo, en mm)

        // Calcular las coordenadas para centrar la imagen
        var x = (anchoPagina - 120) / 2;

        // Luego, puedes agregar la imagen al PDF centrada en (x, y)
        // X,Y,ancho,alto --> si el alto o ancho es 0 se ajusta a la otra medida automaticamente
        doc.addImage(imgData, "JPEG", x, 20, 120, 0);

        // Guardar o mostrar el PDF, según tus necesidades
        // Por ejemplo, para descargar el PDF:
        doc.save("factura.pdf");

    });
}


// Llama solo a getPedidos, que manejará los detalles
getPedidos();
