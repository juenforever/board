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
import kr.or.ddit.board.model.BoardVo;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.post.model.PostVo;
import kr.or.ddit.post.service.IPostService;
import kr.or.ddit.post.service.PostService;
import kr.or.ddit.reply.model.ReplyVo;
import kr.or.ddit.reply.service.IReplyService;
import kr.or.ddit.reply.service.ReplyService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/postDetail")
@MultipartConfig(maxFileSize = 1024 * 1024 * 3, maxRequestSize = 1024 * 1024 * 150)
public class PostDetailController extends HttpServlet {
	private IAttachmentService attachmentService;
	private IPostService postService;
	private IReplyService replyService;
	private IBoardService boardService;
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory
			.getLogger(PostDetailController.class);

	@Override
	public void init() throws ServletException {
		boardService = new BoardService();
		postService = new PostService();
		attachmentService = new AttachmentService();
		replyService = new ReplyService();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		logger.debug("doPost");
		int board_id = 0;
		if (request.getParameter("board_id") != null)
			board_id = Integer.parseInt(request.getParameter("board_id"));
		if (request.getParameter("board_id") != null)
			board_id = Integer.parseInt(request.getParameter("board_id"));
		int post_id = 0;
		PostVo postVo = null;
		if (request.getParameter("post_id") != null) {
			post_id = Integer.parseInt(request.getParameter("post_id"));
			postVo = postService.getPost(post_id);
		}
		List<ReplyVo> replyList = replyService.replyList(post_id);

		List<BoardVo> boardList = boardService.boardList();

		// attachment setAttribute
		/*** Given ***/
		// post_id

		/*** When ***/

		List<AttachmentVo> attachmentList = attachmentService
				.getAttachmentList(post_id);
		/*** Then ***/
		request.setAttribute("attachmentList", attachmentList);

		// 전달할 속성 board_id,post_id,replyList,attachmentList,postVo
		// board_id
		request.setAttribute("board_id", board_id);
		// replyList
		request.setAttribute("replyList", replyList);
		// postVo
		request.setAttribute("postVo", postVo);
		// post_id
		request.setAttribute("post_id", post_id);

		// 페이지 이동
		request.getRequestDispatcher("/post/postDetail.jsp").forward(request,
				response);
	}
}
