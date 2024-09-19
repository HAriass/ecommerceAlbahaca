        function getProductos() {
            axios.get("/producto/listarProductos")
            .then(function(response) {
                const productos = response.data;
                const tbody = document.querySelector(".tbody");
                let htmlContent = '';
                productos.forEach(producto => {
                    htmlContent += `
                        <tr>
                            <td>${producto.nombre}</td>
                            <td>${producto.descripcion}</td>
                            <td>${producto.precio}</td>
                            <td>${producto.marca}</td>
                            <td>${producto.categoria}</td>
                            <td><a href="/modificarProducto/${producto.id}">Modificar</a></td>
                            <td><button onclick="eliminarProducto(${producto.id})">Eliminar</button></td>
                        </tr>
                    `;
                });
                tbody.innerHTML = htmlContent;
            })
            .catch((err) => console.error(err));
        }
        
        function eliminarProducto(id) {
            axios.delete(`/producto/eliminarProducto/${id}`)
            .then(response => {
                console.log('Éxito:', response.data);
            })
            .catch(error => {
                console.error('Error:', error);
            });
        }
        function getProductoById(id) {
            axios.get(`/producto/obtenerProductoPorId/${id}`)
            .then(function(response) {
                const producto = response.data;
                const tbody = document.querySelector(".tbody");
                let htmlContent = '';
                if (producto) {
                    htmlContent += `
                        <tr>
                            <td>${producto.nombre}</td>
                            <td><a href="/modificarProducto/${producto.id}">Modificar</a></td>
                            <td><button onclick="eliminarProducto(${producto.id})">Eliminar</button></td>
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
            getProductos();
            
            // Evento de entrada para el campo de filtro
            filtroInput.addEventListener('input', function() {
                const filtroValue = filtroInput.value;
                if (filtroValue.trim() !== "") {
                    getProductoById(filtroValue);
                } else {
                    getProductos();
                }
            });
        });