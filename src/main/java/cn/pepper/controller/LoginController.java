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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import cn.pepper.model.MyException;
import cn.pepper.model.User;
import cn.pepper.service.UserService;
import cn.pepper.util.Conss;
import cn.pepper.util.MD5Util;
import cn.pepper.util.ReturnData;

@RestController
@RequestMapping(value = "/index")
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
	@RequestMapping(value = "/login")
	public ModelAndView login(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws MyException {
		logger.debug("------------------------------------------------------   login  has start");
		String url = request.getRequestURL().toString();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User existUser = userService.selectUser(username,password);
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
			url = url.replace("index/login", "static/html/main.html");
		} else {
			url = url.replace("index/login", "static/html/error.html?key=-1");
		}
		logger.debug("------------------------------------------------------   login  has  end");
		return new ModelAndView("redirect:"+url);
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
		logger.debug("------------------------------------------------------   logout  has start");
		ReturnData<String> rd = new ReturnData<String>();
		request.getSession().removeAttribute(request.getSession().getId());
		rd.setCode(Conss.NO);
		rd.setMsg("logout success！");
		logger.debug("------------------------------------------------------   logout  has end");
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
	@RequestMapping(value = "/register")
	public ReturnData<User> register(@RequestBody User user) throws MyException {
		logger.debug("------------------------------------------------------   register  has start");
		ReturnData<User> rd = new ReturnData<>();
		User existUser = userService.selectUser(user.getUsername(), user.getPassword());
		if (existUser == null) {
			int code = userService.addUser(user);
			rd.setCode(Conss.NO);
			rd.setMsg(code > 0 ? "register success!" : "register failed!");
		} else {
			rd.setCode(Conss.NO);
			rd.setMsg("current user is exist!");
		}
		logger.debug("------------------------------------------------------   register  has  end");
		return rd;
	}

}
