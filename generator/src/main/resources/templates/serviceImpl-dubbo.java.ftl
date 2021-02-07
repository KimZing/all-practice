package ${package.ServiceImpl};

import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
<#--import ${superServiceImplClassPackage};-->
import com.baomidou.mybatisplus.core.metadata.IPage;
import ${package.Entity}.${entity};
import ${cfg.packageParent}.domain.${cfg.packageName}.*;
import com.kimzing.utils.bean.BeanUtil;
import com.kimzing.utils.page.PageParam;
import com.kimzing.utils.page.PageResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static com.kimzing.utils.page.MPPageUtil.convertPage;
import static com.kimzing.utils.page.MPPageUtil.convertPageResult;

import javax.annotation.Resource;

/**
 * ${table.comment!} 服务实现类
 *
 * @author ${author}
 * @since ${date}
 */
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
@Service
<#--public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {-->
public class ${table.serviceImplName} implements ${table.serviceName} {

    @Resource
    ${table.mapperName} ${table.mapperName?uncap_first};

    /**
     * 保存${table.comment!}
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ${cfg.classPrefixName?cap_first}BO save(${cfg.classPrefixName?cap_first}SaveDTO ${cfg.classPrefixName}SaveDTO) {
        ${entity} ${entity?uncap_first} = BeanUtil.mapperBean(${cfg.classPrefixName}SaveDTO, ${entity}.class);
        ${table.mapperName?uncap_first}.insert(${entity?uncap_first});
        return BeanUtil.mapperBean(${entity?uncap_first}, ${cfg.classPrefixName?cap_first}BO.class);
    }

    /**
     * 移除${table.comment!}
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(Integer id) {
        ${table.mapperName?uncap_first}.delete(id);
    }

    /**
     * 更新${table.comment!}
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(${cfg.classPrefixName?cap_first}UpdateDTO ${cfg.classPrefixName}UpdateDTO) {
        ${entity} ${entity?uncap_first} = BeanUtil.mapperBean(${cfg.classPrefixName}UpdateDTO, ${entity}.class);
        ${table.mapperName?uncap_first}.update(${entity?uncap_first});
    }

    /**
     * 查询单个${table.comment!}
     */
    @Override
    public ${cfg.classPrefixName?cap_first}BO get(Integer id) {
        return ${table.mapperName?uncap_first}.selectById(id);
    }

    /**
     * 分页条件查询${table.comment!}
     */
    @Override
    @Transactional(readOnly = true)
    public PageResult<${cfg.classPrefixName?cap_first}BO> listPage(${cfg.classPrefixName?cap_first}QueryDTO ${cfg.classPrefixName}QueryDTO, PageParam pageParam) {
        IPage<${cfg.classPrefixName?cap_first}BO> ${cfg.classPrefixName}BOPage = ${table.mapperName?uncap_first}.selectPage(convertPage(pageParam), ${cfg.classPrefixName}QueryDTO);
        return convertPageResult(${cfg.classPrefixName}BOPage);
    }

}
</#if>
