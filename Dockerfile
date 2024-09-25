FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app

COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM gcr.io/distroless/java17-debian11:nonroot

COPY --from=build /app/target/demo-*.jar /app/app.jar
WORKDIR /app

CMD ["app.jar"]
USER nonroot