FROM adoptopenjdk/openjdk13:latest
LABEL maintainer="islamversity/Reyan team"
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/reyan-1.0.0-SNAPSHOT.jar
ADD ${JAR_FILE} reyan.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/reyan.jar"]
