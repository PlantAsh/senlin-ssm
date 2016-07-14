package ws.senlin.dao;

import java.util.List;

import ws.senlin.entity.Posts;

public interface PostsDAO {
    int deleteByPrimaryKey(Integer postsId);

    int insert(Posts record);

    int addSelective(Posts record);

    List<Posts> loadPosts(String postsFloor, int first, int number);
    
    List<Posts> getPosts(Integer postsId);
    
    int getPage(String postsFloor);

    int updateByPrimaryKeySelective(Posts record);

    int updateByPrimaryKey(Posts record);
}