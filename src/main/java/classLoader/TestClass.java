package classLoader;

import java.lang.reflect.Method;

/**
 * Created by fanxuehui on 2017/11/10.
 */
public class TestClass extends Thread{
    public static void main(String[] args) {
        Thread thread = new TestClass();
        thread.start();
    }

    public void run(){
        try {
            // 每次都创建出一个新的类加载器D:\GIT\javaLearn\target\classes\classLoader\Foo.class
            CustomCL cl = new CustomCL("D:\\GIT\\javaLearn\\target\\classes\\classLoader", new String[]{"Foo"});
            //CustomCL cl = new CustomCL("/classLoader", new String[]{"Foo"});
            Class cls = cl.loadClass("Foo");
            Object foo = cls.newInstance();
            Class.forName("dd");
            Method m = foo.getClass().getMethod("sayHello", new Class[]{});
            m.invoke(foo, new Object[]{});

        }  catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
