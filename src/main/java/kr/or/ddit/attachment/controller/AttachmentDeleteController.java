package kr.or.ddit.attachment.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.attachment.model.AttachmentVo;
import kr.or.ddit.attachment.service.AttachmentService;
import kr.or.ddit.post.model.PostVo;
import kr.or.ddit.post.service.PostService;
import kr.or.ddit.reply.model.ReplyVo;
import kr.or.ddit.reply.service.ReplyService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/attachmentDelete")
public class AttachmentDeleteController extends HttpServlet {
	private static final Logger logger = LoggerFactory
			.getLogger(AttachmentDeleteController.class);

	private AttachmentService attachmentService;
	private PostService postService;
	private ReplyService replyService;
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		attachmentService = new AttachmentService();
		postService = new PostService();
		replyService = new ReplyService();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		logger.debug("attachmentDelete doGet");

		// 필요한 request 받기 attachment_id,post_id,board_id
		// attachment_id=====================
		int attachment_id = 0;
		if (request.getParameter("attachment_id") != null)
			attachment_id = Integer.parseInt(request
					.getParameter("attachment_id"));
		logger.debug("attchment_id:{}", attachment_id);

		// post_id=============================
		int post_id = 0;
		if (request.getParameter("post_id") != null)
			post_id = Integer.parseInt(request.getParameter("post_id"));
		logger.debug("post_id:{}", post_id);

		// board_id ===========================
		int board_id = 0;
		if (request.getParameter("board_id") != null)
			board_id = Integer.parseInt(request.getParameter("board_id"));
		logger.debug("board_id:{}", board_id);

		// attachmentDelete 삭제
		/*** Given ***/
		// attachment_id
		/*** When ***/
		attachmentService.attchmentDelete(attachment_id);
		/*** Then ***/

		// attachmentList,board_id,postVo, replyList를 넘긴다
		// -attachmentList
		List<AttachmentVo> attachmentList = attachmentService
				.getAttachmentList(post_id);

		request.setAttribute("attachmentList", attachmentList);

		// -board_id
		PostVo postVo = postService.getPost(post_id);
		request.setAttribute("board_id", postVo.getBoard_id());

		// -postVo
		logger.debug("postVo:{}", postVo);
		request.setAttribute("postVo", postVo);

		// replyList
		List<ReplyVo> replyList = replyService.replyList(post_id);

		logger.debug("post_id:{}", postVo.getPost_id());

		// board_id,post_id,replyList,attachmentList,postVo
		request.setAttribute("post_id", postVo.getPost_id());
		request.setAttribute("board_id", board_id);
		request.setAttribute("replyList", replyList);
		request.setAttribute("attachmentList", attachmentList);
		request.setAttribute("postVo", postVo);

		// to
		request.getRequestDispatcher("/post/postDetail.jsp").forward(request,
				response);

	}
}
