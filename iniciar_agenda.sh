#!/bin/bash

echo "=========================================="
echo "🚀 INICIANDO TU AGENDA PREMIUM"
echo "=========================================="
echo "Preparando el servidor, por favor espera..."

# Moverse a la carpeta del proyecto de Spring Boot
cd ejemplo-04 || exit

# Ejecutar Spring Boot en segundo plano
mvn spring-boot:run &
SPRING_PID=$!

echo "⏳ Compilando y levantando servicios... (esto puede tardar unos 10-15 segundos)"

# Esperar a que Spring Boot inicie (asumiendo que toma unos 15 segundos)
sleep 15

echo "🌐 Abriendo la aplicación en tu navegador de Windows..."
# En WSL, explorer.exe puede invocar URLs directamente en el navegador nativo
if command -v explorer.exe &> /dev/null; then
    explorer.exe "http://localhost:8080"
else
    # Por si acaso están usando un Linux puro en lugar de WSL
    xdg-open "http://localhost:8080" || echo "Abre http://localhost:8080 en tu navegador."
fi

echo "✅ ¡Todo listo! La aplicación está en ejecución."
echo "⚠️  (Presiona Ctrl+C en esta terminal cuando desees apagar el servidor)"

# Esperar al proceso de Spring Boot para mantener la terminal activa
wait $SPRING_PID
