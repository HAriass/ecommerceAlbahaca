const form = document.getElementById('addProducto');

        function guardarProducto() {
            form.addEventListener('submit', function(event) {
                event.preventDefault(); // Previene el comportamiento por defecto del formulario

                // Captura los datos del formulario
                const formData = new FormData(form);

                // Convierte los datos del formulario a un objeto
                const data = {};
                formData.forEach((value, key) => {
                    data[key] = value;
                });
                
                // Asegúrate de que el precio sea un número
                data["precio"] = parseFloat(data["precio"]);

                // Convierte el objeto a JSON
                const jsonData = JSON.stringify(data);
                console.log('JSON enviado:', jsonData);

                // Envía los datos JSON usando Axios
                axios.post("/producto/guardarProducto", jsonData, {
                    headers: {
                        'Content-Type': 'application/json'
                    }
                })
                .then(response => {
                    console.log('Éxito:', response.data);
                })
                .catch(error => {
                    console.error('Error:', error);
                });
            });
        }

        // Llama a la función para añadir el listener
        guardarProducto();

