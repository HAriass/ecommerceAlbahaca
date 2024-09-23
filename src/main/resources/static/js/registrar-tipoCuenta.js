const form = document.getElementById('addTipoCuenta');

        function guardarTipoCuenta() {
            form.addEventListener('submit', function(event) {
                event.preventDefault(); // Previene el comportamiento por defecto del formulario

                // Captura los datos del formulario
                const formData = new FormData(form);

                // Convierte los datos del formulario a un objeto
                const data = {};
                formData.forEach((value, key) => {
                    data[key] = value;
                });

                // Convierte el objeto a JSON
                const jsonData = JSON.stringify(data);

                // Envía los datos JSON usando Axios
                axios.post("/tipoCuenta/guardarTipoCuenta", jsonData, {
                    headers: {
                        'Content-Type': 'application/json'
                    }
                })
                .then(response => {
                    console.log('Éxito:', response.data);
                    alert('Tipo de Cuenta registrada exitosamente!');
                    window.location.href='/registrarTipoCuenta';
                })
                
                .catch(error => {
                    console.error('Error:', error);
                });
            });
        }

        // Llama a la función para añadir el listener
        guardarTipoCuenta();

