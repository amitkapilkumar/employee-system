# include java 21
FROM openjdk:21-jdk-slim

# copy all built jar files to app folder
COPY ./employee-system/target/*.jar /app/



# run JAR image
ENTRYPOINT java -XX:+PrintFlagsFinal -jar /app/*.jar
