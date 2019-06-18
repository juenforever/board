package kr.or.ddit.user.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import kr.or.ddit.paging.model.PageVo;
import kr.or.ddit.user.model.UserVo;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserServiceTest {
	private IUserService userService;
	private static final Logger logger = LoggerFactory
			.getLogger(UserServiceTest.class);

	@BeforeClass
	public static void BeforeClass() {
		logger.debug("beforeClass");
	}

	@Before
	public void setup() {
		userService = new UserService();
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

	// @Test
	// public void userListTest() {
	// /*** Given ***/
	// IUserService userService = new UserService();
	// /*** When ***/
	// List<UserVo> userList = userService.userList();
	// /*** Then ***/
	// // assertEquals("brown", userList.get(0).getUserId());
	// assertEquals(108, userList.size());
	// logger.debug("userList : {}", userList);
	//
	// }

	@Test
	public void getUserTest() {
		/*** Given ***/
		IUserService userService = new UserService();
		String userId = "brown";
		/*** When ***/
		UserVo userVo = userService.getUser(userId);
		/*** Then ***/
		assertEquals("브라운", userVo.getName());

		logger.debug("userVo : {}", userVo);

	}

	// 사용자 전체리스트를 조회하는 메소드
	// 1.메소드 인자가 필요한가? : 별다른 인자는 불필요 x
	// 2.리턴 타입은 뭐가 될까? : List<UserVo>
	// 3.메소드 이름은 뭐가 적당하지? :userList

	// @Test
	// public List<UserVo> userListTest() {
	// /*** Given ***/
	// IUserService userService = new UserService();
	// /*** When ***/
	// List<UserVo> userList = userService.userList();
	// /*** Then ***/
	// assertNotNull(userList);
	// assertEquals(5, userList.size());
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
		Map<String, Object> resultMap = userService.userPagingList(pageVo);
		List<UserVo> userList = (List<UserVo>) resultMap.get("userList");
		int paginationSize = (Integer) resultMap.get("paginationSize");

		/*** Then ***/
		assertNotNull(userList);
		assertEquals(10, userList.size());

		assertEquals(12, paginationSize);
	}

	// @Test
	// public void ceilTest() {
	//
	// /*** Given ***/
	// int usersCnt = 105;
	// int pageSize = 10;
	// /*** When ***/
	// double pagenationSize = (int) Math.ceil((double) usersCnt / pageSize);
	// logger.debug("paginationSize : {}", pagenationSize);
	// /*** Then ***/
	// assertEquals(11, (int) pagenationSize);
	//
	// }

	@Test
	public void insertUserTest() {
		/*** Given ***/
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 사용자 정보를 담고 있는 vo 객체 준비
		UserVo userVo = null;
		try {
			userVo = new UserVo("jsde", "userTest", "중앙로", "userTest1234",
					"대전광역시 중구 중앙로 76", "영민빌딩 2층 204호", "34940",
					sdf.parse("2019-05-05"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*** When ***/
		// userDao.insertUser()
		int insertCnt = userService.insertUser(userVo);
		/*** Then ***/
		assertEquals(1, insertCnt);
		// insertCnt(1)
		userService.deleteUser(userVo.getUserId());

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
		int updateCnt = userService.updateUser(userVo);
		/*** Then ***/
		assertEquals(1, updateCnt);
		// insertCnt(1)

	}

}
