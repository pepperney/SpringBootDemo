package cn.pepper.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import cn.pepper.mapper.UserMapper;
import cn.pepper.model.User;

@Service("userService")
public class UserServiceImpl implements UserService{
    
	
	public static final Logger logger =  LoggerFactory.getLogger(UserServiceImpl.class);
	
    
    @Autowired
    private UserMapper userMapper;
    
    
	public List<User> getUser() {
		return userMapper.selectAll();
	}

	public boolean addUser(User user) {
		return userMapper.insertUser(user) == 1 ? true : false;
	}

	public User addUserWithBackId(String username) {
		User user = new User();
		user.setUsername(username);
		userMapper.insertUserThenReturnId(user);
		return user;
	}

	
	@Override
	@Cacheable(value = "usercache",keyGenerator = "wiselyKeyGenerator")  
	public User findUser(int userid) {
		logger.debug("--------> if you have seen this,it indicated that there is no cache");
		return userMapper.selectUserByUserid(userid);
	}

}