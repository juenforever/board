package kr.or.ddit.user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.IUserService;
import kr.or.ddit.user.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IUserService userService;
	private static final Logger logger = LoggerFactory
			.getLogger(UserController.class);

	@Override
	public void init() throws ServletException {
		userService = new UserService();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		logger.debug("UserController doGet");

		// request객체로부터 사용자 아이디 파라미터 획득
		String userId = request.getParameter("userId");
		logger.debug("userId", userId);

		// 사용자 아이디로 사용자 정보를 조회
		UserVo userVo = userService.getUser(userId);
		logger.debug("doget userVo : {}", userVo);

		// 조회 결과를 request객체에 속성으로 저장
		request.setAttribute("userVo", userVo);

		// 화면을 담당하는 /user/user.jsp로 forward
		request.getRequestDispatcher("/user/user.jsp").forward(request,
				response);

	}
}
