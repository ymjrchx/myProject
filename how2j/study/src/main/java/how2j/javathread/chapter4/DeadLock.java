package how2j.javathread.chapter4;

public class DeadLock {
    private final Object MUTEX_READ = new Object();
    private final Object MUTEX_WRITE = new Object();
    public void read(){
        synchronized (MUTEX_READ){
            System.out.println(Thread.currentThread().getName()+"get Read Lock");
            synchronized (MUTEX_WRITE){
                System.out.println(Thread.currentThread().getName()+"get Write Lock");
            }
            System.out.println(Thread.currentThread().getName()+"release Write Lock");
        }
        System.out.println(Thread.currentThread().getName()+"release Read Lock");

    }

    public void write(){
        synchronized (MUTEX_WRITE){
            System.out.println(Thread.currentThread().getName()+"get Write Lock");
            synchronized (MUTEX_READ){
                System.out.println(Thread.currentThread().getName()+"get Read Lock");
            }
            System.out.println(Thread.currentThread().getName()+"release Read Lock");
        }
        System.out.println(Thread.currentThread().getName()+"release Write Lock");

    }

    public static void main(String[] args) {
        final DeadLock deadLock = new DeadLock();
        new Thread(()->{
            while (true){
                deadLock.read();
            }
        },"READ-THREAD").start();

        new Thread(()->{
            while (true){
                deadLock.write();
            }
        },"WRITE-THREAD").start();
    }
}
