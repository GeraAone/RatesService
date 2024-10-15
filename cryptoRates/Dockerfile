FROM bellsoft/liberica-openjdk-alpine-musl

WORKDIR /app

COPY ./target/cryptoRates-0.0.1-SNAPSHOT.jar /app/cryptoRates.jar

CMD ["java", "-jar", "cryptoRates.jar"]