FROM openjdk:15

ADD ./target/service-user.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/user.jar"]

EXPOSE 8994