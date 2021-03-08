package com.kimzing.news.repository.article;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kimzing.news.domain.article.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 文章信息 Mapper 接口
 *
 * @author KimZing
 * @since 2021-03-04
 */
@Mapper
public interface ArticleMapper {

    /**
     * 保存文章信息
     */
    Integer insert(ArticlePO articlePO);

    /**
     * 批量保存文章信息
     */
    void insertBatch(List<ArticlePO> list);

    /**
     * 删除文章信息
     */
    void delete(Integer id);

    /**
     * 根据ID更新文章信息
     */
    void update(ArticlePO articlePO);

    /**
     * 根据ID查询文章信息
     */
    ArticleBO selectById(Integer id);

    /**
     * 条件分页查询文章信息
     */
    IPage<ArticleBO> selectPage(Page<ArticleBO> page, @Param("query") ArticleQueryDTO articleQueryDTO);

    /**
     * 查询用户对某个文章的收藏信息
     * @param articleLikeDTO
     * @return
     */
    ArticleLikeBO selectArticleLike(ArticleLikeDTO articleLikeDTO);

    /**
     * 添加用户收藏
     * @param articleLikeDTO
     */
    void addArticleLike(ArticleLikeDTO articleLikeDTO);

    /**
     * 取消用户收藏
     */
    void removeArticleLike(ArticleLikeDTO articleLikeDTO);
}
