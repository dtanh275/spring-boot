### Build docker image không sử dụng plugin

Cách dễ dàng nhất khi tạo docker image từ một spring boot project là build ra file jar như bình thường, sau đó copy file jar đó vào docker image khi tạo image.

 File `docker/withoutplugin/Dockerfile` sẽ như sau
 
 ```
 FROM adoptopenjdk:11-jre-hotspot
 ARG JAR_FILE=dockersample/hello/build/libs/*.jar
 COPY ${JAR_FILE} application.jar
 EXPOSE 8080
 ENTRYPOINT ["java","-jar","/application.jar"]
 
 ```

Đầu tiên, build ra file jar như bình thường

```sh
./gradlew :dockersample:hello:build
```

Tại root folder, chạy `docker build` để tạo ra image theo cấu hình trong `Dockerfile`

```
docker build -f ./dockersample/hello/docker/withoutplugin/Dockerfile -t hellospringdocker .
```

Chạy application từ image vưa được tạo ra trên port 8080

```
docker run -p 8080:8080 hellospringdocker:latest
```
