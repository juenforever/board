package kr.or.ddit.lprod.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.lprod.service.LprodService;
import kr.or.ddit.lprod.service.ILprodService;

/**
 * Servlet implementation class LprodController
 */
@WebServlet("/lprodList1")
public class LprodControllerOriginal extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ILprodService lprodService;

	@Override
	public void init() throws ServletException {

		lprodService = new LprodService();

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setAttribute("lprodList", lprodService.lprodList());

		request.getRequestDispatcher("/lprod/lprodList.jsp").forward(request,
				response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
