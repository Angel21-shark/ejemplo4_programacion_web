// contacts.js - Lógica separada para la vista de contactos y Tema

document.addEventListener("DOMContentLoaded", function() {
    console.log("Sistema cargado correctamente");

    // ==========================================
    // Tema Dinámico (Ayu Light/Dark)
    // ==========================================
    const applyTheme = (isDark) => {
        if (isDark) {
            document.documentElement.setAttribute('data-theme', 'dark');
        } else {
            document.documentElement.removeAttribute('data-theme');
        }
    };

    // Detectar preferencia actual
    const mediaQuery = window.matchMedia('(prefers-color-scheme: dark)');
    applyTheme(mediaQuery.matches);

    // Escuchar cambios en vivo desde el sistema operativo
    mediaQuery.addEventListener('change', (e) => {
        applyTheme(e.matches);
    });

    // ==========================================
    // UI Helpers (Bootstrap)
    // ==========================================
    
    // Inicializar tooltips o alertas
    var alertList = document.querySelectorAll('.alert')
    var alerts =  [].slice.call(alertList).map(function (element) {
      return new bootstrap.Alert(element)
    });

    // Confirmación antes de eliminar
    const deleteForms = document.querySelectorAll('.delete-form');
    deleteForms.forEach(form => {
        form.addEventListener('submit', function(e) {
            if(!confirm('¿Estás seguro de que deseas eliminar este registro de forma permanente?')) {
                e.preventDefault();
            }
        });
    });
});
