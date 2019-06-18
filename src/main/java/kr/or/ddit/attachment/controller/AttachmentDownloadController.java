package kr.or.ddit.attachment.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
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

@WebServlet("/attachmentDownload")
public class AttachmentDownloadController extends HttpServlet {
	private static final Logger logger = LoggerFactory
			.getLogger(AttachmentDownloadController.class);
	private static final long serialVersionUID = 1L;
	private IAttachmentService attachmentService;
	private IReplyService replyService;
	private IPostService postService;

	@Override
	public void init() throws ServletException {
		attachmentService = new AttachmentService();
		replyService = new ReplyService();
		postService = new PostService();

	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		int board_id = 0;
		if (request.getParameter("board_id") != null)
			board_id = Integer.parseInt(request.getParameter("board_id"));
		int post_id = 0;
		if (request.getParameter("post_id") != null)
			post_id = Integer.parseInt(request.getParameter("post_id"));

		// *** Given ***/
		int attachment_id = 0;
		if (request.getParameter("attachment_id") != null)
			attachment_id = Integer.parseInt(request
					.getParameter("attachment_id"));
		AttachmentVo attachmentVo = null;
		attachmentVo = attachmentService.getAttachment(attachment_id);

		/*** When ***/

		/*** Then ***/
		logger.debug("71 attachmentVo:{}", attachmentVo);

		String fileName = attachmentVo.getAttachment_name();
		String path = attachmentVo.getAttachment_path();

		byte[] b = new byte[4096];
		FileInputStream fis = new FileInputStream(path);

		File file = new File(path);

		String mimeType = getServletContext().getMimeType(path);
		if (mimeType == null) {
			mimeType = "application/octet-stream;charset=utf-8";
		}

		response.setHeader("Content-Disposition", "attachment;fileName="
				+ fileName);
		ServletOutputStream out = response.getOutputStream();

		int numRead;
		while (true) {
			numRead = fis.read(b, 0, b.length);
			if (numRead == -1)
				break;
			out.write(b, 0, numRead);
		}
		out.flush();
		out.close();
		fis.close();

		//
		// String downloadName = null;
		// if (request.getHeader("user-agent").indexOf("MSIE") == -1) {
		// downloadName = new String(fileName.getBytes("UTF-8"), "8859_1");
		// } else {
		// downloadName = new String(fileName.getBytes("EUC-KR"), "8859_1");
		// }
		//
		// FileInputStream fileInputStream = new FileInputStream(file);
		// ServletOutputStream servletOutputStream = response.getOutputStream();
		//
		// byte b[] = new byte[1024];
		// int data = 0;
		//
		// while ((data = (fileInputStream.read(b, 0, b.length))) != -1) {
		// servletOutputStream.write(b, 0, data);
		// }
		//
		// servletOutputStream.flush();
		// servletOutputStream.close();
		// fileInputStream.close();

		//
		// request.setCharacterEncoding("UTF-8");
		// String filename = attachmentVo.getAttachment_name();
		// String orgfilename = attachmentVo.getAttachment_name();
		// String savePath = attachmentVo.getAttachment_path();
		//
		// InputStream in = null;
		// OutputStream os = null;
		// File file = null;
		// boolean skip = false;
		// String client = "";
		//
		// try {
		// // 파일을 읽어 스트림에 담기
		// try {
		// file = new File(savePath, filename);
		// in = new FileInputStream(file);
		// } catch (FileNotFoundException fe) {
		// skip = true;
		// }
		//
		// client = request.getHeader("User-Agent");
		//
		// // 파일 다운로드 헤더 지정
		// response.reset();
		// response.setContentType("application/octet-stream");
		// response.setHeader("Content-Description", "JSP Generated Data");
		//
		// if (!skip) {
		//
		// // IE
		// if (client.indexOf("MSIE") != -1) {
		// response.setHeader(
		// "Content-Disposition",
		// "attachment; filename="
		// + new String(orgfilename
		// .getBytes("KSC5601"), "ISO8859_1"));
		//
		// } else {
		// // 한글 파일명 처리
		// orgfilename = new String(orgfilename.getBytes("utf-8"),
		// "iso-8859-1");
		// response.setHeader("Content-Disposition",
		// "attachment; filename=\"" + orgfilename + "\"");
		// response.setHeader("Content-Type",
		// "application/octet-stream; charset=utf-8");
		// }
		//
		// response.setHeader("Content-Length", "" + file.length());
		//
		// os = response.getOutputStream();
		// byte b[] = new byte[(int) file.length()];
		// int leng = 0;
		//
		// while ((leng = in.read(b)) > 0) {
		// os.write(b, 0, leng);
		// }
		//
		// }
		// in.close();
		// os.close();
		//
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

		// set board_id,post_id,replyList,attachmentList,postVo
		// board_id
//		request.setAttribute("board_id", board_id);
//		// post_id
//		request.setAttribute("post_id", post_id);
//		// replyList
//		List<ReplyVo> replyList = null;
//		replyList = replyService.replyList(post_id);
//		request.setAttribute("replyList", replyList);
//		// attachmentList
//		List<AttachmentVo> attachmentList = null;
//		attachmentList = attachmentService.getAttachmentList(post_id);
//		request.setAttribute("attachmentList", attachmentList);
//		// postVo
//		PostVo postVo = null;
//		logger.debug("150 post_id:{}", post_id);
//
//		postVo = postService.getPost(post_id);
//
//		request.setAttribute("postVo", postVo);
//
//		request.getRequestDispatcher("/post/postDetail.jsp").forward(request,
//				response);
	}
}
