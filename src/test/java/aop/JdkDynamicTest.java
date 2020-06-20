package aop;

import aop.account.AccountService;
import framework.aop.AdviceSupport;
import framework.aop.AopProxy;
import framework.aop.JdkDynamicAopProxy;
import framework.aop.TargetSource;
import framework.context.ApplicationContext;
import framework.context.ClassPathXmlApplicationContext;
import org.junit.Test;

public class JdkDynamicTest {
    @Test
    public void test() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        AccountService accountService = (AccountService) applicationContext.getBean("accountService");
        accountService.saveAccount("lyl");

        //设置被代理对象
        AdviceSupport adviceSupport = new AdviceSupport();
        TargetSource targetSource = new TargetSource(accountService, accountService.getClass());
        adviceSupport.setTargetSource(targetSource);

        //设置拦截器，也就是增强的逻辑
        AuthorityInterceptor authorityInterceptor = new AuthorityInterceptor();
        adviceSupport.setMethodInterceptor(authorityInterceptor);

        //创建代理
        AopProxy aopProxy = new JdkDynamicAopProxy(adviceSupport);
        AccountService accountServiceProxy = (AccountService) aopProxy.getProxy();

        //通过代理对象调用方法
        accountServiceProxy.saveAccount("lyl");
    }
}
