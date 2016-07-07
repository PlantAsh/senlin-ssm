package ws.senlin.service;

import java.util.List;

import ws.senlin.entity.InformationCode;
import ws.senlin.entity.UserInformation;

public interface InformationService {
	public UserInformation loadInformation(UserInformation usin) throws Exception;
	
	public List<InformationCode> loadByType(String codeType) throws Exception;
	
	public String updateInformation(UserInformation usin) throws Exception;
}
