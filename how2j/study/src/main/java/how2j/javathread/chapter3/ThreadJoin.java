package how2j.javathread.chapter3;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ThreadJoin {
    private static Thread create(int seq){
        return new Thread(()->{
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName()+"#"+i);
                shortSleep();

            }
        },String.valueOf(seq));
    }

    private static void shortSleep(){
        try{
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void join() throws InterruptedException {
        List<Thread> threadList = IntStream.range(1,3).mapToObj(ThreadJoin::create).collect(Collectors.toList());
        threadList.forEach(Thread::start);
     /*   for(Thread thread : threadList){
            thread.join();
        }*/
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName()+"#"+i);
            shortSleep();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        join();

    }
}
