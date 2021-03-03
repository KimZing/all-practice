package com.kimzing.news.controller.article;


import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.kimzing.web.resolver.json.JsonParam;
import com.kimzing.utils.page.PageParam;
import com.kimzing.utils.page.PageResult;
import com.kimzing.news.service.article.ArticleService;
import com.kimzing.news.domain.article.*;
import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RestController;

/**
 * 文章信息接口层.
 *
 * @author KimZing
 * @since 2021-03-03
 */
@Api(tags = "文章信息")
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Resource
    ArticleService articleService;

    @ApiOperation(value = "保存文章信息")
    @PostMapping
    public ArticleBO save(@RequestBody ArticleSaveDTO articleSaveDTO) {
        return articleService.save(articleSaveDTO);
    }

    @ApiOperation(value = "移除文章信息")
    @DeleteMapping("/{id}")
    public void remove(@PathVariable("id") Integer id) {
        articleService.remove(id);
    }

    @ApiOperation(value = "更新文章信息")
    @PutMapping
    public void update(@RequestBody ArticleUpdateDTO articleUpdateDTO) {
        articleService.update(articleUpdateDTO);
    }

    @ApiOperation(value = "查询单个文章信息")
    @GetMapping("/{id}")
    public ArticleBO get(@PathVariable("id") Integer id) {
        return articleService.get(id);
    }

    @ApiOperation(value = "分页条件查询文章信息")
    @GetMapping("/list")
    public PageResult<ArticleBO> listPage(@JsonParam(required = false) ArticleQueryDTO articleQueryDTO, @ModelAttribute PageParam pageParam) {
        return articleService.listPage(articleQueryDTO, pageParam);
    }

}
