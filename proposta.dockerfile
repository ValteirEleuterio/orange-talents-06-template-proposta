FROM openjdk:11
ENV HOST_BANCO=postgres:5032
ARG JAR_FILE=target/proposta.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]