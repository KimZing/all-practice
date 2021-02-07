package com.kimzing.news.domain.label;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import com.kimzing.utils.po.AbstractBO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.EqualsAndHashCode;

/**
 * 标签信息业务对象
 *
 * @author KimZing
 * @since 2021-02-07
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="标签信息业务对象", description="标签信息")
public class LabelBO extends AbstractBO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "标签名")
    private String name;

}
