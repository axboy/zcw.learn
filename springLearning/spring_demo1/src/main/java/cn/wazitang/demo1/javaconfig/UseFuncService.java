package cn.wazitang.demo1.javaconfig;

/**
 * Created by zcw on 2016/12/03.
 */
//此处未使用@Service注解
public class UseFuncService {

    //此处未使用@Autowired注解
    FuncService funcService;

    public String sayHello(String word) {
        return funcService.sayHello(word);
    }

    public void setFuncService(FuncService funcService) {
        this.funcService = funcService;
    }
}
