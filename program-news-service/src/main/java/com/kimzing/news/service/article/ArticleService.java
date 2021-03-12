package com.kimzing.news.service.article;

import com.kimzing.news.domain.article.*;
import com.kimzing.utils.page.PageParam;
import com.kimzing.utils.page.PageResult;

import javax.validation.constraints.NotNull;

/**
 * 文章信息 服务接口
 *
 * @author KimZing
 * @since 2021-03-04
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


    /**
     * 根据关键词分页搜索文章信息
     *
     * @param search
     * @param userId
     * @param pageParam
     * @return
     */
    PageResult<ArticleBO> searchWithPage(String search, Integer userId, PageParam pageParam);

    /**
     * 更改文章收藏状态
     *
     * @param articleLikeDTO
     */
    void updateArticleLikeStatus(ArticleLikeDTO articleLikeDTO);

    /**
     * 查询所有已收藏的文章
     * @param userId
     * @param pageParam
     * @return
     */
    PageResult<ArticleBO> listLikeWithPage(Integer userId, PageParam pageParam);
}
