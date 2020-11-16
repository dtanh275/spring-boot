## Spring Boot with Nginx

Read guide in [here](https://dirtyhands.me/deploy-spring-boot-with-nginx/).

## How to run

1. **Create `docker image`**

```bash
$ ./gradlew buildDocker
```

2. **Run `docker-compose`**

```bash
 $ docker-compose up
```

3. **Testing**
```bash
$ curl http://localhost:8080/api/hello
```
