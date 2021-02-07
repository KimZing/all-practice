package com.kimzing.news.controller.label;


import com.kimzing.news.domain.label.LabelBO;
import com.kimzing.news.domain.label.LabelQueryDTO;
import com.kimzing.news.domain.label.LabelSaveDTO;
import com.kimzing.news.domain.label.LabelUpdateDTO;
import com.kimzing.news.service.label.LabelService;
import com.kimzing.utils.page.PageParam;
import com.kimzing.utils.page.PageResult;
import com.kimzing.web.resolver.json.JsonParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 标签信息接口层.
 *
 * @author KimZing
 * @since 2021-02-07
 */
@Api(tags = "标签信息")
@RestController
@RequestMapping("/label")
public class LabelController {

    @Resource
    LabelService labelService;

    @ApiOperation(value = "保存标签信息")
    @PostMapping
    public LabelBO save(@RequestBody LabelSaveDTO labelSaveDTO) {
        return labelService.save(labelSaveDTO);
    }

    @ApiOperation(value = "移除标签信息")
    @DeleteMapping("/{id}")
    public void remove(@PathVariable("id") Integer id) {
        labelService.remove(id);
    }

    @ApiOperation(value = "更新标签信息")
    @PutMapping
    public void update(@RequestBody LabelUpdateDTO labelUpdateDTO) {
        labelService.update(labelUpdateDTO);
    }

    @ApiOperation(value = "查询单个标签信息")
    @GetMapping("/{id}")
    public LabelBO get(@PathVariable("id") Integer id) {
        return labelService.get(id);
    }

    @ApiOperation(value = "分页条件查询标签信息")
    @GetMapping("/list")
    public PageResult<LabelBO> listPage(@JsonParam(required = false) LabelQueryDTO labelQueryDTO, @ModelAttribute PageParam pageParam) {
        return labelService.listPage(labelQueryDTO, pageParam);
    }

    @ApiOperation(value = "查询所有标签信息")
    @GetMapping("/all")
    public List<LabelBO> listAllLabel() {
        return labelService.listAllLabel();
    }

}
