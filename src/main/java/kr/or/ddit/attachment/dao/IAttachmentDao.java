package kr.or.ddit.attachment.dao;

import java.util.List;

import kr.or.ddit.attachment.model.AttachmentVo;

public interface IAttachmentDao {

	int attachmentInsert(AttachmentVo attachmentVo);

	int attachmentDelete(int attachment_id);

	List<AttachmentVo> getAttachmentList(int post_id);

	AttachmentVo getAttachment(int attachment_id);

}
