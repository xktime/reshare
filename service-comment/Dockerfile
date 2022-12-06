FROM openjdk:15

ADD ./target/service-comment.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/comment.jar"]

EXPOSE 8992