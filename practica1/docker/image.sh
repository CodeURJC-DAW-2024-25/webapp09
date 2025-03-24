docker build -t jantoniio3/trippins -f ../docker/Dockerfile ../
docker tag jantoniio3/trippins jantoniio3/trippins:latest
docker push jantoniio3/trippins:latest

