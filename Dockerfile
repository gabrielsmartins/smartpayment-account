FROM openjdk:11-jdk
VOLUME /app
COPY build/libs/smartpayment-accounts.jar .
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=dev", "smartpayment-accounts.jar"]