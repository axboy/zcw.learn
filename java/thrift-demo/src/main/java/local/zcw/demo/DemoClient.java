package local.zcw.demo;

import local.zcw.demo.generate.HelloWorldService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

/**
 * 作者 zcw
 * 时间 2017/9/4 12:50
 * 描述 TODO
 */
public class DemoClient {
    public static void main(String[] args) {
        try {
            TTransport transport = new TFramedTransport(new TSocket("127.0.0.1", 8191, 5000));
            // 协议要和服务端一致
            TProtocol protocol = new TBinaryProtocol(transport);

            HelloWorldService.Client client = new HelloWorldService.Client(protocol);

            transport.open();

            String string = null;
            string = client.sayHello("Neo");

            System.out.println(string);

            transport.close();

        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        }
        //作者：贤狼赫萝
        //链接：http://www.jianshu.com/p/10b7cf0a384e
        //來源：简书
        //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    }
}
