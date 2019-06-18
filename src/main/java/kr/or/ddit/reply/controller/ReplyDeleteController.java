package kr.or.ddit.reply.controller;

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

@WebServlet("/replyDelete")
public class ReplyDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory
			.getLogger(ReplyDeleteController.class);

	private AttachmentService attachmentService;
	private PostService postService;
	private ReplyService replyService;

	@Override
	public void init() throws ServletException {
		attachmentService = new AttachmentService();
		postService = new PostService();
		replyService = new ReplyService();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// replyDelete
		/*** replyDelete Given ***/
		int reply_id = 0;
		if (request.getParameter("reply_id") != null)
			reply_id = Integer.parseInt(request.getParameter("reply_id"));

		/*** replyDelete When ***/
		int DeleteCnt = replyService.replyDelete(reply_id);

		/*** replyDelete Then ***/

		// changePage to postDetail.jsp
		/***
		 * changePage to postDetail.jsp Given set :
		 * board_id,post_id,replyList,attachmentList,postVo
		 ***/
		/*** changePage to postDetail.jsp When ***/
		// get postVo
		/*** get postVo Given ***/
		int post_id = 0;
		if (request.getParameter("post_id") != null)
			post_id = Integer.parseInt(request.getParameter("post_id"));
		/*** get postVo When ***/
		PostVo postVo = postService.getPost(post_id);
		/*** get postVo Then ***/
		request.setAttribute("postVo", postVo);
		// =======================================================================
		/*** set board_id Given ***/
		int board_id = 0;
		if (request.getParameter("board_id") != null)
			board_id = Integer.parseInt(request.getParameter("board_id"));
		/*** set board_id When ***/
		request.setAttribute("board_id", board_id);
		/*** set board_id Then ***/
		// ===============================================================================

		// set-post_id==================================================================
		/*** set post_id Given ***/
		// post_id 위에서 넘겨받음
		/*** set post_id When ***/
		request.setAttribute("post_id", postVo.getPost_id());

		/*** set post_id Then ***/

		// set-board_id====================================================================================
		/*** set board_id Given ***/
		// board_id 위에서 넘겨받음
		/*** set board_id When ***/
		request.setAttribute("board_id", board_id);

		/*** set board_id Then ***/

		// set-replyList=========================================================================

		/*** Given set replyList ***/
		List<ReplyVo> replyList = replyService.replyList(post_id);
		/*** When set replyList ***/
		request.setAttribute("replyList", replyList);

		/*** Then set replyList ***/
		// set-attachmentList====================================================================
		/*** set-attachmentList Given ***/
		// post_id 위에서 넘겨받음
		// replyList-가져오기=======================================================================
		/*** attachmentList 가져오기 Given ***/
		List<AttachmentVo> attachmentList = attachmentService
				.getAttachmentList(post_id);
		/*** attachmentList 가져오기 When ***/
		/*** attachmentList 가져오기 Then ***/
		/*** set-attachmentList When ***/
		request.setAttribute("attachmentList", attachmentList);

		/*** changePage to postDetail.jsp Then ***/
		request.getRequestDispatcher("/post/postDetail.jsp").forward(request,
				response);

	}
}
