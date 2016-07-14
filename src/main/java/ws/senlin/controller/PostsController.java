package ws.senlin.controller;

import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.opensymphony.xwork2.ActionContext;

import ws.senlin.entity.Posts;
import ws.senlin.entity.PostsReply;
import ws.senlin.entity.UserInformation;
import ws.senlin.service.PostsService;
import ws.senlin.service.ReplyService;

@Controller
@RequestMapping("/posts")
@SessionAttributes({ "postsFloor", "Floor", "Posts", "postsReply", "pagenumber", "now", "userReply" })
public class PostsController {

	@Resource
	private PostsService postsService;

	@Resource
	private ReplyService replyService;

	private int first;
	private int page;
	private int pagenumber;
	private int number;

	@RequestMapping(value = "/floor")
	public String Floor(Posts usp, ModelMap model) throws Exception {
		try {
			first = 0;
			number = 15;

			List<Posts> pt = null;
			model.addAttribute("postsFloor", usp.getPostsFloor());
			pt = postsService.loadPosts(usp, first, number);
			page = postsService.getPage(usp);
			if (page % 15 > 0) {
				pagenumber = page / 15 + 1;
			} else {
				pagenumber = page / 15;
			}

			model.addAttribute("pagenumber", pagenumber);
			model.addAttribute("now", first / 15 + 1);
			model.addAttribute("Floor", pt);
			return "posts/Floor";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "user/user_level1";
		}
	}

	@RequestMapping(value = "nextPage")
	public String nextPage(HttpSession session, ModelMap model) throws Exception {
		try {
			if (first >= (page - 15)) {
				return "posts/Floor";
			} else {
				first = first + 15;
				model.addAttribute("now", first / 15 + 1);
				Posts usp = new Posts();
				String postsFloor = (String) session.getAttribute("postsFloor");
				usp.setPostsFloor(postsFloor);
				List<Posts> up = null;
				up = postsService.loadPosts(usp, first, number);
				model.addAttribute("Floor", up);
				return "posts/Floor";
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "posts/Floor";
		}
	}

	@RequestMapping(value = "backPage")
	public String backPage(HttpSession session, ModelMap model) throws Exception {
		try {
			if (first == 0) {
				return "posts/Floor";
			} else {
				first = first - 15;
				model.addAttribute("now", first / 15 + 1);
				Posts usp = new Posts();
				String postsFloor = (String) session.getAttribute("postsFloor");
				usp.setPostsFloor(postsFloor);
				List<Posts> up = null;
				up = postsService.loadPosts(usp, first, number);
				model.addAttribute("Floor", up);
				return "posts/Floor";
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "posts/Floor";
		}
	}

	@RequestMapping(value = "somePage")
	public String somePage(@RequestParam(value = "somePage")int somePage, HttpSession session, ModelMap model) throws Exception {
		try {
			first = (somePage - 1) * 15;
			Posts usp = new Posts();
			String postsFloor = (String) session.getAttribute("postsFloor");
			usp.setPostsFloor(postsFloor);
			List<Posts> up = null;
			up = postsService.loadPosts(usp, first, number);
			model.addAttribute("now", first / 15 + 1);
			model.addAttribute("Floor", up);
			return "posts/Floor";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "posts/Floor";
		}
	}

	@RequestMapping(value = "/addPosts", method = RequestMethod.POST)
	public String addPosts(HttpSession session, ModelMap model, HttpServletResponse response, Posts posts)
			throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		try {
			String postsFloor = (String) session.getAttribute("postsFloor");
			posts.setPostsFloor(postsFloor);
			UserInformation usin = (UserInformation) session.getAttribute("userInformation");
			posts.setUserAccount(usin.getUserAccount());
			posts.setUserName(usin.getUserName());
			String text = posts.getPostsText();
			text = text.replace("\r\n", "<br/>");
			posts.setPostsText(text);

			String p = postsService.addPosts(posts);
			if (p.equals("success")) {
				first = 0;
				List<Posts> up = null;
				up = postsService.loadPosts(posts, first, number);
				page = postsService.getPage(posts);
				if (page % 15 > 0) {
					pagenumber = page / 15 + 1;
				} else {
					pagenumber = page / 15;
				}

				model.addAttribute("pagenumber", pagenumber);
				model.addAttribute("now", first / 15 + 1);
				model.addAttribute("Floor", up);
				return "posts/Floor";
			} else {
				out.print("<script>alert('" + ActionContext.getContext().getSession().get("error") + "')</script>");
				out.flush();
				return "posts/Posts";
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "posts/Posts";
		}
	}

	@RequestMapping(value = "/getPosts")
	public String getPosts(HttpServletResponse response, Posts usp, ModelMap model) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		try {
			Posts usp2 = new Posts();
			List<PostsReply> ur = null;
			PostsReply ur2 = new PostsReply();
			ur2.setPostsSuper(usp.getPostsId());

			usp2 = postsService.getPosts(usp);
			ur = replyService.loadPosts(ur2);
			if (usp2 == null) {
				out.print("<script>alert('数据异常')</script>");
				out.flush();
				return "/posts/Floor";
			} else {
				model.addAttribute("Posts", usp2);
				model.addAttribute("postsReply", ur);
				return "/posts/UserPosts";
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "/posts/Floor";
		}
	}

	@RequestMapping(value = "/replyPosts", method = RequestMethod.POST)
	public String replyPosts(HttpSession session, HttpServletResponse response, ModelMap model, @RequestParam(value = "replyText")String text) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		try {
			PostsReply pr = new PostsReply();
			UserInformation usin = (UserInformation) session.getAttribute("userInformation");
			pr.setUserAccount(usin.getUserAccount());
			pr.setUserName(usin.getUserName());
			text = text.replace("\r\n", "<br/>");
			pr.setReplyText(text);
			Posts usp = (Posts) session.getAttribute("Posts");
			pr.setPostsSuper(usp.getPostsId());

			String a = replyService.addPosts(pr);
			if (a.equals("success")) {
				List<PostsReply> ur = null;
				ur = replyService.loadPosts(pr);
				model.addAttribute("postsReply", ur);
				return "/posts/UserPosts";
			} else {
				out.print("<script>alert('数据异常！')</script>");
				out.flush();
				return "/posts/UserPosts";
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "/posts/UserPosts";
		}
	}

}
