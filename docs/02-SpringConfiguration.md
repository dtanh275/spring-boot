# Using Java Configuration

### Spring configuration using annotation

Ở ví dụ [trước](https://github.com/dtanh275/spring-guide/blob/master/docs/01-HelloSpring.md), chúng ta đã tìm hiểu cách cấu hình Spring xử dụng xml file.

Việc cấu hình sử dụng file xml có thể gây khó khăn cho người sử dụng. Bắt đầu từ Spring 3.0, Spring cho phép cấu hình thông qua class annotation.

Nội dung file `app-context.xml` trên có thế được viết lại sử dụng class annotation.

Định nghĩa `@Configuration` class, khai báo các method sử dụng `@Bean` annotation để đánh dấu Spring tự động khởi tạo các bean đó.

```java
@Configuration
public class SpringAnnotationConfig {

    @Bean
    public MessageProvider provider() {
        return new HelloSpringProvider();
    }

    @Bean
    public MessageRenderer renderer() {
        MessageRenderer renderer = new ConsoleMessageRenderer();
        renderer.setProvider(provider());

        return renderer;
    }
}
```

Khi đó trong `main()` thay vì sủ dụng `ClassPathXmlApplicationContext` context để đọc file xml, chúng ta sử dụng `AnnotationConfigApplicationContext` context để đọc cấu hình sử dụng class annotation.

```java
public static void main(String... args) {
    ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringAnnotationConfig.class);

    MessageRenderer renderer = ctx.getBean("renderer", MessageRenderer.class);
    System.out.println(renderer.render());

}
```


#### Source code

Xem tại [đây](https://github.com/dtanh275/spring-guide/tree/master/spring-configuration/annotation)



### Sử dụng `@ComponentScan` annotation

Sử dụng `@ComponentScan` để yêu cầu Spring scan toàn bộ một package và tìm ra các class `injectable` và tự động khởi tạo chúng.

Đánh dấu class có thể `injectable`, tức là cho phép spring có thể khởi tạo và quản lý object đó.

```java
@Component("renderer")
public class ConsoleMessageRenderer implements MessageRenderer {
   
}

@Component("provider")
public class HelloSpringProvider implements MessageProvider {
    
}
```

`@Configuration` class thêm `@ComponentScan` để thông báo Spring sẽ tìm kiếm `injectable` ở đâu.

```java
@Configuration
@ComponentScan(basePackages = "com.donkey.spring.di.javaconfiguration.annotated")
public class SpringAnnotationConfig {
}
```


#### Source code

Xem tại [đây](https://github.com/dtanh275/spring-guide/tree/master/spring-di/javaconfiguration/src/main/java/com/donkey/spring/di/javaconfiguration/annotated)



### Kết hợp cấu hình sử dụng annotaton và XML

Nếu project đang sử dụng xml để khai báo các bean, không cần thiết phải sử dụng java annotation để khai báo lại. Có thể cấu hình để `@Configuration` class đọc các bean được khai báo trong xml file.

```java
@Configuration
@ImportResource(locations = {"classpath:app-context-xml.xml"})
public class SpringAnnotationConfig {
}
```


#### Source code

Xem tại [đây](https://github.com/dtanh275/spring-guide/tree/master/spring-di/javaconfiguration/src/main/java/com/donkey/spring/di/javaconfiguration/mixedg)
