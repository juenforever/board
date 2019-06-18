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
import kr.or.ddit.reply.model.ReplyVo;
import kr.or.ddit.reply.service.IReplyService;
import kr.or.ddit.reply.service.ReplyService;
import kr.or.ddit.util.PartUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/postReply")
@MultipartConfig(maxFileSize = 1024 * 1024 * 3, maxRequestSize = 1024 * 1024 * 150)
public class PostReplyController extends HttpServlet {
	private static final Logger logger = LoggerFactory
			.getLogger(PostReplyController.class);
	private IAttachmentService attachmentService;
	private IPostService postService;
	private IReplyService replyService;
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		postService = new PostService();
		attachmentService = new AttachmentService();
		replyService = new ReplyService();
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		logger.debug("postReply doGet");
		int board_id = 0;
		if (request.getParameter("board_id") != null)
			board_id = Integer.parseInt(request.getParameter("board_id"));
		logger.debug("board_id:{}", board_id);
		int post_id = 0;
		if (request.getParameter("post_id") != null)
			post_id = Integer.parseInt(request.getParameter("post_id"));

		request.setAttribute("board_id", board_id);
		request.setAttribute("post_id", post_id);
		request.getRequestDispatcher("/post/postReply.jsp").forward(request,
				response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		logger.debug("doPost");
		// multipartRequest 사용할 준비
		int post_id = 0;
		int sizeLimit = 1024 * 1024 * 15;
		String savePath = PartUtil.getUploadPath();

		MultipartRequest multi = new MultipartRequest(request, savePath,
				sizeLimit, "utf-8", new DefaultFileRenamePolicy());

		PostVo postVo = null;
		if (multi.getParameter("post_id") != null) {
			post_id = Integer.parseInt(multi.getParameter("post_id"));
			logger.debug("post_id:{}", post_id);
			postVo = postService.getPost(post_id);
			logger.debug("postVo:{}", postVo);
		}

		// reply 답글 등록 파라미터 설정==============================================
		// reply 답글 등록 파라미터 post_title=====================================
		String post_title = multi.getParameter("post_title");
		logger.debug("post_title:{}", post_title);
		// reply 답글 등록 파라미터 post_content======================================
		String post_content = multi.getParameter("post_content");
		logger.debug("post_content:{}", post_content);
		// reply 답글 등록 파라미터 board_id =======================================
		int board_id = 0;
		logger.debug("board_id:{}", multi.getParameter("board_id"));
		if (multi.getParameter("board_id") != null)
			board_id = Integer.parseInt(multi.getParameter("board_id"));
		// reply 답글 등록 파라미터 user_id ============================================
		String user_id = (String) getServletContext().getAttribute("userId");
		logger.debug("user_id:{}", user_id);
		// reply 답글 등록 파라미터 ref ==============================================
		int ref = postVo.getRef();
		// reply 답글 등록 파라미터 re_step =========================================
		int re_step = postVo.getRe_step() + 1;
		// reply 답글 등록 파라미터 re_level
		int re_level = postVo.getRe_level() + 1;
		// ================================================

		postVo.setPost_title(post_title);
		postVo.setPost_content(post_content);
		postVo.setBoard_id(board_id);
		postVo.setUser_id(user_id);
		postVo.setRef(ref);
		postVo.setRe_step(re_step);
		postVo.setRe_level(re_level);

		// 답글 순서조정 메서드
		postService.updateStep(postVo);
		// 답글 쓰기
		postService.postReply(postVo);

		// 댓글과 첨부파일 가져오기
		PostVo postVoTime = postService.getLatestPost();
		List<ReplyVo> replyList = null;
		List<AttachmentVo> attachmentList = null;
		if (postVoTime != null) {
			post_id = postVoTime.getPost_id();

		}

		// file data 받기=======================================================
		// DB에 저장할 파일명

		String attachment_name1 = multi.getFilesystemName("upfile1");
		if (attachment_name1 != null) {
			logger.debug("attachment_name1:{}", attachment_name1);
			// DB에 저장할 파일 경로
			String attachment_path = savePath + "/" + attachment_name1;

			// 첨부파일 저장
			AttachmentVo attachmentVo = new AttachmentVo();
			attachmentVo.setAttachment_name(attachment_name1);
			attachmentVo.setAttachment_path(attachment_path);
			attachmentVo.setPost_id(post_id);

			attachmentService.attachmentInsert(attachmentVo);
		}
		String attachment_name2 = multi.getFilesystemName("upfile2");
		if (attachment_name2 != null) {
			// DB에 저장할 파일 경로
			String attachment_path = savePath + "/" + attachment_name2;

			// 첨부파일 저장
			AttachmentVo attachmentVo = new AttachmentVo();
			attachmentVo.setAttachment_name(attachment_name2);
			attachmentVo.setAttachment_path(attachment_path);
			attachmentVo.setPost_id(post_id);

			attachmentService.attachmentInsert(attachmentVo);
		}
		String attachment_name3 = multi.getFilesystemName("upfile3");
		if (attachment_name3 != null) {
			// DB에 저장할 파일 경로
			String attachment_path = savePath + "/" + attachment_name3;

			// 첨부파일 저장
			AttachmentVo attachmentVo = new AttachmentVo();
			attachmentVo.setAttachment_name(attachment_name3);
			attachmentVo.setAttachment_path(attachment_path);
			attachmentVo.setPost_id(post_id);

			attachmentService.attachmentInsert(attachmentVo);
		}
		String attachment_name4 = multi.getFilesystemName("upfile4");
		if (attachment_name4 != null) {
			// DB에 저장할 파일 경로
			String attachment_path = savePath + "/" + attachment_name4;

			// 첨부파일 저장
			AttachmentVo attachmentVo = new AttachmentVo();
			attachmentVo.setAttachment_name(attachment_name4);
			attachmentVo.setAttachment_path(attachment_path);
			attachmentVo.setPost_id(post_id);

			attachmentService.attachmentInsert(attachmentVo);
		}
		String attachment_name5 = multi.getFilesystemName("upfile5");
		if (attachment_name5 != null) {
			// DB에 저장할 파일 경로
			String attachment_path = savePath + "/" + attachment_name5;

			// 첨부파일 저장
			AttachmentVo attachmentVo = new AttachmentVo();
			attachmentVo.setAttachment_name(attachment_name5);
			attachmentVo.setAttachment_path(attachment_path);
			attachmentVo.setPost_id(post_id);

			attachmentService.attachmentInsert(attachmentVo);
		}
		replyList = replyService.replyList(post_id);
		attachmentList = attachmentService.getAttachmentList(post_id);
		logger.debug("post_id:{}", post_id);

		// 객체 넘기기=============================================================
		// board_id,post_id,replyList,attachmentList,postVo
		// board_id
		logger.debug("board_id:{}", board_id);
		request.setAttribute("board_id", board_id);
		// post_id
		logger.debug("post_id:{}", post_id);
		request.setAttribute("post_id", post_id);
		// replyList
		logger.debug("replyList:{}", replyList);
		request.setAttribute("replyList", replyList);
		// attachmentList
		logger.debug("attachmentList:{}", attachmentList);

		request.setAttribute("attachmentList", attachmentList);
		// postVo
		logger.debug("postVo:{}", postVo);
		request.setAttribute("postVo", postVo);
		// 페이지
		// 이동====================================================================
		request.getRequestDispatcher("/post/postDetail.jsp").forward(request,
				response);
	}
}
