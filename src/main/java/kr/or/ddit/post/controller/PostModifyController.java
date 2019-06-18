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

@WebServlet("/postModify")
@MultipartConfig(maxFileSize = 1024 * 1024 * 3, maxRequestSize = 1024 * 1024 * 150)
public class PostModifyController extends HttpServlet {
	private IPostService postService;
	private IAttachmentService attachmentService;
	private IReplyService replyService;
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory
			.getLogger(PostModifyController.class);

	@Override
	public void init() throws ServletException {
		postService = new PostService();
		attachmentService = new AttachmentService();
		replyService = new ReplyService();

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		logger.debug("postModify doPost");
		int post_id = 0;
		int sizeLimit = 1024 * 1024 * 15;
		String savePath = PartUtil.getUploadPath();

		MultipartRequest multi = new MultipartRequest(request, savePath,
				sizeLimit, "utf-8", new DefaultFileRenamePolicy());

		// 받아야할 매개변수 받기
		int board_id = 0;
		if (multi.getParameter("board_id") != null)
			board_id = Integer.parseInt(multi.getParameter("board_id"));

		if (multi.getParameter("post_id") != null)
			post_id = Integer.parseInt(multi.getParameter("post_id"));
		logger.debug("post_id:{}", post_id);

		PostVo postVo = new PostVo();
		postVo.setPost_title(multi.getParameter("post_title"));
		postVo.setPost_content(multi.getParameter("post_content"));
		postVo.setPost_id(post_id);

		// post 내용 수정시키고 수정 내용 불러오기
		int ModifyCnt = postService.postModify(postVo);

		postVo = postService.getPost(post_id);

		// 첨부파일 저장하기
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

		// 다음화면으로 넘어가기 위한 첨부파일 목록 불러오기
		List<AttachmentVo> attachmentList = attachmentService
				.getAttachmentList(post_id);
		// 다음화면으로 넘어가기 위해 댓글 목록 가져오기
		logger.debug("다음화면으로 넘어가기 위해 댓글 목록 가져오기 :{}", post_id);
		List<ReplyVo> replyList = replyService.replyList(post_id);

		// 객체 넘기기================================================
		// board_id,post_id,replyLIst,attachmentList,postVo
		// board_id
		request.setAttribute("board_id", board_id);
		// post_id
		request.setAttribute("post_id", post_id);
		// replyList
		request.setAttribute("replyList", replyList);
		// attachmentList
		request.setAttribute("attachmentList", attachmentList);
		// postVo
		request.setAttribute("postVo", postVo);

		// 페이지 이동
		request.getRequestDispatcher("/post/postDetail.jsp").forward(request,
				response);
	}
}
