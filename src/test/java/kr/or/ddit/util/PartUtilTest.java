package kr.or.ddit.util;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PartUtilTest {

	private static final Logger logger = LoggerFactory
			.getLogger(PartUtilTest.class);

	// @Test
	// public void getFileNameTest() {
	// /*** Given ***/
	// // String contentDisposition = "form-data; name=\"profile"\;
	// // filename=\"sally.png\";"
	// // content-disposition : form-data; name="profile"; filename="sally.png"
	// String contentDisposition1 =
	// "form-data; name=\"profile\"; filename=\"sally.png\"";
	// String[] split = contentDisposition1.split("filename=");
	// logger.debug("getFileNameTest split[1] : {}", split[1]);
	// split[1].substring("\"");
	// /*** When ***/
	// String fileName = PartUtil.getFileName(contentDisposition1);
	//
	// /*** Then ***/
	// assertEquals("sally___ddd.png", actual);
	// }

	@Test
	public void uuidTest() {
		/*** Given ***/

		/*** When ***/
		logger.debug("UUID.randomUUID().toString() : {}", UUID.randomUUID()
				.toString());

		/*** Then ***/

	}

	@Test
	public void getExtTest() {

		/*** Given ***/
		String filename = "sally.png";
		String filename2 = "sally.picture.png";
		String filename3 = "sally";
		/*** When ***/
		String ext = PartUtil.getExt(filename);
		logger.debug("ext : {}", ext);
		String ext2 = PartUtil.getExt(filename2);
		logger.debug("ext2 : {}", ext2);
		String ext3 = PartUtil.getExt(filename3);
		logger.debug("ext3 : {}", ext3);
		/*** Then ***/
		assertEquals("png", ext);
		assertEquals("png", ext2);
		assertEquals("", ext3);
	}

	@Test
	public void subStringTest() {
		/*** Given ***/
		String yyyyMM = "201906";
		/*** When ***/
		String yyyy = yyyyMM.substring(0, 4);
		String mm = yyyyMM.substring(4, 6);
		/*** Then ***/
		assertEquals("2019", yyyy);
		assertEquals("06", mm);
	}
}
