package kr.or.ddit.lprod.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.lprod.service.ILprodService;
import kr.or.ddit.lprod.service.LprodService;
import kr.or.ddit.paging.model.PageVo;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/lprodPagingList")
public class LprodPagingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory
			.getLogger(LprodPagingController.class);
	private ILprodService lprodService;

	@Override
	public void init() throws ServletException {

		lprodService = new LprodService();

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		logger.debug("doGet board_id : {}", request.getParameter("board_id"));

		String page = request.getParameter("page");
		String pageSize = request.getParameter("pageSize");
		if (request.getParameter("page") == null
				|| request.getParameter("pageSize") == null) {

			request.getRequestDispatcher("/lprodPagingList?page=1&pageSize=5")
					.forward(request, response);
		} else {
			PageVo pageVo = new PageVo(Integer.parseInt(page),
					Integer.parseInt(pageSize));
			Map<String, Object> resultMap = lprodService
					.lprodPagingList(pageVo);
			List<LprodVo> lprodList = (List<LprodVo>) resultMap
					.get("lprodList");
			int paginationSize = (Integer) resultMap.get("paginationSize");

			request.setAttribute("lprodList", lprodList);
			request.setAttribute("paginationSize", paginationSize);
			request.setAttribute("pageVo", pageVo);

			// 화면 출력을 담당하는 jsp에게 역할 위임
			request.getRequestDispatcher("/lprod/lprodPagingList.jsp").forward(
					request, response);
		}

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
