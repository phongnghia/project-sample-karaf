#FROM maven:3.8.3-openjdk-8 as karaf_build
#
#RUN mkdir src
#WORKDIR /src/
#ADD . .
#RUN mvn clean install

FROM openjdk:8-jdk

VOLUME "/root/.m2"
RUN mkdir src
WORKDIR /src/
ADD . .

ENV JAVA_HOME /usr/local/openjdk-8/
RUN apt-get update \
  && apt-get clean && rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/*

RUN apt-get install -y maven

# build source
RUN mvn clean install

# download karaf 4.3
RUN mkdir karaf
RUN cd karaf && \
    wget https://dlcdn.apache.org/karaf/4.3.6/apache-karaf-4.3.6.zip && \
    unzip apache-karaf-4.3.6.zip

RUN mkdir /opt/karaf;
#COPY --from=karaf_build /src/person-features/target/* /opt/
#RUN mkdir -p /opt/karaf/data/log && touch /opt/karaf/data/log/karaf.log
EXPOSE 1099 8101 44444 8181
COPY person-features/entrypoint.sh /usr/local/bin/docker-entrypoint
RUN chmod +x /usr/local/bin/docker-entrypoint
ENTRYPOINT ["docker-entrypoint"]