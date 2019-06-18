package kr.or.ddit.login;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.model.BoardVo;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.IUserService;
import kr.or.ddit.user.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	IBoardService boardService;
	private static final Logger logger = LoggerFactory
			.getLogger(LoginController.class);

	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		boardService = new BoardService();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		logger.debug("LoginController doGet()");

		if (request.getSession().getAttribute("USER_INFO") != null) {
			request.getRequestDispatcher("main.jsp").forward(request, response);
		} else
			request.getRequestDispatcher("/login/login.jsp").forward(request,
					response);
	}

	// 로그인 요청을 처리
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		logger.debug("dopost()");

		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		// String encryptPassword = KISA_SHA256.encrypt(password);
		// // logger.debug("encryptPassword : {}", encryptPassword);

		IUserService userService = new UserService();
		UserVo userVo = userService.getUser(userId);

		if (userVo != null && password.equals(userVo.getPass())) {

			int cookieMaxAge = 0;
			// remember 파라미터가 존재할 경우 userId, rememberme cookie 설정해준다
			if (request.getParameter("rememberme") != null)

				cookieMaxAge = 60 * 60 * 24 * 30;

			Cookie userIdCookie = new Cookie("userId", userId);
			userIdCookie.setMaxAge(cookieMaxAge);

			Cookie rememberMeCookie = new Cookie("rememberme", "true");
			rememberMeCookie.setMaxAge(cookieMaxAge);

			response.addCookie(userIdCookie);
			response.addCookie(rememberMeCookie);
			// remember 파라미터가 존재하지 않을 경우 userId, rememberme cookie 삭제한다
			List<BoardVo> boardList = boardService.getAllBoardList();
			request.setAttribute("userVo", userVo);

			request.getServletContext().setAttribute("boardList", boardList);
			request.getServletContext().setAttribute("userId", userId);

			request.getRequestDispatcher("/main.jsp")
					.forward(request, response);

		} else {
			request.getRequestDispatcher("/login/login.jsp").forward(request,
					response);
		}
	}
}
