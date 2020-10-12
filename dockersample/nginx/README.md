## Sử dungh Nginx làm Proxy Server

`Repository` hướng dẫn cách cấu hình nginx như một proxy server cho `application` viết bằng Spring Boot.

Sử dụng `docker` để `dockerize` `application` và `nginx`.

`Repository` sử dụng `gradle-docker` plugin để tạo ra `docker iamge`. Chi tiết tham khảo tại [đây](https://github.com/dtanh275/spring-boot/tree/main/dockersample/hello).

1. **Cấu hình cho nginx**

Tạo file `nginx/conf.d/app.conf`

```yaml
server {
    listen 80;
    charset utf-8;
    access_log off;

    location / {
        proxy_pass http://app:8080;
        proxy_set_header Host $host:$server_port;
        proxy_set_header X-Forwarded-Host $server_name;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
    }
}
```

Nginx được cấu hình như một proxy server, sẽ `proxy_pass` toàn bộ `request` tới `application`

2. **Cấu hình `docker-compose.yml`**

```yaml
version: '3.7'

# Define services
services:

  # Nginx
  nginx:
    container_name: spring-nginx
    image: nginx:latest
    restart: always
    ports:
      - 80:80
      - 443:443
    volumes:
      - ./nginx/conf.d:/etc/nginx/conf.d
    depends_on:
      - app

  # Spring boot application
  app:
    image: spring-boot.dockersample/springnginx:latest
    container_name: dockersample-springngix
    ports:
      - 8080:8080
```

`spring-boot.dockersample/springnginx:latest` là tên của `image` được tạo ra từ source code.

## Chạy ứng dụng

1. **Tạo ra `docker image` từ source code**

Tại `spring-boot` folder

```bash
./gradlew buildDocker
```

2. **Chạy `docker-compose`**

Tại `spring-boot/dockersample/nginx` folder

```bash
 docker-compose up
```
