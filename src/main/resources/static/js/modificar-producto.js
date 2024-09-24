const form = document.getElementById('updateProducto');

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


    //validaciones
    if (data["nombre"] === "" || data["descripcion"] === "" || data["imagen"] === "" || data["marcas"] === "" || data["categorias"] === "") {

        Swal.fire({
            title: "Error",
            text: "Debe completar todos los datos del formulario!",
            icon: "error"
        });

        return;
    } else if (isNaN(data["precio"]) || data["precio"] <= 0) {

        Swal.fire({
            title: "Error",
            text: "Debe ingresar un precio valido y mayor a cero!",
            icon: "error"
        });

        return;
    }

    // Aquí se asume que estás usando los IDs en lugar de objetos
    data["marca"] = { id: parseInt(data["marcas"]) }; // Cambia 'marcas' a 'marca'
    data["categoria"] = { id: parseInt(data["categorias"]) }; // Cambia 'categorias' a 'categoria'

    delete data["marcas"]; // Elimina la propiedad original
    delete data["categorias"]; // Elimina la propiedad original

    // Convierte el objeto a JSON
    const jsonData = JSON.stringify(data);
    // console.log('JSON enviado:', jsonData);

    // Envía los datos JSON usando Axios
    axios.post("/producto/guardarProducto", jsonData, {
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {

            Swal.fire({
                position: "top-center",
                icon: "success",
                title: "Producto modificado correctamente!",
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

});

async function getMarcas(marcaProducto) {
    axios.get("/marca/listarMarcas")
        .then(function (response) {
            const marcas = response.data;
            const selecetMarca = document.querySelector("#marcas");
            let htmlContent = '';
            marcas.forEach(marca => {
                htmlContent += `
                <option value="${marca.id}">${marca.nombre}</option>
            `;
            });
            selecetMarca.innerHTML = htmlContent;

            //seteo por defecto el valor del selectMarca con el localstorage
            const marcaEncontrada = localStorage.getItem("marcaEncontrada");
            if (marcaEncontrada) {
                selecetMarca.value = marcaProducto;
            }
        })
        .catch((err) => console.error(err));
}

async function getCategorias(categoriaProducto) {
    axios.get("/categoria/listarCategorias")
        .then(function (response) {
            const categorias = response.data;
            const selectCategoria = document.querySelector("#categorias");
            let htmlContent = '';
            categorias.forEach(categoria => {
                htmlContent += `
                <option value="${categoria.id}">${categoria.nombre}</option>
            `;
            });
            selectCategoria.innerHTML = htmlContent;

            //seteo por defecto el valor del selectCategoria con el localstorage
            const categoriaEncontrada = localStorage.getItem("categoriaEncontrada");
            if (categoriaEncontrada) {
                selectCategoria.value = categoriaProducto;
            }
        })
        .catch((err) => console.error(err));
}

async function getProductoById() {

    //obtengo de la url /obtenerProductoPorId/{id} el parametro {id}
    const path = window.location.pathname;
    // Dividir el path para extraer el ID del producto
    const pathParts = path.split('/');
    const id = pathParts[pathParts.length - 1]; // El último elemento es el ID


    axios.get("/producto/obtenerProductoPorId/" + id)
        .then(function (response) {
            const productoAModificar = response.data;

            //obtengi las marcas y id
            const marcaProducto = productoAModificar.marca.id;
            const categoriaProducto = productoAModificar.categoria.id;

            getMarcas(marcaProducto);
            getCategorias(categoriaProducto);

        })
        .catch((err) => console.error(err));

}

document.addEventListener('DOMContentLoaded', function () {
    //agrego el try-catch con await para asegurar que primero se ejecte la funcion para obtener el producto y luego las otras dos
    getProductoById();
});


