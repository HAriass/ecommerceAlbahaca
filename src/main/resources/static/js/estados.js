        function getEstados() {
            axios.get("/estado/listarEstados")
            .then(function(response) {
                const estados = response.data;
                const tbody = document.querySelector("tbody");
                let htmlContent = '';
                estados.forEach(estado => {
                    htmlContent += `
                        <tr>
                            <td>${estado.nombre}</td>
                            <td>${estado.descripcion}</td>
                            <td><button class="btn-modificar" onclick="location.href='/modificarEstado/${estado.id}'">Modificar</button></td>
                            <td><button class="btn-eliminar" onclick="eliminarEstado(${estado.id})">Eliminar</button></td>
                        </tr>
                    `;
                });
                tbody.innerHTML = htmlContent;
            })
            .catch((err) => console.error(err));
        }
        
        function eliminarEstado(id) {
            axios.delete(`/estado/eliminarEstado/${id}`)
            .then(response => {
                console.log('Éxito:', response.data);
                eliminar();
            })
            .catch(error => {
                console.error('Error:', error);
            });
        }
        
        function getEstadoById(id) {
            axios.get(`/estado/obtenerEstadoPorId/${id}`)
            .then(function(response) {
                const estado = response.data;
                const tbody = document.querySelector("tbody");
                let htmlContent = '';
                if (estado) {
                    htmlContent += `
                        <tr>
                            <td>${estado.nombre}</td>
                            <td>${estado.descripcion}</td>
                            <td><button class="btn-modificar" onclick="location.href='/modificarEstado/${estado.id}'">Modificar</button></td>
                            <td><button class="btn-eliminar" onclick="eliminarEstado(${estado.id})">Eliminar</button></td>
                        </tr>
                    `;
                }
                tbody.innerHTML = htmlContent;
            })
            .catch((err) => console.error(err));
        }
        
        document.addEventListener("DOMContentLoaded", function() {
            const filtroInput = document.getElementById('filtro');
            
            // Cargar estados al inicio
            getEstados();
            
            // Evento de entrada para el campo de filtro
            filtroInput.addEventListener('input', function() {
                const filtroValue = filtroInput.value;
                if (filtroValue.trim() !== "") {
                    getEstadoById(filtroValue);
                } else {
                    getEstados();
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
        