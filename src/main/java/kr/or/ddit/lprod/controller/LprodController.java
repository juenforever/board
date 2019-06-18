package kr.or.ddit.lprod.controller;

import java.io.IOException;

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

/**
 * Servlet implementation class LprodController
 */
@WebServlet("/lprod")
public class LprodController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ILprodService lprodService;
	private static final Logger logger = LoggerFactory
			.getLogger(LprodController.class);

	@Override
	public void init() throws ServletException {

		lprodService = new LprodService();

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		logger.debug("LprodController doGet");
		int lprod_id = Integer.parseInt(request.getParameter("lprod_id"));
		logger.debug("lprod_id", lprod_id);

		// LprodVo lprodVo = lprodService.get

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

}
