## Spring Boot with Nginx

Read guide in [here](https://dirtyhands.me/deploy-spring-boot-with-nginx/).

## How to run

1. **Create `docker image`**

At `spring-boot` folder

```bash
./gradlew buildDocker
```

2. **Run `docker-compose`**

At `spring-boot/dockersample/nginx` folder

```bash
 docker-compose up
```
