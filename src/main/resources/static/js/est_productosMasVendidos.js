async function loadContent(stat) {
    const mainContent = document.getElementById('main-content');

    if (stat === 'estadistica1') {
        mainContent.innerHTML = `
            <canvas id="chartMasVendidos" class="w-full max-w-2xl mt-16 mx-auto bg-white p-4 rounded shadow-md"></canvas>
        `;

        try {
            const response = await axios.get('/estadisticas/masVendidos');
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
