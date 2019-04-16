package ivg.cn.sharding.jdbc.springboot.example.config;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;
import com.dangdang.ddframe.rdb.sharding.api.rule.DataSourceRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.ShardingRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.TableRule;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.DatabaseShardingStrategy;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.TableShardingStrategy;
import com.dangdang.ddframe.rdb.sharding.jdbc.core.datasource.ShardingDataSource;

import ivg.cn.sharding.jdbc.springboot.example.config.tablesharding.OrderTableShardingDBStrategy;
import ivg.cn.sharding.jdbc.springboot.example.config.tablesharding.OrderTableSingleKeyShardingAlgorithm;

@SpringBootConfiguration
public class DBConfiguration {

    @Value("${spring.datasource.url}")
    private String url;
    
    @Value("${spring.datasource.url1}")
    private String url1;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);// 用户名
        dataSource.setPassword(password);// 密码
        dataSource.setInitialSize(10);
        dataSource.setMaxActive(200);
        dataSource.setMinIdle(100);
        dataSource.setMaxWait(60000);
        dataSource.setValidationQuery("SELECT 1");
        dataSource.setTestOnBorrow(true);
        dataSource.setTestWhileIdle(true);
        dataSource.setPoolPreparedStatements(false);
        dataSource.setTimeBetweenEvictionRunsMillis(60000);
        dataSource.setMinEvictableIdleTimeMillis(300000);
        dataSource.setRemoveAbandoned(true);
        dataSource.setRemoveAbandonedTimeout(1800);
        dataSource.setDriverClassName(driverClassName);
        return dataSource;
    }
    
    public DataSource dataSource_1() {
    	DruidDataSource dataSource = new DruidDataSource();
    	dataSource.setUrl(url1);
    	dataSource.setUsername(username);// 用户名
    	dataSource.setPassword(password);// 密码
    	dataSource.setInitialSize(10);
    	dataSource.setMaxActive(200);
    	dataSource.setMinIdle(100);
    	dataSource.setMaxWait(60000);
    	dataSource.setValidationQuery("SELECT 1");
    	dataSource.setTestOnBorrow(true);
    	dataSource.setTestWhileIdle(true);
    	dataSource.setPoolPreparedStatements(false);
    	dataSource.setTimeBetweenEvictionRunsMillis(60000);
    	dataSource.setMinEvictableIdleTimeMillis(300000);
    	dataSource.setRemoveAbandoned(true);
    	dataSource.setRemoveAbandonedTimeout(1800);
    	dataSource.setDriverClassName(driverClassName);
    	return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(shardingDataSource());
//        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/cn/ivg/cscm/*/*/*/*.xml"));
        return sqlSessionFactoryBean.getObject();

    }

    @Bean
    public PlatformTransactionManager transactionManager() throws SQLException {
        return new DataSourceTransactionManager(shardingDataSource());
    }
    
    public DataSourceRule dataSourceRule() {
    	Map<String, DataSource> dataSourceMap = new HashMap<>();
    	dataSourceMap.put("ds_0", dataSource());
    	dataSourceMap.put("ds_1", dataSource_1());
		DataSourceRule dataSourceRule = new DataSourceRule(dataSourceMap);
		return dataSourceRule;
	}
    
    @Bean
    public ShardingDataSource shardingDataSource() throws SQLException {
    	ShardingDataSource source = new ShardingDataSource(shardingRule());
    	return source;
	}
    
    public ShardingRule shardingRule() {
    	
    	List<TableRule> tableRules = new ArrayList<>();
    	tableRules.add(orderTableRule());
    	
		ShardingRule shardingRule = ShardingRule.builder()
				.dataSourceRule(dataSourceRule())
				.tableRules(tableRules)
				.build();
		return shardingRule;
	}
    
    public TableRule orderTableRule() {
    	
    	List<String> actualTables = new ArrayList<>();
    	actualTables.add("t_order_0");
    	actualTables.add("t_order_1");
    	
    	OrderTableSingleKeyShardingAlgorithm algorithm = new OrderTableSingleKeyShardingAlgorithm();
    	algorithm.setTableCount(actualTables.size());
    	algorithm.setDbCount(2);
    	TableShardingStrategy tableShardingStrategy = new TableShardingStrategy("epc", algorithm);

    	OrderTableShardingDBStrategy dbStrategy = new OrderTableShardingDBStrategy();
    	dbStrategy.setDbCount(2);
    	DatabaseShardingStrategy dbShardingStrategy = new DatabaseShardingStrategy("epc", dbStrategy);
    	
		TableRule tableRule = TableRule.builder("t_order")
				.dataSourceRule(dataSourceRule())
				.actualTables(actualTables)
				.tableShardingStrategy(tableShardingStrategy)
				.databaseShardingStrategy(dbShardingStrategy)
				.build();
		return tableRule;
	}

}
