package dynamicProxy;

/**
 * Created by FXH on 2017/9/25.
 */
public class HelloImpl implements Hello{


    @Override
    public void say() {
        System.out.println("he says hello!");
    }
}
