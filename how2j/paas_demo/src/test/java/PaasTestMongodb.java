import com.alibaba.fastjson.JSON;
import model.MongoDbModel;
import net.dgg.framework.tac.mongo.utils.DggMongoUtils;
import net.dgg.framework.tac.mongo.utils.DggReactiveMongoUtils;
import net.tac.paas.test.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.util.CloseableIterator;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class PaasTestMongodb {

    @Autowired
    DggMongoUtils dggMongoUtils;

    @Autowired
    DggReactiveMongoUtils dggReactiveMongoUtils;

    @Test
    public void testMongodb(){
        try(CloseableIterator it = dggMongoUtils.stream(new Query(), MongoDbModel.class)){
            while (it.hasNext())
                System.out.println(JSON.toJSONString(it.next()));
        }
    }
}