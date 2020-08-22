FROM gradle:6.6.0-jdk11 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

#FROM openjdk:11-jdk
#VOLUME /app
#COPY target/*.jar .
#EXPOSE 8080
#ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=dev", "smartpayment-accounts.jar"]