package reactor;

import org.junit.Test;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

import javax.xml.bind.SchemaOutputResolver;
import java.util.concurrent.TimeUnit;

/**
 * @author chenxin
 * @date 2019/11/18 9:14
 */
public class Reactor3 {

    @Test
    public void test1(){
        Flux<Integer> ints = Flux.range(1,4).map( i ->{
            if (i <=3) return i;
            throw new RuntimeException("Go to 4");
        });
        ints.subscribe(i -> System.out.println(i), error -> System.out.println("Error:" +error));
    }

    @Test
    public void test2(){
        Flux<Integer> ints = Flux.range(1, 4).map(i ->{
            if(i == 2){
                throw new RuntimeException("get 3");
            }
            return i;
        });
        ints.subscribe(i -> System.out.println(i),
                error -> System.err.println("Error " + error),
                () -> System.out.println("Done"));
    }

    @Test
    public void test3(){
        Flux<Integer> ints = Flux.range(1, 4);
        ints.subscribe(i -> System.out.println(i),
                error -> System.err.println("Error " + error),
                () -> System.out.println("Done"),
                sub -> sub.request(10));
    }

    public static class SampleSubscriber<T> extends BaseSubscriber<T> {

        @Override
        public void hookOnSubscribe(Subscription subscription) {
            System.out.println("Subscribed");
            request(1);
        }

        @Override
        public void hookOnNext(T value) {
            System.out.println(value);
            request(1);
        }
    }

    @Test
    public void test4() throws InterruptedException {
        SampleSubscriber<Integer> ss = new SampleSubscriber<>();
        Flux<Integer> ints = Flux.range(1, 4);
        ints.subscribe(i -> System.out.println(i),
                error -> System.err.println("Error " + error),
                () -> {System.out.println("Done");},
                s -> s.request(10));
        System.out.println("*************************************");
        ints.subscribe(ss);

        TimeUnit.SECONDS.sleep(20);

    }

    @Test
    public void test5(){
        Flux.range(1, 10)
                .doOnRequest(r -> System.out.println("request of " + r))
                .subscribe(new BaseSubscriber<Integer>() {

                    @Override
                    public void hookOnSubscribe(Subscription subscription) {
                        request(1);
                    }

                    @Override
                    public void hookOnNext(Integer integer) {
                        System.out.println("Cancelling after having received " + integer);
                        cancel();
                    }
                });
    }

































































}
