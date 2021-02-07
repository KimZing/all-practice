package com.kimzing.news.service.label;

import com.kimzing.news.domain.label.*;
import com.kimzing.utils.page.PageParam;
import com.kimzing.utils.page.PageResult;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 标签信息 服务接口
 *
 * @author KimZing
 * @since 2021-02-07
 */
public interface LabelService {

    /**
     * 保存标签信息
     */
    LabelBO save(LabelSaveDTO labelSaveDTO);

    /**
     * 移除标签信息
     */
    void remove(@NotNull(message = "LABEL_1001") Integer id);

    /**
     * 更新标签信息
     */
    void update(LabelUpdateDTO labelUpdateDTO);

    /**
     * 查询单个标签信息
     */
    LabelBO get(@NotNull(message = "LABEL_1001") Integer id);

    /**
     * 分页条件查询标签信息
     */
    PageResult<LabelBO> listPage(LabelQueryDTO labelQueryDTO, PageParam pageParam);

    List<LabelBO> listAllLabel();
}
