package ws.senlin.dao;

import ws.senlin.entity.UserInformation;

public interface UserInformationDAO {
    int deleteByPrimaryKey(Integer informationId);

    int insert(UserInformation record);

    int addSelective(UserInformation record);

    UserInformation loadInformation(String userAccount);

    int updateInformationSelective(UserInformation record);

    int updateByPrimaryKey(UserInformation record);
}