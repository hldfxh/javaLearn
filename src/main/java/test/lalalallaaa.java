package test;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by fanxuehui on 2017/8/25.
 */
public class lalalallaaa {

    public static void main(String[] args) {


        String dfsfadt = "fds";
        try {
            dfsfadt = "432324";
            throw new Exception();
        } catch (Exception e) {
            System.out.println(dfsfadt);
        }


        int a = 2;
        assert(a > 0);
        a = 0;
        assert(a >= 0);
        System.out.println(Calendar.getInstance().getTimeInMillis());
        System.out.println(Calendar.getInstance().getTimeInMillis());

        Calendar time = Calendar.getInstance();
        System.gc();
        Runtime.getRuntime().gc();
        List<String> list = new ArrayList<>();
        list.add("dsfds");
        Object obj = new Object();


        String s1 = "Programming";
        String s2 = new String("Programming");
        String s3 = "Program";
        String s4 = "ming";
        String s5 = "Program" + "ming";
        String s6 = s3 + s4;
        System.out.println(s1 == s2);
        System.out.println(s1 == s5);
        System.out.println(s1 == s6);
        System.out.println(s1 == s1.intern());
        System.out.println(s6 == s6.intern());
        System.out.println(s1 == s6.intern());
        System.out.println(s2 == s2.intern());

        Integer f1 = 100, f2 = 100, f3 = 150, f4 = 150,f5 = -126,f6 = -126,f7 = -129,f8 = -129;
    /*    Integer f1 = new Integer(100);
        Integer f2 = new Integer(100);*/

        System.out.println(f1 == f2);
        /*System.out.println(f3 == f4);
        System.out.println(f5 == f6);
        System.out.println(f7 == f8);*/

        String match = "http:/sdf";
        boolean aa = match.matches("\\w+:/\\w*");
        System.out.println(aa);
    }
}
