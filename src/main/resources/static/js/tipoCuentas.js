
        function getTipoCuentas() {
            axios.get("/tipoCuenta/listarTipoCuentas")
            .then(function(response) {
                const tipoCuentas = response.data;
                const tbody = document.querySelector("tbody");
                let htmlContent = '';
                tipoCuentas.forEach(tipoCuenta => {
                    htmlContent += `
                        <tr>
                            <td>${tipoCuenta.nombre}</td>
                            <td>${tipoCuenta.descripcion}</td>
                            <td><button class="btn-eliminar" onclick="eliminarTipoCuenta(${tipoCuenta.id})">Eliminar</button></td>
                        </tr>
                    `;
                });
                tbody.innerHTML = htmlContent;
            })
            .catch((err) => console.error(err));
        }
        
        function eliminarTipoCuenta(id) {
            axios.delete(`/tipoCuenta/eliminarTipoCuenta/${id}`)
            .then(response => {
                console.log('Éxito:', response.data);
                window.location.href='/registrarTipoCuenta';
            })
            .catch(error => {
                console.error('Error:', error);
            });
        }
        function getTipoCuentaById(id) {
            axios.get(`/tipoCuenta/obtenerTipoCuentaPorId/${id}`)
            .then(function(response) {
                const tipoCuenta = response.data;
                const tbody = document.querySelector("tbody");
                let htmlContent = '';
                if (tipoCuenta) {
                    htmlContent += `
                        <tr>
                            <td>${tipoCuenta.nombre}</td>
                            <td>${tipoCuenta.descripcion}</td>
                            <td><button onclick="eliminarTipoCuenta(${tipoCuenta.id})">Eliminar</button></td>
                        </tr>
                    `;
                }
                tbody.innerHTML = htmlContent;
            })
            .catch((err) => console.error(err));
        }
        

        document.addEventListener("DOMContentLoaded", function() {
            const filtroInput = document.getElementById('filtro');
            
            // Cargar usuarios al inicio
            getTipoCuentas();
            
            // Evento de entrada para el campo de filtro
            filtroInput.addEventListener('input', function() {
                const filtroValue = filtroInput.value;
                if (filtroValue.trim() !== "") {
                    getTipoCuentaById(filtroValue);
                } else {
                    getTipoCuentas();
                }
            });
        });