package kr.or.ddit.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.model.BoardVo;
import kr.or.ddit.board.service.BoardService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/boardCreate")
@MultipartConfig(maxFileSize = 1024 * 1024 * 3, maxRequestSize = 1024 * 1024 * 150)
public class BoardCreateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory
			.getLogger(BoardCreateController.class);

	private BoardService boardService;

	@Override
	public void init() throws ServletException {
		boardService = new BoardService();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		logger.debug("doget");

		request.getRequestDispatcher("/board/boardCreate.jsp").forward(request,
				response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		logger.debug("doPost");
		request.setCharacterEncoding("UTF-8");

		String board_name = request.getParameter("board_name");
		String board_usage = request.getParameter("board_usage");
		String user_id = (String) getServletContext().getAttribute("userId");

		BoardVo bvo = new BoardVo();
		bvo.setBoard_name(board_name);
		bvo.setBoard_usage(board_usage);
		bvo.setUser_id(user_id);

		logger.debug("bvo:{}", bvo);

		boardService.boardInsert(bvo);
		List<BoardVo> boardList = boardService.getAllBoardList();

		request.getServletContext().setAttribute("boardList", boardList);
		response.sendRedirect(request.getContextPath() + "/boardCreate");

	}
}
