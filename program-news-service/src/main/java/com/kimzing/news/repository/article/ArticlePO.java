package com.kimzing.news.repository.article;

import com.baomidou.mybatisplus.annotation.TableName;
import com.kimzing.utils.po.AbstractPO;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
 * 文章信息实体
 *
 * @author KimZing
 * @since 2021-03-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("article")
public class ArticlePO extends AbstractPO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文章标题
     */
    @TableField("title")
    private String title;

    /**
     * 文章内容
     */
    @TableField("content")
    private String content;

    /**
     * 文章图片
     */
    @TableField("images")
    private String images;

    /**
     * 阅读量
     */
    @TableField("read_number")
    private Integer readNumber;

    /**
     * 卡片模式
     */
    @TableField("mode")
    private String mode;

}
