package ws.senlin.dao;

import java.util.List;

import ws.senlin.entity.PostsReply;

public interface PostsReplyDAO {
    int deleteByPrimaryKey(Integer replyId);

    int insert(PostsReply record);

    int addSelective(PostsReply record);

    List<PostsReply> loadReply(Integer postsSuper);

    int updateByPrimaryKeySelective(PostsReply record);

    int updateByPrimaryKey(PostsReply record);
}