FROM adoptopenjdk/openjdk13
LABEL maintainer="hojat.vaheb@gmail.com"
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/reyan-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} reyan.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/reyan.jar"]
