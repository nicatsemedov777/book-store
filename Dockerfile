FROM openjdk:17-jdk-slim
ADD build/libs/*.jar dockerapp.jar
EXPOSE 8888
ENTRYPOINT ["java","-jar","/dockerapp.jar"]