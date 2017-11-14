package classLoader;

/**
 * Created by fanxuehui on 2017/11/10.
 */
public class Foo{

    public static final int a = (int) (Math.random()*100000);
    static {
        System.out.println("Foo was initialized");
    }
    public void sayHello() {
        System.out.println("hello world! (version one)");
    }
}
