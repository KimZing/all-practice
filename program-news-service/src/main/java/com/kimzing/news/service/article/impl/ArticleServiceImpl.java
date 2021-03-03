package com.kimzing.news.service.article.impl;

import com.kimzing.news.repository.article.ArticleMapper;
import com.kimzing.news.service.article.ArticleService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kimzing.news.repository.article.ArticlePO;
import com.kimzing.news.domain.article.*;
import com.kimzing.utils.bean.BeanUtil;
import com.kimzing.utils.page.PageParam;
import com.kimzing.utils.page.PageResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static com.kimzing.utils.page.MPPageUtil.convertPage;
import static com.kimzing.utils.page.MPPageUtil.convertPageResult;

import javax.annotation.Resource;

/**
 * 文章信息 服务实现类
 *
 * @author KimZing
 * @since 2021-03-03
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Resource
    ArticleMapper articleMapper;

    /**
     * 保存文章信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ArticleBO save(ArticleSaveDTO articleSaveDTO) {
        ArticlePO articlePO = BeanUtil.mapperBean(articleSaveDTO, ArticlePO.class);
        articleMapper.insert(articlePO);
        return BeanUtil.mapperBean(articlePO, ArticleBO.class);
    }

    /**
     * 移除文章信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(Integer id) {
        articleMapper.delete(id);
    }

    /**
     * 更新文章信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ArticleUpdateDTO articleUpdateDTO) {
        ArticlePO articlePO = BeanUtil.mapperBean(articleUpdateDTO, ArticlePO.class);
        articleMapper.update(articlePO);
    }

    /**
     * 查询单个文章信息
     */
    @Override
    public ArticleBO get(Integer id) {
        return articleMapper.selectById(id);
    }

    /**
     * 分页条件查询文章信息
     */
    @Override
    @Transactional(readOnly = true)
    public PageResult<ArticleBO> listPage(ArticleQueryDTO articleQueryDTO, PageParam pageParam) {
        IPage<ArticleBO> articleBOPage = articleMapper.selectPage(convertPage(pageParam), articleQueryDTO);
        return convertPageResult(articleBOPage);
    }

}
