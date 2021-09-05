FROM maven:3.6-jdk-8 as build
COPY src/ /app/src/
COPY pom.xml /app/pom.xml
WORKDIR /app
RUN mvn clean install

FROM apache/sling:11
COPY --from=build /app/target/*.jar /opt/sling/sling/fileinstaller/
CMD ["java", "-Xms1g", "-jar", "org.apache.sling.starter.jar", "-Dsling.fileinstall.dir=/opt/sling/sling/fileinstaller", "-Dorg.apache.sling.launchpad.startupmode=UPDATE"]