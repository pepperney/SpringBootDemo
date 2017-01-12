package cn.pepper.util;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.pepper.controller.LoginController;



//下面就是实现为数据库获取一个唯一的主键id的代码

public class UUIDGenerator {

	public static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	public UUIDGenerator() {
	}

	/**
	 * 获得一个UUID
	 * 
	 * @return String UUID
	 */
	public static String getUUID() {
		if (logger.isDebugEnabled()) {
			logger.debug("getUUID() - start"); //$NON-NLS-1$
		}

		String s = UUID.randomUUID().toString();
		// 去掉“-”符号
		String returnString = s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23)
				+ s.substring(24);
		if (logger.isDebugEnabled()) {
			logger.debug("getUUID() - end"); //$NON-NLS-1$
		}
		return returnString;
	}

	/**
	 * 获得指定数目的UUID
	 * 
	 * @param number
	 *            int 需要获得的UUID数量
	 * @return String[] UUID数组
	 */
	public static String[] getUUID(int number) {
		if (logger.isDebugEnabled()) {
			logger.debug("getUUID(int) - start"); //$NON-NLS-1$
		}

		if (number < 1) {
			if (logger.isDebugEnabled()) {
				logger.debug("getUUID(int) - end"); //$NON-NLS-1$
			}
			return null;
		}
		String[] ss = new String[number];
		for (int i = 0; i < number; i++) {
			ss[i] = getUUID();
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getUUID(int) - end"); //$NON-NLS-1$
		}
		return ss;
	}

	/*public static void main(String[] args) {
		if (logger.isDebugEnabled()) {
			logger.debug("main(String[]) - start"); //$NON-NLS-1$
		}

		String[] ss = getUUID(10);
		for (int i = 0; i < ss.length; i++) {
			System.out.println(ss[i]);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("main(String[]) - end"); //$NON-NLS-1$
		}
	}*/
}
