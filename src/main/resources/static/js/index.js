const tarjeta = document.querySelector(".tarjeta-categoria .grid"); // Selecciona el contenedor de grid
console.log("entre a la funcion");
function getCategorias() {
    console.log("entre a la funcion");
    axios.get("/categoria/listarCategorias")
    .then(function(response) {
        const categorias = response.data;
        let htmlContent = '';
        
        categorias.forEach(categoria => {
            htmlContent += `
                <div class="max-w-sm bg-gradient-to-r from-gray-900 to-gray-800 border border-gray-800 rounded-lg shadow flex flex-col justify-between">
                    <a href="#">
                        <img style="width:100%" class="rounded-t-lg" src="https://drive.google.com/thumbnail?id=${categoria.imagen}" alt="${categoria.nombre}" />
                    </a>
                    <div class="p-5 flex flex-col flex-grow">
                        <a href="#">
                            <h5 class="mb-2 text-2xl font-bold tracking-tight text-white">${categoria.nombre}</h5>
                        </a>
                        <p class="mb-3 font-normal text-white dark:text-gray-400">${categoria.descripcion}</p>
                        <div class="mt-auto">
                            <a href="#" class="inline-flex items-center px-3 py-2 text-sm font-medium text-center text-white bg-blue-700 rounded-lg hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300">
                                Ver más
                                <svg class="rtl:rotate-180 w-3.5 h-3.5 ms-2" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 14 10">
                                    <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M1 5h12m0 0L9 1m4 4L9 9"/>
                                </svg>
                            </a>
                        </div>
                    </div>
                </div>
            `;
        });

        tarjeta.innerHTML = htmlContent; // Actualiza el contenido de grid
    })
    .catch((err) => console.error(err));
}

document.addEventListener("DOMContentLoaded", function() {
    getCategorias();
});
