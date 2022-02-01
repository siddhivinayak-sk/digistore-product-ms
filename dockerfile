FROM openjdk:8-jre-alpine
LABEL maintainer="Sandeep Kumar"
WORKDIR /
ARG VER=0.0.1
ENV VER ${VER}
ADD ./target/digistore-product-ms-$VER.jar /opt/lib/digistore-product-ms.jar
EXPOSE 80
ENTRYPOINT ["java", "-Xmx512m","-Xss16m","-jar", "-Dconsole.level=INFO", "/opt/lib/digistore-product-ms.jar"]
