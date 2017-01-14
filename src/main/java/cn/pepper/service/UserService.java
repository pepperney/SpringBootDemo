package cn.pepper.service;

import java.util.List;

import cn.pepper.model.MyException;
import cn.pepper.model.User;

public interface UserService {

	List<User> getUser()throws MyException ;

	int addUser(User user)throws MyException ;

	User findUserByUserid(int userid)throws MyException ;

	User selectUser(String username, String password)throws MyException ;



}
