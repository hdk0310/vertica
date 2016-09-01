FROM centos:centos6.6

# Set java environment variables
ENV JAVA_VERSION 1.7.0
ENV JAVA_HOME /usr/lib/jvm/java-1.7.0-openjdk.x86_64

# Set Maven environment variables
ARG USER_HOME_DIR="/home/dbadmin"
ENV M2_HOME /usr/local/maven
ENV PATH=${M2_HOME}/bin:${PATH}
ENV MAVEN_CONFIG "$USER_HOME_DIR/.m2"

# Update Image
RUN yum update -y; yum clean all

# Install Java and dependencies
RUN yum install -y \
	 java-"${JAVA_VERSION}"-openjdk-devel \ 
	 tar.x86_64 \ 
	 wget

# Install Maven and set it up
RUN wget http://mirror.cc.columbia.edu/pub/software/apache/maven/maven-3/3.0.5/binaries/apache-maven-3.0.5-bin.tar.gz && \
	 tar xzf apache-maven-3.0.5-bin.tar.gz -C /usr/local && \
    cd /usr/local && \
    ln -s apache-maven-3.0.5 maven


# Install Vertica Dependencies
RUN yum -y install dialog which openssl
RUN yum -y install openssh-server openssh-clients
RUN yum -y install mcelog gdb sysstat sudo

RUN yum clean all

ENV LANG en_US.utf8
ENV TZ "US/Western"

RUN groupadd -r verticadba
RUN useradd -r -m -g verticadba dbadmin

# Run Vertica RPM
ADD vertica-7.2.3-0.x86_64.RHEL6.rpm /vertica-7.2.3-0.x86_64.RHEL6.rpm
RUN rpm -Uvh vertica-7.2.3-0.x86_64.RHEL6.rpm		

# In theory, someone should make things work without ignoring the errors.
# But that's in theory, and for now, this seems sufficient.
RUN /opt/vertica/sbin/install_vertica --license CE --accept-eula --hosts 127.0.0.1 --dba-user-password-disabled --failure-threshold NONE --no-system-configuration

USER dbadmin

RUN /opt/vertica/bin/admintools -t create_db -s localhost --skip-fs-checks -d docker -c /home/dbadmin/docker/catalog -D /home/dbadmin/docker/data

USER root

ENV VERTICADATA /home/dbadmin/docker
VOLUME  /home/dbadmin/docker

EXPOSE 5433

USER dbadmin

ADD ./entrypoint.sh /
ADD ./vertica_setup.sql /
ENTRYPOINT ["/entrypoint.sh"]
