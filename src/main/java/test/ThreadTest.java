package test;

import thread.Athread;

/**
 * Created by fanxuehui on 2017/10/10.
 */
public class ThreadTest {

    public static void main(String[] args) {
        ExecutorPool.initPool();
        Athread athread = new Athread();
        try {
            ExecutorPool.executeTask(athread);
            Thread.sleep(1000);
            Athread.setStr("fsdfs");
            ExecutorPool.executeTask(athread);
            Thread.sleep(1000);
            Athread.setStr("fsdfsfdsfds");

            ExecutorPool.executeTask(athread);
            Thread.sleep(1000);

            ExecutorPool.executeTask(athread);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Athread.setStr("fsdfs");

    }
}
