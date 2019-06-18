package kr.or.ddit.post.model;

import java.util.Date;

public class PostVo {
	private int post_id;
	private Date post_time;
	private String post_title;
	private String post_content;
	private String post_delete;
	private int board_id;
	private String user_id;
	private int ref;
	private int re_step;
	private int re_level;

	public int getPost_id() {
		return post_id;
	}

	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}

	public Date getPost_time() {
		return post_time;
	}

	public void setPost_time(Date post_time) {
		this.post_time = post_time;
	}

	public String getPost_title() {
		return post_title;
	}

	public void setPost_title(String post_title) {
		this.post_title = post_title;
	}

	public String getPost_content() {
		return post_content;
	}

	public void setPost_content(String post_content) {
		this.post_content = post_content;
	}

	public String getPost_delete() {
		return post_delete;
	}

	public void setPost_delete(String post_delete) {
		this.post_delete = post_delete;
	}

	public int getBoard_id() {
		return board_id;
	}

	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public int getRef() {
		return ref;
	}

	public void setRef(int ref) {
		this.ref = ref;
	}

	public int getRe_step() {
		return re_step;
	}

	public void setRe_step(int re_step) {
		this.re_step = re_step;
	}

	public int getRe_level() {
		return re_level;
	}

	public void setRe_level(int re_level) {
		this.re_level = re_level;
	}

	@Override
	public String toString() {
		return "PostVo [post_id=" + post_id + ", post_time=" + post_time
				+ ", post_title=" + post_title + ", post_content="
				+ post_content + ", post_delete=" + post_delete + ", board_id="
				+ board_id + ", user_id=" + user_id + ", ref=" + ref
				+ ", re_step=" + re_step + ", re_level=" + re_level + "]";
	}

}
