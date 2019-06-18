package kr.or.ddit.post.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.model.BoardVo;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.paging.model.PageVo;
import kr.or.ddit.post.model.PostVo;
import kr.or.ddit.post.service.IPostService;
import kr.or.ddit.post.service.PostService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/postPagingList")
@MultipartConfig(maxFileSize = 1024 * 1024 * 3, maxRequestSize = 1024 * 1024 * 150)
public class PostPagingController extends HttpServlet {
	private static final Logger logger = LoggerFactory
			.getLogger(PostPagingController.class);
	private static final long serialVersionUID = 1L;
	private IPostService postService;
	private IBoardService boardService;

	@Override
	public void init() throws ServletException {
		postService = new PostService();
		boardService = new BoardService();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int board_id = 0;
		if (request.getParameter("board_id") != null)
			board_id = Integer.parseInt(request.getParameter("board_id"));

		String pageString = request.getParameter("page");
		String pageSizeString = request.getParameter("pageSize");

		int page = pageString == null ? 1 : Integer.parseInt(pageString);
		int pageSize = pageSizeString == null ? 10 : Integer
				.parseInt(pageSizeString);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", page);
		map.put("pageSize", pageSize);
		if (board_id != 0)
			map.put("board_id", board_id);

		Map<String, Object> resultMap = postService.postPagingList(map);
		List<PostVo> postList = (List<PostVo>) resultMap.get("postList");
		logger.debug("postList:{}", postList);
		int paginationSize = (Integer) resultMap.get("paginationSize");

		PageVo pageVo = new PageVo();
		pageVo.setPage(page);
		pageVo.setPageSize(pageSize);

		// ====================================================
		// set board_id,postList,pageVo,paginationSize
		// board_id
		request.setAttribute("board_id", board_id);
		// postList
		request.setAttribute("postList", postList);
		// pageVo
		request.setAttribute("pageVo", pageVo);
		// paginationSize
		request.setAttribute("paginationSize", paginationSize);

		// 화면 출력을 담당하는 jsp에게 역할 위임
		request.getRequestDispatcher("/post/postPagingList.jsp").forward(
				request, response);
	}
}
