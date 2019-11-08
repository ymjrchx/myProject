package how2j.javathread.chapter3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class FightQueryTask extends Thread implements FightQuery {
    private final String origin;
    private final String destination;
    private final List<String> flightList = new ArrayList<>();

    public FightQueryTask(String airLing,String origin, String destination) {
        super("["+airLing+"]");
        this.origin = origin;
        this.destination = destination;
    }

    @Override
    public void run(){
        System.out.printf("%s-query from %s to %s \n",getName(),origin,destination);
        int randomVal = ThreadLocalRandom.current().nextInt(10);
        try{

            TimeUnit.SECONDS.sleep(randomVal);
            this.flightList.add(getName()+"-"+randomVal);
            System.out.printf("the fight :%s list query successful\n",getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> get() {
        return this.flightList;
    }
}
