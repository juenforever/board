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

//import com.oreilly.servlet.MultipartRequest;
//import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/postRegister")
@MultipartConfig(maxFileSize = 1024 * 1024 * 3, maxRequestSize = 1024 * 1024 * 150)
public class PostRegisterController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private IAttachmentService attachmentService;
	private IPostService postService;
	private IReplyService replyService;
	private static final Logger logger = LoggerFactory
			.getLogger(PostRegisterController.class);

	@Override
	public void init() throws ServletException {
		postService = new PostService();
		attachmentService = new AttachmentService();
		replyService = new ReplyService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int board_id = 0;
		if (req.getParameter("board_id") != null)
			board_id = Integer.parseInt(req.getParameter("board_id"));
		logger.debug("doGet board_id:{}", board_id);

		req.setAttribute("board_id", board_id);
		req.getRequestDispatcher("/post/postRegister.jsp").forward(req, resp);

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

		// form data를 받는다 boar_id/post_title/post_content/user_id
		int board_id = Integer.parseInt(multi.getParameter("board_id"));
		String post_title = multi.getParameter("post_title");

		String post_content = multi.getParameter("post_content");
		logger.debug("post_content:{}", post_content);
		String user_id = (String) request.getServletContext().getAttribute(
				"userId");

		// post등록
		PostVo postVo = new PostVo();
		postVo.setPost_title(post_title);
		postVo.setPost_content(post_content);
		postVo.setBoard_id(board_id);
		postVo.setUser_id(user_id);

		postService.postInsert(postVo);
		postVo = postService.getLatestPost();
		post_id = postVo.getPost_id();
		request.setAttribute("post_id", post_id);
		request.setAttribute("postVo", postService.getPost(post_id));

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
			attachmentVo.setPost_id(postVo.getPost_id());

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
			attachmentVo.setPost_id(postVo.getPost_id());

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
			attachmentVo.setPost_id(postVo.getPost_id());

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
			attachmentVo.setPost_id(postVo.getPost_id());

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
			attachmentVo.setPost_id(postVo.getPost_id());

			attachmentService.attachmentInsert(attachmentVo);
		}

		// request 넘기기
		// board_id,post_id,replyList,attachmentList,postVo
		// post_id
		request.setAttribute("post_id", post_id);
		// attachmentList
		List<AttachmentVo> attachment = attachmentService
				.getAttachmentList(postVo.getPost_id());
		request.setAttribute("attachmentList", attachment);
		// board_id
		request.setAttribute("board_id", board_id);
		// postVo
		request.setAttribute("postVo", postVo);
		// replyList
		List<ReplyVo> replyList = replyService.replyList(post_id);
		request.setAttribute("replyList", replyList);

		request.getRequestDispatcher("/post/postDetail.jsp").forward(request,
				response);
	}
}
