package cn.pepper.mapper;

import java.util.List;

import cn.pepper.model.User;

public interface UserMapper {

	public int insertUserThenReturnId(User user);

	public List<User> selectAll();

	public int insertUser(User user);

	public User selectUserByUserid(int userid);
}