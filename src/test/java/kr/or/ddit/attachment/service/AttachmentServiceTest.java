package kr.or.ddit.attachment.service;

import java.util.List;

import kr.or.ddit.attachment.model.AttachmentVo;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AttachmentServiceTest {
	private static final Logger logger = LoggerFactory
			.getLogger(AttachmentServiceTest.class);
	private IAttachmentService attachmentService;

	@BeforeClass
	public static void BeforeClass() {
		logger.debug("beforeClass");
	}

	@Before
	public void setup() {
		attachmentService = new AttachmentService();
		logger.debug("setup");
	}

	@After
	public void teardown() {
		logger.debug("teardown");
	}

	@AfterClass
	public static void afterClass() {
		logger.debug("afterClass");
	}

	// @Test
	// public void AttachmentInsertTest() {
	// /*** Given ***/
	// AttachmentVo attachmentVo = new AttachmentVo();
	// String post_id = "a";
	// String attachment_path = "a";
	// String attachment_name = "a";
	// attachmentVo.setPost_id("a");
	// attachmentVo.setAttachment_path("a");
	// attachmentVo.setAttachment_name("a");
	// logger.debug("attachmentVo : {}", attachmentVo);
	// /*** When ***/
	// int attachmentCnt = attachmentDao.attachmentInsert(attachmentVo);
	// // logger.debug("attachmentCnt : {}", attachmentCnt);
	//
	// /*** Then ***/
	//
	// }

	// @Test
	// public void attachmentDeleteTest() {
	// logger.debug("attachmentDelete");
	// /*** Given ***/
	// String attachment_id = "attachment1";
	//
	// /*** When ***/
	// int deleteCnt = attachmentDao.attachmentDelete(attachment_id);
	// logger.debug("deleteCnt:{}", deleteCnt);
	//
	// /*** Then ***/
	//
	// }

	@Test
	public void getAttachmentListTest() {

		logger.debug("getAttachmentTest");
		/*** Given ***/
		String post_id = "a";
		/*** When ***/
		List<AttachmentVo> avoList = attachmentService
				.getAttachmentList(post_id);
		logger.debug("avo : {}", avoList);
		/*** Then ***/

	}

	@Test
	public void AttachmentInsertServiceTest() {
		/*** Given ***/
		AttachmentVo attachmentVo = new AttachmentVo();
		int post_id = 17;
		String attachment_path = "a";
		String attachment_name = "a";
		attachmentVo.setPost_id(post_id);
		attachmentVo.setAttachment_path("a");
		attachmentVo.setAttachment_name("a");
		logger.debug("attachmentVo : {}", attachmentVo);
		/*** When ***/
		int attachmentCnt = attachmentService.attachmentInsert(attachmentVo);
		logger.debug("attachmentCnt : {}", attachmentCnt);

		/*** Then ***/

	}

	@Test
	public void attchmentDeleteServiceTest() {
		/*** Given ***/
		int attchment_id = 72;
		/*** When ***/
		int attchmentCnt = attachmentService.attchmentDelete(attchment_id);
		logger.debug("attachmentDelete attachmetCnt : {}", attchmentCnt);
		/*** Then ***/

	}

	@Test
	public void getAttachmentTest() {
		/*** Given ***/
		int attachment_id = 50;
		/*** When ***/
		attachmentService.getAttachment(attachment_id);

		/*** Then ***/

	}

}
