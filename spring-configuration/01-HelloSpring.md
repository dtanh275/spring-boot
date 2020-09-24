## Hello Spring

Để hiểu rõ cách Spring hoạt động, trước tiên chúng ta sẽ code một ứng dụng đơn giản mô phỏng lại cách hoạt động của Spring framework.


**Configuration file**

Tạo `ms.properties` trong `resources` với nội dung

```xml
class.provider=com.donkey.spring.springconfiguration.hello.message.HelloSpringProvider
class.renderer=com.donkey.spring.springconfiguration.hello.message.ConsoleMessageRenderer
ref-class.renderer-setProvider=class.provider
```

Nội dung file thể hiện:

- Tạo 2 beans với id lần lượt `class.provider` và `class.renderer`
- Ứng dụng tìm kiếm trong object có id `class.renderer` method có tên `setProvider` và truyền vào object có id `class.provider`

**Container Class**

Tiếp theo, chúng ta tạo `MessageFactory` class làm nhiệm vụ đọc file cấu hình bên trên, sau  tự động tạo các beans như trong mô tả. Trong ứng dụng này chúng ta sử dụng Factory Pattern.

```java
private MessageFactory() {
    properties = new Properties();
    beans = new HashMap<>();
    try {
        properties.load(getClass().getResourceAsStream("/ms.properties"));

        properties.stringPropertyNames().stream()
                  .filter(s -> s.startsWith("class"))
                  .forEach(this::constructInstance);

        properties.stringPropertyNames().stream()
                  .filter(s -> s.startsWith("ref-"))
                  .forEach(this::handleMethod);
    } catch (IOException e) {
        e.printStackTrace();
    }
}

private void constructInstance(String key) {
    try {
        beans.put(key, Class.forName(properties.getProperty(key)).getDeclaredConstructor().newInstance());
    } catch (Exception e) {
        e.printStackTrace();
    }
}

private void handleMethod(String key) {
    try {
        String[] meta = key.split("-");
        Object refObj = beans.get(properties.getProperty(key));
        Object baseObj = beans.get(meta[1]);

        Method method = baseObj.getClass().getMethod(meta[2], refObj.getClass().getInterfaces()[0]);
        method.invoke(baseObj, refObj);
    } catch (Exception e) {
        e.printStackTrace();
    }
}
```

**Application**

Để lấy instance của class nào chỉ đơn giản cần biết `id` của class đó.
```java
MessageFactory factory = MessageFactory.getInstance();

MessageRenderer renderer = factory.getBean("class.renderer", MessageRenderer.class);
System.out.println(renderer.render());
```

**Hoạt động**

Ví dụ đơn giản nhưng đã mô tả lại cách Spring framework tự động tạo và quản lý các beans cho người dùng dựa theo mô tả trong file cấu hình.

Khi ứng dụng start, `MessageFactory` sẽ đọc file cấu hình, tự động tạo các beans theo mô tả. File cấu hình cần phải được viết theo một `format` được quy định từ trước.

`MessgeFactory` đóng vai trò như `Contaier` lưu trữ và quản lý các beans.

Ngươi dùng muốn sử dụng bean nào đơn giản chỉ gần phải biết `id` của bean đó.

Khi người dùng muốn thay đổi hoạt đông của ứng dụng, giả sử là hiện thị dòng chữ khác, thì chỉ đơn giản là tạo một class mới và sử file `ms.properties` tới class mới. Toàn bộ ứng dụng không phải viết lại.

### Source code

Source code cho ví dụ này có thể tim ở [đây](https://github.com/dtanh275/spring-guide/tree/master/spring-configuration/hello)

## Refactoring with Spring

Ví dụ mô tả các mô tả một cách đơn giản hoạt động của Spring framework, ở đây ta thấy ngay là với các project mới thì phải viết lại rất nhiều các code đã có sẵn. Đó là lý  Spring ra đời. Spring giúp chúng ta không phải viết lại các code đã có, đồng thời linh hoạt trong việc cấu hình với các project khác nhau.

Ví dụ này sẽ mô tả cách Spring (cụ thể là `spring-context` module) giúp đỡ người dùng tránh việc phải viết lại.

File `ms.properties` được viết dạng xml theo [quy đinh](https://docs.spring.io/spring/docs/4.2.x/spring-framework-reference/html/xsd-configuration.html)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:p="http://www.springframework.org/schema/p"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd">

  <bean id="provider" class="com.donkey.spring.springconfiguration.context.message.HelloSpringProvider"/>

  <bean id="renderer" class="com.donkey.spring.springconfiguration.context.message.ConsoleMessageRenderer"
        p:provider-ref="provider"/>
</beans>
```

`MessageFactory` sẽ được thay thế bằng `ApplicationContext`. `ApplicationContext` sẽ đọc file xml và tạo ra các beans theo mô tả. Tương tự như cách `MessageFactory` đọc file `ms.properties` và tạo các beans như trên.

```java
public static void main(String... args) {
    ApplicationContext ctx = new ClassPathXmlApplicationContext(
            "config/app-context.xml");

    MessageRenderer renderer = ctx.getBean("renderer", MessageRenderer.class);
    System.out.println(renderer.render());

}
```
### Source code

Source code cho ví dụ này có thể tim ở [đây](https://github.com/dtanh275/spring-guide/tree/master/spring-configuration/context)

