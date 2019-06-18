package kr.or.ddit.post.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.paging.model.PageVo;
import kr.or.ddit.post.model.PostVo;

public interface IPostDao {

	List<PostVo> postList(int board_id);

	List<PostVo> postPagingList(PageVo pageVo);

	int postInsert(PostVo postVo);

	int postModify(PostVo pvo);

	int postDelete(int post_id);

	PostVo getLatestPost();

	int updateStep(PostVo PostVo);

	int postReply(PostVo PostVo);

	List<PostVo> postPagingList(Map<String, Object> map);

	PostVo getPost(int post_id);

	int postCnt(int board_id);

}
