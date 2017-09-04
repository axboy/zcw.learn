package local.zcw.demo;

import local.zcw.demo.generate.HelloWorldService;
import org.apache.thrift.TException;

/**
 * 作者 zcw
 * 时间 2017/9/4 12:44
 * 描述 TODO
 */
public class HelloWorldServiceImpl implements HelloWorldService.Iface {
    @Override
    public String sayHello(String username) throws TException {
        return "hello thrift";
    }
}
