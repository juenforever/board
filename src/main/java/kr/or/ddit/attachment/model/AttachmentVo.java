package kr.or.ddit.attachment.model;

public class AttachmentVo {

	private int attachment_id;
	private int post_id;
	private String attachment_path;
	private String attachment_name;

	public int getAttachment_id() {
		return attachment_id;
	}

	public void setAttachment_id(int attachment_id) {
		this.attachment_id = attachment_id;
	}

	public int getPost_id() {
		return post_id;
	}

	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}

	public String getAttachment_path() {
		return attachment_path;
	}

	public void setAttachment_path(String attachment_path) {
		this.attachment_path = attachment_path;
	}

	public String getAttachment_name() {
		return attachment_name;
	}

	public void setAttachment_name(String attachment_name) {
		this.attachment_name = attachment_name;
	}

	@Override
	public String toString() {
		return "AttachmentVo [attachment_id=" + attachment_id + ", post_id="
				+ post_id + ", attachment_path=" + attachment_path
				+ ", attachment_name=" + attachment_name + "]";
	}

}
