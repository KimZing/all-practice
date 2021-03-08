package com.kimzing.news.domain.article;

import com.kimzing.utils.po.AbstractBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 文章收藏状态
 *
 * @author KimZing
 * @since 2021-03-04
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="文章收藏状态", description="收藏的状态")
public class ArticleLikeDTO extends AbstractBO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @ApiModelProperty(value = "文章ID")
    private Integer articleId;

}
