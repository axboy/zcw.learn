package local.zcw.demo.es;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EsApplicationTests {


    @Autowired
    private TransportClient esClient;

    @Test
    public void testAddDoc() throws IOException {
        XContentBuilder content = XContentFactory.jsonBuilder().startObject()
                .field("name", "user1")
                .field("country", "China")
                .field("age", 20)
                .field("birth", "2000-01-01")
                .endObject();
        IndexResponse response = esClient.prepareIndex("demo", "user", "2")
                .setSource(content)
                .get();
        Optional.of(response).ifPresent(it -> {
            System.out.println(it.toString());
        });
    }

    @Test
    public void testUpdateDoc() throws IOException, ExecutionException, InterruptedException {
        UpdateRequest update = new UpdateRequest("demo", "user", "2");
        XContentBuilder content = XContentFactory.jsonBuilder().startObject()
                .field("name", "user2")
                .endObject();
        update.doc(content);
        UpdateResponse response = esClient.update(update).get();
        System.out.println(response.toString());
    }

    @Test
    public void testGet() {
        GetResponse response = esClient.prepareGet("demo", "user", "2").get();
        if (response.isExists()) {
            System.out.println(response.getSource());
        } else {
            System.err.println("not found");
        }
    }

    @Test
    public void query() {
        QueryBuilder query = QueryBuilders.boolQuery();
        //todo
        SearchRequestBuilder builder = esClient.prepareSearch("demo")
                .setTypes("user")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(query)
                .setFrom(0)
                .setSize(10);
        builder.get().getHits().forEach(it -> {
            System.out.println(it.getSource());
        });
    }

    @Test
    public void testDel() {
        DeleteResponse response = esClient.prepareDelete("demo", "user", "2")
                .get();
        System.out.println(response.getResult());
    }


}
