package kr.or.ddit.post.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.paging.model.PageVo;
import kr.or.ddit.post.model.PostVo;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PostDaoTest {

	private static final Logger logger = LoggerFactory
			.getLogger(PostDaoTest.class);
	private IPostDao postDao;

	@BeforeClass
	public static void BeforeClass() {
		logger.debug("beforeClass");
	}

	@Before
	public void setup() {
		postDao = new PostDao();

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
	public void postListTest() {

		/*** Given ***/
		String board_id = "1";
		/*** When ***/
		List<PostVo> postVo = postDao.postList(board_id);
		logger.debug("postListTest postVo : {}", postVo);
		/*** Then ***/
	}

	@Test
	public void postPagingListTest() {
		/*** Given ***/
		PageVo pageVo = new PageVo(1, 10);
		int page = pageVo.getPage();
		int pageSize = pageVo.getPageSize();
		int board_id = 41;
		Map<String, Object> map = new HashMap<>();
		map.put("page", page);
		map.put("pageSize", pageSize);
		map.put("board_id", board_id);

		/*** When ***/
		List<PostVo> postList = postDao.postPagingList(map);
		logger.debug("postList:{}", postList);
		/*** Then ***/
		// assertNotNull(postList);
		// assertEquals(10, postList.size());

	}

	@Test
	public void postInsertTest() {

		logger.debug("postInsertTest");
		/*** Given ***/
		// 제목
		String post_title = "b";
		// 글내용
		String post_content = "a";
		// 첨부파일
		int board_id = 1;

		String user_id = "brown";
		PostVo postVo = new PostVo();
		postVo.setPost_title(post_title);
		postVo.setPost_content(post_content);
		postVo.setBoard_id(board_id);
		postVo.setUser_id(user_id);

		/*** When ***/
		logger.debug("postVo : {}", postVo);
		int postCnt = postDao.postInsert(postVo);
		logger.debug("postCnt : {}", postCnt);
		/*** Then ***/
	}

	@Test
	public void post1InsertTest() {

		logger.debug("postInsertTest");
		/*** Given ***/
		// 제목
		String post_title = "post_title";
		// 글내용
		String post_content = "post_content";
		// 첨부파일
		String board_id = "board_id";

		String user_id = "user_id";
		int re_step = 1;
		int re_level = 1;
		PostVo postVo = new PostVo();
		postVo.setPost_title(post_title);
		postVo.setPost_content(post_content);
		postVo.setBoard_id(board_id);
		postVo.setUser_id(user_id);
		postVo.setRe_step(re_step);
		postVo.setRe_level(re_level);

		/*** When ***/
		logger.debug("postVo : {}", postVo);
		int postCnt = postDao.postInsert(postVo);
		logger.debug("postCnt : {}", postCnt);
		/*** Then ***/
	}

	@Test
	public void getPostTest() {
		logger.debug("getPostTest");
		/*** Given ***/
		int post_id = 115;

		/*** When ***/
		PostVo postVo = postDao.getPost(post_id);
		/*** Then ***/
		logger.debug("getPostTest postVo : {}", postVo);
	}

	@Test
	public void postModifyTest() {
		logger.debug("postModifyTest");
		PostVo pvo = new PostVo();
		/*** Given ***/
		String post_title = "aadfasdfasdf";
		String post_content = "adfasdf";
		String post_id = "post2";
		pvo.setPost_id(post_id);
		pvo.setPost_title(post_title);
		pvo.setPost_content(post_content);

		/*** When ***/
		int modifyCnt = postDao.postModify(pvo);
		/*** Then ***/
		logger.debug("modifyCnt:{}", modifyCnt);

	}

	@Test
	public void postDeleteTest() {
		/*** Given ***/
		int post_id = 1;

		/*** When ***/
		int deleteCnt = postDao.postDelete(post_id);

		/*** Then ***/
		logger.debug("deleteCnt:{}", deleteCnt);
	}

	@Test
	public void getLatestPostTest() {

		/*** Given ***/

		/*** When ***/
		PostVo postVo = postDao.getLatestPost();

		/*** Then ***/
		logger.debug("postVo:{}", postVo);
	}

	@Test
	public void postReplyTest() {
		/*** Given ***/
		PostVo post1Vo = new PostVo();
		post1Vo.setPost_title("post_title");
		post1Vo.setPost_content("post_content");
		post1Vo.setBoard_id(1);
		post1Vo.setUser_id("brown");
		post1Vo.setRef(1);
		post1Vo.setRe_step(1);
		post1Vo.setRe_level(1);
		/*** When ***/
		int insertCnt = postDao.postReply(postVo);
		/*** Then ***/

	}

	@Test
	public void postUpdateStep() {

		/*** Given ***/
		PostVo post1Vo = new PostVo();
		post1Vo.setRef(1);
		post1Vo.setRe_step(2);
		/*** When ***/
		int updateCnt = postDao.updateStep(post1Vo);
		logger.debug("updateCnt", updateCnt);
		/*** Then ***/

	}

	@Test
	public void postCntTest() {
		/*** Given ***/
		int board_id = 1;
		/*** When ***/
		int postCnt = postDao.postCnt(board_id);
		logger.debug("postCnt:{}", postCnt);
		/*** Then ***/

	}
}
