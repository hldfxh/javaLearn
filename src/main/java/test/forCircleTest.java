package test;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by fanxuehui on 2017/7/21.
 */
public class forCircleTest {
    public static void main(String[] args) {

        int amount = 800 * 10000;

        List<Integer> scores = new ArrayList<Integer>(amount);//RandomAccess

        for (int i = 0; i < amount; i++) {
            scores.add(new Random().nextInt(100));
        }

        long start = System.currentTimeMillis();
        System.out.println("平均分："+average(scores,1));
        long end1 = System.currentTimeMillis();
        System.out.println("耗时："+ (end1-start) + "ms");

        System.out.println("平均分："+average(scores,2));
        System.out.println("耗时："+ (System.currentTimeMillis()-end1) + "ms");

    }

    private static int average(List<Integer> scores, int type) {
        int amount = scores.size();
        int sum = 0;
        switch (type) {
            case 1:
                for (int j = 0; j < amount; j++) {
                    sum += scores.get(j);
                }
                break;
            case 2:
                for (Integer score : scores) {
                    sum += score;
                }
                break;
        }
        return (sum/amount);
    }
}
