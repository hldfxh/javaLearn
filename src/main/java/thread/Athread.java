package thread;

/**
 * Created by fanxuehui on 2017/10/10.
 */
public class Athread implements Runnable {

    public static String str = "s";

    public static String getStr() {
        return str;
    }

    public static void setStr(String str) {
        Athread.str = str;
    }

    @Override
    public void run() {

        for (int i = 0;i<4;i++) {
            System.out.println(str);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
