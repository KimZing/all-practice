package com.kimzing.news.domain.article;

import java.time.LocalDateTime;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 文章信息查询
 *
 * @author KimZing
 * @since 2021-03-03
 */
@Data
@Accessors(chain = true)
@ApiModel(value="文章信息查询", description="文章信息")
public class ArticleQueryDTO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "文章标题")
    private String title;

    @ApiModelProperty(value = "文章内容")
    private String content;

    @ApiModelProperty(value = "文章图片")
    private String images;

    @ApiModelProperty(value = "阅读量")
    private Integer readNumber;

    @ApiModelProperty(value = "卡片模式")
    private String mode;

}
