FROM eclipse-temurin@sha256:831f00e903bf888787b8ffd1bf5252e5903e1b19d5bc17f7e5b9761ffec11785 AS builder
WORKDIR /opt/build
COPY . .
RUN ./mvnw --no-transfer-progress --batch-mode verify

FROM eclipse-temurin@sha256:831f00e903bf888787b8ffd1bf5252e5903e1b19d5bc17f7e5b9761ffec11785 AS release
WORKDIR /opt/app
COPY --from=builder /opt/build/target/app.jar .

ENV APP_SIGN_ALGO HmacSHA256
ENV APP_SIGN_KEY 073b9ce8277c4d70dba2edf33e80f6a2bde0dbd7e5c9d149ccc20ba8b125129b

USER 1000

EXPOSE 8080
CMD ["java", "-jar", "app.jar"]