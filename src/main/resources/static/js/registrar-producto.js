import { registrar } from './alertas.js';

const form = document.getElementById('addProducto');

function extractDriveId(url) {
    const regex = /\/d\/([a-zA-Z0-9_-]+)/;
    const match = url.match(regex);
    return match ? match[1] : null;
}

function guardarProducto() {
    form.addEventListener('submit', function (event) {
        event.preventDefault(); // Previene el comportamiento por defecto del formulario

        // Captura los datos del formulario
        const formData = new FormData(form);
        //console.log("DATOS FORMULARIO:", Array.from(formData.entries()));

        // Convierte los datos del formulario a un objeto
        const data = {};
        formData.forEach((value, key) => {
            data[key] = value;
        });

        // Asegúrate de que el precio sea un número
        data["precio"] = parseFloat(data["precio"]);

        // Aquí se asume que estás usando los IDs en lugar de objetos
        data["marca"] = { id: parseInt(data["marcas"]) }; // Cambia 'marcas' a 'marca'
        data["categoria"] = { id: parseInt(data["categorias"]) }; // Cambia 'categorias' a 'categoria'

        delete data["marcas"]; // Elimina la propiedad original
        delete data["categorias"]; // Elimina la propiedad original
        
        // Extrae el ID de Google Drive si hay un campo 'imagen'
        if (data['imagen']) {
            const driveId = extractDriveId(data['imagen']); // Extrae el ID
            data['imagen'] = driveId; // Reemplaza la URL completa con el ID
        }

        // Convierte el objeto a JSON
        const jsonData = JSON.stringify(data);
        console.log('JSON enviado:', jsonData);

        // Envía los datos JSON usando Axios
        axios.post("/producto/guardarProducto", jsonData, {
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(response => {
                console.log('Éxito:', response.data);
                registrar();
            })
            .catch(error => {
                console.error('Error:', error);
            });

         });
}

function getMarcas() {
    axios.get("/marca/listarMarcas")
        .then(function (response) {
            const marcas = response.data;
            const selecetMarca = document.querySelector("#marcas");
            let htmlContent = '';

            //valido si hay marcar cargadar
            if (marcas.length === 0) {
                selecetMarca.innerHTML = '<option value="" selected>No existen marcas registradas</option>';
            } else {
                //Agrego opcion por defecto
                htmlContent += `<option value="" selected hidden>Seleccionar Marca</option>`;
                marcas.forEach(marca => {
                    htmlContent += ` <option value="${marca.id}">${marca.nombre}</option>
                        `;
                });
                selecetMarca.innerHTML = htmlContent;
            }


        })
        .catch((err) => console.error(err));
}

function getCategorias() {
    axios.get("/categoria/listarCategorias")
        .then(function (response) {
            const categorias = response.data;
            const selectCategoria = document.querySelector("#categorias");
            let htmlContent = '';

            //valido si hay categorias registradas
            if (categorias.length === 0) {
                selectCategoria.innerHTML = '<option value="" selected>No existen categorias registradas</option>';
            } else {
                //agrego opcion por defecto
                htmlContent += `<option value="" selected hidden>Seleccionar Categoría</option>`;
                categorias.forEach(categoria => {
                    htmlContent += `<option value="${categoria.id}">${categoria.nombre}</option>
                        `;
                });
                selectCategoria.innerHTML = htmlContent;
            }

        })
        .catch((err) => console.error(err));
}


// Llama a la función para añadir el listener
guardarProducto();
getMarcas();
getCategorias();
