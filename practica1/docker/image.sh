docker build -t trippins:1.0 . -f docker/Dockerfile
docker tag trippins:1.0 jantoniio3/trippins:1.0
docker push jantoniio3/trippins:1.0


