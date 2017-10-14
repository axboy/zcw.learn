package cn.wazitang.utils.model;

import cn.wazitang.utils.annotation.ExportField;
import lombok.Data;

import java.util.Date;

/**
 * 作者 zcw
 * 时间 2017/10/14 17:05
 * 描述 测试数据
 */
@Data
public class TestModel {

    @ExportField(name = "str")
    private String str;

    @ExportField(name = "longTime")
    private Long longTime;

    @ExportField(name = "dateTime")
    private Date dateTime;

    private String unchecked;
}
