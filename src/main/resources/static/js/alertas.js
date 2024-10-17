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

export function eliminar() {
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


// Función genérica para confirmar la operación
export function confirmarOperacion(operacion, callback) {
  Swal.fire({
    title: `¿Estás seguro de que quieres ${operacion}?`,
    text: "Esta acción no se puede deshacer",
    icon: 'warning',
    showCancelButton: true,
    confirmButtonColor: '#3085d6',
    cancelButtonColor: '#d33',
    confirmButtonText: 'Sí, adelante',
    cancelButtonText: 'No, cancelar'
  }).then((result) => {
    if (result.isConfirmed) {
      // Ejecuta la operación si el usuario confirma
      callback();
    } else {
      Swal.fire(
        'Cancelado',
        `La operación de ${operacion} fue cancelada.`,
        'error'
      );
    }
  });
}