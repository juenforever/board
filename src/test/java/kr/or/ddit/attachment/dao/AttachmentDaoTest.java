package kr.or.ddit.attachment.dao;

import java.util.List;

import kr.or.ddit.attachment.model.AttachmentVo;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AttachmentDaoTest {
	private static final Logger logger = LoggerFactory
			.getLogger(AttachmentDaoTest.class);
	private IAttachmentDao attachmentDao;

	@BeforeClass
	public static void BeforeClass() {
		logger.debug("beforeClass");
	}

	@Before
	public void setup() {
		attachmentDao = new AttachmentDao();
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

	@Test
	public void AttachmentInsertTest() {
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
		int attachmentCnt = attachmentDao.attachmentInsert(attachmentVo);
		logger.debug("attachmentCnt : {}", attachmentCnt);

		/*** Then ***/

	}

	@Test
	public void attachmentDeleteTest() {
		logger.debug("attachmentDelete");
		/*** Given ***/
		int attachment_id = 1;

		/*** When ***/
		int deleteCnt = attachmentDao.attachmentDelete(attachment_id);
		logger.debug("deleteCnt:{}", deleteCnt);

		/*** Then ***/

	}

	@Test
	public void getAttachmentListTest() {

		logger.debug("getAttachmentTest");
		/*** Given ***/
		int post_id = 1;
		/*** When ***/
		List<AttachmentVo> avoList = attachmentDao.getAttachmentList(post_id);
		logger.debug("avo : {}", avoList);
		/*** Then ***/

	}

	@Test
	public void getAttachmentTest() {
		/*** Given ***/
		int attachment_id = 50;
		/*** When ***/
		attachmentDao.getAttachment(attachment_id);

		/*** Then ***/

	}
}
