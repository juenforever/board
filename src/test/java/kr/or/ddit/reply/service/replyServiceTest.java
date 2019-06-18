package kr.or.ddit.reply.service;

import java.util.List;

import kr.or.ddit.reply.model.ReplyVo;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class replyServiceTest {

	private static final Logger logger = LoggerFactory
			.getLogger(replyServiceTest.class);

	private IReplyService replyService;

	@BeforeClass
	public static void BeforeClass() {
		logger.debug("beforeClass");
	}

	@Before
	public void setup() {
		logger.debug("setup");
		replyService = new ReplyService();

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
	public void replyInsertServiceTest() {
		/*** Given ***/
		// 테이블 컬럼은 댓글 아이디, 게시글 아이디, 댓글 내용, 댓글 작성시간, 댓글 작성자로 구성되어 있다.
		// 댓글 작성시간은 xml에서 sysdate로 댓글 아이디는 sequence로 들어갈 수 있도록 설정한다.
		int post_id = 1;
		String reply_content = "a";
		String user_id = "a";
		ReplyVo rvo = new ReplyVo();
		rvo.setPost_id(post_id);
		rvo.setReply_content("a");
		rvo.setUser_id("a");

		/*** When ***/
		logger.debug("replyVo : {}", rvo);
		int replyCnt = replyService.replyInsert(rvo);
		logger.debug("replyCnt : {}", replyCnt);

		/*** Then ***/
	}

	@Test
	public void replyListTest() {

		/*** Given ***/
		String post_id = "a";
		/*** When ***/
		List<ReplyVo> replyList = replyService.replyList(post_id);
		logger.debug("replyListTest replyList : {}", replyList);
		/*** Then ***/
	}

	@Test
	public void replyDeleteTest() {

		/*** Given ***/
		int reply_id = 85;
		/*** When ***/
		int deleteCnt = replyService.replyDelete(reply_id);
		/*** Then ***/
		logger.debug("deleteCnt:{}", deleteCnt);

	}

}
