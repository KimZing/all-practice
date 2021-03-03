package com.kimzing.news.service.article;

import com.kimzing.news.domain.article.*;
import com.kimzing.utils.page.PageParam;
import com.kimzing.utils.page.PageResult;
import javax.validation.constraints.NotNull;

/**
 * 文章信息 服务接口
 *
 * @author KimZing
 * @since 2021-03-03
 */
public interface ArticleService {

    /**
     * 保存文章信息
     */
    ArticleBO save(ArticleSaveDTO articleSaveDTO);

    /**
     * 移除文章信息
     */
    void remove(@NotNull(message = "ARTICLE_1001") Integer id);

    /**
     * 更新文章信息
     */
    void update(ArticleUpdateDTO articleUpdateDTO);

    /**
     * 查询单个文章信息
     */
    ArticleBO get(@NotNull(message = "ARTICLE_1001") Integer id);

    /**
     * 分页条件查询文章信息
     */
    PageResult<ArticleBO> listPage(ArticleQueryDTO articleQueryDTO, PageParam pageParam);
}
