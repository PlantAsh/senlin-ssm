package ws.senlin.service;

import ws.senlin.entity.User;
import ws.senlin.entity.UserInformation;

public interface UserService {
	public String addUser(User user) throws Exception;
	
	public User loadUser(User user) throws Exception;
	
//	public UserInformation getInformation(UserInformation usin) throws Exception;
//	
//	public String updateInformation(UserInformation usin) throws Exception;

}
