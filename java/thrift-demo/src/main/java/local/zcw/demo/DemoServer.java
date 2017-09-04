package local.zcw.demo;

import local.zcw.demo.generate.HelloWorldService;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;
import org.apache.thrift.transport.TTransportFactory;

/**
 * 作者 zcw
 * 时间 2017/9/4 12:46
 * 描述 TODO
 */
public class DemoServer {

    public static void main(String[] args) {
        try {
            System.out.println("Starting Thrift Server......");

            TProcessor processor = new HelloWorldService.Processor<HelloWorldService.Iface>(new HelloWorldServiceImpl());

            TServerSocket serverTransport = new TServerSocket(8191);

            TTransportFactory transportFactory = new TFramedTransport.Factory();

            TBinaryProtocol.Factory factory = new TBinaryProtocol.Factory();

            TServer.Args tArgs = new TServer.Args(serverTransport);
            tArgs.protocolFactory(factory);
            tArgs.transportFactory(transportFactory);
            tArgs.processor(processor);
            // 简单的单线程服务模型，一般用于测试
            TServer server = new TSimpleServer(tArgs);
            server.serve();
        } catch (TTransportException e) {
            System.out.println("Starting Thrift Server......Error!!!");
            e.printStackTrace();
        }
        //作者：贤狼赫萝
        //链接：http://www.jianshu.com/p/10b7cf0a384e
        //來源：简书
        //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    }
}
