package how2j.javathread.chapter1;

import java.util.concurrent.TimeUnit;

public class TryConcurrency {
    private static void browseNews()
    {
        for(;;)
        {
            System.out.println("uh-hum,the good nows");
            sleep(1);
        }
    }

    private static void sleep(int seconds)
    {
        try{
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void enjoyMusic(){
        for (;;){
            System.out.println("uh-huh,this nice musice");
            sleep(1);
        }
    }

    public static void main(String[] args) throws InterruptedException {
//       thread1();
        //thread2();
        thread3();


    }

    private static void  thread1(){
        new Thread(TryConcurrency::enjoyMusic).start();
        browseNews();
    }

    private static void thread2() throws InterruptedException {
        Thread thread = new Thread(){
            @Override
            public void run(){
                try{
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("over");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };

        thread.start();
        TimeUnit.SECONDS.sleep(2);
        thread.start();
    }

    public static class TicketWindow extends Thread{
        private final String name;
        private static final int Max=40;
        private int index=1;

        public TicketWindow(String name) {
            this.name=name;

        }

        @Override
        public void run() {
            while (index<= Max){
                System.out.println("柜台:"+name+"当前号码是: "+(index++));
            }
        }
    }

    private static void thread3(){
        TicketWindow ticketWindow= new TicketWindow("一号出号机");
        TicketWindow ticketWindow2= new TicketWindow("二号出号机");
        TicketWindow ticketWindow3= new TicketWindow("三号出号机");
        TicketWindow ticketWindow4= new TicketWindow("四号出号机");
        ticketWindow.start();
        ticketWindow2.start();
        ticketWindow3.start();
        ticketWindow4.start();
    }


}
