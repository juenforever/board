package kr.or.ddit.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.paging.model.PageVo;
import kr.or.ddit.user.dao.IUserDao;
import kr.or.ddit.user.dao.UserDao;
import kr.or.ddit.user.model.UserVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserService implements IUserService {
	private static final Logger logger = LoggerFactory
			.getLogger(UserService.class);
	IUserDao userdao = new UserDao();

	@Override
	public List<UserVo> userList() {

		List<UserVo> userList = userdao.userList();

		return userList;
	}

	@Override
	public UserVo getUser(String userId) {
		logger.debug("getUser userId:{}", userId);
		UserVo uservo = userdao.getUser(userId);
		System.out.println("userservice getUser " + uservo);
		logger.debug("getUser uservo:{}", uservo);
		return uservo;
	}

	@Override
	public Map<String, Object> userPagingList(PageVo pageVo) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<UserVo> userList = userdao.userPagingList(pageVo);
		resultMap.put("userList", userList);

		int usersCnt = userdao.usersCnt();
		int pageSize = pageVo.getPageSize();
		int paginationSize = (int) Math.ceil((double) usersCnt / pageSize);
		resultMap.put("paginationSize", paginationSize);

		return resultMap;
	}

	@Override
	public int insertUser(UserVo userVo) {
		return userdao.insertUser(userVo);
	}

	@Override
	public int deleteUser(String userId) {
		return userdao.deleteUser(userId);
	}

	@Override
	public int updateUser(UserVo userVo) {
		return userdao.updateUser(userVo);
	}
}
