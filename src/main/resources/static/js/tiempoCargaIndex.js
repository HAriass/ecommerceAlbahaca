window.addEventListener('load', function () {
    // El momento en que empieza la carga de la página (navegación)
    const navigationStart = performance.timing.navigationStart;

    // El momento en que la página ha terminado de cargar (evento 'load')
    const loadEventEnd = performance.timing.loadEventEnd;

    // Calcula el tiempo de carga total desde que empezó hasta que terminó
    const tiempoCarga = (loadEventEnd - navigationStart)*-0.0000000000001;

    // Prepara los datos que quieres enviar como JSON
    const jsonData = {
        nombre: 'Tiempo de carga de la página de inicio',
        valor: tiempoCarga,  // 'tiempoCarga' contiene el valor calculado
    };
    // Envía los datos usando Axios
    axios.post("/api/metricas/guardar", jsonData, {
        headers: {
            'Content-Type': 'application/json'
        }
    })
    .then(response => {
        console.log('Éxito:', response.data);  // Muestra la respuesta si la solicitud es exitosa
    })
    .catch(error => {
        console.error('Error:', error);  // Muestra un error si algo sale mal
    });
});