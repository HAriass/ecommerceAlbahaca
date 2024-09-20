        function getCategorias() {
            axios.get("/categoria/listarCategorias")
            .then(function(response) {
                const categorias = response.data;
                const tbody = document.querySelector("tbody");
                let htmlContent = '';
                categorias.forEach(categoria => {
                    htmlContent += `
                        <tr>
                            <td>${categoria.nombre}</td>
                            <td>${categoria.descripcion}</td>
                            <td><button class="btn-modificar" onclick="location.href='/modificarCategoria/${categoria.id}'">Modificar</button></td>
                            <td><button class="btn-eliminar" onclick="eliminarCategoria(${categoria.id})">Eliminar</button></td>
                        </tr>
                    `;
                });
                tbody.innerHTML = htmlContent;
            })
            .catch((err) => console.error(err));
        }
        
        function eliminarCategoria(id) {
            axios.delete(`/categoria/eliminarCategoria/${id}`)
            .then(response => {
                console.log('Éxito:', response.data);
                window.location.href='/registrarCategoria';
            })
            .catch(error => {
                console.error('Error:', error);
            });
        }
        function getCategoriaById(id) {
            axios.get(`/categoria/obtenerCategoriaPorId/${id}`)
            .then(function(response) {
                const categoria = response.data;
                const tbody = document.querySelector("tbody");
                let htmlContent = '';
                if (categoria) {
                    htmlContent += `
                        <tr>
                            <td>${categoria.nombre}</td>
                            <td>${categoria.descripcion}</td>
                            <td><a href="/modificarMarca/${categoria.id}">Modificar</a></td>
                            <td><button onclick="eliminarMarca(${categoria.id})">Eliminar</button></td>
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
            getCategorias();
            
            // Evento de entrada para el campo de filtro
            filtroInput.addEventListener('input', function() {
                const filtroValue = filtroInput.value;
                if (filtroValue.trim() !== "") {
                    getCategoriaById(filtroValue);
                } else {
                    getCategorias();
                }
            });
        });
