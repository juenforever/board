package kr.or.ddit.board.model;

import java.util.Date;

public class BoardVo {

	private int board_id;
	private String board_name;
	private String board_usage;
	private Date board_time;
	private String user_id;

	public int getBoard_id() {
		return board_id;
	}

	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}

	public String getBoard_name() {
		return board_name;
	}

	public void setBoard_name(String board_name) {
		this.board_name = board_name;
	}

	public String getBoard_usage() {
		return board_usage;
	}

	public void setBoard_usage(String board_usage) {
		this.board_usage = board_usage;
	}

	public Date getBoard_time() {
		return board_time;
	}

	public void setBoard_time(Date board_time) {
		this.board_time = board_time;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	@Override
	public String toString() {
		return "BoardVo [board_id=" + board_id + ", board_name=" + board_name
				+ ", board_usage=" + board_usage + ", board_time=" + board_time
				+ ", user_id=" + user_id + "]";
	}

}
