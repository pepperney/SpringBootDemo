package cn.pepper.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.pepper.model.User;
import cn.pepper.service.UserService;
import cn.pepper.util.Conss;
import cn.pepper.util.MD5Util;
import cn.pepper.util.MyException;
import cn.pepper.util.ReturnData;

@RestController
public class LoginController {

	public static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private UserService userService;

	/**
	 * 
	 * @Description 1.登录
	 * @author niepei
	 * @param session
	 * @param user
	 * @return
	 * @throws MyException
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ReturnData<User> loginPost(HttpSession session, HttpServletRequest request, HttpServletResponse response,@RequestBody User user) throws MyException {
		logger.debug("------------------------------------------------------   login  has start");
		ReturnData<User> rd = new ReturnData<>();
		User existUser = userService.selectUser(user.getUsername(), user.getPassword());
		if (existUser != null) {
			// 生成token并存入cookie
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			long nowTime = new Date().getTime();
			String local_token = MD5Util.getMD5ofStr(existUser.getUsername() + "&&" + sdf.format(nowTime));
			Cookie cookie = new Cookie("req_token", local_token);
			cookie.setMaxAge(3600);
			// 设置路径，这个路径即该工程下都可以访问该cookie 如果不设置路径，那么只有设置该cookie路径及其子路径可以访问
			cookie.setPath("/");
			response.addCookie(cookie);
			// 将当前用户存入session
			session.setAttribute(session.getId(), existUser);
			rd.setCode(Conss.SUCCESS);
			rd.setMsg("login success!");
		} else {
			rd.setCode(Conss.FAIL);
			rd.setMsg("login fail!");
		}
		logger.debug("------------------------------------------------------   login  has  end");
		return rd;
	}

	/**
	 * 
	 * @Description 2.注销
	 * @author niepei
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/logout")
	public ReturnData<String> loginout(HttpServletRequest request) {
		ReturnData<String> rd = new ReturnData<String>();
		request.getSession().removeAttribute(request.getSession().getId());
		rd.setCode(Conss.SUCCESS);
		rd.setMsg("logout success！");
		return rd;
	}

	/**
	 * 
	 * @Description 3.注册
	 * @author niepei
	 * @param user
	 * @return
	 * @throws MyException
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ReturnData<User> loginPost(@RequestBody User user) throws MyException {
		logger.debug("------------------------------------------------------   register  has start");
		ReturnData<User> rd = new ReturnData<>();
		User existUser = userService.selectUser(user.getUsername(), user.getPassword());
		if (existUser == null) {
			int code = userService.addUser(user);
			rd.setCode(Conss.SUCCESS);
			rd.setMsg(code > 0 ? "register success!" : "register failed!");
		} else {
			rd.setCode(Conss.FAIL);
			rd.setMsg("current user is exist!");
		}
		logger.debug("------------------------------------------------------   register  has  end");
		return rd;
	}

}
