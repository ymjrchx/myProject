package reactor;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Hooks;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author chenxin
 * @date 2019/11/15 16:21
 */
public class FluxTest {
    public static void main(String[] args) throws InterruptedException {
        Hooks.onOperatorDebug();
        System.out.println(Thread.currentThread().getName());
        System.out.println("********************Just******************************");
        Flux.just("hello","world").subscribe(System.out::println);
        System.out.println("*******************FromArray**********************");

        Flux.fromArray(new Integer[]{1,2,3}).subscribe(System.out::println);

        System.out.println("*******************Empty**********************");
        Flux.empty().subscribe(System.out::println);
        System.out.println("*******************FromArray**********************");
        Flux.range(1,10).subscribe(System.out::println);
        System.out.println("*******************Interval**********************");
        Flux.interval(Duration.of(10, ChronoUnit.SECONDS)).subscribe(System.out::println);
        TimeUnit.SECONDS.sleep(60);
        System.out.println("*******************FromArray**********************");
        System.out.println("*******************FromArray**********************");
    }

    @Test
    public void generate(){
        Flux.generate(sink ->{
            sink.next("hello");
            sink.complete();
        }).subscribe(System.out::println);


        final Random random = new Random();
        Flux.generate(ArrayList::new,(list,sink) ->{
            int value = random.nextInt(100);
            list.add(value);
            sink.next(value);
            if(list.size() == 10){
                sink.complete();
            }
            return list;
        }).subscribe(System.out::println);

    }

    @Test
    public void create(){
        Flux.create(sink ->{
            for (int i = 0; i < 10; i++) {
                sink.next(i);
            }
            sink.complete();
        }).subscribe(System.out::println);
    }


































































}
