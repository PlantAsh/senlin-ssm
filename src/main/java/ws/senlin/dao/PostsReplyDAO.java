package ws.senlin.dao;

import ws.senlin.entity.PostsReply;

public interface PostsReplyDAO {
    int deleteByPrimaryKey(Integer replyId);

    int insert(PostsReply record);

    int insertSelective(PostsReply record);

    PostsReply selectByPrimaryKey(Integer replyId);

    int updateByPrimaryKeySelective(PostsReply record);

    int updateByPrimaryKey(PostsReply record);
}