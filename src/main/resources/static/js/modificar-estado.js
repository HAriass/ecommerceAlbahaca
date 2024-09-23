const form = document.getElementById('updateEstado');

function guardarEstado() {
    form.addEventListener('submit', function(event) {
        event.preventDefault(); // Previene el comportamiento por defecto del formulario

        // Captura los datos del formulario
        const formData = new FormData(form);
        console.log(formData);

        // Convierte los datos del formulario a un objeto
        const data = {};
        formData.forEach((value, key) => {
            data[key] = value;
        });

        // Convierte el objeto a JSON
        const jsonData = JSON.stringify(data);
        console.log(jsonData); // Añade esta línea
        // Envía los datos JSON usando Axios
        axios.post("/estado/guardarEstado", jsonData, {
            headers: {
                'Content-Type': 'application/json'
            }
        })
        .then(response => {
            console.log('Éxito:', response.data);
            // Opcional: redirigir a otra página o mostrar un mensaje de éxito
            alert('Estado Modificado exitosamente!');
            window.location.href='/registrarEstado';
        })
        .catch(error => {
            console.error('Error:', error);
        });
        
        
    });
}

document.addEventListener('DOMContentLoaded', function() {
    guardarEstado();
});
