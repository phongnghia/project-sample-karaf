FROM ubuntu:latest

RUN apt-get update

RUN apt-get install -y curl

# download mariadb
RUN mkdir install && \
    cd install && \
    curl -s http://archive.ubuntu.com/ubuntu/pool/universe/m/mariadb-10.3/libmariadb-dev_10.3.34-0ubuntu0.20.04.1_amd64.deb -o libmariadb-dev_10.3.34-0ubuntu0.20.04.1_amd64.deb

RUN cd install && \
    apt-get install -y libmariadb-dev_10.3.34-0ubuntu0.20.04.1_amd64.deb
#install mysql
#RUN apt-get install -y mysql-server
# install jdk 8
RUN apt-get install -y openjdk-8-jdk
# config java
RUN update-alternatives --config java
ENV JAVA_HOME /usr/lib/jvm/java-11-openjdk-amd64