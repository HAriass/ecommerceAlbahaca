import {confirmarOperacion} from './alertas.js';
import {eliminar} from './alertas.js';

function getCategorias() {
    axios.get("/categoria/listarCategorias")
        .then(function (response) {
            const categorias = response.data;
            const tbody = document.querySelector("tbody");
            let htmlContent = '';
            categorias.forEach(categoria => {
                htmlContent += `
                    <tr>
                        <td>${categoria.nombre}</td>
                        <td>${categoria.descripcion}</td>
                        <td><a class="btn-imagen" href="https://drive.google.com/uc?export=view&id=${categoria.imagen}" target="_blank">Ver Imagen</a></td>
                        <td><button class="btn-modificar" onclick="location.href='/modificarCategoria/${categoria.id}'">Modificar</button></td>
                        <td><button class="btn-eliminar" data-id="${categoria.id}">Eliminar</button></td>
                    </tr>
                `;
            });
            tbody.innerHTML = htmlContent;

            // Agregar event listeners a los botones de eliminar
            const eliminarButtons = document.querySelectorAll('.btn-eliminar');
            eliminarButtons.forEach(button => {
                button.addEventListener('click', function() {
                    const id = button.getAttribute('data-id');
                    eliminarCategoria(id); // Llama a la función eliminarCategoria con el ID
                });
            });
        })
        .catch((err) => console.error(err));
}

function eliminarCategoria(id) {
    confirmarOperacion('eliminar', () => {
        // Si el usuario confirma, se procede con la eliminación
        axios.delete(`/categoria/eliminarCategoria/${id}`)
            .then(response => {
                console.log('Éxito:', response.data);
                eliminar();
                getCategorias(); // Vuelve a cargar las categorías después de eliminar
            })
            .catch(error => {
                console.error('Error:', error);
            });
    });
}

function getCategoriaPorFiltro(filtro) {
    axios.get(`/categoria/obtenerCategoriaPorNombre/${filtro}`)
        .then(function (response) {
            const categorias = response.data;
            const tbody = document.querySelector("tbody");
            let htmlContent = '';
            if (categorias && categorias.length > 0) {
                categorias.forEach(categoria => {
                    htmlContent += `
                        <tr>
                            <td>${categoria.nombre}</td>
                            <td>${categoria.descripcion}</td>
                            <td><a class="btn-imagen" href="https://drive.google.com/uc?export=view&id=${categoria.imagen}" target="_blank">Ver Imagen</a></td>
                            <td><button class="btn-modificar" onclick="location.href='/modificarCategoria/${categoria.id}'">Modificar</button></td>
                            <td><button class="btn-eliminar" onclick="eliminarCategoria(${categoria.id})">Eliminar</button></td>
                        </tr>
                    `;
                });
            } else {
                htmlContent = '<tr><td colspan="5">No se encontraron categorías.</td></tr>';
            }

            tbody.innerHTML = htmlContent;
        })
        .catch((err) => {
            console.error(err);
            alert('Ocurrió un error al buscar categorías.'); // Notificación de error
        });
}

document.addEventListener("DOMContentLoaded", function () {
    const filtroInput = document.getElementById('filtro');
    const filtroName = document.getElementById('filtroName');

    // Cargar categorías al inicio
    getCategorias();

    // Evento de entrada para el campo de filtro por ID
    filtroInput.addEventListener('input', function () {
        const filtroValue = filtroInput.value;
        if (filtroValue.trim() !== "") {
            getCategoriaPorFiltro(filtroValue);
        } else {
            getCategorias();
        }
    });

    // Evento de entrada para el campo de filtro por nombre
    filtroName.addEventListener('input', function () {
        const filtroValue = filtroName.value;
        if (filtroValue.trim() !== "") {
            getCategoriaPorFiltro(filtroValue);
        } else {
            getCategorias();
        }
    });
});