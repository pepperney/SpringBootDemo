package cn.pepper.util;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.pepper.model.MyException;

/**
 * 全局异常处理
 * @Description 
 * @author niepei
 * @date 2017年1月14日 上午11:33:44 
 * @version V1.3.1
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	// 在返回自定义相应类的情况下必须有，这是@ControllerAdvice注解的规定
	@ExceptionHandler(RuntimeException.class)
	public @ResponseBody ReturnData<RuntimeException> exceptionHandler(RuntimeException e, HttpServletResponse response) {
		ReturnData<RuntimeException> rd = new ReturnData<>();
		rd.setCode(Conss.NO);
		if (e instanceof MyException) {
			rd.setMsg(e.getMessage());
		} else {
			rd.setMsg("系统错误，请联系管理员！");
		}
		return rd;
	}

}
