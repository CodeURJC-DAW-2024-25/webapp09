echo "🔨 Construyendo imagen Docker desde la raíz del proyecto..."

docker build -t trippins:latest -f Dockerfile ..  # Contexto: ".." (la raíz)

echo "🏷️ Etiquetando imagen..."
docker tag trippins:latest jantoniio3/trippins:latest

echo "📤 Subiendo a Docker Hub..."
docker push jantoniio3/trippins:latest
echo "🔄 Actualizando imagen en el servidor..."


echo ""🔄 Actualizando imagen de docker""
docker-compose pull


echo "✅ ¡Todo listo!"