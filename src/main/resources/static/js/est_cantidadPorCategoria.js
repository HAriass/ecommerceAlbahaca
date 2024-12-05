async function loadCantidadPorCategoria() {
    const mainContent = document.getElementById('main-content');

    mainContent.innerHTML = `
        <canvas id="chartPorCategoria" class="w-full max-w-2xl mt-16 mx-auto bg-white p-4 rounded shadow-md"></canvas>
    `;

    try {
        // Hacemos la solicitud a la API para obtener los datos por categoría
        const response = await axios.get('/estadisticas/cantidadPorCategoria');
        const data = response.data;
        console.log(data);

        // Extraemos las categorías y las cantidades
        const categorias = data.map(item => item.categoria);
        const cantidades = data.map(item => item.cantidadVendida);

        // Creamos el gráfico usando Chart.js
        const ctx = document.getElementById('chartPorCategoria').getContext('2d');
        new Chart(ctx, {
            type: 'bar',
            data: {
                labels: categorias,
                datasets: [{
                    label: 'Cantidad Vendida',
                    data: cantidades,
                    backgroundColor: 'rgba(54, 162, 235, 0.2)', // Color de fondo
                    borderColor: 'rgba(54, 162, 235, 1)', // Color de borde
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
}
