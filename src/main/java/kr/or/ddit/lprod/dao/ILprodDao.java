package kr.or.ddit.lprod.dao;

import java.util.List;

import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.paging.model.PageVo;

/**
 * IUserDao.java
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
/**
 * IUserDao.java
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
/**
 * IUserDao.java
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
public interface ILprodDao {

	List<LprodVo> lprodList();

	// 상품 페이징 리스트 조회
	List<LprodVo> lprodPagingList(PageVo pageVo);

	// 상품 전체수 조회
	int lprodCnt();

	LprodVo getLprod(int lprod_id);

}
