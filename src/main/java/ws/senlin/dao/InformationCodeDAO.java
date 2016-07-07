package ws.senlin.dao;

import java.util.List;

import ws.senlin.entity.InformationCode;

public interface InformationCodeDAO {
    int deleteByPrimaryKey(Integer codeId);

    int insert(InformationCode record);

    int insertSelective(InformationCode record);

    List<InformationCode> loadByType(String codeType);

    int updateByPrimaryKeySelective(InformationCode record);

    int updateByPrimaryKey(InformationCode record);
}