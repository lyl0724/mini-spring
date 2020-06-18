package framework;

import framework.context.ApplicationContext;
import framework.context.ClassPathXmlApplicationContext;
import org.junit.Test;

public class UserTest {
    @Test
    public void test() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        User user = (User) applicationContext.getBean("user");
        School school = (School) applicationContext.getBean("school");

        System.out.println(user);
        System.out.println(school);
    }
}
