package com.kimzing.generator.config;

import com.baomidou.mybatisplus.generator.config.DataSourceConfig;

import static com.kimzing.generator.param.Parameters.*;

/**
 * 数据源配置.
 *
 * @author KimZing - kimzing@163.com
 * @since 2020/7/5 15:44
 */
public class DataSourceConfiguration {

    public static DataSourceConfig getDataSourceConfig() {
        DataSourceConfig dataSource = new DataSourceConfig();
        dataSource.setUrl("jdbc:mysql://" + url + "?useUnicode=true&useSSL=false&characterEncoding=utf8");
        dataSource.setDriverName("com.mysql.jdbc.Driver");
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

}
