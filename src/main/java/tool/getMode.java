package tool;

import java.util.Scanner;

/**
 * Created by fanxuehui on 2017/8/3.
 */
public class getMode {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Long uid = in.nextLong();
        long a = (uid.longValue()>>23)%16;
        System.out.printf(""+a);
    }
}
