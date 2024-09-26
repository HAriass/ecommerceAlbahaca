const contenido = `
            <nav class="navbar bg-black border-gray-200 bg-black">
                <div class="max-w-screen-xl flex flex-wrap items-center justify-between mx-auto p-4">
                    <a href="/" class="flex items-center space-x-2 rtl:space-x-reverse">
                        <img src="/imgs/albahaca.png" class="w-8 h-8" alt="Logo Albahaca">
                        <span class="self-center text-2xl font-semibold whitespace-nowrap text-white">Albahaca Suplementos</span>
                    </a>
                    <div class="hidden w-full md:block md:w-auto" id="navbar-default">
                        <ul class="font-medium flex flex-col p-4 md:p-0 mt-4 border md:flex-row md:space-x-8 rtl:space-x-reverse md:mt-0 md:border-0 dark:bg-black md:dark:bg-black dark:border-gray-700">
                            <!-- Siempre visible -->
                            <li>
                                <a href="/" class="block py-2 px-3 text-white rounded hover:bg-gray-700 md:hover:bg-transparent md:border-0 md:hover:text-blue-400" aria-current="page">Inicio</a>
                            </li>

                            <!-- Visible solo para ADMIN -->
                            <li sec:authorize="hasAuthority('ADMIN')">
                                <a href="/menu" class="block py-2 px-3 text-white rounded hover:bg-gray-700 md:hover:bg-transparent md:border-0 md:hover:text-blue-400">Menu</a>
                            </li>

                            <!-- Icono de usuario con menú desplegable -->
                            <li class="relative">
                                <button id="userMenuButton" data-dropdown-toggle="userMenu" class="block py-2 px-3 text-white rounded hover:bg-gray-700">
                                    <img src="https://cdn-icons-png.flaticon.com/512/747/747376.png" class="w-6 h-6 rounded-full filter invert" alt="User icon">
                                </button>

                                <!-- Menú desplegable -->
                                <div id="userMenu" class="hidden absolute right-0 mt-2 w-48 bg-black text-center rounded-md shadow-lg">
                                    <ul class="py-2">
                                        <!-- Visible solo para usuarios no autenticados -->
                                        <li sec:authorize="isAnonymous()">
                                            <a href="/login" class="block px-2 py-2 text-white">Iniciar Sesion</a>
                                        </li>
                                        <li sec:authorize="isAnonymous()">
                                            <a href="/registrarCuenta" class="block px-4 py-2 text-white">Registrarse</a>
                                        </li>

                                        <!-- Visible solo para usuarios autenticados -->
                                        <li sec:authorize="isAuthenticated()">
                                            <a href="/logout" class="block px-4 py-2 text-red-400">Cerrar Sesion</a>
                                        </li>
                                    </ul>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>`;

function cargarBarra (){
    const barraNav = document.querySelector(".divNavBar");
    barraNav.innerHTML = contenido;
}

document.addEventListener('DOMContentLoaded', function() {
    cargarBarra();
});


