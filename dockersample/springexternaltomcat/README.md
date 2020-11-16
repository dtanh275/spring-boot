## Run Spring Boot Application wit External Tomcat

Read guide in [here](https://dirtyhands.me/run-spring-boot-application-external-tomcat/).

### How to run

1. Build `war` file
```bash
$ ./gradlew bootWar
```

2. Run start tomcat and apache
```bash
$ docker-compose up
```

3. Test
```bash
$ curl http://localhost/springexternaltomcat/api/hello
```
