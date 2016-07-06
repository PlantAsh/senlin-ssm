package ws.senlin.service;

import java.util.List;

import ws.senlin.entity.PostsReply;

public interface ReplyService {
public List<PostsReply> loadPosts(PostsReply userReply) throws Exception;
	
	public String addPosts(PostsReply userReply) throws Exception;

}
