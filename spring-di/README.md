# Spring Injection

### Các kiểu `Injection` trong Spring

Spring có 3 kiểu `injection`
- Constructor injection
- Setter injection
- Field injection


### Construct injection

Sử dụng `@Autowired` trên constructor của class.

```java
@Component("constructorRenderer")
public class ConstructorInjectionRenderer {

    private String message;

    @Autowired
    public ConstructorInjectionRenderer(String message) {
        this.message = message;
    }
}    
```

Spring sẽ tìm trong project `bean` có id `message` và inject vào `constructor` của class.

```java
@Configuration
@ComponentScan(basePackages = "com.donkey.spring.di.injection.injection")
public class SpringInjectionConfig {

    @Bean("message")
    public String message() {
        return "Hello, constructor injection";
    }
}
```

Nếu sử dụng `@Value` annotation để tạo giá trị mặc định cho `String message` ở trên, thì Spring sẽ không inject `bean` có id là `message` nữa, mà sử dụng giá trị mặc định trong `@Value`

```java
@Component("defaultRenderer")
public class DefaultMessageRenderer {

    private String message;

    @Autowired
    public DefaultMessageRenderer(@Value("Hello, default value") String message) {
        this.message = message;
    }
}
```


### Setter injection

Sử dụng `@Autowired` trên constructor của class.

```java
@Component("setterRenderer")
public class SetterInjectionRenderer {

    private String message;

    @Autowired
    @Qualifier("setterMessage")
    public void setMessage(String message) {
        this.message = message;
    }
}
```

Spring sẽ tìm trong project `bean` có id `message` và inject vào `setMessage` của class.

```java
@Configuration
@ComponentScan(basePackages = "com.donkey.spring.di.injection.injection")
public class SpringInjectionConfig {

    @Bean("setterMessage")
    public String setterMessage() {
        return "Hello, setter injection";
    }
}
```


### Field injection

```java
@Component("fieldRenderer")
public class FieldInjectionRenderer {
    
    @Autowired
    @Qualifier("fieldMessage")
    private String message;
}
```

`Spring IoC container` sử dụng reflection để inject `bean` vào `private filed`

**Note:** Nên tránh sử dụng `filed injection`

Có thể sử dụng `@Value` để inject giá trị cho filed. Spring hỗ trợ Spring Expression Language (SpEL) để tăng tính linh động khi sử dụng `@Value` annotation để inject giá trị ban đầu cho filed. 

```java
@Component("injectSimple")
public class InjectSimple {

    @Value("John Mayer")
    private String name;
    @Value("40")
    private int age;
    @Value("1.92")
    private float height;
}
```


### Source code
Xem tại [đây](https://github.com/dtanh275/spring-guide/tree/master/spring-di/injection/src/main/java/com/donkey/spring/di/injection/injection)


## Injecting collections

Đăng ký các `bean` trong `Configuration` file

```java
@Configuration
public class CollectionConfig {
    @Bean("allBoys")
    public List<NameHolder> allBoys() {
        return List.of(ron(), harry());
    }

    @Bean("allGirls")
    public List<NameHolder> allGirls() {
        return List.of(hermione(), ginny());
    }
    
    @Bean("ron")
    public NameHolder ron() {
        return new NameHolder("Ron");
    }

    @Bean("harry")
    public NameHolder harry() {
        return new NameHolder("Harry");
    }

    @Bean("hermione")
    public NameHolder hermione() {
        return new NameHolder("Hermione");
    }

    @Bean("ginny")
    public NameHolder ginny() {
        return new NameHolder("Ginny");
    }
}
```

Tạo danh sách `NameHolder`

```java
@Service("injectCollection")
public class CollectionInjection {

    @Autowired
    private List<NameHolder> allMember;
}    
```

Khi này spring sẽ tìm toàn bộ các `bean` của class `NameHolder` và inject và list. Kết quả danh sách gồm ron, harry, hermione và ginny.

Để xác định cụ thể danh sách muốn khởi tạo, sử dụng `@Autowire` cùng với `@Qualifier`

```java
@Service("injectCollection")
public class CollectionInjection {
    @Autowired
    @Qualifier("allGirls")
    private List<NameHolder> allGirls;
}    
```

Danh sách sẽ bao gồm: hermione và ginny.

Có thể sử dụng `@Resource` annotation để thay thế `@Autowire` cùng với `@Qualifier` mà vấn đạt được kết quả tương tự.

```java
@Service("injectCollection")
public class CollectionInjection {
    @Resource(name = "allBoys")
    private List<NameHolder> allBoys;
}    
```

Danh sách sẽ bao gồm: harry và ron.


Để sắp xếp thứ tự của các `bean` được iject và list, có thể sử dụng `@Order` để sắp xếp các `bean`

```java
@Configuration
public class CollectionConfig {

    @Bean("ron")
    @Order(4)
    public NameHolder ron() {
        return new NameHolder("Ron");
    }

    @Bean("harry")
    @Order(2)
    public NameHolder harry() {
        return new NameHolder("Harry");
    }

    @Bean("hermione")
    @Order(3)
    public NameHolder hermione() {
        return new NameHolder("Hermione");
    }

    @Bean("ginny")
    @Order(1)
    public NameHolder ginny() {
        return new NameHolder("Ginny");
    }
}
```

### Source code
Xem tại [đây](https://github.com/dtanh275/spring-guide/tree/master/spring-di/injection/src/main/java/com/donkey/spring/di/injection/collection)
