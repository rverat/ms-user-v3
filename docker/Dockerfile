# OpenJDK JRE
FROM openjdk:21-jdk-slim

WORKDIR /opt/app

#env
ENV DB_PASSWORD=your_db_password

ARG JAR_FILE=ms-user-v3-0.0.1-SNAPSHOT.jar

EXPOSE 9006

# Copy JAR file
COPY ${JAR_FILE} ms-user-v3-0.0.1.jar

# Run the app
ENTRYPOINT ["java", "-jar", "ms-user-v3-0.0.1.jar", "-web", "-webAllowOthers", "-tcp", "-tcpAllowOthers", "-browser"]
