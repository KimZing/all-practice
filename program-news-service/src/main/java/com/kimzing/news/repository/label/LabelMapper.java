package com.kimzing.news.repository.label;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kimzing.news.domain.label.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 标签信息 Mapper 接口
 *
 * @author KimZing
 * @since 2021-02-07
 */
@Mapper
public interface LabelMapper {

    /**
     * 保存标签信息
     */
    Integer insert(LabelPO labelPO);

    /**
     * 批量保存标签信息
     */
    void insertBatch(List<LabelPO> list);

    /**
     * 删除标签信息
     */
    void delete(Integer id);

    /**
     * 根据ID更新标签信息
     */
    void update(LabelPO labelPO);

    /**
     * 根据ID查询标签信息
     */
    LabelBO selectById(Integer id);

    /**
     * 条件分页查询标签信息
     */
    IPage<LabelBO> selectPage(Page<LabelBO> page, @Param("query") LabelQueryDTO labelQueryDTO);

    List<LabelBO> selectAll();
}
