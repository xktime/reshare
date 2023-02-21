FROM openjdk:17

ADD ./target/service-crawler.jar /app/
CMD ["java", "-XX:+UseZGC", "-Xmx200m","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=18993", "-jar", "/app/crawler.jar"]
VOLUME['/data']

EXPOSE 8993