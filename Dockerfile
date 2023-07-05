# syntax=docker/dockerfile:1

# base image
FROM arm64v8/eclipse-temurin:17-jdk-focal as base
WORKDIR /app
COPY gradle gradle
COPY gradlew .
COPY build.gradle .
COPY settings.gradle .
COPY src src

# test image
FROM base as test
RUN ./gradlew test

# run image on dev env
FROM base as dev
CMD ./gradlew run

# build jar
FROM base as build
WORKDIR /app
RUN ./gradlew build

# run image on prod env
FROM arm64v8/eclipse-temurin:17-jdk-focal as prod
EXPOSE 8080
COPY --from=build /app/build/libs/rest-service-*.jar /my-coupon-issuer.jar
CMD ["java", "-jar", "/my-coupon-issuer.jar"]