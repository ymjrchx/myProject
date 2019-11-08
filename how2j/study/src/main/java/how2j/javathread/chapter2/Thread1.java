package how2j.javathread.chapter2;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Thread1 {
    private final static String prefix = "Alex-";
    private static void thread1(){
        IntStream.range(0,5).mapToObj(Thread1::createThread).forEach(Thread::start);
    }
    private static  Thread createThread(final int initNmae){
        return new Thread(()-> System.out.println(Thread.currentThread()),prefix+initNmae);
    }

    public static void main(String[] args) throws InterruptedException {
        //thread1();
       // group();
       // stackSize();
       // daemonThread();
        //threadPriority();
        System.out.println(Thread.currentThread().getId());
        threadPriority1();
        System.out.println(Thread.currentThread().getContextClassLoader());
    }

    public static void group(){
        Thread t1 = new Thread("t1");
        ThreadGroup group = new ThreadGroup("TestGRoup");
        Thread t2 = new Thread(group,"t2");
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
        System.out.println(mainGroup);
        System.out.println(t1.getThreadGroup());
        System.out.println(t2.getThreadGroup());
    }

    public static void stackSize(){
        ThreadGroup group = new ThreadGroup("TestGroup");
        Runnable runnable = new Runnable() {
            final int max = Integer.MAX_VALUE;
            @Override
            public void run() {
                int i=0;
                recurse(i);
            }
            private void recurse(int i){
                System.out.println(i);
                if(i<max) {
                    recurse(i+1);
                }
            }
        };
        Thread thread = new Thread(group,runnable,"Test",1000000);
        thread.start();
    }

    public static void daemonThread() throws InterruptedException {
        Thread thread = new Thread(()->{
            while (true){
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"mytest");
      //  thread.setDaemon(true);
        thread.start();
        Thread.sleep(20000);
        System.out.println("main thread finished");
        TimeUnit.SECONDS.sleep(1);
    }

    public static void threadPriority(){
        ThreadGroup group = new ThreadGroup("test");
        group.setMaxPriority(7);
        Thread thread = new Thread(group,"test-thread");
        thread.setPriority(11);
        System.out.println(thread.getPriority());

    }
    public static void threadPriority1(){
        Thread t1 = new Thread();
        System.out.println(t1.getPriority());
        Thread t2 = new Thread(()->{
            Thread t3 = new Thread();
            System.out.println(t3.getPriority());
            System.out.println("t3 id:"+t3.getId());
            System.out.println(t3.getContextClassLoader());
        });
        t2.setPriority(6);
        t2.start();
        System.out.println(t2.getPriority());
        System.out.println("t2 id:"+t2.getId());
    }
}
