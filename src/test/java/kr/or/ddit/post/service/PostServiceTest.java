package kr.or.ddit.post.service;

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

public class PostServiceTest {

	private static final Logger logger = LoggerFactory
			.getLogger(PostServiceTest.class);
	private IPostService postService;

	@BeforeClass
	public static void BeforeClass() {
		logger.debug("beforeClass");
	}

	@Before
	public void setup() {
		postService = new PostService();
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
		int board_id = 1;
		/*** When ***/
		List<PostVo> postVo = postService.postList(board_id);
		logger.debug("postListTest postVo : {}", postVo);
		/*** Then ***/

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

		String user_id = "a";
		PostVo postVo = new PostVo();
		postVo.setPost_title(post_title);
		postVo.setPost_content(post_content);
		postVo.setBoard_id(board_id);
		postVo.setUser_id(user_id);

		/*** When ***/
		logger.debug("postVo : {}", postVo);
		int postCnt = postService.postInsert(postVo);
		logger.debug("postCnt : {}", postCnt);
		/*** Then ***/

	}

	@Test
	public void getPostTest() {
		logger.debug("getPostTest");
		/*** Given ***/
		int post_id = 115;
		/*** When ***/
		PostVo postVo = postService.getPost(post_id);
		/*** Then ***/
		logger.debug("getPostTest postVo : {}", postVo);
	}

	@Test
	public void postDeleteTest() {
		/*** Given ***/
		int post_id = 2;

		/*** When ***/
		int deleteCnt = postService.postDelete(post_id);

		/*** Then ***/
		logger.debug("deleteCnt:{}", deleteCnt);
	}

	@Test
	public void getLatestPostTest() {

		/*** Given ***/

		/*** When ***/
		PostVo postVo = postService.getLatestPost();

		/*** Then ***/
		logger.debug("postVo:{}", postVo);
	}

	@Test
	public void postPagingListTest() {
		/*** Given ***/
		PageVo pageVo = new PageVo(1, 10);
		int page = pageVo.getPage();
		int pageSize = pageVo.getPageSize();
		int board_id = 1;
		Map<String, Object> map = new HashMap<>();
		map.put("page", page);
		map.put("pageSize", pageSize);
		map.put("board_id", board_id);

		/*** When ***/
		map = postService.postPagingList(map);

		logger.debug("map:{}", map);
		/*** Then ***/
		// assertNotNull(postList);
		// assertEquals(10, postList.size());

	}

	@Test
	public void postCntTest() {
		/*** Given ***/
		int board_id = 1;
		/*** When ***/
		int postCnt = postService.postCnt(board_id);
		logger.debug("postCnt:{}", postCnt);
		/*** Then ***/

	}

	@Test
	public void postModifyTest() {
		logger.debug("postModifyTest");
		/*** Given ***/
		PostVo postVo = new PostVo();
		postVo.setPost_title("a");
		postVo.setPost_content("a");
		postVo.setPost_id(17);

		/*** When ***/
		int modifyCnt = postService.postModify(postVo);
		logger.debug("modifyCnt:{}", modifyCnt);
		/*** Then ***/

	}

	@Test
	public void postUpdateStep() {

		/*** Given ***/
		PostVo postVo = new PostVo();
		postVo.setRef(1);
		postVo.setRe_step(2);
		/*** When ***/
		int updateCnt = postService.updateStep(postVo);
		logger.debug("updateCnt:{}", updateCnt);
		/*** Then ***/

	}

	@Test
	public void postReplyTest() {
		/*** Given ***/
		PostVo postVo = new PostVo();
		postVo.setPost_title("post_title");
		postVo.setPost_content("post_content");
		postVo.setBoard_id(1);
		postVo.setUser_id("brown");
		postVo.setRef(1);
		postVo.setRe_step(1);
		postVo.setRe_level(1);
		/*** When ***/
		int insertCnt = postService.postReply(postVo);
		/*** Then ***/

	}

}
