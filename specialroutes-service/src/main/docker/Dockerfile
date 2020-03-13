FROM openjdk:8-jdk-alpine
RUN apk update && apk upgrade && apk add netcat-openbsd && apk add bash
RUN mkdir -p /usr/local/specialroutes-services
ADD @project.build.finalName@.jar /usr/local/specialroutes-services/
ADD run.sh run.sh
RUN chmod +x run.sh
CMD ./run.sh
