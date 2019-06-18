package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.model.BoardVo;

public interface IBoardService {

	List<BoardVo> boardList();

	BoardVo board(int board_id);

	int boardInsert(BoardVo bvo);

	int boardUpdate(BoardVo bvo);

	List<BoardVo> getAllBoardList();

}
