package test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by fanxuehui on 2017/7/21.
 */
public class asListTest {

    public static void main(String[] args) {
        List<String> arrayList = Arrays.asList("一","二","三");
        arrayList.add("四");
        System.out.printf(arrayList.toString());
    }
}
