package kr.or.ddit.reply.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.reply.dao.IReplyDao;
import kr.or.ddit.reply.dao.ReplyDao;
import kr.or.ddit.reply.model.ReplyVo;

public class ReplyService implements IReplyService {
	private static final Logger logger = LoggerFactory
			.getLogger(ReplyService.class);
	private IReplyDao replyDao = new ReplyDao();

	@Override
	public int replyInsert(ReplyVo replyVo) {
		int replyCnt = replyDao.replyInsert(replyVo);
		return replyCnt;
	}

	@Override
	public List<ReplyVo> replyList(int post_id) {
		List<ReplyVo> replyList = replyDao.replyList(post_id);
		return replyList;
	}

	@Override
	public int replyDelete(int reply_id) {
		return replyDao.replyDelete(reply_id);
	}
}
