package ws.senlin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ws.senlin.dao.PostsReplyDAO;
import ws.senlin.entity.PostsReply;
import ws.senlin.service.ReplyService;

@Service("replyService")
public class ReplyServiceimpl implements ReplyService {
	@Resource
	private PostsReplyDAO postsReplyDAO;

	public List<PostsReply> loadPosts(PostsReply postsReply) throws Exception {
		// TODO Auto-generated method stub
		try {
			List<PostsReply> ur = null;
			ur = postsReplyDAO.loadReply(postsReply.getPostsSuper());
			return ur;
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

	public String addPosts(PostsReply postsReply) throws Exception {
		// TODO Auto-generated method stub
		try {
			postsReplyDAO.addSelective(postsReply);
			return "success";
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

}
