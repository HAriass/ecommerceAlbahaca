        function getMarcas() {
            axios.get("/marca/listarMarcas")
            .then(function(response) {
                const marcas = response.data;
                const tbody = document.querySelector("tbody");
                let htmlContent = '';
                marcas.forEach(marca => {
                    htmlContent += `
                        <tr>
                            <td>${marca.nombre}</td>
                            <td><button class="btn-modificar" onclick="location.href='/modificarMarca/${marca.id}'">Modificar</button></td>
                            <td><button class="btn-eliminar" onclick="eliminarMarca(${marca.id})">Eliminar</button></td>
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
                eliminar();
            })
            .catch(error => {
                console.error('Error:', error);
            });
        }
        function getMarcaById(id) {
            axios.get(`/marca/obtenerMarcaPorId/${id}`)
            .then(function(response) {
                const marca = response.data;
                const tbody = document.querySelector("tbody");
                let htmlContent = '';
                if (marca) {
                    htmlContent += `
                        <tr>
                            <td>${marca.nombre}</td>
                            <td><button class="btn-modificar" onclick="location.href='/modificarMarca/${marca.id}'">Modificar</button></td>
                            <td><button class="btn-eliminar" onclick="eliminarMarca(${marca.id})">Eliminar</button></td>
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
        
        function eliminar(){ {
              Swal.fire({
                title: "Eliminado!",
                text: "Registro eliminado correctamente.",
                showConfirmButton: false,
                icon: "success"
              });
              setTimeout(() => {
                window.location.reload();
            }, 1500);
            }
        }