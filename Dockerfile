FROM openjdk:14
EXPOSE 8080
ARG JAR_FILE=/build/libs/affinityNow-1.3.0.jar
ADD ${JAR_FILE} affinityNow-1.3.0.jar
ENTRYPOINT ["java","-jar","affinityNow-1.3.0.jar"]