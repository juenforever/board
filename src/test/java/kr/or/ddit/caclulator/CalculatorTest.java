//package kr.or.ddit.caclulator;
//
//import static org.junit.Assert.assertArrayEquals;
//import static org.junit.Assert.assertEquals;
//
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import kr.or.ddit.calculator.Calculator;
//
//import org.junit.Test;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//public class CalculatorTest {
//
//	private static final Logger logger = LoggerFactory
//			.getLogger(CalculatorTest.class);
//
//	@Test
//	public void empty_test() {
//		/*** Given ***/
//		Calculator cal = new Calculator();
//
//		String text = "";
//
//		/*** When ***/
//		int result = cal.calculate(text);
//		/*** Then ***/
//		assertEquals(0, result);
//	}
//
//	@Test
//	public void null_test() {
//		/*** Given ***/
//		Calculator cal = new Calculator();
//		String text = null;
//
//		/*** When ***/
//		int result = cal.calculate(text);
//		/*** Then ***/
//		assertEquals(0, result);
//	}
//
//	@Test
//	public void defaultSeperatorTest() {
//		/*** Given ***/
//		Calculator cal = new Calculator();
//		String text = "1,5:10,20";
//		/*** When ***/
//		int result = cal.calculate(text);
//		/*** Then ***/
//		assertEquals(36, result);
//	}
//
//	// @Test
//	// public void seperatorTest() {
//	// String text = "1,10:20";
//	// String[] textArray = text.split(",|:");
//	// assertArrayEquals("1", textArray.length);
//	// assertArrayEquals("3", textArray[0]);
//	// }
//
//	@Test
//	public void regexpCaptureTest() {
//		/*** Given ***/
//		String text = "//;\n1;5;10;20";
//		/*** When ***/
//		Matcher m = Pattern.compile("//(.)\n(.*)").matcher(text);
//		m.find();
//		String customSeperator = m.group(1);
//		String textNumbers = m.group(2);
//
//		/*** Then ***/
//		assertEquals(";", customSeperator);
//		assertEquals("1;5;10;20", textNumbers);
//	}
//
//	@Test
//	public void customSeperatorTest() {
//		/*** Given ***/
//		String text = "//;\n1;5;10;20";
//		Calculator cal = new Calculator();
//		/*** When ***/
//		int result = cal.calculate(text);
//		/*** Then ***/
//		assertEquals(36, result);
//
//	}
//}
