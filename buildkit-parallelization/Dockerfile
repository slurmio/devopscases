FROM eclipse-temurin:17-jdk AS package
WORKDIR /opt/build
COPY . .
RUN ./mvnw --no-transfer-progress --batch-mode -Dmaven.test.skip package

FROM package AS test
RUN ./mvnw --no-transfer-progress --batch-mode test
RUN touch .faky-test

FROM package AS verify
# Чтобы пропустить долгий maven dependency check, добавьте -Ddependency-check.skip
RUN ./mvnw --no-transfer-progress --batch-mode -Dassembly.skipAssembly -Dmaven.test.skip verify
RUN touch .faky-verify

FROM eclipse-temurin:17-ubi9-minimal AS release
WORKDIR /opt/app
COPY --from=package /opt/build/target/app.jar .
COPY --from=test /opt/build/.faky-test /dev/null
COPY --from=verify /opt/build/.faky-verify /dev/null

ENV APP_SIGN_ALGO HmacSHA256
ENV APP_SIGN_KEY 073b9ce8277c4d70dba2edf33e80f6a2bde0dbd7e5c9d149ccc20ba8b125129b

USER 1000

EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
