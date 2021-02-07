<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${package.Mapper}.${table.mapperName}">
<#--  PO属性列表  -->
<#if enableCache>
    <!-- 开启二级缓存 -->
    <cache type="org.mybatis.caches.ehcache.LoggingEhcache"/>
</#if>
<#if baseColumnList>
    <sql id="all_column"><#list table.commonFields as field>${field.columnName},</#list>${table.fieldNames?replace(" ","")}</sql>
</#if>
<#if baseColumnList>
    <sql id="po_column">${table.fieldNames?replace(" ","")}</sql>
</#if>

    <insert id="insert" useGeneratedKeys="true" keyColumn="id" keyProperty="id">
        INSERT INTO `${table.name}`(<include refid="po_column"></include>)
        VALUES(<#list table.fields as field><#noparse>#</#noparse>{${field.propertyName}}<#if field_has_next>,</#if></#list>);
    </insert>

    <insert id="insertBatch">
        INSERT INTO `${table.name}` (<include refid="po_column"></include>) VALUES
        <foreach item="item" collection="list" separator=",">
            (<#list table.fields as field><#noparse>#</#noparse>{item.${field.propertyName}}<#if field_has_next>,</#if></#list>)
        </foreach>
    </insert>

    <update id="update">
        UPDATE `${table.name}`
        <set>
            <#list table.fields as field>
            <if test="${field.propertyName} != null">${field.columnName} = <#noparse>#</#noparse>{${field.propertyName}},</if>
            </#list>
        </set>
        WHERE id = <#noparse>#</#noparse>{id} AND deleted = 0;
    </update>

    <delete id="delete">
        UPDATE `${table.name}` SET deleted = 1 WHERE id = <#noparse>#</#noparse>{id}
    </delete>

    <select id="selectById" resultType="${cfg.packageParent}.domain.${cfg.packageName}.${cfg.classPrefixName?cap_first}BO">
        SELECT <include refid="all_column"></include> FROM `${table.name}` WHERE id = <#noparse>#</#noparse>{id} AND deleted = 0
    </select>

    <select id="selectPage" resultType="${cfg.packageParent}.domain.${cfg.packageName}.${cfg.classPrefixName?cap_first}BO">
        SELECT <include refid="all_column"></include> FROM `${table.name}`
        <where>
            AND deleted = 0
            <if test="query != null">
            <#list table.fields as field>
                <if test="query.${field.propertyName} != null">AND ${field.columnName} = <#noparse>#</#noparse>{query.${field.propertyName}}</if>
            </#list>
            </if>
        </where>
    </select>

<#if baseResultMap>
    <resultMap id="BaseResultMap" type="${package.Entity}.${entity}">
<#list table.fields as field>
<#if field.keyFlag><#--生成主键排在第一位-->
        <id column="${field.name}" property="${field.propertyName}" />
</#if>
</#list>
<#list table.commonFields as field><#--生成公共字段 -->
        <result column="${field.name}" property="${field.propertyName}" />
</#list>
<#list table.fields as field>
<#if !field.keyFlag><#--生成普通字段 -->
        <result column="${field.name}" property="${field.propertyName}" />
</#if>
</#list>
    </resultMap>

</#if>
</mapper>
