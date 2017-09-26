package aop;

import dynamicProxy.Hello;
import dynamicProxy.HelloImpl;
import org.springframework.aop.framework.ProxyFactory;

/**
 * Created by fanxuehui on 2017/9/26.
 */
public class AopClient {
    public static void main(String[] args) {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(new HelloImpl());
        proxyFactory.addAdvice(new GreetingBeforeAdvice());
        proxyFactory.addAdvice(new GreetingAfterAdvice());

        Hello hello = (Hello) proxyFactory.getProxy();
        hello.say();
    }
}
