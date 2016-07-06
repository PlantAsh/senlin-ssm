package ws.senlin.service.impl;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.ActionContext;

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
			if (!us.equals(null)) {
				ActionContext.getContext().getSession().put("error", "账号已存在");
				return "error";
			}
//			userDAO.addSelective(user);
//			UserInformation usin = new UserInformation();
//			usin.setUserAccount(user.getUserAccount());
//			usin.setUserName(user.getUserAccount());
//			userInformationDAO.add(usin);
			return "success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ActionContext.getContext().getSession().put("error", "未知错误");
			return "error";
		}
	}

	public User loadUser(User user) throws Exception {
		// TODO Auto-generated method stub
		try {
			User us = new User();
			us = userDAO.loadUser(user.getUserAccount());
			if (us.equals(null)) {
				ActionContext.getContext().getSession().put("error", "账号或密码错误");
				return null;
			}
			
			if(!us.getUserPassword().equals(user.getUserPassword())) {
				ActionContext.getContext().getSession().put("error", "账号或密码错误");
				us.setUserLevel("error");
				return null;
			}else if(us.getUserLevel().equals("1")) {
				return us;
			}else {
				ActionContext.getContext().getSession().put("error", "账号或密码错误");
				us.setUserLevel("error");
				return null;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

//	public UserInformation getInformation(UserInformation usin) throws Exception {
//		// TODO Auto-generated method stub
//		try {
//			List<UserInformation> us = null;
//			us = userInformationDAO.getInformation(usin.getUserAccount());
//			UserInformation usin2 = new UserInformation();
//			Iterator<UserInformation> iterator = us.iterator();
//			usin2 = iterator.next();
//			return usin2;
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			throw e;
//		}
//	}
//
//	public String updateInformation(UserInformation usin) throws Exception {
//		try {
//			List<UserInformation> us = null;
//			us = userInformationDAO.getInformation(usin.getUserAccount());
//			UserInformation usin2 = new UserInformation();
//			Iterator<UserInformation> iterator = us.iterator();
//			usin2 = iterator.next();
//			usin.setInformationID(usin2.getInformationID());
//			userInformationDAO.updateInformation(usin);
//			return "success";
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			throw e;
//		}
//	}

}
