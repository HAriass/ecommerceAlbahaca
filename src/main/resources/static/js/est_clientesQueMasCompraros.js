async function loadClientesCantCompras() {
    const mainContent = document.getElementById('main-content');

    // Limpiar el contenido previo
    mainContent.innerHTML = `
        <canvas id="clientesChart" class="w-full max-w-2xl mt-16 mx-auto bg-white p-4 rounded shadow-md"></canvas>
    `;

    try {
        // Hacemos la solicitud a la API para obtener los datos de clientes y sus compras
        const response = await axios.get('/estadisticas/clientesMasCompraron');
        const data = response.data;

        // Log de los datos para comprobar que llegaron correctamente
        console.log(data);

        // Extraemos los clientes y las cantidades de productos comprados
        const clientes = data.map(item => item.cliente);
        const cantidades = data.map(item => item.cantidadComprada);

        // Creamos el gráfico usando Chart.js
        const ctx = document.getElementById('clientesChart').getContext('2d');
        new Chart(ctx, {
            type: 'bar',
            data: {
                labels: clientes, // Los nombres de los clientes en el eje X
                datasets: [{
                    label: 'Cantidad de Productos Comprados',
                    data: cantidades, // Las cantidades de productos comprados por cada cliente
                    backgroundColor: 'rgba(75, 192, 192, 0.2)', // Color de fondo de las barras
                    borderColor: 'rgba(75, 192, 192, 1)', // Color de borde de las barras
                    borderWidth: 1
                }]
            },
            options: {
                responsive: true,
                scales: {
                    y: {
                        beginAtZero: true // Asegura que el eje Y comience desde cero
                    }
                }
            }
        });

    } catch (error) {
        // En caso de error, mostramos un mensaje en la interfaz
        mainContent.innerHTML = `
            <h2 class="text-xl font-bold text-red-500">Error</h2>
            <p class="text-white">No se pudieron cargar los datos de la estadística.</p>
        `;
        console.error('Error al cargar los datos:', error);
    }
}
