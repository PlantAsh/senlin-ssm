package ws.senlin.controller;

import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.opensymphony.xwork2.ActionContext;

import ws.senlin.entity.Posts;
import ws.senlin.entity.PostsReply;
import ws.senlin.entity.UserInformation;
import ws.senlin.service.PostsService;
import ws.senlin.service.ReplyService;

@Controller
@RequestMapping("/posts")
@SessionAttributes({"postsFloor","Floor"})
public class PostsController {
	
	@Resource
	private PostsService postsService;
	
	@Resource
	private ReplyService replyService;
	
	private int first;
	private int quantity;
	private int number;
	
	@RequestMapping(value = "/floor")
	public String Floor(Posts usp, ModelMap model) throws Exception {
		try {
			first = 0;
			number = 15;
			
			List<Posts> up = null;
			model.addAttribute("postsFloor", usp.getPostsFloor());
			up = postsService.loadPosts(usp, first, number);
//			quantity = postsService.getQuantity(usp);
			
//			ActionContext.getContext().getSession().put("quantity", quantity/15);
			model.addAttribute("Floor", up);
			return "posts/Floor";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "user/user_level1";
		}
	}
	
	public String nextFloor() throws Exception {
		try {
			if(first > quantity) {
				return "floor";
			} else {
				first = first + 15;
				ActionContext.getContext().getSession().put("number", first/15+1);
				Posts usp = new Posts();
				String postsFloor = (String) ActionContext.getContext().getSession().get("postsFloor");
				usp.setPostsFloor(postsFloor);
				List<Posts> up = null;
	        	up = postsService.loadPosts(usp, first, number);
				ActionContext.getContext().getSession().put("Floor", up);
				return "floor";
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "floor";
		}
	}
	
	public String backFloor() throws Exception {
		try {
			if(first < 15) {
				return "floor";
			} else {
				first = first - 15;
				ActionContext.getContext().getSession().put("number", first/15+1);
				Posts usp = new Posts();
				String postsFloor = (String) ActionContext.getContext().getSession().get("postsFloor");
				usp.setPostsFloor(postsFloor);
				List<Posts> up = null;
	        	up = postsService.loadPosts(usp, first, number);
				ActionContext.getContext().getSession().put("Floor", up);
				return "floor";
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "floor";
		}
	}

	public String addPosts(HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        
		try {
			Posts usp = new Posts();
			String postsFloor = (String) ActionContext.getContext().getSession().get("postsFloor");
			usp.setPostsFloor(postsFloor);
			UserInformation usin = (UserInformation) ActionContext.getContext().getSession().get("userInformation");
			usp.setUserAccount(usin.getUserAccount());
			usp.setUserName(usin.getUserName());
            String text = usp.getPostsText();
            text = text.replace("\r\n", "<br/>");
            usp.setPostsText(text);
            
            String p = postsService.addPosts(usp);
            if(p.equals("success")) {
            	first = 0;
            	List<Posts> up = null;
            	up = postsService.loadPosts(usp, first, number);
//            	quantity = postsService.getQuantity(usp);
    			
//    			ActionContext.getContext().getSession().put("quantity", quantity/15);
    			ActionContext.getContext().getSession().put("Floor", up);
            	return "floor";
            } else {
            	out.print("<script>alert('" + ActionContext.getContext().getSession().get("error") + "')</script>");
				out.flush();
				return "posts";
            }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "posts";
		}
	}

	public String getPosts(HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        
		try {
			Posts usp = new Posts();
			Posts usp2 = new Posts();
			List<PostsReply> ur = null;
			PostsReply ur2 = new PostsReply();
			ur2.setPostsSuper(usp.getPostsId());
			
			usp2 = postsService.getPosts(usp);
			ur = replyService.loadPosts(ur2);
			if(usp2 == null) {
				out.print("<script>alert('" + ActionContext.getContext().getSession().get("error") + "')</script>");
				out.flush();
				return "floor";
			} else {
				ActionContext.getContext().getSession().put("userPosts", usp2);
				ActionContext.getContext().getSession().put("userReply", ur);
				return "userPosts";
			}
		} catch (Exception e) {
			// TODO: handle exception
			return "floor";
		}
	}

	public String replyPosts(HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        
		try {
			PostsReply ur = new PostsReply();
			UserInformation usin = (UserInformation) ActionContext.getContext().getSession().get("userInformation");
			ur.setUserAccount(usin.getUserAccount());
			ur.setUserName(usin.getUserName());
			Date date=new Date();
			ur.setReplyDate(date);
			String text = ur.getReplyText();
            text = text.replace("\r\n", "<br/>");
            ur.setReplyText(text);
            Posts usp = (Posts) ActionContext.getContext().getSession().get("userPosts");
            ur.setReplyId(usp.getPostsId());
			
			String a = replyService.addPosts(ur);
			if(a.equals("success")) {
				List<PostsReply> ur2 = null;
				ur2 = replyService.loadPosts(ur);
				ActionContext.getContext().getSession().put("userReply", ur2);
				return "userPosts";
			} else {
				out.print("<script>alert('" + ActionContext.getContext().getSession().get("error") + "')</script>");
				out.flush();
				return "userPosts";
			}
		} catch (Exception e) {
			// TODO: handle exception
			return "userPosts";
		}
	}

}
