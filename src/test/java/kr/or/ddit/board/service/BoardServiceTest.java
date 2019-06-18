package kr.or.ddit.board.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import kr.or.ddit.board.model.BoardVo;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BoardServiceTest {

	private IBoardService boardService;
	private static final Logger logger = LoggerFactory
			.getLogger(BoardServiceTest.class);

	@BeforeClass
	public static void BeforeClass() {
		logger.debug("beforeClass");
	}

	@Before
	public void setup() {
		boardService = new BoardService();
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
		List<BoardVo> boardlist = boardService.boardList();
		logger.debug("boardListTest boardList : {}", boardlist);
		/*** Then ***/

	}

	@Test
	public void boardTest() {
		logger.debug("boardTest");
		/*** Given ***/
		String boardId = "1";
		/*** When ***/
		logger.debug("boardTest boardId : {}", boardId);
		BoardVo boardVo = boardService.board(boardId);
		logger.debug("boardTest boardVo : {}", boardVo);
		logger.debug("boardTest board_name:{}", boardVo.getBoard_name());
		/*** Then ***/
		assertEquals("자유게시판", boardVo.getBoard_name());
		logger.debug("boardTest boardVo : {}", boardVo);

	}

	@Test
	public void boardInsertTest() {
		/*** Given ***/
		// logger.debug("doPost bvo : {}", bvo);
		/*** When ***/
		// int insertCnt = boardService.boardInsert(bvo);
		/*** Then ***/

	}

	@Test
	public void boardUpdateTest() {
		/*** Given ***/
		String board_usage = "no";
		String board_id = "board30";
		BoardVo bvo = new BoardVo();
		bvo.setBoard_usage(board_usage);
		bvo.setBoard_id(board_id);

		/*** When ***/
		int a = boardService.boardUpdate(bvo);
		logger.debug("boardCreateTest a : {}", a);

		/*** Then ***/
	}

	@Test
	public void getAllBoardListTest() {
		logger.debug("getAllBoardList");
		/*** Given ***/

		/*** When ***/
		List<BoardVo> allBoardList = boardService.getAllBoardList();
		logger.debug("allBoardList:{}", allBoardList);
		/*** Then ***/

	}
}
