# bin/bash

VERSION=`./gradlew properties -q | grep "version:" | awk '{print $2}'`
IMAGE_NAME=jtoh0227/my-coupon-issuer
TAG=$IMAGE_NAME:$VERSION

docker build -t $TAG .

docker tag $IMAGE_NAME $IMAGE_NAME:latest

docker push -a $IMAGE_NAME