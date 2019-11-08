package how2j.javathread.chapter3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class FightQueryExample {
    private static List<String> fightCompany = Arrays.asList("CSA","CEA","HNA");
    private static List<String> search(String original,String dest){
        final List<String> result = new ArrayList<>();
        List<FightQueryTask> tasks = fightCompany.stream()
                .map(f->createSearchTask(f,original,dest)).collect(Collectors.toList());
        tasks.forEach(Thread::start);
        tasks.forEach(t->{
            try{
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        tasks.stream().map(FightQuery::get).forEach(result::addAll);
        return result;
    }

    private static FightQueryTask createSearchTask(String fight,String original,String dest){

      return new FightQueryTask(fight,original,dest);
    }

    public static void main(String[] args) {
        List<String> results = search("SH","BJ");
        System.out.println("************************");
        results.forEach(System.out::println);
    }

}
