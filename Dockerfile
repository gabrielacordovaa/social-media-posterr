FROM openjdk:11

COPY /target/*.jar posterr.jar

ENTRYPOINT ["java", "-jar", "/posterr.jar"]