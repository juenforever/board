package kr.or.ddit.reply.dao;

import java.util.List;

import kr.or.ddit.reply.model.ReplyVo;

public interface IReplyDao {

	int replyInsert(ReplyVo replyVo);

	List<ReplyVo> replyList(int post_id);

	int replyDelete(int reply_id);

}
