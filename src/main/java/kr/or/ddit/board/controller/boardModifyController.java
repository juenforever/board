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
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.IUserService;
import kr.or.ddit.user.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/boardModify")
@MultipartConfig(maxFileSize = 1024 * 1024 * 3, maxRequestSize = 1024 * 1024 * 150)
public class boardModifyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory
			.getLogger(boardModifyController.class);

	private BoardService boardService;
	private IUserService userService;

	@Override
	public void init() throws ServletException {
		userService = new UserService();
		boardService = new BoardService();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		logger.debug("doget");

		response.sendRedirect(request.getContextPath() + "/boardCreate");

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		logger.debug("doPost");
		request.setCharacterEncoding("UTF-8");

		String board_usage = request.getParameter("board_usage");
		logger.debug("doPost board_usage : {}", board_usage);
		int board_id = Integer.parseInt(request.getParameter("board_id"));
		logger.debug("doPost board_id : {}", board_id);

		BoardVo bvo = new BoardVo();
		bvo.setBoard_usage(board_usage);
		bvo.setBoard_id(board_id);

		logger.debug("doPost bvo : {}", bvo);
		int a = boardService.boardUpdate(bvo);
		List<BoardVo> boardList = boardService.getAllBoardList();
		request.getServletContext().setAttribute("boardList", boardList);

		response.sendRedirect(request.getContextPath() + "/boardCreate");

	}
}
