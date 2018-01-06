FROM java:8
MAINTAINER email@example.com
VOLUME /tmp
EXPOSE 8080
 
ENV USER_NAME eugene
ENV APP_HOME /home/$USER_NAME/app
 
RUN useradd -ms /bin/bash $USER_NAME
RUN mkdir $APP_HOME
 
ADD spring-boot-sample-data-jest-3.1.0-SNAPSHOT.jar $APP_HOME/spring-boot-sample-data-jest-3.1.0-SNAPSHOT.jar
RUN chown $USER_NAME $APP_HOME/spring-boot-sample-data-jest-3.1.0-SNAPSHOT.jar
 
USER $USER_NAME
WORKDIR $APP_HOME
RUN bash -c 'touch spring-boot-sample-data-jest-3.1.0-SNAPSHOT.jar'
 
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","spring-boot-sample-data-jest-3.1.0-SNAPSHOT.jar"]
