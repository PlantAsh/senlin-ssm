package ws.senlin.dao;

import ws.senlin.entity.UserCode;

public interface UserCodeDAO {
    int deleteByPrimaryKey(Integer codeId);

    int insert(UserCode record);

    int insertSelective(UserCode record);

    UserCode selectByPrimaryKey(Integer codeId);

    int updateByPrimaryKeySelective(UserCode record);

    int updateByPrimaryKey(UserCode record);
}