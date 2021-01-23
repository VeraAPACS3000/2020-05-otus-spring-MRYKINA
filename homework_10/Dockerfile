FROM store/oracle/jdk:11
LABEL maintainer="MrykinaVA@gmail.com"
COPY /target/app.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]