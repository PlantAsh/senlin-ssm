package ws.senlin.controller;

import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import ws.senlin.entity.User;
import ws.senlin.entity.UserInformation;
import ws.senlin.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController extends ActionSupport {
	private static final long serialVersionUID = -2266695172069461659L;
	@Resource
	private UserService userService;
	
	private javax.servlet.http.HttpServletResponse response;
	
	private User userBean;
	private UserInformation userinBean;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = {
    "application/json; charset=utf-8" })
	public String Login() throws Exception {
		response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
		
		try {
			User user = new User();
			user = this.getUserBean();
			User us = userService.loadUser(user);
			
			if(us == null) {
				out.print("<script>alert('" + ActionContext.getContext().getSession().get("error") + "')</script>");
				out.flush();
				return "login";
			}else {
				String level = "level" + us.getUserLevel();
				ActionContext.getContext().getSession().put("UserAccount", us.getUserAccount());
				UserInformation usin = new UserInformation();
				usin.setUserAccount(us.getUserAccount());
//				UserInformation usin2 = userService.getInformation(usin);
//				ActionContext.getContext().getSession().put("userInformation", usin2);
				return "login";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "login";
	}
	
	@RequestMapping(value = "/regist", method = RequestMethod.POST, produces = {
    "application/json; charset=utf-8" })
	public String Regist() throws Exception {
		response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
		
		try {
			User user = new User();
			user = this.getUserBean();
			user.setUserLevel("1");
			String us = userService.addUser(user);
			
			if (us.equals("error")) {
				out.print("<script>alert('" + ActionContext.getContext().getSession().get("error") + "')</script>");
				out.flush();
				return "regist";
			}else if(us.equals("success")){
				return "login";
			}
			return "regist";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "regist";
		}
	}
	
//	@Action(value = "User_Cancel")
//	public String Cancel() throws Exception {
//		try {
//			ActionContext.getContext().getSession().clear();
//			return "login";
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return "login";
//		}
//	}
//
//	@Action(value = "User_updateInformation")
//	public String updateInformation() throws Exception {
//		response.setContentType("text/html;charset=UTF-8");
//        response.setCharacterEncoding("UTF-8");
//        PrintWriter out = response.getWriter();
//        
//		try {
//			UserInformation usin = new UserInformation();
//			usin = this.getUserinBean();
//			String userAccount = (String) ActionContext.getContext().getSession().get("UserAccount");
//			usin.setUserAccount(userAccount);
//			String usin2 = userService.updateInformation(usin);
//			if(usin2.equals("error")) {
//				out.print("<script>alert('" + ActionContext.getContext().getSession().get("error") + "')</script>");
//				out.flush();
//				return "information";
//			}
//			ActionContext.getContext().getSession().put("userInformation", usin);
//			return "level1";
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return "information";
//		}
//	}
	
	/*-------------------------------------------------------------------------------------------------------------
	 * -----------------------------------------------------------------------------------------------------------*/
	
	public User getUserBean() {
		return userBean;
	}
	public void setUserBean(User userBean) {
		this.userBean = userBean;
	}
	
	public UserInformation getUserinBean() {
		return userinBean;
	}
	public void setUserinBean(UserInformation userinBean) {
		this.userinBean = userinBean;
	}

	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;
	}

}
