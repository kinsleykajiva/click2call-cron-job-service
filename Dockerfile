FROM openjdk:11
ARG JAR_FILE=build/libs/cron-jobs-services-app.jar
COPY ${JAR_FILE} app.jar
EXPOSE 3150:3150
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]