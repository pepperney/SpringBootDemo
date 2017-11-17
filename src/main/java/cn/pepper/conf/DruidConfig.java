package cn.pepper.conf;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import org.springframework.transaction.support.TransactionTemplate;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.WebStatFilter;


@Configuration
@EnableTransactionManagement
@MapperScan("cn.pepper.mapper")
public class DruidConfig implements EnvironmentAware, TransactionManagementConfigurer {
	
	@Value("${spring.datasource.url}")
	private String url;

	@Value("${spring.datasource.username}")
	private String username;

	@Value("${spring.datasource.password}")
	private String password;

	@SuppressWarnings("unused")
	private Environment environment;

	@Bean(initMethod = "init", destroyMethod = "close")
	@ConditionalOnMissingBean
	public DataSource dataSource(){
		DruidDataSource druidDataSource = new DruidDataSource();
		//基本属性 驱动 url、user、password
		druidDataSource.setUrl(this.url);
		druidDataSource.setUsername(this.username);
		druidDataSource.setPassword(this.password);
		//配置初始化大小、最小、最大
		druidDataSource.setMinIdle(10);                        
		druidDataSource.setMaxActive(200);                     
		druidDataSource.setInitialSize(10);    
		 //配置获取连接等待超时的时间
		druidDataSource.setMaxWait(60000);         
		//配置一个连接在池中最小生存的时间,单位是毫秒
		druidDataSource.setMinEvictableIdleTimeMillis(300000);   
		//配置间隔多久才进行一次检测,检测需要关闭的空闲连接,单位是毫秒
		druidDataSource.setTimeBetweenEvictionRunsMillis(60000); 
		//默认的testWhileIdle=true,testOnBorrow=false,testOnReturn=false
		druidDataSource.setValidationQuery("SELECT 1");         
		try{
			druidDataSource.setFilters("wall,mergeStat,stat");
		}catch(SQLException e){
			e.printStackTrace();
		}
		return druidDataSource;
	}

	@Bean
	public FilterRegistrationBean druidFilterRegistrationBean(){
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new WebStatFilter());
		filterRegistrationBean.addUrlPatterns("/*");
		filterRegistrationBean.addInitParameter("profileEnable", "true");
		filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
		return filterRegistrationBean;
	}


	@Override
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	@Bean
	@ConditionalOnMissingBean
	public TransactionTemplate transactionTemplate(DataSourceTransactionManager transactionManager){
		return new TransactionTemplate(transactionManager);
	}
	@Bean
	@ConditionalOnMissingBean
	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
}