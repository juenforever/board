package kr.or.ddit.reply.dao;

import java.util.List;

import kr.or.ddit.mybatis.MyBatisUtil;
import kr.or.ddit.reply.model.ReplyVo;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReplyDao implements IReplyDao {
	private static final Logger logger = LoggerFactory
			.getLogger(ReplyDao.class);

	@Override
	public int replyInsert(ReplyVo replyVo) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int insertCnt = sqlSession.insert("reply.replyInsert", replyVo);
		sqlSession.commit();
		sqlSession.close();
		return insertCnt;
	}

	@Override
	public List<ReplyVo> replyList(int post_id) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		List<ReplyVo> replyList = sqlSession.selectList("reply.replyList",
				post_id);
		sqlSession.commit();
		sqlSession.close();
		return replyList;
	}

	@Override
	public int replyDelete(int reply_id) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int boardDeleteCnt = sqlSession.update("reply.replyDelete", reply_id);
		sqlSession.commit();
		sqlSession.close();
		return boardDeleteCnt;
	}

}
