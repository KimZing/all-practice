package com.kimzing.news.service.label.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kimzing.news.domain.label.LabelBO;
import com.kimzing.news.domain.label.LabelQueryDTO;
import com.kimzing.news.domain.label.LabelSaveDTO;
import com.kimzing.news.domain.label.LabelUpdateDTO;
import com.kimzing.news.repository.label.LabelMapper;
import com.kimzing.news.repository.label.LabelPO;
import com.kimzing.news.service.label.LabelService;
import com.kimzing.utils.bean.BeanUtil;
import com.kimzing.utils.page.PageParam;
import com.kimzing.utils.page.PageResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

import static com.kimzing.utils.page.MPPageUtil.convertPage;
import static com.kimzing.utils.page.MPPageUtil.convertPageResult;

/**
 * 标签信息 服务实现类
 *
 * @author KimZing
 * @since 2021-02-07
 */
@Service
public class LabelServiceImpl implements LabelService {

    @Resource
    LabelMapper labelMapper;

    /**
     * 保存标签信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public LabelBO save(LabelSaveDTO labelSaveDTO) {
        LabelPO labelPO = BeanUtil.mapperBean(labelSaveDTO, LabelPO.class);
        labelMapper.insert(labelPO);
        return BeanUtil.mapperBean(labelPO, LabelBO.class);
    }

    /**
     * 移除标签信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(Integer id) {
        labelMapper.delete(id);
    }

    /**
     * 更新标签信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(LabelUpdateDTO labelUpdateDTO) {
        LabelPO labelPO = BeanUtil.mapperBean(labelUpdateDTO, LabelPO.class);
        labelMapper.update(labelPO);
    }

    /**
     * 查询单个标签信息
     */
    @Override
    public LabelBO get(Integer id) {
        return labelMapper.selectById(id);
    }

    /**
     * 分页条件查询标签信息
     */
    @Override
    @Transactional(readOnly = true)
    public PageResult<LabelBO> listPage(LabelQueryDTO labelQueryDTO, PageParam pageParam) {
        IPage<LabelBO> labelBOPage = labelMapper.selectPage(convertPage(pageParam), labelQueryDTO);
        return convertPageResult(labelBOPage);
    }

    @Override
    public List<LabelBO> listAllLabel() {
        return labelMapper.selectAll();
    }

}
