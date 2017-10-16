package cn.wazitang.utils.model;

import cn.wazitang.utils.annotation.ExportField;
import cn.wazitang.utils.annotation.ExportType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 作者 zcw
 * 时间 2017/10/14 17:05
 * 描述 测试数据
 */
@Data
public class TestModel {

    @ExportField(name = "str")
    private String str = "str12345678901234567890123456789";

    @ExportField(name = "doubleValue", type = ExportType.NUM)
    private Double doubleValue = 1.23;

    @ExportField(name = "bigDecimalValue", type = ExportType.NUM)
    private BigDecimal bigDecimalValue = BigDecimal.valueOf(1.23435);

    @ExportField(name = "intValue", type = ExportType.NUM)
    private Integer intValue = 543;

    @ExportField(name = "intValue")     //未指定type
    private Long longValue = 78L;

    @ExportField(name = "longTime", type = ExportType.DATE)
    private Long longTime = System.currentTimeMillis();

    @ExportField(name = "dateTime", type = ExportType.DATE)
    private Date dateTime = new Date();

    @ExportField(name = "url", type = ExportType.URL)
    private String url = "https://wazitang.cn/";

    private String unchecked = "unchecked";
}
