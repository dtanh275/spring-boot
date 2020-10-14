FROM tomcat:9.0

COPY ./tomcat/server.xml /usr/local/tomcat/conf/

# add context to /usr/local/tomcat/webapps
COPY ./build/libs/springexternaltomcat.war /usr/local/tomcat/webapps/

EXPOSE 8009
