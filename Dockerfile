FROM openjdk:13
EXPOSE 8082
ADD ./target/pay-0.0.1-SNAPSHOT.jar   micro-payment.jar
ENTRYPOINT ["java","-jar","/micro-payment.jar"]
