package cn.pepper.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ConfigurationProperties(prefix="user") 
 * 自动读取application.properties（是spring-boot默认查找的文件）文件中的user.*的属性
 * 在没有使用@ConfigurationProperties的情况下， 可以使用@Value("${user.id}")来一个个指定属性的值
 * 注意：如果要使用@ConfigurationProperties和@Value， 需要将该bean添加@Component， 
 * 因为在后边的对该类的使用中，需要直接将该类使用@Autowire注解注入，这样这些属性的自动注入才起作用，
 * 具体使用查看"UserServiceImpl"
 * 
 * 
 * 注意：该类就是一个普通的model，在ssm框架中我们并没有将这样的model归给spring容器去管理，
 * 在这里使用@Component注解将其交由spring容器去处理，这样在之后的使用中，就可以直接将该model注入到其使用类中。
 * 在该类上添加了@ConfigurationProperties(prefix="user")注解，
 * 这样的意思就是可以自动扫描application.properties文件相关前缀的配置，并根据名称配置到该类的每一个属性上去
 * 也可以在属性上使用@Value注解单独复值，当然前提是没有配置@ConfigurationProperties，如果配置了，@Value注解失效
 * 
 * 
 * @Description 
 * @author niepei
 * @date 2016年12月9日 上午11:33:56 
 * @version V1.3.1
 */

@Component
@ConfigurationProperties(prefix = "user")
public class User {


	private int userid;
	private String username;
	private String password;
	private String realname;
	private String email;
	private String mobile;
	private String qq;
	private String openid;
	private String nickname;
	private String headimgurl;
	private String usertype;
	private String userstatus;
	
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	public String getUserstatus() {
		return userstatus;
	}
	public void setUserstatus(String userstatus) {
		this.userstatus = userstatus;
	}

	

}