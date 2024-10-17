import {confirmarOperacion} from './alertas.js';
import {modificar} from './alertas.js';

const form = document.getElementById('updateCategoria');

function extractDriveId(url) {
    const regex = /\/d\/([a-zA-Z0-9_-]+)/;
    const match = url.match(regex);
    return match ? match[1] : null;
}

function guardarCategoria() {
    form.addEventListener('submit', function(event) {
        event.preventDefault(); // Previene el comportamiento por defecto del formulario

        // Captura los datos del formulario
        const formData = new FormData(form);

        // Convierte los datos del formulario a un objeto
        const data = {};
        formData.forEach((value, key) => {
            data[key] = value;
        });

        // Extrae el ID de Google Drive si hay un campo 'imagen'
        if (data['imagen']) {
            const driveId = extractDriveId(data['imagen']); // Extrae el ID
            data['imagen'] = driveId; // Reemplaza la URL completa con el ID
        }

        // Convierte el objeto a JSON
        const jsonData = JSON.stringify(data);

        // Llamar a confirmarOperacion para la alerta
        confirmarOperacion('modificar', () => {
            // Si el usuario confirma, se procede con el envío de los datos
            axios.post("/categoria/guardarCategoria", jsonData, {
                headers: {
                    'Content-Type': 'application/json'
                }
            })
            .then(response => {
                console.log('Éxito:', response.data);
                modificar();
                // Redirige después del registro exitoso
                setTimeout(() => {
                    window.location.href = '/registrarCategoria';
                }, 1500);
            })
            .catch(error => {
                console.error('Error:', error);
                setTimeout(() => {
                            window.location.href = '/registrarCategoria';
                        }, 1500);

            });
        });
    });
}

// Llama a la función para añadir el listener
guardarCategoria();
