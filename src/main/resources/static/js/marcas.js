        function getMarcas() {
            axios.get("/marca/listarMarcas")
            .then(function(response) {
                const marcas = response.data;
                const tbody = document.querySelector(".tbody");
                let htmlContent = '';
                marcas.forEach(marca => {
                    htmlContent += `
                        <tr>
                            <td>${marca.nombre}</td>
                            <td><a href="/modificarMarca/${marca.id}">Modificar</a></td>
                            <td><button onclick="eliminarMarca(${marca.id})">Eliminar</button></td>
                        </tr>
                    `;
                });
                tbody.innerHTML = htmlContent;
            })
            .catch((err) => console.error(err));
        }
        
        function eliminarMarca(id) {
            axios.delete(`/marca/eliminarMarca/${id}`)
            .then(response => {
                console.log('Éxito:', response.data);
            })
            .catch(error => {
                console.error('Error:', error);
            });
        }
        function getMarcaById(id) {
            axios.get(`/marca/obtenerMarcaPorId/${id}`)
            .then(function(response) {
                const marca = response.data;
                const tbody = document.querySelector(".tbody");
                let htmlContent = '';
                if (marca) {
                    htmlContent += `
                        <tr>
                            <td>${marca.nombre}</td>
                            <td><a href="/modificarMarca/${marca.id}">Modificar</a></td>
                            <td><button onclick="eliminarMarca(${marca.id})">Eliminar</button></td>
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
            getMarcas();
            
            // Evento de entrada para el campo de filtro
            filtroInput.addEventListener('input', function() {
                const filtroValue = filtroInput.value;
                if (filtroValue.trim() !== "") {
                    getMarcaById(filtroValue);
                } else {
                    getMarcas();
                }
            });
        });