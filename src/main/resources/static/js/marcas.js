function getMarcas() {
    axios.get("/marca/listarMarcas")
        .then(function (response) {
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
        .then(function (response) {
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
            } else {
                htmlContent = '<tr><td colspan="3">No se encontraron marcas.</td></tr>';
            }
            tbody.innerHTML = htmlContent;
        })
        .catch((err) => console.error(err));
}

function getMarcaByName(filtroName) {
    axios.get(`/marca/obtenerMarcaPorNombre/${filtroName}`)
        .then(function (response) {
            const marcas = response.data;
            const tbody = document.querySelector("tbody");
            let htmlContent = '';
            if (marcas && marcas.length > 0) {
                marcas.forEach(marca => {
                    htmlContent += `
                        <tr>
                            <td>${marca.nombre}</td>
                            <td><button class="btn-modificar" onclick="location.href='/modificarMarca/${marca.id}'">Modificar</button></td>
                            <td><button class="btn-eliminar" onclick="eliminarMarca(${marca.id})">Eliminar</button></td>
                        </tr>
                    `;
                });
            } else {
                htmlContent = '<tr><td colspan="3">No se encontraron marcas.</td></tr>';
            }
            tbody.innerHTML = htmlContent;
        })
        .catch((err) => console.error(err));
}


document.addEventListener("DOMContentLoaded", function () {
    const filtroInput = document.getElementById('filtro');
    const filtroName = document.getElementById('filtroName');

    // Cargar usuarios al inicio
    getMarcas();

    // Evento de entrada para el campo de filtro
    filtroInput.addEventListener('input', function () {
        const filtroValue = filtroInput.value;
        if (filtroValue.trim() !== "") {
            getMarcaById(filtroValue);
        } else {
            getMarcas();
        }
    });

    filtroName.addEventListener('input', function () {
        const filtroValue = filtroName.value;
        if (filtroValue.trim() !== "") {
            getMarcaByName(filtroValue);
        } else {
            getMarcas();
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