import {confirmarOperacion} from './alertas.js';
import {eliminar} from './alertas.js';

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
                            <td><button class="btn-eliminar" data-id="${marca.id}">Eliminar</button></td>
                        </tr>
                    `;
            });
            tbody.innerHTML = htmlContent;
            
            const eliminarButtons = document.querySelectorAll('.btn-eliminar');
            eliminarButtons.forEach(button => {
                button.addEventListener('click', function() {
                    const id = button.getAttribute('data-id');
                    eliminarMarca(id); // Llama a la función eliminarCategoria con el ID
                });
            });
        })
        .catch((err) => console.error(err));
}

function eliminarMarca(id) {
    confirmarOperacion('eliminar', () => {
        axios.delete(`/marca/eliminarMarca/${id}`)
            .then(response => {
                console.log('Éxito:', response.data);
                eliminar();
                getMarcas(); // Vuelve a cargar los productos después de eliminar
            })
            .catch(error => {
                console.error('Error:', error);
            });
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