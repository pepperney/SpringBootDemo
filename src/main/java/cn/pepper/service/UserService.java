package cn.pepper.service;

import java.util.List;

import cn.pepper.model.User;

public interface UserService {

	List<User> getUser();

	int addUser(User user);

	User findUserByUserid(int userid);

	User selectUser(String username, String password);



}
