FROM centos
RUN yum update -y
RUN yum install java-1.8.0-openjdk -y
COPY target/Rest-Application-0.0.1-SNAPSHOT.jar ./app.jar
EXPOSE 8073
ENTRYPOINT ["java","-jar","/app.jar"]
