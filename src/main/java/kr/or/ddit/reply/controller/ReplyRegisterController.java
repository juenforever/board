package kr.or.ddit.reply.controller;

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
import kr.or.ddit.reply.model.ReplyVo;
import kr.or.ddit.reply.service.IReplyService;
import kr.or.ddit.reply.service.ReplyService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/replyRegister")
@MultipartConfig(maxFileSize = 1024 * 1024 * 3, maxRequestSize = 1024 * 1024 * 150)
public class ReplyRegisterController extends HttpServlet {

	private IAttachmentService attachmentService;
	private IPostService postService;
	private IReplyService replyService;

	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory
			.getLogger(ReplyRegisterController.class);

	@Override
	public void init() throws ServletException {
		postService = new PostService();
		attachmentService = new AttachmentService();
		replyService = new ReplyService();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		logger.debug("replyRegister doPost");

		String reply_content = request.getParameter("reply_content");
		int post_id = 0;
		if (request.getParameter("post_id") != null)
			post_id = Integer.parseInt(request.getParameter("post_id"));
		String user_id = (String) getServletContext().getAttribute("userId");
		ReplyVo replyVo = new ReplyVo();
		replyVo.setPost_id(post_id);
		replyVo.setReply_content(reply_content);
		replyVo.setUser_id(user_id);

		int result = replyService.replyInsert(replyVo);
		if(result>0){
			logger.debug("성공 : {}", result);
		}

		PostVo postVo = postService.getPost(post_id);

		// set ==================================================
		// board_id,post_id,attachmentList,postVo,replyList
		// board_id
		int board_id = 0;
		if (request.getParameter("board_id") != null)
			board_id = Integer.parseInt(request.getParameter("board_id"));
		request.setAttribute("board_id", board_id);
		// post_id
		request.setAttribute("post_id", post_id);
		// attachmentList
		List<AttachmentVo> attachmentList = attachmentService
				.getAttachmentList(post_id);
		request.setAttribute("attachmentList", attachmentList);
		// postVo
		request.setAttribute("postVo", postVo);
		// replyList
		List<ReplyVo> replyList = replyService.replyList(post_id);
		request.setAttribute("replyList", replyList);
		// 페이지 이동 ==============================================
		request.getRequestDispatcher("/post/postDetail.jsp").forward(request,
				response);
	}
}
