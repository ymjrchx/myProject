package how2j.javathread.chapter4;

import java.util.concurrent.TimeUnit;

public class Mutex  implements Cloneable{
    private final static Object MUTEX = new Object();

    ThreadLocal local = new ThreadLocal();
    ThreadLocal local1 = new ThreadLocal();

    public void accessResource(){
        local.set("ddd");
        synchronized (MUTEX){
            try {
                synchronized (MUTEX){
                    TimeUnit.SECONDS.sleep(10);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public void readObject(){

    }

    public static void main(String[] args) throws InterruptedException {

       final Mutex mutex = new Mutex();
       mutex.local.set("ssss");
       mutex.local1.set("dgege");
   /*    mutex.local=null;
       mutex.local1=null;*/
       System.gc();
       Runtime.getRuntime().addShutdownHook(new Thread(() -> {
           System.out.println(Thread.currentThread().getName());

       }));

        System.out.println(Thread.currentThread());
       /* System.out.println(mutex.local.get());
        System.out.println(mutex.local1.get());*/



       /* for (int i = 0; i < 5 ; i++) {
            new Thread(mutex::accessResource).start();
            Thread thread = new Thread();
            thread.wait(1,1);


        }*/
    }
}
