FROM openjdk:17

ADD ./target/registry.jar /app/
CMD ["java", "-XX:+UseZGC", "-Xmx200m", "-jar", "/app/registry.jar"]

EXPOSE 9999