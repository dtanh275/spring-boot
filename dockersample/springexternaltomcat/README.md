## Run Spring Boot Application wit External Tomcat

Mặc định, `Spring boot` có thể chạy độc lập sử dụng `embedded tomcat`, nhưng với trường hợp cần tích hợp ứng dụng vào một hệ thống đang chạy sư dụng `external tomcat`. Trong bài viêt này sẽ mô tả cách `build` một ứng dụng `spring boot` chạy với `external tomcat` và `apache`.

### Đóng gói ứng dụng dạng `war`

`Tomcat` là một `servlet container`, để ứng dụng có thể hoạt động được với `tomcat`, thì ứng dụng cần phải đáp ứng các tiêu chuẩn để `tomcat` có thể chạy được ứng dụng.

1. Cần `build` source code [dưới dạng `war`](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#howto-create-a-deployable-war-file) thay vì `jar`

Thêm `plugin` trong `build.gradle` cho phép `build` ra file `war`
```yaml
apply plugin: 'war'
```

2. Cấu hình `embedded tomcat` ở scope `providedRuntime`

Việc cấu hình này để tránh xung đột với `exteranal tomcat`. Nhờ đó khi `complie` sẽ bỏ qua `embedded tocmat`, nhưng khi phát triển chạy ứng dung thì `spring boot` vẫn được chạy trên `embedded tomcat`.
```yaml
providedRuntime 'org.springframework.boot:spring-boot-starter-tomcat'
```

3. `Tomcat` yêu cầu ứng dụng cần phải khởi tạo `servlet context`, thực hiện việc này bằng `extends SpringBootServletInitializer`

```java
@SpringBootApplication
public class ApiApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ApiApplication.class);
    }
}
```

### Cài đặt `apache server`

`Tomcat` có thể hoạt động độc lập được nhưng `tomcat` không mạnh và không hỗ trợ nhiều trong việc xử lý `http protocol`, nên thường cài đặt một server `apache` đứng trước `tomcat`.

Để giao tiếp giữa apache và tomcat có thể sử dụng:
- `mod_proxy`: Sử dụng http protocol. Apache đóng vai trò proxy và pass request tới tomcat. 
- `mod_jk`: sử dụng AJP protocol.
- `mod_jk` sẽ tốt hơn về về hiệu năng so với `mod_proxy`.

Cấu hình `mod_jk`, trước hết cần phải cặt đặt `mod_jk` module.
```bash
<IfModule !mod_jk.c>
    LoadModule jk_module modules/mod_jk.so
</IfModule>

<IfModule mod_jk.c>
        # workers.properties location
        JkWorkersFile /etc/httpd/conf.d/workers.properties
        # url to worker mount
        JkMount /* tomcat-worker
</IfModule>
```

Trong `workers.properties` file, mô tả `worker` sử dụng
```bash
worker.list=tomcat-worker
worker.tomcat-worker.port=8009
worker.tomcat-worker.host=tomcat
worker.tomcat-worker.type=ajp13
```

`wokrer` tên là `tomcat-worker`, lắng nghe trên port 8009 và sử dụng `ajp13` protocol.

### Cài đặt `tomcat server`

Trước hết, cấu hình `tomcat` sử dụng `ajp13` protocal trên port 8009
```xml
    <Connector protocol="AJP/1.3"
               address="0.0.0.0" port="8009" connectionTimeout="20000"
               redirectPort="8443" secretRequired="false" />
```

Copy `war` file tới `/usr/local/tomcat/webapps/` folder

### Chạy ứng dụng

1. Tại `spring-boot` folder, build ra file `war`
```bash
./gradlew dockersample:springexternaltomcat:bootWar
```

2. Tại `dockersample/springexternaltomcat` chạy `apache` và `tomcat`
```bash
docker-compose up
```

3. Kiểm tra
```bash
curl http://localhost/springexternaltomcat/api/hello
```
