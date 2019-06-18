package kr.or.ddit.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class CookieUtilTest {

	/**
	 * Method : getCookietest 작성자 : PC01 변경이력 : Method 설명 : getCookie 테스트
	 */
	@Test
	public void getCookietest() {
		/*** Given ***/
		String cookieString = "test=testValue; rememberme=true; userId=brown";
		CookieUtil.setCookieString(cookieString);

		/*** When ***/
		// String cookieValue = CookieUtil.getCookie("userId");
		// String cookieValue1 = CookieUtil.getCookie("rememberme");
		// String cookieValue2 = CookieUtil.getCookie("test");
		// String cookieValue3 = CookieUtil.getCookie("test123123");
		String cookieValue4 = CookieUtil.getCookie("userI");

		/*** Then ***/
		// assertEquals("brown", cookieValue);
		// assertEquals("true", cookieValue1);
		// assertEquals("testValue", cookieValue2);
		// assertEquals("", cookieValue3);
		assertEquals("", cookieValue4);
	}

}
