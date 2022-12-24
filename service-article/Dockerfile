FROM openjdk:15

ADD ./target/service-article.jar /app/
CMD ["java", "-Xmx200m", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=18991", "-jar", "/app/article.jar"]
VOLUME['/data']

EXPOSE 8991