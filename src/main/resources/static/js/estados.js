import {confirmarOperacion} from './alertas.js';
import {eliminar} from './alertas.js';

function getEstados() {
    axios.get("/estado/listarEstados")
        .then(function (response) {
            const estados = response.data;
            const tbody = document.querySelector("tbody");
            let htmlContent = '';
            estados.forEach(estado => {
                htmlContent += `
                        <tr>
                            <td>${estado.nombre}</td>
                            <td>${estado.descripcion}</td>
                            <td><button class="btn-modificar" onclick="location.href='/modificarEstado/${estado.id}'">Modificar</button></td>
                            <td><button class="btn-eliminar" data-id="${estado.id}">Eliminar</button></td>
                        </tr>
                    `;
            });
            tbody.innerHTML = htmlContent;

            // Agregar event listeners a los botones de eliminar
            const eliminarButtons = document.querySelectorAll('.btn-eliminar');
            eliminarButtons.forEach(button => {
                button.addEventListener('click', function() {
                    const id = button.getAttribute('data-id');
                    eliminarEstado(id); // Llama a la función eliminarProducto con el ID
                });
            });
        })
        .catch((err) => console.error(err));
}

function eliminarEstado(id) {
    confirmarOperacion('eliminar', () => {
    axios.delete(`/estado/eliminarEstado/${id}`)
        .then(response => {
            console.log('Éxito:', response.data);
            eliminar();
            getEstados();
        })
        .catch(error => {
            console.error('Error:', error);
        });
    });
}

function getEstadoById(id) {
    axios.get(`/estado/obtenerEstadoPorId/${id}`)
        .then(function (response) {
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
            } else {
                htmlContent = '<tr><td colspan="4">No se encontraron estados.</td></tr>';
            }
            tbody.innerHTML = htmlContent;
        })
        .catch((err) => console.error(err));
}

function getEstadoByName(filtroName) {
    axios.get(`/estado/obtenerEstadoPorNombre/${filtroName}`)
        .then(function (response) {
            const estados = response.data;
            const tbody = document.querySelector("tbody");
            let htmlContent = '';
            if (estados && estados.length > 0) {
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
            } else {
                htmlContent = '<tr><td colspan="4">No se encontraron estados.</td></tr>';
            }
            tbody.innerHTML = htmlContent;
        })
        .catch((err) => console.error(err));

}

document.addEventListener("DOMContentLoaded", function () {
    const filtroInput = document.getElementById('filtro');
    const filtroName = document.getElementById('filtroName');

    // Cargar estados al inicio
    getEstados();

    // Evento de entrada para el campo de filtro
    filtroInput.addEventListener('input', function () {
        const filtroValue = filtroInput.value;
        if (filtroValue.trim() !== "") {
            getEstadoById(filtroValue);
        } else {
            getEstados();
        }
    });

    filtroName.addEventListener('input', function () {
        const filtroValue = filtroName.value;
        if (filtroValue.trim() !== "") {
            getEstadoByName(filtroValue);
        } else {
            getEstados();
        }
    });

});
