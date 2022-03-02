FROM openjdk:11.0-oracle
VOLUME /tmp
EXPOSE 8097
ADD ./target/debit-service-0.0.1-SNAPSHOT.jar debit-service.jar
ENTRYPOINT ["java","-jar","/debit-service.jar"]