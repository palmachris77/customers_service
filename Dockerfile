FROM openjdk:8
VOLUME /tmp
EXPOSE 8888
ADD ./target/client-service-0.0.1-SNAPSHOT.jar service-customers.jar
ENTRYPOINT [ "java", "-jar","./service-customers.jar" ]