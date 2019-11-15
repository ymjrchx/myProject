package reactor;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author chenxin
 * @date 2019/11/15 16:21
 */
public class MonoTest {
    public static void main(String[] args) {
        Mono.fromSupplier(() ->"Hello").subscribe(System.out::println);
        Mono.justOrEmpty(Optional.of("Hello")).subscribe(System.out::println);
        Mono.create(sink ->
            sink.success("hello")
        ).subscribe(System.out::println);
    }

    @Test
    public void buffer(){
        Flux.range(1,100).buffer(20).subscribe(System.out::println);
        Flux.interval(Duration.ofMillis(100)).buffer(Duration.ofMillis(1001)).take(2).toStream().forEach(System.out::println);
        Flux.range(1,10).bufferUntil(i -> i %2 == 0).subscribe(System.out::println);
        Flux.range(1,10).bufferWhile(i -> i%2 == 0).subscribe(System.out::println);
    }

    @Test
    public void filter(){
        Flux.range(1,10).filter(i -> i%2 == 0).subscribe(System.out::println);
    }

    @Test
    public void window() throws InterruptedException {
        Flux.range(1,100).window(20).subscribe(System.out::println);
        Flux.interval(Duration.ofMillis(100)).window(Duration.ofMillis(1001)).take(2).toStream().forEach(System.out::println);
        TimeUnit.SECONDS.sleep(60);

    }

    @Test
    public void zipWith(){
        Flux.just("a","b").zipWith(Flux.just("c","d")).subscribe(System.out::println);
        Flux.just("a","b").zipWith(Flux.just("c","d"),(s1,s2)->String.format("%s-%s",s1,s2)).subscribe(System.out::println);

        Flux.range(1, 1000).take(10).subscribe(System.out::println);
        Flux.range(1, 1000).takeLast(10).subscribe(System.out::println);
        Flux.range(1, 1000).takeWhile(i -> i < 10).subscribe(System.out::println);
        Flux.range(1, 1000).takeUntil(i -> i == 10).subscribe(System.out::println);

        Flux.range(1, 100).reduce((x, y) -> x + y).subscribe(System.out::println);
        Flux.range(1, 100).reduceWith(() -> 100, (x, y) -> x + y).subscribe(System.out::println);


        System.out.println("**************************************");
        Flux.merge(Flux.interval(Duration.ofMillis(0), Duration.ofMillis(100)).take(5), Flux.interval(Duration.ofMillis(50), Duration.ofMillis(100)).take(5))
                .toStream()
                .forEach(System.out::println);
        Flux.mergeSequential(Flux.interval(Duration.ofMillis(0), Duration.ofMillis(100)).take(5), Flux.interval(Duration.ofMillis(50), Duration.ofMillis(100)).take(5))
                .toStream()
                .forEach(System.out::println);

    }



























































}
