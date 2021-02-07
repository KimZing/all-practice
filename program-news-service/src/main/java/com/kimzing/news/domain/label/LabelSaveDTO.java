package com.kimzing.news.domain.label;

import java.time.LocalDateTime;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 标签信息保存
 *
 * @author KimZing
 * @since 2021-02-07
 */
@Data
@Accessors(chain = true)
@ApiModel(value="标签信息保存", description="标签信息")
public class LabelSaveDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "标签名")
    private String name;

}
