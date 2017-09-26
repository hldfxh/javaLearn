package test;

/**
 * Created by fanxuehui on 2017/7/20.
 */
public class evenOdd {

    public static void main(String[] args) {
        //  模2
        int[] numbers = {1,2,3,0,-1,-2,-3};
        for (int i = 0; i < numbers.length; i++) {
            System.out.println(numbers[i]+"是" +(numbers[i]%2==1?"奇数":"偶数") );
        }

    }
}
