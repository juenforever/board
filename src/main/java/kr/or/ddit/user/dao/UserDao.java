package kr.or.ddit.user.dao;

import java.util.List;

import kr.or.ddit.mybatis.MyBatisUtil;
import kr.or.ddit.paging.model.PageVo;
import kr.or.ddit.user.model.UserVo;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserDao implements IUserDao {
	private static final Logger logger = LoggerFactory.getLogger(UserDao.class);

	@Override
	public List<UserVo> userList() {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		List<UserVo> userList = sqlSession.selectList("user.userList");
		sqlSession.close();
		return userList;

	}

	@Override
	public UserVo getUser(String userId) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		UserVo getUser = sqlSession.selectOne("user.getUser", userId);
		sqlSession.close();
		return getUser;
	}

	// 사용자 페이징 리스트 조회
	@Override
	public List<UserVo> userPagingList(PageVo pageVo) {
		List<UserVo> userList = null;
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		userList = sqlSession.selectList("user.userPagingList", pageVo);
		sqlSession.close();
		return userList;
	}

	// 사용자 전체수 조회
	@Override
	public int usersCnt() {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int usersCnt = sqlSession.selectOne("user.usersCnt");
		sqlSession.close();
		return usersCnt;
	}

	/**
	 * Method : insertUser 작성자 : PC01 변경이력 :
	 * 
	 * @param userVo
	 * @return Method 설명 : 사용자 등
	 */

	@Override
	public int insertUser(UserVo userVo) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		logger.debug("insertUser userVo : {}", userVo);
		int insertCnt = sqlSession.insert("user.insertUser", userVo);
		sqlSession.commit();
		sqlSession.close();
		return insertCnt;
	}

	/**
	 * Method : deleteUser 작성자 : PC01 변경이력 :
	 * 
	 * @param userId
	 * @return Method 설명 : 사용자 삭제
	 */
	@Override
	public int deleteUser(String userId) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int deleteCnt = sqlSession.delete("user.deleteUser", userId);
		sqlSession.commit();
		sqlSession.close();
		return deleteCnt;
	}

	@Override
	public int updateUser(UserVo userVo) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		logger.debug("update User userVo : {}", userVo);
		int updateCnt = sqlSession.update("user.updateUser", userVo);
		sqlSession.commit();
		sqlSession.close();
		return updateCnt;
	}

}
