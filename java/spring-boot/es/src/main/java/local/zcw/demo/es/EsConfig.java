package local.zcw.demo.es;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author zcw
 * @version 1.0.0
 * @date 2018/3/4 22:26
 */
@Configuration
public class EsConfig {

    @Bean
    public TransportClient esClient() throws UnknownHostException {
        InetSocketTransportAddress node = new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300);

        Settings settings = Settings.builder().put("cluster.name", "wali").build();

        TransportClient client = new PreBuiltTransportClient(settings);
        client.addTransportAddress(node);
        return client;
    }
}
