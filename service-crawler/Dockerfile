FROM openjdk:15

ADD ./target/service-crawler.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/crawler.jar"]

EXPOSE 8993