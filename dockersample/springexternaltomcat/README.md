## Run Spring Boot Application wit External Tomcat

Read guide in [here](https://dirtyhands.me/run-spring-boot-application-external-tomcat/).

### How to run

1. On `spring-boot` folder, build `war` file
```bash
./gradlew dockersample:springexternaltomcat:bootWar
```

2. On `dockersample/springexternaltomcat` folder, run start tomcat and apache
```bash
docker-compose up
```

3. Test
```bash
curl http://localhost/springexternaltomcat/api/hello
```
