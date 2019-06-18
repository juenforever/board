package kr.or.ddit.board.dao;

import java.util.List;

import kr.or.ddit.board.model.BoardVo;

public interface IBoardDao {

	List<BoardVo> boardList();

	BoardVo board(int boardId);

	int boardInsert(BoardVo bvo);

	int boardUpdate(BoardVo bvo);

	int boardDelete(int board_id);

	List<BoardVo> getAllBoardList();

}
