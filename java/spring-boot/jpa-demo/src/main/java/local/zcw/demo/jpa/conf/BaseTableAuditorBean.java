package local.zcw.demo.jpa.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

/**
 * 作者 zcw
 * 时间 2017/9/8 11:33
 * 描述 配置获取当前用户名，未登录，写死
 */
@Configuration
public class BaseTableAuditorBean implements AuditorAware<String> {
    @Override
    public String getCurrentAuditor() {
        return "system";
    }
}
