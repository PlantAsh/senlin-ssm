package ws.senlin.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ws.senlin.dao.UserDAO;
import ws.senlin.dao.UserInformationDAO;
import ws.senlin.entity.User;
import ws.senlin.entity.UserInformation;
import ws.senlin.service.UserService;

@Service("userService")
public class UserServiceimpl implements UserService {
	@Resource
	private UserDAO userDAO;
	
	@Resource
	private UserInformationDAO userInformationDAO;

	public String addUser(User user) throws Exception {
		// TODO Auto-generated method stub
		try {
			User us = new User();
			us = userDAO.loadUser(user.getUserAccount());
			if (us == null) {
				userDAO.addSelective(user);
				UserInformation usin = new UserInformation();
				usin.setUserAccount(user.getUserAccount());
				usin.setUserName(user.getUserAccount());
				userInformationDAO.addSelective(usin);
				return "success";
			} else {
				return "账号已存在";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "未知错误";
		}
	}

	public User loadUser(User user) throws Exception {
		// TODO Auto-generated method stub
		try {
			User us = new User();
			us = userDAO.loadUser(user.getUserAccount());
			return us;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
	}

}
