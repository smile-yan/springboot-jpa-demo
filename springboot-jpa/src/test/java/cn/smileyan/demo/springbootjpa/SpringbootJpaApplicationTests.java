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
