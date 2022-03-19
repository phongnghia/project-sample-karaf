FROM ubuntu:latest

RUN mkdir src
WORKDIR /src/
ADD . .
RUN apt-get update
RUN apt-get install -y openjdk-8-jdk && \
    apt-get install -y wget unzip nginx

ENV JAVA_HOME /usr/lib/jvm/java-1.8.0-openjdk-amd64

RUN apt-get install -y maven mysql-server

RUN service mysql start

# build source
RUN mvn clean install

# download karaf 4.3
RUN mkdir /karaf
RUN cd /karaf && \
    wget https://dlcdn.apache.org/karaf/4.3.6/apache-karaf-4.3.6.zip && \
    unzip apache-karaf-4.3.6.zip

RUN mkdir /opt/karaf;
#COPY --from=karaf_build /src/person-features/target/* /opt/\
#RUN mkdir -p /opt/karaf/data/log && touch /opt/karaf/data/log/karaf.log
EXPOSE 1099 8101 44444 8181
COPY person-features/entrypoint.sh /usr/local/bin/docker-entrypoint
RUN chmod +x /usr/local/bin/docker-entrypoint
ENTRYPOINT ["docker-entrypoint"]