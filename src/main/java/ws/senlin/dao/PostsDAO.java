package ws.senlin.dao;

import ws.senlin.entity.Posts;

public interface PostsDAO {
    int deleteByPrimaryKey(Integer postsId);

    int insert(Posts record);

    int insertSelective(Posts record);

    Posts selectByPrimaryKey(Integer postsId);

    int updateByPrimaryKeySelective(Posts record);

    int updateByPrimaryKey(Posts record);
}