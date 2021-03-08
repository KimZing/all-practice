package com.kimzing.news.domain.article;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 文章信息更新
 *
 * @author KimZing
 * @since 2021-03-04
 */
@Data
@Accessors(chain = true)
@ApiModel(value="文章信息更新", description="文章信息")
public class ArticleUpdateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "文章信息ID")
    private Integer id;

    @ApiModelProperty(value = "作者信息")
    private Integer userId;

    @ApiModelProperty(value = "文章标题")
    private String title;

    @ApiModelProperty(value = "文章内容")
    private String content;

    @ApiModelProperty(value = "文章图片")
    private String images;

    @ApiModelProperty(value = "分类信息")
    private String classify;

    @ApiModelProperty(value = "卡片模式")
    private String mode;

    @ApiModelProperty(value = "阅读量")
    private Integer readCount;

    @ApiModelProperty(value = "文章集合")
    private Integer collectionCount;

    @ApiModelProperty(value = "评论数")
    private Integer commentCount;

    @ApiModelProperty(value = "点赞量")
    private Integer thumbsUpCount;

}
