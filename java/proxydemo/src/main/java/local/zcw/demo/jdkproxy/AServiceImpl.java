package local.zcw.demo.jdkproxy;

/**
 * 作者 zcw
 * 时间 2017/11/14 12:00
 * 版本 1.0.0
 * 描述 IService实现
 */
public class AServiceImpl implements IService {
    @Override
    public void output() {
        System.out.println("AService output");
    }
}
