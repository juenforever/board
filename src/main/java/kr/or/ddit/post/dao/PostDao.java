package kr.or.ddit.post.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.mybatis.MyBatisUtil;
import kr.or.ddit.paging.model.PageVo;
import kr.or.ddit.post.model.PostVo;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PostDao implements IPostDao {

	private static final Logger logger = LoggerFactory.getLogger(PostDao.class);

	@Override
	public List<PostVo> postList(int board_id) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		List<PostVo> postList = sqlSession
				.selectList("post.postList", board_id);
		sqlSession.commit();
		sqlSession.close();
		return postList;
	}

	@Override
	public List<PostVo> postPagingList(PageVo pageVo) {
		List<PostVo> postList = null;
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		postList = sqlSession.selectList("post.postPagingList", pageVo);
		sqlSession.commit();
		sqlSession.close();
		return postList;
	}

	@Override
	public int postInsert(PostVo postVo) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int postInsertCnt = sqlSession.insert("post.postInsert", postVo);
		sqlSession.commit();
		sqlSession.close();
		return postInsertCnt;
	}

	@Override
	public PostVo getPost(int post_id) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		PostVo postVo = sqlSession.selectOne("post.getPost", post_id);
		sqlSession.commit();
		sqlSession.close();
		return postVo;
	}

	@Override
	public int postModify(PostVo pvo) {

		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int modifyCnt = sqlSession.update("post.postModify", pvo);
		sqlSession.commit();
		sqlSession.close();
		return modifyCnt;
	}

	@Override
	public int postDelete(int post_id) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int deleteCnt = sqlSession.update("post.postDelete", post_id);
		sqlSession.commit();
		sqlSession.close();
		return deleteCnt;
	}

	@Override
	public PostVo getLatestPost() {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		PostVo postVo = sqlSession.selectOne("post.getLatestPost");
		sqlSession.commit();
		sqlSession.close();
		return postVo;
	}

	@Override
	public int updateStep(PostVo PostVo) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int updateCnt = sqlSession.update("post.updateStep", PostVo);
		sqlSession.commit();
		sqlSession.close();
		return updateCnt;

	}

	@Override
	public int postReply(PostVo PostVo) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int postInsertCnt = sqlSession.insert("post.postReply", PostVo);
		sqlSession.commit();
		sqlSession.close();
		return postInsertCnt;
	}

	@Override
	public List<PostVo> postPagingList(Map<String, Object> map) {
		List<PostVo> postList = null;
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		postList = sqlSession.selectList("post.postPagingList", map);
		sqlSession.commit();
		sqlSession.close();
		return postList;
	}

	@Override
	public int postCnt(int board_id) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int postCnt = sqlSession.selectOne("post.postCnt", board_id);
		sqlSession.commit();
		sqlSession.close();
		return postCnt;
	}
}
