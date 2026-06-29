#!/bin/bash

echo "=========================================="
echo "🚀 INICIANDO ECOSISTEMA DE MICROSERVICIOS"
echo "=========================================="
echo "Levantando MS-Usuarios (Puerto 8081)..."
cd ms-usuarios || exit
mvn spring-boot:run &
PID_USERS=$!
cd ..

echo "Levantando MS-Contactos (Puerto 8082)..."
cd ms-contactos || exit
mvn spring-boot:run &
PID_CONTACTS=$!
cd ..

echo "Levantando MS-Frontend (Puerto 8080)..."
cd ms-frontend || exit
mvn spring-boot:run &
PID_FRONTEND=$!
cd ..

echo "⏳ Esperando a que todos los microservicios inicialicen (15 segundos)..."
sleep 15

echo "🌐 Abriendo la aplicación en tu navegador de Windows..."
if command -v explorer.exe &> /dev/null; then
    explorer.exe "http://localhost:8080"
else
    xdg-open "http://localhost:8080" || echo "Abre http://localhost:8080 en tu navegador."
fi

echo "✅ ¡Ecosistema Microservicios en ejecución!"
echo "⚠️  (Presiona Ctrl+C en esta terminal para apagar todos los servidores)"

# Atrapar la señal SIGINT (Ctrl+C) para matar a los tres
trap "echo 'Apagando microservicios...'; kill $PID_USERS $PID_CONTACTS $PID_FRONTEND; exit" SIGINT

wait $PID_USERS $PID_CONTACTS $PID_FRONTEND
