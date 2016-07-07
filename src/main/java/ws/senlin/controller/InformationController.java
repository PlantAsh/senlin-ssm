package ws.senlin.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import ws.senlin.entity.InformationCode;
import ws.senlin.entity.UserInformation;
import ws.senlin.service.InformationService;

@Controller
@RequestMapping("/information")
@SessionAttributes({"inFloor","inMajor","userInformation","UserAccount"})
public class InformationController {
	@Resource
	private InformationService informationService;

	@RequestMapping(value = "/userinfo")
	public String Login(ModelMap model) throws Exception {
		
		try {
			List<InformationCode> inFloor = null;
			List<InformationCode> inMajor = null;
			inFloor = informationService.loadByType("floor");
			inMajor = informationService.loadByType("major");
			model.addAttribute("inFloor", inFloor);
			model.addAttribute("inMajor", inMajor);
			return "user/user_information";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "user/user_information";
	}
	
	@RequestMapping(value = "/update")
	public String updateInformation(@ModelAttribute("UserAccount")String userAccount, ModelMap model, HttpServletResponse response, UserInformation usin) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        
		try {
			usin.setUserAccount(userAccount);
			String usin2 = informationService.updateInformation(usin);
			if(!usin2.equals("success")) {
				out.print("<script>alert('" + usin2 + "')</script>");
				out.flush();
				return "user/information";
			}
			model.addAttribute("userInformation", usin);
			return "user/user_level1";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "user/information";
		}
	}

}
