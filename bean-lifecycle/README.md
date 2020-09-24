# Bean Life Cycle in Spring

### The Spring Bean Life Cycle

![Bean Lifecycle](https://github.com/dtanh275/spring-guide/blob/master/images/bean-lifecycle.png)

### The Execution Order
Tạo một bean thực hiện các phương thức như mô tả ở sơ đồ trên

```java

public class LifeCycleBean implements BeanNameAware, BeanClassLoaderAware,
                                      ApplicationContextAware, InitializingBean, DisposableBean {
    @Override
    public void setBeanName(String name) {
        System.out.println(String.format("--- setBeanName([%s]) executed ---", name));
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("--- setBeanClassLoader() executed ---");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("--- setApplicationContext() executed ---");
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("--- @PostConstruct executed ---");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("--- afterPropertiesSet() executed ---");
    }

    public void initMethod() {
        System.out.println("--- init-method executed ---");
    }

    public void annotationInitMethod() {
        System.out.println("--- annotationInitMethod() executed ---");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("--- @PreDestroy executed ---");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("--- destroy executed ---");
    }

    public void destroyMethod() {
        System.out.println("--- destroy-method executed ---");
    }

    public void annotationDestroyMethod() {
        System.out.println("--- annotationDestroyMethod() executed ---");
    }

}

```

Trong `app-context-xml.xml` sử dụng `destroy-method` và `init-method` để khai báo các init và destroy method. mo

```xml

<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:p="http://www.springframework.org/schema/p"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
          http://www.springframework.org/schema/beans/spring-beans.xsd
          http://www.springframework.org/schema/context
          http://www.springframework.org/schema/context/spring-context.xsd"
       default-lazy-init="true">

  <context:annotation-config />

  <bean id="lifeCycleBean"
        class="com.donkey.spring.lifecycle.lifecycle.LifeCycleBean"
        destroy-method="annotationDestroyMethod"
        init-method="annotationInitMethod"/>
</beans>

```

Nếu sử dụng annotation để khai báo init và destroy method sẽ như sau

```java

@Bean(initMethod = "annotationInitMethod", destroyMethod = "annotationDestroyMethod")

```

Kết quả khi bean được khởi tạo và destroy bởi context

```

--- setBeanName([lifeCycleBean]) executed ---
--- setBeanClassLoader() executed ---
--- setApplicationContext() executed ---
--- @PostConstruct executed ---
--- afterPropertiesSet() executed ---
--- annotationInitMethod() executed ---
--- @PreDestroy executed ---
--- destroy executed ---
--- annotationDestroyMethod() executed ---

```
