package cn.pepper.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

import cn.pepper.model.MyException;
import cn.pepper.model.User;

/**
 * 拦截器
 * @Description 
 * @author niepei
 * @date 2017年1月14日 上午11:55:37 
 * @version V1.3.1
 */
public class MyInterceptor implements HandlerInterceptor {

	private static final Logger logger = Logger.getLogger(MyInterceptor.class);

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws MyException, IOException {
	
		boolean flag = false;
		String req_token = null;
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("req_token")) {
				req_token = cookie.getValue().toString();
			}
		}
		if (req_token == null) {
			logger.debug("----------------------------------> req_token  is  null!");
			this.returnErrorInfo(response);
		} else {
			User user = (User) request.getSession().getAttribute(request.getSession().getId());
			if (null != user) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				long nowTime = new Date().getTime();
				String local_token = MD5Util.getMD5ofStr(user.getUsername() + "&&" + sdf.format(nowTime));
				if (req_token.equals(local_token)) {
					flag = true;
				} else {
					logger.debug("----------------------------------> req_token isn't matched!");
					this.returnErrorInfo(response);
				}
			}else{
				logger.debug("----------------------------------> can't find user in session!");
				this.returnErrorInfo(response);
			}
			
		}
		return flag;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

	private void returnErrorInfo(HttpServletResponse response) throws IOException {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("code", -1);
		jsonObj.put("desc", "please login ！");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.append(jsonObj.toString());
	}

}
