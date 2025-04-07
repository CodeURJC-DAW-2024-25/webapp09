echo "🔨 Construyendo imagen Docker desde la raíz del proyecto..."

docker build -t trippins:1.0 -f Dockerfile ..  # Contexto: ".." (la raíz)

echo "🏷️ Etiquetando imagen..."
docker tag trippins:1.0 jantoniio3/trippins:latest

echo "📤 Subiendo a Docker Hub..."
docker push jantoniio3/trippins:latest
echo "🔄 Actualizando imagen en el servidor..."


echo ""🔄 Actualizando imagen de docker""
docker-compose pull


echo "✅ ¡Todo listo!"