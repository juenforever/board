package kr.or.ddit.attachment.service;

import java.util.List;

import kr.or.ddit.attachment.dao.AttachmentDao;
import kr.or.ddit.attachment.dao.IAttachmentDao;
import kr.or.ddit.attachment.model.AttachmentVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AttachmentService implements IAttachmentService {
	private static final Logger logger = LoggerFactory
			.getLogger(AttachmentService.class);
	IAttachmentDao attachmentDao = new AttachmentDao();

	@Override
	public List<AttachmentVo> getAttachmentList(int post_id) {
		return attachmentDao.getAttachmentList(post_id);
	}

	@Override
	public int attachmentInsert(AttachmentVo attachmentVo) {
		return attachmentDao.attachmentInsert(attachmentVo);
	}

	@Override
	public int attchmentDelete(int attachment_id) {
		return attachmentDao.attachmentDelete(attachment_id);
	}

	@Override
	public AttachmentVo getAttachment(int attachment_id) {
		return attachmentDao.getAttachment(attachment_id);
	}

}
