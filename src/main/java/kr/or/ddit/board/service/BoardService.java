package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.dao.BoardDao;
import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.model.BoardVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BoardService implements IBoardService {

	private static final Logger logger = LoggerFactory
			.getLogger(BoardService.class);
	IBoardDao boardDao = new BoardDao();

	@Override
	public List<BoardVo> boardList() {
		List<BoardVo> boardList = boardDao.boardList();
		return boardList;
	}

	@Override
	public BoardVo board(int board_id) {
		BoardVo boardVo = boardDao.board(board_id);
		return boardVo;
	}

	@Override
	public int boardInsert(BoardVo bvo) {
		int insertCnt = boardDao.boardInsert(bvo);
		return insertCnt;
	}

	@Override
	public int boardUpdate(BoardVo bvo) {
		int updateCnt = boardDao.boardUpdate(bvo);
		return updateCnt;
	}

	@Override
	public List<BoardVo> getAllBoardList() {
		return boardDao.getAllBoardList();
	}
}
