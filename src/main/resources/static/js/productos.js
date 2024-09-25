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
                                    <td title="${producto.nombre}">${producto.nombre}</td>
                                    <td title="${producto.descripcion}">${producto.descripcion}</td>
                                    <td title="${producto.precio}">${producto.precio}</td>
                                    <td title="${producto.marca.nombre}">${producto.marca.nombre}</td>
                                    <td title="${producto.categoria.nombre}">${producto.categoria.nombre}</td>
                                    <td><a class="btnTabla btn-imagen" href="${producto.imagen}" target="_blank">Ver imagen</a></td>
                                    <td><a class="btnTabla btn-modificar" onclick="location.href='/modificarProducto/${producto.id}'">Modificar</a></td>
                                    <td><a class="btnTabla btn-eliminar" onclick="eliminarProducto(${producto.id})">Eliminar</a></td>
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

function eliminarProducto(id) {
    axios.delete(`/producto/eliminarProducto/${id}`)
        .then(response => {
            console.log('Éxito:', response.data);
            eliminar();
        })
        .catch(error => {
            console.error('Error:', error);
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
                                    <td title="${producto.nombre}">${producto.nombre}</td>
                                    <td title="${producto.descripcion}">${producto.descripcion}</td>
                                    <td title="${producto.precio}">${producto.precio}</td>
                                    <td title="${producto.marca.nombre}">${producto.marca.nombre}</td>
                                    <td title="${producto.categoria.nombre}">${producto.categoria.nombre}</td>
                                    <td><a class="btnTabla btn-imagen" href="${producto.imagen}" target="_blank">Ver imagen</a></td>
                                    <td><a class="btnTabla btn-modificar" onclick="location.href='/modificarProducto/${producto.id}'">Modificar</a></td>
                                    <td><a class="btnTabla btn-eliminar" onclick="eliminarProducto(${producto.id})">Eliminar</a></td>
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

function eliminar() {
    {
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
