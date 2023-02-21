FROM openjdk:17

ADD ./target/service-comment.jar /app/
CMD ["java", "-XX:+UseZGC", "-Xmx200m","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=18992", "-jar", "/app/comment.jar"]
VOLUME['/data']

EXPOSE 8992