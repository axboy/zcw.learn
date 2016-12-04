package cn.wazitang.demo1.di;

import org.springframework.stereotype.Service;

/**
 * Created by zcw on 2016/12/03.
 */
@Service        //声明当前类是spring管理的一个Bean，@Component、@Service、@Repository、@Controller等效
public class FuncService {
    public String sayHello(String word) {
        return "hello " + word;
    }
}
