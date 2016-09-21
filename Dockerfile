#Based on Ubuntu 14.04
FROM ubuntu:14.04

MAINTAINER zoltan.ferenczik@icellmobilsoft.hu

LABEL Description="This image is created for testing and demonstration purposes" Vendor="iCell Mobilsoft" Version="1.0"

ENV DEBIAN_FRONTEND noninteractive

#Set timezone
RUN echo "Europe/Budapest" > /etc/timezone
RUN dpkg-reconfigure -f noninteractive tzdata

# Download latest package list (required)
RUN apt-get update

#Install additional repository
#RUN apt-get -y install python-software-properties
RUN apt-get -y install software-properties-common

# Install Java.
RUN \
  echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | debconf-set-selections && \
  add-apt-repository -y ppa:webupd8team/java && \
  apt-get update && \
  apt-get install -y oracle-java8-installer && \
  rm -rf /var/lib/apt/lists/* && \
rm -rf /var/cache/oracle-jdk8-installer

EXPOSE 9000 9001 8080 

# Define commonly used JAVA_HOME variable
ENV JAVA_HOME /usr/lib/jvm/java-8-oracle

#Commands

CMD ["/sbin/init"]
