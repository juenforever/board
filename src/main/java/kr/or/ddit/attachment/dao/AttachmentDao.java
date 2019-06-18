package kr.or.ddit.attachment.dao;

import java.util.List;

import kr.or.ddit.attachment.model.AttachmentVo;
import kr.or.ddit.mybatis.MyBatisUtil;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AttachmentDao implements IAttachmentDao {
	private static final Logger logger = LoggerFactory
			.getLogger(AttachmentDao.class);

	@Override
	public int attachmentInsert(AttachmentVo attachmentVo) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int insertCnt = sqlSession.insert("attachment.attachmentInsert",
				attachmentVo);
		sqlSession.commit();
		sqlSession.close();
		return insertCnt;
	}

	@Override
	public int attachmentDelete(int attachment_id) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int deleteCnt = sqlSession.update("attachment.attachmentDelete",
				attachment_id);
		sqlSession.commit();
		sqlSession.close();
		return deleteCnt;
	}

	@Override
	public List<AttachmentVo> getAttachmentList(int post_id) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		List<AttachmentVo> avoList = sqlSession.selectList(
				"attachment.getAttachmentList", post_id);
		sqlSession.commit();
		sqlSession.close();
		return avoList;
	}

	@Override
	public AttachmentVo getAttachment(int attachment_id) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		AttachmentVo attachmentVo = sqlSession.selectOne(
				"attachment.getAttachment", attachment_id);
		sqlSession.commit();
		sqlSession.close();
		return attachmentVo;

	}

}
