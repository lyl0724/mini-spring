## 简介

`mini-spring`是仿照[tiny-spring](https://github.com/code4craft/tiny-spring)实现的一个toy项目，并参考Spring源码对其进行一定修改，实现了最基本的IOC和AOP功能。

Spring源码层次结构太复杂，直接阅读困难重重。而首先实现一个toy级别的Spring，了解Spring核心模块的原理，再去阅读源码并对toy项目做一定修改，这样的学习路线则会平缓很多。



## IOC容器

### IOC的使用

在了解容器原理之前，先来看看Spring IOC的最基本的使用方式，从而提纲挈领的去思考容器到底为我们做了什么，然后再去深入看看容器为我们做的这些事儿底层是怎么实现的。

这里举一个简单的存储账户的例子，我们定义一个Account类和AccountService类，我们的目标是去通过saveAccount()方法存储一个账户：

```java
@Data
public class Account {
    private String name;
    private int age;

    public Account(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
```

```java
@Data
public class AccountService {
    private Account account;

    public void saveAccount() {
        System.out.println("save account!");
        System.out.println(account);
    }
}
```

不考虑使用Spring IOC容器时，业务程序需要使用到什么对象，就自己去new这个对象，并处理对象间的依赖，如下：

```java
@Test
public void withOutSpringTest() {
    AccountService accountService = new AccountService();
    Account account = new Account("lyl", 23);
    accountService.setAccount(account);
    accountService.saveAccount();
}
```

这种实现方式的弊端是显而易见的，业务代码和对象管理的代码是完全耦合的。一旦依赖关系发生改变，就需要大量的去修改业务代码，违背了最基本的开闭原则，代码的可维护性和可扩展性很差。

而如果使用了IOC，我们会通过配置的方式去描述需要使用的对象及它们之间的依赖，从而将对象管理与业务逻辑解耦。这样一旦依赖变化了，也只用修改配置文件，从而提高代码的可维护性。以XML配置方式为例：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="lyl" class="lyl.Account">
        <constructor-arg name="name" value="lyl"></constructor-arg>
        <constructor-arg name="age" value="23"></constructor-arg>
    </bean>

    <bean id="accountService" class="lyl.AccountService" autowire="byName">
        <property name="account" ref="lyl"></property>
    </bean>
</beans>
```

通过配置方式告知容器需要管理哪些对象，以及它们的依赖关系后，我们在使用的时候就可以直接从容器中取了。

```java
@Test
public void test() {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
    AccountService accountService = (AccountService) applicationContext.getBean("accountService");
    accountService.saveAccount();
}
```



### 容器帮我们做了什么？

上面这个例子自然是简单的不得了。我们来回忆一下，作为使用者，我们做了什么：

1. 给出了一个配置文件，以告知容器需要哪些对象，以及对象间的依赖关系。
2. 需要用到的对象，直接从容器中去取。

既然我们的目标是开发一个简单的容器，我们就要去思考一下，这个过程中，容器又做了什么呢？为什么使用者给出一个配置后，就能从容器中拿到直接能使用的对象呢？

其实不难发现，容器大概会做这些事情：

1. 读取配置并解析，从而获得待管理对象的一些信息，并将它保存下来
2. 创建对象，注入对象的依赖
3. 可能还需要做一些后处理，对特定对象做一些特殊处理



### 配置加载与解析

在配置加载与解析的过程中，容器的执行过程：

1. 加载配置位置的资源到内存中
2. 将资源转换为XML Document的形式，以便于验证和解析
3. 解析Document，将解析的信息转为Bean的注册信息BeanDefinition，并存入BeanRegistry中

相关的类及其详细说明如图。

加载配置：

![](https://tva1.sinaimg.cn/large/007S8ZIlgy1ggdoou67qvj313j0gcwi0.jpg)

解析配置：

![](https://tva1.sinaimg.cn/large/007S8ZIlgy1ggdopdf3ipj31hw0q27aj.jpg)





























