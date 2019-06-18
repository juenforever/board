package kr.or.ddit.post.controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/fileUpload")
@MultipartConfig(maxFileSize = 1024 * 1024 * 3, maxRequestSize = 1024 * 1024 * 150)
public class PostUploadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory
			.getLogger(PostUploadController.class);

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String directory = "C://JSP/upload";
		int maxSize = 1024 * 1024 * 100;
		String encoding = "UTF-8";

		MultipartRequest multipartRequest = new MultipartRequest(request,
				directory, maxSize, encoding, new DefaultFileRenamePolicy());
		Enumeration fileNames = multipartRequest.getFileNames();

		int board_id = Integer.parseInt(multipartRequest
				.getParameter("board_id"));
		String parameter = null;
		while (fileNames.hasMoreElements()) {
			parameter = (String) fileNames.nextElement();
			String fileName = multipartRequest.getOriginalFileName(parameter);
			String fileRealName = multipartRequest.getFilesystemName("file");
			logger.debug("fileName:{}", fileName);
			logger.debug("fileRealName:{}", fileRealName);

		}

		request.setAttribute("board_id", board_id);
		String fileName = multipartRequest.getOriginalFileName(parameter);
		request.setAttribute("filName", fileName);
		request.getRequestDispatcher("/post/postRegister.jsp").forward(request,
				response);
		//
		// if (part1.getSize() > 0) {
		//
		// String contentDisposition = part1.getHeader("content-disposition");
		// String fileName = PartUtil.getFileName(contentDisposition);
		// String ext = PartUtil.getExt(fileName);
		// ext = ext.equals("") ? "" : "." + ext;
		//
		// Collection<String> headerNames = part1.getHeaderNames();
		//
		// for (String header : headerNames)
		// logger.debug("{} : {} ", header, part1.getHeader(header));
		//
		// }

	}
}