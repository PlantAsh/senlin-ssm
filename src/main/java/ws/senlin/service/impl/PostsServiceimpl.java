package ws.senlin.service.impl;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ws.senlin.dao.PostsDAO;
import ws.senlin.entity.Posts;
import ws.senlin.service.PostsService;

@Service("postsService")
public class PostsServiceimpl implements PostsService {
	@Resource
	private PostsDAO postsDAO;

	public List<Posts> loadPosts(Posts posts, int first) throws Exception {
		try {
			List<Posts> up = null;
//			up = PostsDAO.loadPosts(posts, first);
			return up;
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

	public String addPosts(Posts posts) throws Exception {
		// TODO Auto-generated method stub
		try {
//			PostsDAO.add(posts);
			return "success";
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

	public Posts getPosts(Posts posts) throws Exception {
		// TODO Auto-generated method stub
		try {
			List<Posts> up = null;
//			up = PostsDAO.getPosts(posts);
//			if(up.isEmpty()) {
//				ActionContext.getContext().getSession().put("error", "���ݿ����");
//				return null;
//			}
			Iterator<Posts> iterator = up.iterator();
			Posts usp = new Posts();
			usp = iterator.next();
			return usp;
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

	public int getQuantity(Posts posts) throws Exception {
		// TODO Auto-generated method stub
		try {
			int up = 0;
//			up = PostsDAO.getQuantity(posts);
			return up;
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

}