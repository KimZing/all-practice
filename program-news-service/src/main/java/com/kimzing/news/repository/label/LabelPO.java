package com.kimzing.news.repository.label;

import com.baomidou.mybatisplus.annotation.TableName;
import com.kimzing.utils.po.AbstractPO;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
 * 标签信息实体
 *
 * @author KimZing
 * @since 2021-02-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("label")
public class LabelPO extends AbstractPO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 标签名
     */
    @TableField("name")
    private String name;

}
