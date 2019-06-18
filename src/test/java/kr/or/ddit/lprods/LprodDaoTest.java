package kr.or.ddit.lprods;

import java.util.List;

import kr.or.ddit.lprod.dao.ILprodDao;
import kr.or.ddit.lprod.dao.LprodDao;
import kr.or.ddit.lprod.model.LprodVo;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * LprodDaoTest.java
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
public class LprodDaoTest {
	private static final Logger logger = LoggerFactory
			.getLogger(LprodDaoTest.class);

	private ILprodDao lprodDao;

	@BeforeClass
	public static void BeforeClass() {
		logger.debug("beforeClass");
	}

	@Before
	public void setup() {
		lprodDao = new LprodDao();
		logger.debug("setup");
	}

	@After
	public void teardown() {
		logger.debug("teardown");
	}

	@AfterClass
	public static void afterClass() {
		logger.debug("afterClass");
	}

	/**
	 * Method : lprodListTest 작성자 : PC01 변경이력 : Method 설명 : 사용자 전체 조회 테스트
	 */
	@Test
	public void lprodListTest() {

		/*** Given ***/

		/*** When ***/
		List<LprodVo> lprodList = lprodDao.lprodList();
		/*** Then ***/
		logger.debug("lprodList : {}", lprodList);

	}

	/**
	 * Method : getLprodTest 작성자 : PC01 변경이력 : Method 설명 : 사용자 조회 테스트
	 */

}
