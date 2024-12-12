async function loadCantidadDePedidos() {
    const mainContent = document.getElementById('main-content');

    // Mostrar inputs para rango de fechas
    mainContent.innerHTML = `
        <h2 class="text-xl font-bold text-white">Pedidos por Fecha</h2>
        <p class="text-gray-300">Seleccione el período de tiempo para la estadística</p>
        <input type="date" id="fechaInicio" class="text-black mb-2" placeholder="Fecha Inicio" />
        <input type="date" id="fechaFin" class="text-black mb-4" placeholder="Fecha Fin" />
        <button id="btnFiltrar" class="bg-blue-500 text-white py-2 px-4 rounded">Mostrar Estadísticas</button>
    `;

    const btnFiltrar = document.getElementById('btnFiltrar');
    if (!btnFiltrar) {
        console.error("El botón 'btnFiltrar' no se encontró en el DOM");
        return;
    }

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

        try {
            console.log("Cargando estadística con fechas:", fechaInicio, fechaFin);
            await crearGrafico('pedidosPorFecha', fechaInicio, fechaFin);
        } catch (error) {
            console.error('Error al cargar estadísticas:', error);
        }
    });
}

async function crearGrafico(stat, fechaInicio, fechaFin) {
    console.log("entre a la funcion crearGrafico");
    const mainContent = document.getElementById('main-content');

    if (stat === 'pedidosPorFecha') {
        mainContent.innerHTML = `
            <canvas id="chartPedidosPorFecha" class="w-full max-w-2xl mt-16 mx-auto bg-white p-4 rounded shadow-md"></canvas>
        `;

        try {
            console.log(`Endpoint llamado: /estadisticas/pedidosPorFecha?fechaInicio=${fechaInicio}&fechaFin=${fechaFin}`);
            const response = await axios.get(`/estadisticas/pedidosPorFecha?fechaInicio=${fechaInicio}&fechaFin=${fechaFin}`);
            const data = response.data;

            console.log("Datos recibidos:", data);

            if (!Array.isArray(data) || data.length === 0) {
                console.warn("No hay datos para mostrar");
                mainContent.innerHTML = `
                    <h2 class="text-xl font-bold text-yellow-500">Sin datos</h2>
                    <p class="text-white">No se encontraron pedidos en el rango de fechas seleccionado.</p>
                `;
                return;
            }

            const fechas = data.map(item => dayjs(item.fecha).format('YYYY-MM-DD'));
            const cantidades = data.map(item => item.cantidad);

            const canvas = document.getElementById('chartPedidosPorFecha');
            if (!canvas) {
                console.error("El canvas 'chartPedidosPorFecha' no se encontró en el DOM");
                return;
            }

            const ctx = canvas.getContext('2d');
            if (!ctx) {
                console.error("No se pudo obtener el contexto 2D del canvas");
                return;
            }

            // Destruir instancias previas de Chart.js si existen
            if (window.chartInstance) {
                window.chartInstance.destroy();
            }

            // Crear el gráfico
            window.chartInstance = new Chart(ctx, {
                type: 'bar',
                data: {
                    labels: fechas,
                    datasets: [{
                        label: 'Pedidos por Fecha',
                        data: cantidades,
                        backgroundColor: 'rgba(75, 192, 192, 0.2)',
                        borderColor: 'rgba(75, 192, 192, 1)',
                        borderWidth: 1,
                        tension: 0.4
                    }]
                },
                options: {
                    responsive: true,
                    scales: {
                        y: { beginAtZero: true },
                        x: { type: 'category' }
                    },
                    plugins: {
                        legend: { display: true }
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

// Verifica que Chart.js y dayjs están cargados
if (typeof Chart === 'undefined') {
    console.error('Chart.js no está cargado. Asegúrate de incluirlo en el proyecto.');
}
if (typeof dayjs === 'undefined') {
    console.error('dayjs no está cargado. Asegúrate de incluirlo en el proyecto.');
}