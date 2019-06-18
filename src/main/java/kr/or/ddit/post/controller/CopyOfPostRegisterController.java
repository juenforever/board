package kr.or.ddit.post.controller;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

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

public class CopyOfPostRegisterController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private IAttachmentService attachmentService;
	private IPostService postService;
	private IReplyService replyService;
	private static final Logger logger = LoggerFactory
			.getLogger(CopyOfPostRegisterController.class);

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
		int post_id = 0;
		AttachmentVo attachmentVo = new AttachmentVo();
		if (request.getParameter("post_id") != null)
			post_id = Integer.parseInt(request.getParameter("post_id"));
		for (Part part : request.getParts()) {
			logger.debug("part:{}", part);
			if (part != null && part.getSize() > 0) {

				String contentDispositin = part.getHeader("content-disposition");

					String[] splited = contentDisposition.split("; ");
					for (String split : splited) {
						if (split.startsWith("filename=")) {
							int startIndex = split.indexOf("\"") + 1;
							int lastIndex = split.lastIndexOf("\"");
							return split.substring(startIndex, lastIndex);
						}
					}
					return "";
				}
				String fileName = PartUtil.getFileName(contentDispositin);
				String ext = PartUtil.getExt(fileName);
				logger.debug("fileName:{}", fileName);
				logger.debug("ext:{}", ext);

				String uploadPath = PartUtil.getUploadPath();
				logger.debug("uploadPath:{}", uploadPath);
				File uploadFolder = new File(uploadPath);
				attachmentVo.setPost_id(post_id);
				attachmentVo.setAttachment_name(fileName);

				if (uploadFolder.exists()) {
					String filePath = uploadPath + File.separator
							+ UUID.randomUUID().toString() + ext;
					attachmentVo.setAttachment_path(filePath);
					logger.debug("filePath:{}", filePath);
					part.write(filePath);
					logger.debug("attachmentVo:{}", attachmentVo);
					logger.debug("filePath:{}", filePath);
					part.delete();
				}
			}
		}
		attachmentService.attachmentInsert(attachmentVo);

		logger.debug("doPost");
		String user_id = (String) getServletContext().getAttribute("userId");
		String post_title = request.getParameter("post_title");
		String post_content = request.getParameter("post_content");
		int board_id = Integer.parseInt(request.getParameter("board_id"));

		PostVo postVo = new PostVo();
		postVo.setPost_title(post_title);
		postVo.setPost_content(post_content);
		postVo.setBoard_id(board_id);
		postVo.setUser_id(user_id);

		postService.postInsert(postVo);

		request.setAttribute("board_id", board_id);
		logger.debug("doPost :{}", board_id);

		// 댓글과 첨부파일 가져오기

		PostVo postVoTime = postService.getLatestPost();
		if (postVoTime != null) {
			post_id = postVoTime.getPost_id();
			List<ReplyVo> replyList = replyService.replyList(post_id);
			logger.debug("post_id:{}", post_id);
			List<AttachmentVo> attachmentList = attachmentService
					.getAttachmentList(post_id);
			request.setAttribute("replyList", replyList);
			request.setAttribute("attachmentList", attachmentList);

		}

		request.setAttribute("postVo", postService.getPost(post_id));

		request.setAttribute("board_id", board_id);
		request.setAttribute("post_id", post_id);

		request.getRequestDispatcher("/post/postDetail.jsp").forward(request,
				response);
	}
	
}
