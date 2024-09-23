const form = document.getElementById('addCuenta');

        function guardarCuenta() {
            form.addEventListener('submit', function(event) {
                event.preventDefault(); // Previene el comportamiento por defecto del formulario

                // Captura los datos del formulario
                const formData = new FormData(form);

                // Convierte los datos del formulario a un objeto
                const data = {};
                formData.forEach((value, key) => {
                    data[key] = value;
                });
                
                //para que funcione, el tipo de cuenta tiene que estar tal cual.. 
                data['rol'] = "USER";

                // Convierte el objeto a JSON
                const jsonData = JSON.stringify(data);

                // Envía los datos JSON usando Axios
                axios.post("/cuenta/guardarCuenta", jsonData, {
                    headers: {
                        'Content-Type': 'application/json',
                    }
                })
                .then(response => {
                    console.log('Éxito:', response.data);
                    //window.location.href='/registrarEstado';
                })
                .catch(error => {
                    console.error('Error:', error);
                });
                
            });
        }

        // Llama a la función para añadir el listener
        guardarCuenta();
