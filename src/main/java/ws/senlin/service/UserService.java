package ws.senlin.service;

import ws.senlin.entity.User;

public interface UserService {
	public String addUser(User user) throws Exception;
	
	public User loadUser(User user) throws Exception;

}
