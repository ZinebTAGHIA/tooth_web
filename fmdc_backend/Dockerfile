FROM openjdk:17-jdk-alpine
WORKDIR /backend
COPY target/fmdc.jar .
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "fmdc.jar"]