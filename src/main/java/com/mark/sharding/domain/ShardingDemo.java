package com.mark.sharding.domain;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.InlineShardingStrategyConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.apache.shardingsphere.shardingjdbc.api.yaml.YamlShardingDataSourceFactory;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Auther: lulei
 * @Date: 2019/9/20 18:00
 * @Description:
 */
public class ShardingDemo {


    public static DataSource getDatasource() throws SQLException {
        // 配置真实数据源
        Map<String, DataSource> dataSourceMap = new HashMap<>();

        // 配置第一个数据源
        HikariDataSource dataSource1 = new HikariDataSource();
        dataSource1.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource1.setJdbcUrl("jdbc:mysql://localhost:3306/ds0?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&autoReconnect=true");
        dataSource1.setUsername("root");
        dataSource1.setPassword("qq123456");
        dataSourceMap.put("ds0", dataSource1);

//        // 配置第二个数据源
////        HikariDataSource dataSource2 = new HikariDataSource();
////        dataSource2.setDriverClassName("com.mysql.jdbc.Driver");
////        dataSource2.setJdbcUrl("jdbc:mysql://localhost:3306/ds1?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&autoReconnect=true");
////        dataSource2.setUsername("root");
////        dataSource2.setPassword("qq123456");
////        dataSourceMap.put("ds1", dataSource2);

        // 配置Order表规则
        TableRuleConfiguration orderTableRuleConfig = new TableRuleConfiguration("t_order","ds0.t_order${0..1}");

        // 配置分库 + 分表策略
//        orderTableRuleConfig.setDatabaseShardingStrategyConfig(new InlineShardingStrategyConfiguration("orderId", "ds${orderId % 2}"));
        orderTableRuleConfig.setTableShardingStrategyConfig(new InlineShardingStrategyConfiguration("orderId", "t_order${order_id % 2}"));

        // 配置分片规则
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTableRuleConfigs().add(orderTableRuleConfig);

        // 省略配置order_item表规则...
        // ...

        // 获取数据源对象
        DataSource dataSource = ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfig, new Properties());
        return dataSource;
    }

    public static DataSource getDatasource2() throws IOException, SQLException {
        DataSource dataSource = YamlShardingDataSourceFactory.createDataSource(new File("D:/app/gitcode/sharding/src/main/resources/sharding.yml"));
        return dataSource;
    }

    public static void query() throws SQLException, IOException {
        DataSource dataSource = getDatasource2();
        String sql = "SELECT order_id,user_id,name FROM t_order WHERE  order_id=?";
        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, 1);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while(rs.next()) {
                    System.out.println(rs.getLong(1) + ":" + rs.getLong(2) + ":" + rs.getString(3));
                }
            }
        }
    }

    public static void add() throws SQLException, IOException {
//        DataSource dataSource = getDatasource();
        DataSource dataSource = getDatasource2();
        String sql = "insert into t_order(order_id,user_id,name) values(?,?,?)";
        for(long i=0; i<10; i++){
            try (
                    Connection conn = dataSource.getConnection();
                    PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setLong(1, i);
                preparedStatement.setLong(2,i);
                preparedStatement.setString(3,"name1");
                int count = preparedStatement.executeUpdate();
                System.out.println(count);
            }
        }

    }
    public static void main(String[] args) throws SQLException, IOException {
        add();
//        query();
    }
}
