package how2j.javathread.chapter3;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class ThreadExit {

    public static void exit1() throws InterruptedException {
        Thread thread = new Thread() {
            @Override
            public void run() {
                System.out.println("i will start work");
                while (!isInterrupted()) {

                }
                System.out.println("i will be exiting");
            }
        };
        thread.start();
        TimeUnit.SECONDS.sleep(2);
        System.out.println("system will be shutdown");
        thread.interrupt();
    }

    public static void exit2() throws InterruptedException {
        Thread thread = new Thread() {
            @Override
            public void run() {
                System.out.println("i will start work");
                for (; ; ) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                }
                System.out.println("i will be exiting");
            }
        };
        thread.start();
        TimeUnit.SECONDS.sleep(2);
        System.out.println("system will be shutdown");
        thread.interrupt();
    }

    static class MyTask extends Thread{
        private volatile boolean closed = false;
        public void run(){
            System.out.println("i will be working");
            while (!closed && ! isInterrupted()){
                System.out.println("working");
            }
            System.out.println("i will be exiting");
        }
        public void close(){
            closed = true;
            this.interrupt();
        }
    }

    public static void exit3() throws InterruptedException {
        MyTask myTask=  new MyTask();
        myTask.start();
        TimeUnit.SECONDS.sleep(2);
        System.out.println("system will be shutdown");
        myTask.close();

    }

    public static void main(String[] args) throws InterruptedException {
//        exit1();
//        exit2();
        exit3();
    }
}
