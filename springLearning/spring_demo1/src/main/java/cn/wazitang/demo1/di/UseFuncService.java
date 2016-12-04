package cn.wazitang.demo1.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zcw on 2016/12/03.
 */
@Service
public class UseFuncService {

    @Autowired
    FuncService funcService;

    public String sayHello(String word) {
        return funcService.sayHello(word);
    }
}
