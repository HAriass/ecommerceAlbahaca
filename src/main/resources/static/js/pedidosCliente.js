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
    axios.get("/pedido/listarPedidosCliente")
        .then(function(response) {
            const pedidos = response.data;
            console.log(pedidos);
            let htmlContent = '';

            pedidos.forEach(pedido => {
                // Llama a getDetallesPedido para cada pedido y pasa el id
                getDetallesPedido(pedido.id).then(detallesHtml => {
                    const botonCancelar = pedido.estado.nombre === 'enPreparacion'
                        ? `<button onclick="cancelarPedido(${pedido.id})">Cancelar</button>`
                        : ''; // Solo mostrar botón si está en estado "enPreparacion"
                    
                    htmlContent += `
                        <tr>
                            <td>${pedido.id}</td>
                            <td>${formatFechaHora(pedido.fechaHora)}</td> <!-- Formatea la fecha aquí -->
                            <td>
                                <ul>${detallesHtml}</ul> <!-- Mostrar detalles en lista -->
                            </td>
                            <td>${pedido.estado.nombre}</td> 
                            <td>${pedido.total}</td>
                            <td>${botonCancelar}</td> <!-- Botón para cancelar el pedido -->
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
        .then(function(response) {
            const detalles = response.data;
            let htmlContent = '';
            
            detalles.forEach(detalle => {
                htmlContent += `
                    <li>${detalle.producto.nombre} - Cantidad: ${detalle.cantidad} - Subtotal: ${detalle.subtotal}</li>
                `;
            });

            return htmlContent; // Devuelve el contenido de los detalles
        })
        .catch((err) => console.error(err));
}

// Función para cancelar un pedido
function cancelarPedido(pedidoId) {
    axios.post(`/pedido/cancelarPedido/${pedidoId}`)
        .then(function(response) {
            console.log(`Pedido ${pedidoId} cancelado exitosamente`);
            getPedidos(); // Actualizar la lista de pedidos después de la cancelación
        })
        .catch(function(error) {
            console.error(`Error al cancelar el pedido ${pedidoId}`, error);
        });
}

// Llama solo a getPedidos, que manejará los detalles
getPedidos();
