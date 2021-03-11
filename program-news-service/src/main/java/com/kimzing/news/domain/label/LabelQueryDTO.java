package com.kimzing.news.domain.label;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 标签信息查询
 *
 * @author KimZing
 * @since 2021-02-07
 */
@Data
@Accessors(chain = true)
@ApiModel(value="标签信息查询", description="标签信息")
public class LabelQueryDTO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "标签名")
    private String name;

    @ApiModelProperty(value = "是否显示")
    private Integer show;

}
