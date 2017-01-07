package cn.pepper.service;

import java.util.List;

import cn.pepper.model.User;

public interface UserService {

	List<User> getUser();

	public boolean addUser(User user);

	public User addUserWithBackId(String username);

	User findUser(int i);

}
