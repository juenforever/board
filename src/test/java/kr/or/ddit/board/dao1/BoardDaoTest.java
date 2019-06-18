package kr.or.ddit.board.dao1;

import static org.junit.Assert.assertEquals;

import java.util.List;

import kr.or.ddit.board.dao.BoardDao;
import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.model.BoardVo;
import kr.or.ddit.lprod.dao.LprodDao;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BoardDaoTest {
	private static final Logger logger = LoggerFactory
			.getLogger(BoardDaoTest.class);
	private IBoardDao boardDao;

	@BeforeClass
	public static void BeforeClass() {
		logger.debug("beforeClass");
	}

	@Before
	public void setup() {
		boardDao = new BoardDao();
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
	public void boardListTest() {

		/*** Given ***/

		/*** When ***/
		List<BoardVo> boardlist = boardDao.boardList();
		logger.debug("boardList : {}", boardlist);
		/*** Then ***/

	}

	@Test
	public void getboardTest() {
		logger.debug("getpostTest");
		/*** Given ***/
		String boardId = "1";
		/*** When ***/
		logger.debug("boardId : {}", boardId);
		BoardVo boardVo = boardDao.board(boardId);
		logger.debug("boardVo : {}", boardVo);
		logger.debug("board_name:{}", boardVo.getBoard_name());
		/*** Then ***/
		assertEquals("브라운", boardVo.getBoard_name());
		logger.debug("boardVo : {}", boardVo);

	}

	@Test
	public void boardInsertTest() {
		/*** Given ***/
		String board_name = "a";
		String board_usage = "YES";
		String user_id = "c";
		BoardVo bvo = new BoardVo();

		bvo.setBoard_name(board_name);
		bvo.setBoard_usage(board_usage);
		bvo.setUser_id(user_id);

		/*** When ***/
		int a = boardDao.boardInsert(bvo);
		logger.debug("boardInsertTest a : {}", a);

		/*** Then ***/

	}

	@Test
	public void boardUpdateTest() {
		/*** Given ***/
		String board_id = "board17";
		String board_name = "a";
		String board_usage = "No";
		String user_id = "c";
		BoardVo bvo = new BoardVo();

		bvo.setBoard_id(board_id);
		bvo.setBoard_name(board_name);
		bvo.setBoard_usage(board_usage);
		bvo.setUser_id(user_id);
		/*** When ***/
		int a = boardDao.boardUpdate(bvo);
		logger.debug("a : {}", a);
		logger.debug("boardCreateTest a : {}", a);

		/*** Then ***/

	}

	@Test
	public void boardDeleteTest() {
		/*** Given ***/
		String board_id = "board2";
		/*** When ***/
		int boardDeleteCnt = boardDao.boardDelete(board_id);
		logger.debug("boardDeleteCnt:{}", boardDeleteCnt);
		/*** Then ***/

	}
}
