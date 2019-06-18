package kr.or.ddit.util;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CookieUtil {

	private static final Logger logger = LoggerFactory
			.getLogger(CookieUtil.class);

	public static String cookie;

	/**
	 * Method : setCookieString 작성자 : PC01 변경이력 :
	 * 
	 * @param cookie
	 *            Method 설명 : 쿠키 문자열에서 특정 쿠키 값을 가져온다
	 */

	public static void setCookieString(String cookie) {
		// "userId = brown; rememberme=true; test=testValue";
		// cookie = "userId"
		CookieUtil.cookie = cookie;
	}

	public static String getCookie(String cookie) {
		// cookieArray[0] = "test=testValue"
		// cookieArray[1] = "remembermHe=true"
		// cookieArray[2] = "userId=brown"
		String[] cookieArray = CookieUtil.cookie.split("; ");
		String cookieValue = "";
		for (String str : cookieArray) {
			logger.debug("str : {}", str);
			logger.debug("cookie");
			if (str.startsWith((cookie + "="))) {
				String[] cookieStr = str.split("=");
				cookieValue = cookieStr[1];
				break;
				// for (String str2 : cookieStr) {
				// logger.debug("cookieStrg : {}", str2);
				// cookieStr[0] = "userId"
				// cookieStr[1] = "true"
			}
		}
		return cookieValue;
	}
	// public static String getCookie(String cookie12) {
	//
	// cookie = CookieUtil.cookie;
	// cookie = cookie + " ";
	// String[] cookie1 = cookie.split(";");
	// String key;
	// String value;
	// Map<String, String> map = new HashMap<String, String>();
	//
	// for (int i = 0; i < cookie1.length; i++) {
	// cookie1[i].indexOf("=");
	// System.out.println(cookie1[i].indexOf("="));
	// key = (cookie1[i].substring(0, cookie1[i].indexOf("="))).trim();
	// value = (cookie1[i].substring(cookie1[i].indexOf("=") + 1,
	// cookie1[i].length())).trim();
	// logger.debug(key);
	// logger.debug(value);
	// map.put(key, value);
	// }
	// logger.debug(map.get(cookie12));
	// if (map.get(cookie12) == null) {
	// return "";
	// } else
	// return map.get(cookie12);
	// }
}
