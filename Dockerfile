FROM gcc as toolchain
RUN apt -y update && apt -y upgrade && apt -y install openjdk-17-jdk

FROM toolchain as builder
WORKDIR /project
COPY src ./src
COPY build.gradle.kts ./build.gradle.kts
COPY gradlew ./gradlew
COPY gradle ./gradle
RUN ./gradlew clean build

FROM debian as backend
WORKDIR /root
COPY --from=builder /project/build/graal/project ./app
ENTRYPOINT ["/root/app"]