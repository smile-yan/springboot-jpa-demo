## springboot-jpa-demo

springboot整合jpa的简单例子。

### 引入依赖包

```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```

### 添加配置

其中withyan.cn是我自己云服务的域名，本地运行的话也就是localhost。

而com.mysql.cj.jdbc.Driver是mysql新的驱动，是springboot官方推荐使用的。

```yml
spring:
  datasource:
    url: jdbc:mysql://withyan.cn:3306/oppo?useUnicode=true&characterEncoding=UTF-8&useSSL=false
    username: root
    password: 123456
    driver-class-name: com.mysql.cj.jdbc.Driver

  jpa:
    database: mysql
    show-sql: true
    hibernate:
      ddl-auto: create
```

### 编写实体类，注意注解时选择相应的类

注意注解时选择相应的类，不要图快导入错的类了。

而Table注解对应的是表的名称。@GeneratedValue表示该字段自增。

```java
package cn.smileyan.demo.springbootjpa.entity;

import javax.persistence.*;

@Entity
@Table(name="test")
public class Test {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String username;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
```

### 编写dao层接口

这层至关重要，如果本来逻辑就简单的话，不妨继承CrudRepository这个接口，可以使用默认的增删改查。

```java
package cn.smileyan.demo.springbootjpa.dao;

import cn.smileyan.demo.springbootjpa.entity.Test;
import org.springframework.data.repository.CrudRepository;

public interface TestDao extends CrudRepository<Test,Long> {
	// 没错，进行简单的增删改查时就是什么都不用做
}
```

### 编写service层接口以及实现类

```java
package cn.smileyan.demo.springbootjpa.service;

import cn.smileyan.demo.springbootjpa.entity.Test;

public interface TestService {

    long save(Test test);
}
```

实现类如下：

```java
package cn.smileyan.demo.springbootjpa.service.impl;

import cn.smileyan.demo.springbootjpa.dao.TestDao;
import cn.smileyan.demo.springbootjpa.entity.Test;
import cn.smileyan.demo.springbootjpa.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private TestDao testDao;

    @Override
    public long save(Test test) {
       testDao.save(test);
        return 0;
    }
}
```

### 编写测试方法

直接在新建springboot项目时自带的JUnit测试类中运行下面的代码：

```java
package cn.smileyan.demo.springbootjpa;

import cn.smileyan.demo.springbootjpa.service.TestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootJpaApplicationTests {

    @Autowired
    private TestService testService;

    @Test
    public void contextLoads() {
        cn.smileyan.demo.springbootjpa.entity.Test test = new cn.smileyan.demo.springbootjpa.entity.Test();
        test.setUsername("hello world");
        test.setId(3455L);
        long id = testService.save(test);
        System.out.println(id);
    }
}
```

### 运行结果

运行结果，如果数据库连接输入的用户名密码等没有错误的话，运行后会自动创建这张表，并且把数据写到表中。但是可以发现id并不是3455而是1，这个是该字段自增的原因导致的。

#### 另外需要注意

但是特别要注意配置文件中  jpa.hibernate.ddl-auto为create是代表每次运行时都会新建这张表，所以每次运行时都不会有以前的数据。



> Smileyan 2019年7月15日

