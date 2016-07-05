package ws.senlin.dao;

import ws.senlin.entity.UserInformation;

public interface UserInformationDAO {
    int deleteByPrimaryKey(Integer informationId);

    int insert(UserInformation record);

    int insertSelective(UserInformation record);

    UserInformation selectByPrimaryKey(Integer informationId);

    int updateByPrimaryKeySelective(UserInformation record);

    int updateByPrimaryKey(UserInformation record);
}