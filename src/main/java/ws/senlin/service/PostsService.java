package ws.senlin.service;

import java.util.List;

import ws.senlin.entity.Posts;

public interface PostsService {
	public List<Posts> loadPosts(Posts userPosts, int first, int number) throws Exception;
	
	public int getPage(Posts userPosts) throws Exception;
	
	public String addPosts(Posts userPosts) throws Exception;
	
	public Posts getPosts(Posts userPosts) throws Exception;

}
