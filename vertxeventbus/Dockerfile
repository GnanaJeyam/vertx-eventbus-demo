FROM gradle:6.7.1-jdk15 as gradleBuilder
WORKDIR '/app/usr/jeyam'
COPY . .
RUN ./gradlew assemble

FROM adoptopenjdk:15-jre-hotspot
ENV APP_DIR /application
ENV APP_FILE vertx-eventbus-jar.jar
WORKDIR $APP_DIR
COPY --from=gradleBuilder /app/usr/jeyam/build/libs/*-fat.jar $APP_DIR/$APP_FILE

CMD ["java", "-jar", "vertx-eventbus-jar.jar"]