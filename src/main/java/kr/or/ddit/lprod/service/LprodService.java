package kr.or.ddit.lprod.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.lprod.dao.ILprodDao;
import kr.or.ddit.lprod.dao.LprodDao;
import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.paging.model.PageVo;

/**
 * LprodService.java
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
public class LprodService implements ILprodService {

	ILprodDao lproddao = new LprodDao();

	// 사용자 전체 리스트 조회
	/**
	 * Method : lprodList 작성자 : PC01 변경이력 :
	 * 
	 * @return Method 설명 : 사용자 전체 리스트 조회
	 */

	@Override
	public List<LprodVo> lprodList() {

		List<LprodVo> lprodList = lproddao.lprodList();

		return lprodList;
	}

	@Override
	public Map<String, Object> lprodPagingList(PageVo pageVo) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<LprodVo> lprodList = lproddao.lprodPagingList(pageVo);
		resultMap.put("lprodList", lprodList);

		// paginationSize 변경
		int lprodCnt = lproddao.lprodCnt();
		int pageSize = pageVo.getPageSize();
		int paginationSize = (int) Math.ceil((double) lprodCnt / pageSize);
		resultMap.put("paginationSize", paginationSize);

		return resultMap;
	}
}
