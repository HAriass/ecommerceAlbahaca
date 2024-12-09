async function loadCantidadDePedidos() {
    const mainContent = document.getElementById('main-content');

    // Mostrar inputs para rango de fechas
    mainContent.innerHTML = `
        <h2 class="text-xl font-bold text-white">Pedidos por Fecha</h2>
        <p class="text-gray-300">Seleccione el período de tiempo para la estadística</p>
        <input type="date" id="fechaInicio" class="text-black" placeholder="Fecha Inicio" />
        <input type="date" id="fechaFin" class="text-black" placeholder="Fecha Fin" />
        <button id="btnFiltrar" class="bg-blue-500 text-white py-2 px-4 rounded">Mostrar Estadísticas</button>
    `;

    const btnFiltrar = document.getElementById('btnFiltrar');

    btnFiltrar.addEventListener('click', async () => {
        const fechaInicio = document.getElementById('fechaInicio').value;
        const fechaFin = document.getElementById('fechaFin').value;

        if (!fechaInicio || !fechaFin) {
            alert('Por favor, seleccione ambas fechas.');
            return;
        }

        if (fechaInicio > fechaFin) {
            alert('El rango de fechas es inválido.');
            return;
        }

        // Llama a la función para cargar la estadística de pedidos por fecha
        await loadstat('pedidosPorFecha', fechaInicio, fechaFin);
    });
}

async function loadstat(stat, fechaInicio, fechaFin) {
    const mainContent = document.getElementById('main-content');

    if (stat === 'pedidosPorFecha') {
        mainContent.innerHTML = `
            <canvas id="chartPedidosPorFecha" class="w-full max-w-2xl mt-16 mx-auto bg-white p-4 rounded shadow-md"></canvas>
        `;

        try {
            // Solicitud a la API para obtener datos agrupados por fecha
            const response = await axios.get('/estadisticas/pedidosPorFecha?fechaInicio=' + fechaInicio + '&fechaFin=' + fechaFin);
            const data = response.data;
            console.log(data);

            // Extraer fechas y cantidades del resultado
            const fechas = data.map(item => dayjs(item.fecha).format('YYYY-MM-DD')); // Usar dayjs para formatear la fecha
            const cantidades = data.map(item => item.cantidad);

            // Crear el gráfico
            const ctx = document.getElementById('chartPedidosPorFecha').getContext('2d');
            new Chart(ctx, {
                type: 'line',
                data: {
                    labels: fechas,
                    datasets: [{
                        label: 'Pedidos por Fecha',
                        data: cantidades,
                        backgroundColor: 'rgba(75, 192, 192, 0.2)',
                        borderColor: 'rgba(75, 192, 192, 1)',
                        borderWidth: 1,
                        tension: 0.4 // Suavizar la línea
                    }]
                },
                options: {
                    responsive: true,
                    scales: {
                        y: {
                            beginAtZero: true
                        },
                        x: {
                            type: 'category', // Aseguramos que las fechas sean tratadas como categorías
                            labels: fechas
                        }
                    },
                    plugins: {
                        legend: {
                            display: true
                        }
                    }
                }
            });
        } catch (error) {
            mainContent.innerHTML = `
                <h2 class="text-xl font-bold text-red-500">Error</h2>
                <p class="text-white">No se pudieron cargar los datos de pedidos por fecha.</p>
            `;
            console.error('Error al cargar los datos:', error);
        }
    } else {
        mainContent.innerHTML = `
            <h2 class="text-xl font-bold text-white">Estadística ${stat}</h2>
            <p class="text-gray-300">Contenido para la Estadística ${stat}</p>
        `;
    }
}
