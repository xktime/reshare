FROM openjdk:15

ADD ./target/service-user.jar /app/
CMD ["java", "-Xmx200m","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=18994", "-jar", "/app/user.jar"]
VOLUME['/data']

EXPOSE 8994