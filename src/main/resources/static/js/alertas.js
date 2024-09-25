export function registrar() {
    Swal.fire({
        icon: "success",
        title: "Registrado Correctamente",
        showConfirmButton: false,
        timer: 1500
    }).then(() => {
        window.location.reload();
    });
}

export function modificar(){
    Swal.fire({
        icon: "success",
        title: "Modificado Correctamente",
        showConfirmButton: false,
        timer: 1500
    })
}
