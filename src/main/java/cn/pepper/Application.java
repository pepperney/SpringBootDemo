package cn.pepper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


 


/**
 * 
 * 
 * 注意：
 * 主类要位于根包路径下（例如，cn.pepper），这是推荐做法，方便扫描
 * 每一个jar（即每一个子项目）都要有一个主方法，用于启动该jar（也就是一个微服务）
 * 在主类上添加注解@SpringBootApplication，该注解相当于添加了如下三个注解
 * 
 * @Configuration：该注解指明该类由spring容器管理
 * @EnableAutoConfiguration：该注解是无xml配置启动的关键部分
 * @ComponentScan：该注解指定扫描包（如果主类不是位于根路径下，这里需要指定扫描路径），类似于spring的包扫描注解
 * 
 * 
 * @Description
 * @author niepei
 * @date 2016年12月9日 上午11:20:06
 * @version V1.3.1
 */
@ComponentScan
@SpringBootApplication  
@EnableAutoConfiguration
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
