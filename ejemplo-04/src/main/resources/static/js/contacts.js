// contacts.js - Lógica separada para la vista de contactos

document.addEventListener("DOMContentLoaded", function() {
    console.log("Contactos cargados correctamente");

    // Inicializar tooltips o alertas si es necesario
    var alertList = document.querySelectorAll('.alert')
    var alerts =  [].slice.call(alertList).map(function (element) {
      return new bootstrap.Alert(element)
    });

    // Confirmación antes de eliminar
    const deleteForms = document.querySelectorAll('.delete-form');
    deleteForms.forEach(form => {
        form.addEventListener('submit', function(e) {
            if(!confirm('¿Estás seguro de que deseas eliminar este elemento?')) {
                e.preventDefault();
            }
        });
    });
});
