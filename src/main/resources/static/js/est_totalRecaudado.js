async function loadTotalRecaudado() {
    const mainContent = document.getElementById('main-content');

    // Mostrar inputs para rango de fechas
    mainContent.innerHTML = `
        <h2 class="text-xl font-bold text-white">Total Recaudado por Fecha</h2>
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

        // Llama a la función para cargar la estadística de total recaudado
        await loadstat('totalRecaudado', fechaInicio, fechaFin);
    });
}

function formatPrecio(precio) {
    return precio.toLocaleString('es-ES', {
        minimumFractionDigits: 2,
        maximumFractionDigits: 2
    });
}


async function loadstat(stat, fechaInicio, fechaFin) {
    const mainContent = document.getElementById('main-content');

    if (stat === 'totalRecaudado') {
        mainContent.innerHTML = `
            <canvas id="chartTotalRecaudado" class="w-full max-w-2xl mt-16 mx-auto bg-white p-4 rounded shadow-md"></canvas>
        `;

        try {
            // Solicitud a la API para obtener los datos
            const response = await axios.get('/estadisticas/totalRecaudadoPorFecha?fechaInicio=' + fechaInicio + '&fechaFin=' + fechaFin);
            const data = response.data;
            console.log(data);

            // Extraer fechas y totales del resultado
            const fechas = data.map(item => item.fecha);
            const totales = data.map(item => item.total);
      

            // Crear el gráfico
            const ctx = document.getElementById('chartTotalRecaudado').getContext('2d');
            new Chart(ctx, {
                type: 'bar',
                data: {
                    labels: fechas,
                    datasets: [{
                        label: 'Total Recaudado por Fecha',
                        data: totales,
                        backgroundColor: 'rgba(54, 162, 235, 0.2)',
                        borderColor: 'rgba(54, 162, 235, 1)',
                        borderWidth: 1,
                        tension: 0.4 // Suavizar la línea
                    }]
                },
                options: {
                    responsive: true,
                    scales: {
                        y: {
                            beginAtZero: true
                        }
                    }
                }
            });
        } catch (error) {
            mainContent.innerHTML = `
                <h2 class="text-xl font-bold text-red-500">Error</h2>
                <p class="text-white">No se pudieron cargar los datos de recaudación.</p>
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
