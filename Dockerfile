FROM amazoncorretto:17.0.7-alpine
WORKDIR /app
COPY target/brand.jar /app/brand.jar
EXPOSE 8080
CMD ["java", "-jar", "brand.jar"]