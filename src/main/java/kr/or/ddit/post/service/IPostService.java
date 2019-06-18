package kr.or.ddit.post.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.paging.model.PageVo;
import kr.or.ddit.post.model.PostVo;

public interface IPostService {

	List<PostVo> postList(int board_id);

	int postInsert(PostVo postVo);

	PostVo getPost(int post_id);

	int postDelete(int post_id);

	PostVo getLatestPost();

	Map<String, Object> postPagingList(Map<String, Object> map);

	int postCnt(int board_id);

	int postModify(PostVo postVo);

	int updateStep(PostVo postVo);

	int postReply(PostVo postVo);

}
