package kr.or.ddit.post.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.post.dao.IPostDao;
import kr.or.ddit.post.dao.PostDao;
import kr.or.ddit.post.model.PostVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PostService implements IPostService {

	private static final Logger logger = LoggerFactory
			.getLogger(PostService.class);
	IPostDao postDao = new PostDao();

	@Override
	public List<PostVo> postList(int board_id) {

		List<PostVo> postList = postDao.postList(board_id);
		logger.debug("postList postList : {}", postList);

		return postList;
	}

	@Override
	public int postInsert(PostVo postVo) {
		return postDao.postInsert(postVo);
	}

	@Override
	public PostVo getPost(int post_id) {
		return postDao.getPost(post_id);
	}

	@Override
	public int postDelete(int post_id) {
		return postDao.postDelete(post_id);
	}

	@Override
	public PostVo getLatestPost() {
		return postDao.getLatestPost();
	}

	@Override
	public Map<String, Object> postPagingList(Map<String, Object> map) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<PostVo> postList = postDao.postPagingList(map);
		resultMap.put("postList", postList);
		int postCnt = (int) map.get("board_id");
		postCnt = postDao.postCnt((int) map.get("board_id"));
		int pageSize = (int) map.get("pageSize");
		int paginationSize = (int) Math.ceil((double) postCnt / pageSize);
		resultMap.put("paginationSize", paginationSize);

		return resultMap;
	}

	@Override
	public int postCnt(int board_id) {
		return postDao.postCnt(board_id);
	}

	@Override
	public int postModify(PostVo postVo) {
		return postDao.postModify(postVo);
	}

	@Override
	public int updateStep(PostVo postVo) {
		return postDao.updateStep(postVo);
	}

	@Override
	public int postReply(PostVo postVo) {
		return postDao.postReply(postVo);
	}
}
