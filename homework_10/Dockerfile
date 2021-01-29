FROM store/oracle/jdk:11
LABEL maintainer="MrykinaVA@gmail.com"
COPY /target/app.jar /app/app.jar
COPY entrypoint.sh wait-for-it.sh
EXPOSE 8080
ENTRYPOINT [ "entrypoint.sh" ]
ENTRYPOINT ["java", "-jar", "/app/app.jar"]