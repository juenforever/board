package kr.or.ddit.user.dao;

import java.util.List;

import kr.or.ddit.paging.model.PageVo;
import kr.or.ddit.user.model.UserVo;

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

public interface IUserDao {

	/**
	 * Method : userList 작성자 : PC01 변경이력 :
	 * 
	 * @return Method 설명 : 사용자 전체 조회
	 */
	List<UserVo> userList();

	UserVo getUser(String userId);

	/**
	 * Method : userPagingList 작성자 : PC01 변경이력 :
	 * 
	 * @param pageVo
	 * @return Method 설명 : 사용자 페이징 리스트 조회
	 */
	List<UserVo> userPagingList(PageVo pageVo);

	/**
	 * Method : usersCnt 작성자 : PC01 변경이력 :
	 * 
	 * @return Method 설명 : 사용자 전체수 조회
	 */
	int usersCnt();

	/**
	 * Method : insertUser 작성자 : PC01 변경이력 :
	 * 
	 * @param userVo
	 * @return Method 설명 : 사용자 등
	 */
	int insertUser(UserVo userVo);

	/**
	* Method : deleteUser
	* 작성자 : PC01
	* 변경이력 :
	* @param userId
	* @return
	* Method 설명 : 사용자 삭제
	*/
	int deleteUser(String userId);

	int updateUser(UserVo userVo);
	
}
