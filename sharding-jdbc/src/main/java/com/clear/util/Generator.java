package com.clear.util;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class Generator {
    public static void main(String[] args) {
        //1. 全局配置
        GlobalConfig config = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
//        gc.setOutputDir(projectPath + "/src/main/java");

        config.setActiveRecord(true) // 是否支持AR模式
                .setAuthor("qml") // 作者
                .setOutputDir(projectPath+"/sharding-jdbc" + "/src/main/java")
//                .setFileOverride(true)  // 文件覆盖
//                .setIdType(IdType.ID_WORKER) // 主键策略
                .setServiceName("%sService")  // 设置生成的service接口的名字的首字母是否为I
                .setMapperName("%sDao")
                .setXmlName("%sDao")
                // IEmployeeService
                .setBaseResultMap(true)
                .setBaseColumnList(true);

        //2. 数据源配置
        DataSourceConfig dsConfig  = new DataSourceConfig();
//        dsConfig.setDbType(DbType.POSTGRE_SQL)  // 设置数据库类型
//                .setDriverName("org.postgresql.Driver")
//                .setUrl("jdbc:postgresql://10.110.18.211:5432/DataCenter")
//                .setUsername("postgres")
//                .setPassword("postgres");
//测试的mysql
        dsConfig.setDbType(DbType.MYSQL)  // 设置数据库类型
                .setDriverName("com.mysql.cj.jdbc.Driver")
                .setUrl("jdbc:mysql://192.168.145.132:3306/lagou1")
                .setUsername("root")
                .setPassword("root");
        String[] split = "t_data_water_resource,t_base_water_resource".split(",");

        //3. 策略配置
        StrategyConfig stConfig = new StrategyConfig();
        stConfig.setCapitalMode(true) //全局大写命名
//                .setDbColumnUnderline(true)  // 指定表名 字段名是否使用下划线
                .setColumnNaming(NamingStrategy.underline_to_camel)
                .setNaming(NamingStrategy.underline_to_camel) // 数据库表映射到实体的命名策略
//				.setTablePrefix("tbl_")
                .setEntityLombokModel(true)
                .setInclude("position");  // 生成的表
//				.setInclude("login_info3");  // 生成的表

        //4. 包名策略配置
        PackageConfig pkConfig = new PackageConfig();
        pkConfig
                .setParent("com.clear")
                .setMapper("dao")
                .setService("service")
                .setController("controller")
                .setEntity("entity")
                .setXml("mapper");

        //5. 整合配置
        AutoGenerator ag = new AutoGenerator();
        ag.setGlobalConfig(config)
                .setDataSource(dsConfig)
                .setStrategy(stConfig)
                .setPackageInfo(pkConfig);

        //6. 执行
        ag.execute();
    }
}
