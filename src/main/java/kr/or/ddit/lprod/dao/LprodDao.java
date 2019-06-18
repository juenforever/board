package kr.or.ddit.lprod.dao;

import java.util.List;

import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.mybatis.MyBatisUtil;
import kr.or.ddit.paging.model.PageVo;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * LprodDao.java
 *
 * @author PC01
 * @version 1.0
 * @see
 *
 *      <pre>
 * << 개정이력(Modification Information) >>
 * 
 * 수정자 수정내용
 * ------ ------------------------
 * PC01 최초 생성
 *
 * </pre>
 */
public class LprodDao implements ILprodDao {
	private static final Logger logger = LoggerFactory
			.getLogger(LprodDao.class);

	public static void main(String[] args) {
		/*** Given ***/
		ILprodDao lprodDao = new LprodDao();
		/*** When ***/
		List<LprodVo> lprodList = lprodDao.lprodList();
		/*** Then ***/
		logger.debug("lprodList : {}", lprodList);

	}

	/**
	 * Method : lprodList 작성자 : PC01 변경이력 :
	 * 
	 * @return Method 설명 : 상품 전체 조회
	 */
	@Override
	public List<LprodVo> lprodList() {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		List<LprodVo> lprodList = sqlSession.selectList("lprod.lprodList");
		sqlSession.close();
		return lprodList;

	}

	// 사용자 페이징 리스트 조회
	@Override
	public List<LprodVo> lprodPagingList(PageVo pageVo) {
		List<LprodVo> lprodList = null;
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		lprodList = sqlSession.selectList("lprod.lprodPagingList", pageVo);
		sqlSession.close();
		return lprodList;
	}

	// 사용자 전체수 조회
	@Override
	public int lprodCnt() {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int lprodCnt = sqlSession.selectOne("lprod.lprodCnt");
		sqlSession.close();
		return lprodCnt;
	}

	@Override
	public LprodVo getLprod(int lprod_id) {
		// TODO Auto-generated method stub
		return null;
	}
}
