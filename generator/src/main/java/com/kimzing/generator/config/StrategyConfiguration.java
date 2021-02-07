package com.kimzing.generator.config;

import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.kimzing.utils.po.AbstractPO;

import static com.kimzing.generator.param.Parameters.superEntityColumns;
import static com.kimzing.generator.param.Parameters.table;

/**
 * 策略配置.
 *
 * @author KimZing - kimzing@163.com
 * @since 2020/7/5 15:43
 */
public class StrategyConfiguration {

    public static StrategyConfig getStrategyConfig() {
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setSuperEntityClass(AbstractPO.class);
        //TODO 逻辑删除， 另外生成api和服务的包要分开
        // strategy.setLogicDeleteFieldName()
        strategy.setEntityLombokModel(true);
        // 写于父类中的公共字段
        strategy.setSuperEntityColumns(superEntityColumns);
        strategy.setInclude(table);
        strategy.setChainModel(true);
        strategy.setEntityTableFieldAnnotationEnable(true);
        strategy.setRestControllerStyle(true);
        return strategy;
    }

}
