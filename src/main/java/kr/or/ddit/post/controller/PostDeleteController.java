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

@WebServlet("/postDelete")
@MultipartConfig(maxFileSize = 1024 * 1024 * 3, maxRequestSize = 1024 * 1024 * 150)
public class PostDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory
			.getLogger(PostDeleteController.class);
	private IPostService postService;
	private IBoardService boardService;

	@Override
	public void init() throws ServletException {
		postService = new PostService();
		boardService = new BoardService();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// board_id============================================
		int board_id = 0;
		logger.debug("board_id:{}", request.getParameter("board_id"));
		if (request.getParameter("board_id") != null)
			board_id = Integer.parseInt(request.getParameter("board_id"));
		request.setAttribute("board_id", board_id);

		// post_id==============================================
		int post_id = 0;
		if (request.getParameter("post_id") != null)
			post_id = Integer.parseInt(request.getParameter("post_id"));
		request.setAttribute("post_id", post_id);
		// post 삭제========================================
		postService.postDelete(post_id);

		// 페이징 처리=============================================
		String pageString = request.getParameter("page");
		String pageSizeString = request.getParameter("pageSize");

		int page = pageString == null ? 1 : Integer.parseInt(pageString);
		int pageSize = pageSizeString == null ? 10 : Integer
				.parseInt(pageSizeString);
		PageVo pageVo = new PageVo(1, 10);
		int paginationSize = 0;
		List<PostVo> postList = null;
		Map<String, Object> resultMap = null;
		if (board_id != 0) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("page", page);
			map.put("pageSize", pageSize);
			map.put("board_id", board_id);

			resultMap = postService.postPagingList(map);
			logger.debug("map:{}", resultMap);
			if (resultMap.get("postList") != null)
				postList = (List<PostVo>) resultMap.get("postList");
			paginationSize = (Integer) resultMap.get("paginationSize");
		}

		// 속성값 넣어주기 board_id,postList,pageVo,paginationSize=============
		// board_id
		request.setAttribute("board_id", board_id);
		// postList
		request.setAttribute("postList", postList);
		// pageVo
		request.setAttribute("pageVo", pageVo);
		// paginationSize
		request.setAttribute("paginationSize", paginationSize);

		// 페이지 이동========================================
		request.getRequestDispatcher("/post/postPagingList.jsp").forward(
				request, response);
	}
}
