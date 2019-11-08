import model.EsModel;
import net.dgg.framework.tac.elasticsearch.DggESTemplate;
import net.dgg.framework.tac.elasticsearch.exception.DggEsException;
import net.tac.paas.test.Application;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class PaasTestEs {

    @Autowired
    DggESTemplate dggESTemplate;

    @Test
    public void testEs() throws DggEsException, IllegalAccessException {
        //示例：打印出所有索引
        dggESTemplate.getAliases().forEach(System.out::println);
        //示例：Create
        EsModel model = new EsModel();
        model.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        model.setName("张三");
        model.setPhone("XXXXXXXXXXXXXX");
        dggESTemplate.createDocument(model);
        //示例：Retrieve
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.matchQuery("name", "张三"));
        List<EsModel> model2List = dggESTemplate.retrieveDocument(sourceBuilder, EsModel.class);
        //**********通过ID查询
        EsModel model3 = dggESTemplate.retrieveDocumentById(model.getId(), EsModel.class);
        //示例：Update
        model.setName("小二");
        model.setPhone("ZZZZZZZZZZZZ");
        dggESTemplate.updateDocument(model);
        //示例：Delete
        dggESTemplate.deleteDocument(model);
    }
}