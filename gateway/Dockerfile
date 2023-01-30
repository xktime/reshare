FROM openjdk:15

ADD ./target/gateway.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/gateway.jar"]

EXPOSE 80