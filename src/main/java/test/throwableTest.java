package test;

/**
 * Created by fanxuehui on 2017/7/21.
 */
public class throwableTest {

    public static void main(String[] args) {
       getStack();
    }

    private static void getStack() {
        StackTraceElement[] sts = new Throwable().getStackTrace();
        System.out.printf(sts.toString());

    }

}
