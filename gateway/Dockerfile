FROM openjdk:15

COPY ./src/main/resources/application.yml /app
ADD ./target/gateway.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/gateway.jar"]

EXPOSE 80