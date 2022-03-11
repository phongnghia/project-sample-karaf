FROM maven:3.8.3-openjdk-8 as KARAF_BUILD

RUN mkdir src

WORKDIR /src/
ADD . .
RUN mvn clean install

FROM openjdk:8-jdk

VOLUME "/root/.m2"

ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64
RUN apt-get update \
  && apt-get clean && rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/*
RUN mkdir /opt/karaf;
#COPY --from=KARAF-BUILD /src/project-sample-karaf/person-features/target/person-features-1.0-SNAPSHOT.tar.gz /opt/person-features-1.0-SNAPSHOT.tar.gz
#RUN tar --strip-components=1 -C /opt/karaf -xzf /opt/person-features-1.0-SNAPSHOT.tar.gz; \
#    rm /opt/person-features-1.0-SNAPSHOT.tar.gz
#RUN mkdir -p /opt/karaf/data/log && touch /opt/karaf/data/log/karaf.log
#EXPOSE 1099 8101 44444 8181
#COPY karaf-sandbox-distribution/entrypoint.sh /usr/local/bin/docker-entrypoint
#RUN chmod +x /usr/local/bin/docker-entrypoint
#ENTRYPOINT ["docker-entrypoint"]