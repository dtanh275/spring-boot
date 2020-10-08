## Build docker image không sử dụng plugin

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

```console
./gradlew :dockersample:hello:build
```

Tại root folder, chạy `docker build` để tạo ra image theo cấu hình trong `Dockerfile`

```console
docker build -f ./dockersample/hello/docker/withoutplugin/Dockerfile -t hellospringdocker .
```

Chạy application từ image vưa được tạo ra trên port 8080

```console
docker run -p 8080:8080 hellospringdocker:latest
```

## Sử dụng `gradle-docker` plugin

Thay vì phải build ra file `jar` và copy vào `image`, có thể sử dụng [gradle-docker](https://github.com/Transmode/gradle-docker) để hỗ trợ tạo `image` sử dụng `gradle`.

Thêm `gradle-docker` plugin vào project

```groovy
apply plugin: 'docker'

buildscript {
    repositories { jcenter() }
    dependencies {
        classpath 'se.transmode.gradle:gradle-docker:1.2'
    }
}
```

Định nghĩa một `buildDocker` task để build `image`

```groovy
task buildDocker(type: Docker, dependsOn: build) {
    push = false
    applicationName = 'hello'
    tagVersion = 'latest'
    dockerfile = file('docker/gradledocker/Dockerfile')
    
    doFirst {
        addFile {
            from jar
            rename {'dockersample.hello.jar'}
        }
    }
}
```

Build `image` sử dụng gradle

```console
./gradlew :dockersample:hello:buildDocker
```

Nếu sử dụng maven có thể tham khảo [jib-maven-plugin](https://github.com/GoogleContainerTools/jib/tree/master/jib-maven-plugin)

## Sử dụng Spring boot plugin

[Spring boot gradle plugin](https://docs.spring.io/spring-boot/docs/current-SNAPSHOT/gradle-plugin/reference/html/#build-image) hỗ trợ build ra `image`. Tuy nhiên `Spring boot plugin` tạo ra [OIC image](https://opencontainers.org/about/overview/) thay vì `docker image`.

`OIC image` hiểu là một quy chuẩn cho `image` được build ra để tất cả container engines có thể chạy được `container` từ `image` đó. Thay vì `docker image` thì chỉ docker mới hiểu vào chạy được `container` từ `image` đó.

Đầu tiên khai báo sử dụng sprint boot plugin, đa phần các project spring boot đều sử dụng plugin này

```groovy
apply plugin: 'org.springframework.boot'

```

Chạy `bootBuildImage` task để tạo ra `OIC image`

```console
./gradlew :dockersample:hello:bootBuildImage
```

OCI image được tạo ra là một chuẩn cho các container engines, docker cũng hỗ trợ chạy image này nên hoàn toàn dùng docker để chạy container từ OCI image này.

```console
docker run -p 8080:8080 hello:latest
```
