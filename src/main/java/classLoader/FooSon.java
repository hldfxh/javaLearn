package classLoader;

/**
 * Created by fanxuehui on 2017/11/10.
 */
public class FooSon extends Foo {
    public static final int a = (int) (Math.random()*3);
    static {
        System.out.println("FooSon was initialized");
    }
    public void sayHello() {
        System.out.println("hello world! (version one)");
    }

}
