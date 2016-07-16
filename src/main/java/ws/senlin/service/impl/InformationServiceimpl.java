package ws.senlin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ws.senlin.dao.InformationCodeDAO;
import ws.senlin.dao.PostsDAO;
import ws.senlin.dao.UserInformationDAO;
import ws.senlin.entity.InformationCode;
import ws.senlin.entity.Posts;
import ws.senlin.entity.UserInformation;
import ws.senlin.service.InformationService;

@Service("informationService")
public class InformationServiceimpl implements InformationService {
	@Resource
	private UserInformationDAO userInformationDAO;
	@Resource
	private InformationCodeDAO informationCodeDAO;
	@Resource
	private PostsDAO postsDAO;

	public UserInformation loadInformation(UserInformation usin) throws Exception {
		// TODO Auto-generated method stub
		try {
			UserInformation us = new UserInformation();
			us = userInformationDAO.loadInformation(usin.getUserAccount());
			return us;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
	}

	public List<InformationCode> loadByType(String codeType) throws Exception {
		// TODO Auto-generated method stub
		try {
			List<InformationCode> incd = null;
			incd = informationCodeDAO.loadByType(codeType);
			return incd;
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}
	
	public String updateInformation(UserInformation usin) throws Exception {
		try {
			UserInformation us = new UserInformation();
			us = userInformationDAO.loadInformation(usin.getUserAccount());
			usin.setInformationId(us.getInformationId());
			Posts pt = new Posts();
			pt.setUserAccount(usin.getUserAccount());
			pt.setUserName(usin.getUserName());
			int i = userInformationDAO.updateInformationSelective(usin);
			int a = postsDAO.updatePostsSelective(pt);
			if(i == 0 | a == 0) {
				return "数据库错误";
			}
			return "success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
	}

}
