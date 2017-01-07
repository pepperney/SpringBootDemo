package cn.pepper.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.pepper.model.User;
import cn.pepper.service.UserService;
import cn.pepper.util.MyConstants;
import cn.pepper.util.ReturnData;


@RestController /*springmvc的注解， 相当于@Controller与@ResponseBody的合体，可以直接返回json*/
@RequestMapping("/user")
public class UserController {

	
	public static final Logger logger =  LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	/**
	 * 获取所有用户
	 * @Description 
	 * @author niepei
	 * @return
	 */
	@RequestMapping("/getUser")
	public ReturnData<User>  getUser() {
		logger.debug("【****************     getUser    has  begin       ***************】");
		ReturnData<User> rd = new ReturnData<>();
		List<User> list = userService.getUser();
		rd.setCode(MyConstants.SUCCESS);
		rd.setMsg("success");
		rd.setList(list);
		logger.debug("【****************     getUser    has  end         ***************】");
		return rd;
	}

	/**
	 * 增加用户
	 * @Description 
	 * @author niepei
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/addUser")
	public boolean addUser(@RequestBody User user) {
		
		return userService.addUser(user);
	}

	/**
	 * 增加用户并返回用户id
	 * @Description 
	 * @author niepei
	 * @param username
	 * @return
	 */
	@RequestMapping(value = "/addUserThenReturnId", method = RequestMethod.POST)
	public ReturnData<User> addUserWithBackId(@RequestParam("username") String username) {
		logger.debug("【****************     addUserThenReturnId    has  begin       ***************】");
		ReturnData<User> rd = new ReturnData<>();
		User user = userService.addUserWithBackId(username);
		rd.setCode(MyConstants.SUCCESS);
		rd.setMsg("success");
		rd.setData(user);
		logger.debug("【****************     addUserThenReturnId    has  end         ***************】");
		return rd;
	}
}