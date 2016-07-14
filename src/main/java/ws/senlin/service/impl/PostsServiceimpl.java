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

	public List<Posts> loadPosts(Posts posts, int first, int number) throws Exception {
		try {
			List<Posts> up = null;
			up = postsDAO.loadPosts(posts.getPostsFloor(), first, number);
			return up;
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

	public String addPosts(Posts posts) throws Exception {
		// TODO Auto-generated method stub
		try {
			postsDAO.addSelective(posts);
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
			up = postsDAO.getPosts(posts.getPostsId());
			if(up.isEmpty()) {
				return null;
			}
			Iterator<Posts> iterator = up.iterator();
			Posts usp = new Posts();
			usp = iterator.next();
			return usp;
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

	public int getPage(Posts posts) throws Exception {
		// TODO Auto-generated method stub
		try {
			int up = 0;
			up = postsDAO.getPage(posts.getPostsFloor());
			return up;
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

}
