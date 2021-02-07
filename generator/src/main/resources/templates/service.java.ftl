package ${package.Service};

import ${cfg.packageParent}.domain.${cfg.packageName}.*;
<#--import ${superServiceClassPackage};-->
import com.kimzing.utils.page.PageParam;
import com.kimzing.utils.page.PageResult;
import javax.validation.constraints.NotNull;

/**
 * ${table.comment!} 服务接口
 *
 * @author ${author}
 * @since ${date}
 */
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
<#--public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {-->
public interface ${table.serviceName} {

    /**
     * 保存${table.comment!}
     */
    ${cfg.classPrefixName?cap_first}BO save(${cfg.classPrefixName?cap_first}SaveDTO ${cfg.classPrefixName}SaveDTO);

    /**
     * 移除${table.comment!}
     */
    void remove(@NotNull(message = "${table.name?upper_case}_1001") Integer id);

    /**
     * 更新${table.comment!}
     */
    void update(${cfg.classPrefixName?cap_first}UpdateDTO ${cfg.classPrefixName}UpdateDTO);

    /**
     * 查询单个${table.comment!}
     */
    ${cfg.classPrefixName?cap_first}BO get(@NotNull(message = "${table.name?upper_case}_1001") Integer id);

    /**
     * 分页条件查询${table.comment!}
     */
    PageResult<${cfg.classPrefixName?cap_first}BO> listPage(${cfg.classPrefixName?cap_first}QueryDTO ${cfg.classPrefixName}QueryDTO, PageParam pageParam);
}
</#if>
