FROM openjdk:17

ADD ./target/gateway.jar /app/
CMD ["java", "-XX:+UseZGC", "-Xmx200m", "-jar", "/app/gateway.jar"]

EXPOSE 80