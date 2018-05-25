package cn.axboy.demo.dubbo.hello.provider;

import cn.axboy.demo.dubbo.hello.api.IDemoService;
import com.alibaba.dubbo.rpc.RpcContext;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zcw
 * @version 1.0.0
 * @date 2018/5/24 00:10
 */
public class DemoServiceImpl implements IDemoService {

    public String sayHello(String name) {
        System.out.println("[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] Hello " + name + ", request from consumer: " + RpcContext.getContext().getRemoteAddress());
        return "Hello " + name + ", response from provider: " + RpcContext.getContext().getLocalAddress();
    }
}
