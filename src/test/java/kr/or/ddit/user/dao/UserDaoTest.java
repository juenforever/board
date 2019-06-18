package kr.or.ddit.user.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import kr.or.ddit.paging.model.PageVo;
import kr.or.ddit.user.model.UserVo;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * UserDaoTest.java
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
public class UserDaoTest {

	private static final Logger logger = LoggerFactory
			.getLogger(UserDaoTest.class);

	private IUserDao userDao;

	@BeforeClass
	public static void BeforeClass() {
		logger.debug("beforeClass");
	}

	@Before
	public void setup() {
		userDao = new UserDao();
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
	 * Method : userListTest 작성자 : PC01 변경이력 : Method 설명 : 사용자 전체 조회 테스트
	 */
	@Test
	public void userListTest() {

		/*** Given ***/

		/*** When ***/
		List<UserVo> userList = userDao.userList();
		/*** Then ***/
		// assertEquals("brown", userList.get(0).getUserId());
		// assertEquals(5, userList.size());
		logger.debug("userList : {}", userList);

	}

	/**
	 * Method : getUserTest 작성자 : PC01 변경이력 : Method 설명 : 사용자 조회 테스트
	 */
	// @Test
	// public void getUserTest() {
	// /*** Given ***/
	// String userId = "brown";
	// /*** When ***/
	// UserVo userVo = userDao.getUser(userId);
	// /*** Then ***/
	// assertEquals("브라운", userVo.getName());
	// logger.debug("userVo : {}", userVo);
	//
	// }

	/**
	 * Method : userPagingListTest 작성자 : PC01 변경이력 : Method 설명 : 사용자 페이징 리스트 조회
	 * 테스트
	 */
	@Test
	public void userPagingListTest() {
		/*** Given ***/
		PageVo pageVo = new PageVo(1, 10);

		/*** When ***/
		List<UserVo> userList = userDao.userPagingList(pageVo);

		/*** Then ***/
		assertNotNull(userList);
		assertEquals(10, userList.size());

	}

	/**
	 * Method : usersCntTest 작성자 : PC01 변경이력 : Method 설명 : 사용자 전체수 조회 테스트
	 */

	@Test
	public void usersCntTest() {
		/*** Given ***/

		/*** When ***/
		int usersCnt = userDao.usersCnt();
		/*** Then ***/
		assertEquals(117, usersCnt);
	}

	/**
	 * Method : insertUserTest 작성자 : PC01 변경이력 : Method 설명 : 사용자 등록 테스트
	 */
	@Test
	public void insertUserTest() {
		/*** Given ***/
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 사용자 정보를 담고 있는 vo 객체 준비
		UserVo userVo = null;
		try {
			userVo = new UserVo("con2y", "userTest", "중앙로", "userTest1234",
					"대전광역시 중구 중앙로 76", "영민빌딩 2층 204호", "34940",
					sdf.parse("2019-05-05"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*** When ***/
		// userDao.insertUser()
		int insertCnt = userDao.insertUser(userVo);
		/*** Then ***/
		assertEquals(1, insertCnt);
		// insertCnt(1)
		userDao.deleteUser(userVo.getUserId());

	}

	@Test
	public void updateUserTest() {
		/*** Given ***/
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 사용자 정보를 담고 있는 vo 객체 준비
		UserVo userVo = null;
		try {
			userVo = new UserVo("cony", "userTest", "중앙로", "userTest1234",
					"대전광역시 중구 중앙로 76", "영민빌딩 2층 204호", "34940",
					sdf.parse("2019-05-05"));

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*** When ***/
		// userDao.insertUser()
		logger.debug("userVo : {}", userVo);
		int updateCnt = userDao.updateUser(userVo);
		/*** Then ***/
		assertEquals(1, updateCnt);
		// insertCnt(1)

	}

}
