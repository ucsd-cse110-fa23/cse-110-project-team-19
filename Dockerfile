# Stage 1: Build
FROM openjdk:8 AS build
WORKDIR /server
COPY ./src/ /java/src/

# Debug: Print the contents of the directories
RUN ls -R /java/src

RUN javac src/main/server/MyServer.java src/main/server/RequestHandler.java src/main/server/MyHandler.java

# Stage 2: Final Image
FROM openjdk:8
WORKDIR /server
COPY --from=build /server/ /server/
EXPOSE 8100
ENTRYPOINT ["java", "src/main/server/MyServer"]