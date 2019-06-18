package kr.or.ddit.board.dao;

import java.util.List;

import kr.or.ddit.board.model.BoardVo;
import kr.or.ddit.mybatis.MyBatisUtil;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BoardDao implements IBoardDao {
	private static final Logger logger = LoggerFactory
			.getLogger(BoardDao.class);

	@Override
	public List<BoardVo> boardList() {
		logger.debug("boardList");
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		List<BoardVo> boardList = sqlSession.selectList("board.boardList");
		logger.debug("boardList:{}", boardList);
		sqlSession.commit();
		sqlSession.close();
		return boardList;
	}

	@Override
	public BoardVo board(int board_id) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		logger.debug("board_id : {}", board_id);
		BoardVo boardVo = sqlSession.selectOne("board.board", board_id);
		logger.debug("boardVo : {}", boardVo);
		sqlSession.close();
		return boardVo;
	}

	@Override
	public int boardInsert(BoardVo bvo) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int insertCnt = sqlSession.insert("board.boardInsert", bvo);
		sqlSession.commit();
		sqlSession.close();
		return insertCnt;
	}

	@Override
	public int boardUpdate(BoardVo bvo) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int insertCnt = sqlSession.update("board.boardUpdate", bvo);
		sqlSession.commit();
		sqlSession.close();
		return insertCnt;
	}

	@Override
	public int boardDelete(int board_id) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int boardDeleteCnt = sqlSession.update("board.boardDelete", board_id);
		sqlSession.commit();
		sqlSession.close();
		return boardDeleteCnt;
	}

	@Override
	public List<BoardVo> getAllBoardList() {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		List<BoardVo> allBoardList = sqlSession
				.selectList("board.getAllBoardList");
		sqlSession.commit();
		sqlSession.close();
		return allBoardList;
	}

}
