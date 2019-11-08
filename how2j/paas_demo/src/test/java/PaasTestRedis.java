import lombok.extern.slf4j.Slf4j;
import net.dgg.framework.tac.redis.utils.DggReactiveRedisUtils;
import net.dgg.framework.tac.redis.utils.DggRedisUtils;
import net.dgg.framework.tac.redis.utils.DggRedissonUtils;
import net.tac.paas.test.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@Slf4j
public class PaasTestRedis {

    @Autowired
    DggRedisUtils<String> dggRedisUtils;

    @Autowired
    DggReactiveRedisUtils dggReactiveRedisUtils;

    @Autowired
    DggRedissonUtils dggRedissonUtils;

    @Test
    public void testRedis(){
        //set操作
        dggRedisUtils.set("key001", "value001");
        //get操作
        System.out.println("value===" + dggRedisUtils.get("key001"));
        //rename操作
        dggRedisUtils.renameKey("key001", "key002");
        System.out.println("value2===" + dggRedisUtils.get("key002"));
        //delete操作
        System.out.println("key002删除结果===" + dggRedisUtils.delete("key002"));
    }

    @Test
    public void testReactiveRedis() throws InterruptedException {
        //set操作
        dggReactiveRedisUtils.set("{tag1:}key001", "value001").subscribe(System.out::println);
        Thread.sleep(500);//等待异步操作完成
        //get操作
        dggReactiveRedisUtils.get("{tag1:}key001").subscribe(System.out::println);
        Thread.sleep(500);//等待异步操作完成
        log.info("Threadid==={}", Thread.currentThread().getId());
        dggReactiveRedisUtils.renameKey("{tag1:}key001", "{tag1:}key002").subscribe(res -> {
                log.info("************renameKey==={}, Threadid=={}", res, Thread.currentThread().getId());
                dggReactiveRedisUtils.get("{tag1:}key002").subscribe(value -> {
                    log.info("**************value==={}, Threadid=={}", value, Thread.currentThread().getId());
                    dggReactiveRedisUtils.delete("{tag1:}key002").subscribe(res2 -> {
                        log.info("**************key002删除结果==={}, Threadid=={}", res2, Thread.currentThread().getId());
                    });
                });
            }
        );
    }
}