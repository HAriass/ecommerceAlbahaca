function getProductos() {
    axios.get("/producto/listarProductos")
        .then(function (response) {
            const productos = response.data;
            const tbody = document.querySelector("tbody");
            let htmlContent = '';
            productos.forEach(producto => {
                // Comprobar que producto, marca y categoría existen
                if (producto && producto.marca && producto.categoria) {
                    htmlContent += `
                            <tr>
                                <td>${producto.nombre}</td>
                                <td>${producto.descripcion}</td>
                                <td><a href="${producto.imagen}" target="_blank">Ver imagen</a></td>
                                <td>${producto.precio}</td>
                                <td>
                                    ${producto.marca.nombre}
                                </td>
                                <td>
                                    ${producto.categoria.nombre}
                                </td>
                                <td><button class="btn-modificar" onclick="location.href='/modificarProducto/${producto.id}'">Modificar</button></td>
                                <td><button class="btn-eliminar" onclick="eliminarProducto(${producto.id}, '${producto.nombre}')">Eliminar</button></td>
                            </tr>
                        `;
                } else {
                    console.warn('Producto o propiedades faltantes:', producto);
                }
            });

            tbody.innerHTML = htmlContent;
        })
        .catch((err) => console.error(err));
}

function eliminarProducto(id, nombre) {

    Swal.fire({
        title: "Está seguro de eliminar este producto?",
        text: "Se eliminará: " + nombre,
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Sí, eliminar",
        cancelButtonText: "Cancelar"
    }).then((result) => {
        if (result.isConfirmed) {

            axios.delete(`/producto/eliminarProducto/${id}`)
                .then(response => {

                    Swal.fire({
                        position: "top-center",
                        icon: "success",
                        title: "Producto eliminado correctamente!",
                        showConfirmButton: false,
                        timer: 1500
                    });

                    setTimeout(function () {
                        window.location.href = '/registrarProducto';
                    }, 1500);
                })
                .catch(error => {
                    console.error('Error:', error);
                });

        } else {
            return;
        }
    });


}
function getProductoById(id) {
    axios.get(`/producto/obtenerProductoPorId/${id}`)
        .then(function (response) {
            const producto = response.data;
            const tbody = document.querySelector("tbody");
            let htmlContent = '';
            if (producto) {
                htmlContent += `
                        <tr>
                            <td>${producto.nombre}</td>
                            <td>${producto.descripcion}</td>
                            <td><a href="${producto.imagen}" target="_blank">Ver imagen</a></td>
                            <td>${producto.precio}</td>
                            <td>
                                <li>${producto.marca.nombre}</li>
                            </td>
                            <td>
                                <li>${producto.categoria.nombre}</li>
                                <li>${producto.categoria.descripcion}</li>
                            </td>
                            <td><button onclick="location.href='/modificarProducto/${producto.id}'">Modificar</button></td>
                            <td><button onclick="eliminarProducto(${producto.id})">Eliminar</button></td>
                        </tr>
                    `;
            }
            tbody.innerHTML = htmlContent;
        })
        .catch((err) => console.error(err));
}


document.addEventListener("DOMContentLoaded", function () {
    const filtroInput = document.getElementById('filtro');

    // Cargar usuarios al inicio
    getProductos();

    // Evento de entrada para el campo de filtro
    filtroInput.addEventListener('input', function () {
        const filtroValue = filtroInput.value;
        if (filtroValue.trim() !== "") {
            getProductoById(filtroValue);
        } else {
            getProductos();
        }
    });
});