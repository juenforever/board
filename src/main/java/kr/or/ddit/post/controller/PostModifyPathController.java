package kr.or.ddit.post.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.attachment.model.AttachmentVo;
import kr.or.ddit.attachment.service.AttachmentService;
import kr.or.ddit.attachment.service.IAttachmentService;
import kr.or.ddit.post.model.PostVo;
import kr.or.ddit.post.service.IPostService;
import kr.or.ddit.post.service.PostService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/postModifyPath")
@MultipartConfig(maxFileSize = 1024 * 1024 * 3, maxRequestSize = 1024 * 1024 * 150)
public class PostModifyPathController extends HttpServlet {
	private IPostService postService;
	private IAttachmentService attachmentService;
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory
			.getLogger(PostModifyPathController.class);

	@Override
	public void init() throws ServletException {
		postService = new PostService();
		attachmentService = new AttachmentService();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		logger.debug("doPost");

		// board_id ===========================================================
		int board_id = 0;
		logger.debug("board_id:{}", request.getParameter("board_id"));
		if (request.getParameter("board_id") != null)
			board_id = Integer.parseInt(request.getParameter("board_id"));
		logger.debug("board_id:{}", board_id);
		// post_id======================================================
		int post_id = 0;
		if (request.getParameter("post_id") != null)
			post_id = Integer.parseInt(request.getParameter("post_id"));
		logger.debug("post_id:{}", post_id);

		// postVo ==========================================
		PostVo postVo = postService.getPost(post_id);
		logger.debug("postVo:{}", postVo);

		// attachmentList==========================================
		List<AttachmentVo> attachmentList = attachmentService
				.getAttachmentList(post_id);
		logger.debug("attachmentList:{}", attachmentList);

		// ====================================================
		// set board_id,post_id,attachmentList,postVo
		// board_id
		request.setAttribute("board_id", board_id);
		// post_id
		request.setAttribute("post_id", post_id);
		// attachmentList
		request.setAttribute("attachmentList", attachmentList);
		// postVo
		request.setAttribute("postVo", postVo);

		// 페이지 이동 ====================================================
		request.getRequestDispatcher("/post/postModify.jsp").forward(request,
				response);
	}
}
