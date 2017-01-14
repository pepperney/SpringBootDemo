package cn.pepper.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import cn.pepper.mapper.UserMapper;
import cn.pepper.model.MyException;
import cn.pepper.model.User;
import cn.pepper.util.Conss;
import cn.pepper.util.MD5Util;

@Service("userService")
public class UserServiceImpl implements UserService{
    
	
	public static final Logger logger =  LoggerFactory.getLogger(UserServiceImpl.class);
	
    
    @Autowired
    private UserMapper userMapper;
    
    @Override 
	public List<User> getUser() throws MyException {
		return userMapper.selectAll();
	}
    
	@Override  
	@Cacheable(value = "usercache",keyGenerator = "wiselyKeyGenerator")  
	public User findUserByUserid(int userid)throws MyException  {
		logger.debug("--------> if you have seen this,it indicated that there is no cache");
		return userMapper.selectByPrimaryKey(userid);
	}

	@Override
	public int addUser(User user) throws MyException {
		user.setPassword(MD5Util.getMD5ofStr(user.getUsername()+ "$$" + user.getPassword()));
		user.setUsertype(Conss.USER_TYPE_COMMON);
		return userMapper.insertSelective(user);
	}

	@Override
	public User selectUser(String username, String password)throws MyException  {
		password = MD5Util.getMD5ofStr(username+ "$$" + password);
		User existUser = userMapper.selectByNameAndPwd(username,password);
		logger.debug("-----------------------------------> " + existUser);
		return existUser;
	}

}