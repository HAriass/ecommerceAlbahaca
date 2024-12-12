async function loadProductosPorCantidad(stat) {

    const mainContent = document.getElementById('main-content');

    //muestro en el main los input para poner las fecha inicio y fecha fin
    mainContent.innerHTML = `
        <h2 class="text-xl font-bold text-white">Productos Vendidos Por Cantidad</h2>
        <p class="text-gray-300">Seleccione el período de tiempo para la estadística</p>
        <input type="date" id="fechaInicio" class="text-black" placeholder="Fecha Inicio" />
        <input type="date" id="fechaFin" class="text-black" placeholder="Fecha Fin" />
        <button id="btnFiltrar" class="bg-blue-500 text-white py-2 px-4 rounded">Mostrar Estadisticas</button>
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
            alert('El rango de fechas es invalido');
            return;
        }

        console.log(fechaInicio, fechaFin,stat);
        await crearGraph(stat, fechaInicio, fechaFin);
    });

}


async function crearGraph(stat, fechaInicio, fechaFin) {
    const mainContent = document.getElementById('main-content');

    if (stat === 'estadistica1') {
        mainContent.innerHTML = `
            <canvas id="chartMasVendidos" class="w-full max-w-2xl mt-16 mx-auto bg-white p-4 rounded shadow-md"></canvas>
        `;

        try {
            // Hacemos la solicitud a la API para obtener los datos con parametro fecha inicio y fin
            const response = await axios.get('/estadisticas/masVendidos?fechaInicio=' + fechaInicio + '&fechaFin=' + fechaFin);
            const data = response.data;
            console.log(data);

            const nombres = data.map(item => item.nombre);
            const cantidades = data.map(item => item.cantidad);

            const ctx = document.getElementById('chartMasVendidos').getContext('2d');
            new Chart(ctx, {
                type: 'bar',
                data: {
                    labels: nombres,
                    datasets: [{
                        label: 'Cantidad Vendida',
                        data: cantidades,
                        backgroundColor: 'rgba(75, 192, 192, 0.2)',
                        borderColor: 'rgba(75, 192, 192, 1)',
                        borderWidth: 1
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
                <p class="text-white">No se pudieron cargar los datos de la estadística.</p>
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
