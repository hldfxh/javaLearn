package CGlib;

import dynamicProxy.Hello;
import dynamicProxy.HelloImpl;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by fanxuehui on 2017/9/26.
 */
public class CGLibProxy implements MethodInterceptor {

    public <T> T getProxy(Class<T> cls) {
        return (T) Enhancer.create(cls,this);
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {

        Object result = proxy.invokeSuper(obj,args);
        return result;
    }

    public static void main(String[] args) {
        CGLibProxy cgLibProxy = new CGLibProxy();
        Hello helloProxy = cgLibProxy.getProxy(HelloImpl.class);
        helloProxy.say();
    }
}
