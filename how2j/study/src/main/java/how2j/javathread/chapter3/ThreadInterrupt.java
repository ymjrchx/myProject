package how2j.javathread.chapter3;

import java.util.concurrent.TimeUnit;

public class ThreadInterrupt {

   public static void  interrupt() throws InterruptedException {
       Thread thread = new Thread(()->{
           try{
               TimeUnit.SECONDS.sleep(1);
           } catch (InterruptedException e) {
               e.printStackTrace();
               System.out.println("oh, i am be interrupted");
           }
       });
       thread.start();
       TimeUnit.MILLISECONDS.sleep(2);
       thread.interrupt();
   }

   public static void interrupted() throws InterruptedException {
        Thread thread = new Thread(){
            @Override
            public void run() {
                while (true){

                }
            }
        };
        thread.start();
        TimeUnit.MILLISECONDS.sleep(2);
       System.out.printf("thiread is interrupet? %s\n",thread.isInterrupted());
       thread.interrupt();
       System.out.printf("thread is interuped ? %s \n",thread.isInterrupted());

   }

   public static void interrupted1() throws InterruptedException {
        Thread thread = new Thread(){
            @Override
            public void run(){
                while (true){
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        System.out.println(" i am be interrupted: "+isInterrupted());
                    }
                }
            }
        };

        //thread.setDaemon(true);
        thread.start();
        TimeUnit.MILLISECONDS.sleep(2);
       System.out.println("thread is interrupted: "+ thread.isInterrupted());
       thread.interrupt();
       TimeUnit.MILLISECONDS.sleep(2);
       System.out.println("thread is interrupted: "+ thread.isInterrupted());
   }

   public static void interrupted2() throws InterruptedException {
       Thread thread = new Thread(){
           public void run(){
               while (true){
                   System.out.println(Thread.interrupted());
               }
           }
       };
       thread.setDaemon(true);
       thread.start();
       TimeUnit.MILLISECONDS.sleep(2);
       thread.interrupt();
   }

    public static void interrupted3() throws InterruptedException {
        System.out.println("main thread is interrupted:"+Thread.interrupted());
        Thread.currentThread().interrupt();
        System.out.println("main thread is interrupted:"+Thread.currentThread().isInterrupted());
        try{
            TimeUnit.SECONDS.sleep(2);
            System.out.println("sleop");
        }catch (InterruptedException e){
            System.out.println("t whi bi ");
        }

    }


    public static void main(String[] args) throws InterruptedException {
        //interrupt();
        //interrupted();
//        interrupted1();
//        interrupted2();
        interrupted3();
    }

}
