FROM openjdk:17-alpine

RUN mkdir "app"

RUN mkdir "logs"
VOLUME $PWD/logs

WORKDIR /app

COPY ./target/contact-book.jar .

ENV SERVER_PORT=9898
ENV POSTGRES_URL="jdbc:postgresql://localhost:5432/postgres?currentSchema=public"
ENV POSTGRES_USER=""
ENV POSTGRES_PASS=""


EXPOSE ${SERVER_PORT}

ENTRYPOINT ["java", "-jar"]

CMD ["contact-book.jar"]