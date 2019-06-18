package kr.or.ddit.reply.model;

import java.util.Date;

public class ReplyVo {

	private int reply_id;
	private int post_id;
	private String reply_content;
	private Date reply_time;
	private String user_id;
	private String reply_delete;

	public int getReply_id() {
		return reply_id;
	}

	public void setReply_id(int reply_id) {
		this.reply_id = reply_id;
	}

	public int getPost_id() {
		return post_id;
	}

	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}

	public String getReply_content() {
		return reply_content;
	}

	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}

	public Date getReply_time() {
		return reply_time;
	}

	public void setReply_time(Date reply_time) {
		this.reply_time = reply_time;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getReply_delete() {
		return reply_delete;
	}

	public void setReply_delete(String reply_delete) {
		this.reply_delete = reply_delete;
	}

	@Override
	public String toString() {
		return "ReplyVo [reply_id=" + reply_id + ", post_id=" + post_id
				+ ", reply_content=" + reply_content + ", reply_time="
				+ reply_time + ", user_id=" + user_id + ", reply_delete="
				+ reply_delete + "]";
	}

}
