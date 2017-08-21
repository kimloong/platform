FROM java:8-jre
MAINTAINER KimLoong <312891416@qq.com>

ADD ./target/registry.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/registry.jar"]

EXPOSE 8761