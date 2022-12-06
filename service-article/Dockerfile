FROM openjdk:15

ADD ./target/service-article.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/article.jar"]

EXPOSE 8991