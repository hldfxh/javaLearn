package aop;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * Created by fanxuehui on 2017/9/26.
 */
public class GreetingAfterAdvice implements AfterReturningAdvice  {
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("After***********");
    }
}
