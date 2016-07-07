package ws.senlin.controller;

import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import ws.senlin.entity.User;
import ws.senlin.entity.UserInformation;
import ws.senlin.service.InformationService;
import ws.senlin.service.UserService;

@Controller
@RequestMapping("/user")
@SessionAttributes({"UserAccount","userInformation"})
public class UserController {
	@Resource
	private UserService userService;
	@Resource
	private InformationService informationService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String Login(HttpServletResponse response, ModelMap model, User user) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
		
		try {
			User us = userService.loadUser(user);
			
			if(us == null) {
				out.print("<script>alert('" + "账号或密码错误" + "')</script>");
				out.flush();
				return "user/login";
			} else if(!us.getUserPassword().equals(user.getUserPassword())) {
				out.print("<script>alert('" + "账号或密码错误" + "')</script>");
				out.flush();
				return "user/login";
			} else {
				String level = "user_level" + us.getUserLevel();
				model.addAttribute("UserAccount", us.getUserAccount());
				UserInformation usin = new UserInformation();
				usin.setUserAccount(us.getUserAccount());
				UserInformation usin2 = informationService.loadInformation(usin);
				model.addAttribute("userInformation", usin2);
				return "user/" + level;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "user/login";
	}
	
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public String Regist(HttpServletResponse response, User userBean) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
		
		try {
			userBean.setUserLevel("1");
			String result = userService.addUser(userBean);
			
			if (!result.equals("success")) {
				out.print("<script>alert('" + result + "')</script>");
				out.flush();
				return "user/regist";
			}else {
				return "user/login";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "user/regist";
		}
	}
	
	@RequestMapping(value = "/cancel")
	public String Cancel(ModelMap mode) throws Exception {
		try {
			mode.clear();
//			model.addAttribute("UserAccount");
//			model.addAttribute("userInformation");
			return "user/login";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "user/login";
		}
	}

}
