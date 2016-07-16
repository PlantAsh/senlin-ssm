package ws.senlin.controller;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import net.sf.json.JSONObject;
import ws.senlin.entity.Posts;
import ws.senlin.entity.PostsReply;
import ws.senlin.entity.UserInformation;
import ws.senlin.service.PostsService;
import ws.senlin.service.ReplyService;

@Controller
@RequestMapping("/posts")
@SessionAttributes({ "postsFloor", "Posts", "postsReply", "userReply" })
public class PostsController {

	@Resource
	private PostsService postsService;

	@Resource
	private ReplyService replyService;

	private int first;
	private int page;
	private int pagenumber;
	private int number;
	private String level;

	@RequestMapping(value = "/floor", produces = "text/plain;charset=UTF-8")
	public String Floor(HttpSession session, HttpServletResponse response, String postsFloor, ModelMap model) throws Exception {
		try {
			first = 0;
			number = 15;

			Posts usp = new Posts();
			usp.setPostsFloor(postsFloor);
			List<Posts> pt = null;
			model.addAttribute("postsFloor", usp.getPostsFloor());
			pt = postsService.loadPosts(usp, first, number);
			page = postsService.getPage(usp);
			if (page % 15 > 0) {
				pagenumber = page / 15 + 1;
			} else {
				pagenumber = page / 15;
			}
			boolean flag = false;
			if(pt.size() > 0) {
				flag = true;
			}
			
			for(int i = 0; i<pt.size(); i++) {
				Posts up = pt.get(i);
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String dateString = formatter.format(up.getPostsDate());
				up.setDate(dateString);
			}
			
			level = (String) session.getAttribute("userLevel");
			Map<String, Object> map = new HashMap<String, Object>();
	        map.put("flag", flag);
	        map.put("posts", pt);
	        map.put("pagenumber", pagenumber);
	        map.put("now", first / 15 + 1);
	        map.put("level", level);
	        String json = JSONObject.fromObject(map).toString();
	        //将数据返回
	        response.setCharacterEncoding("UTF-8");
	        response.flushBuffer();
	        response.getWriter().write(json);
	        response.getWriter().flush();
	        response.getWriter().close();
	        return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "nextPage", produces = "text/plain;charset=UTF-8")
	public String nextPage(HttpSession session, HttpServletResponse response) throws Exception {
		try {
			if (first >= (page - 15)) {
				return null;
			} else {
				first = first + 15;
				Posts usp = new Posts();
				String postsFloor = (String) session.getAttribute("postsFloor");
				usp.setPostsFloor(postsFloor);
				List<Posts> pt = null;
				pt = postsService.loadPosts(usp, first, number);
				boolean flag = false;
				if(pt.size() > 0) {
					flag = true;
				}
				
				for(int i = 0; i<pt.size(); i++) {
					Posts up = pt.get(i);
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String dateString = formatter.format(up.getPostsDate());
					up.setDate(dateString);
				}
				
				Map<String, Object> map = new HashMap<String, Object>();
		        map.put("flag", flag);
		        map.put("posts", pt);
		        map.put("pagenumber", pagenumber);
		        map.put("now", first / 15 + 1);
		        map.put("level", level);
		        String json = JSONObject.fromObject(map).toString();
		        //将数据返回
		        response.setCharacterEncoding("UTF-8");
		        response.flushBuffer();
		        response.getWriter().write(json);
		        response.getWriter().flush();
		        response.getWriter().close();
		        return null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "backPage", produces = "text/plain;charset=UTF-8")
	public String backPage(HttpServletResponse response, HttpSession session) throws Exception {
		try {
			if (first == 0) {
				return null;
			} else {
				first = first - 15;
				Posts usp = new Posts();
				String postsFloor = (String) session.getAttribute("postsFloor");
				usp.setPostsFloor(postsFloor);
				List<Posts> pt = null;
				pt = postsService.loadPosts(usp, first, number);
				boolean flag = false;
				if(pt.size() > 0) {
					flag = true;
				}
				
				for(int i = 0; i<pt.size(); i++) {
					Posts up = pt.get(i);
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String dateString = formatter.format(up.getPostsDate());
					up.setDate(dateString);
				}
				
				Map<String, Object> map = new HashMap<String, Object>();
		        map.put("flag", flag);
		        map.put("posts", pt);
		        map.put("pagenumber", pagenumber);
		        map.put("now", first / 15 + 1);
		        map.put("level", level);
		        String json = JSONObject.fromObject(map).toString();
		        //将数据返回
		        response.setCharacterEncoding("UTF-8");
		        response.flushBuffer();
		        response.getWriter().write(json);
		        response.getWriter().flush();
		        response.getWriter().close();
		        return null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "somePage", produces = "text/plain;charset=UTF-8")
	public String somePage(HttpServletResponse response, @RequestParam(value = "somePage")int somePage, HttpSession session) throws Exception {
		try {
			first = (somePage - 1) * 15;
			Posts usp = new Posts();
			String postsFloor = (String) session.getAttribute("postsFloor");
			usp.setPostsFloor(postsFloor);
			List<Posts> pt = null;
			pt = postsService.loadPosts(usp, first, number);
			boolean flag = false;
			if(pt.size() > 0) {
				flag = true;
			}
			
			for(int i = 0; i<pt.size(); i++) {
				Posts up = pt.get(i);
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String dateString = formatter.format(up.getPostsDate());
				up.setDate(dateString);
			}
			
			Map<String, Object> map = new HashMap<String, Object>();
	        map.put("flag", flag);
	        map.put("posts", pt);
	        map.put("pagenumber", pagenumber);
	        map.put("now", first / 15 + 1);
	        map.put("level", level);
	        String json = JSONObject.fromObject(map).toString();
	        //将数据返回
	        response.setCharacterEncoding("UTF-8");
	        response.flushBuffer();
	        response.getWriter().write(json);
	        response.getWriter().flush();
	        response.getWriter().close();
	        return null;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value = "/deletePosts", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public String deletePosts(HttpSession session, ModelMap model, HttpServletResponse response, @RequestParam(value = "postsId")int postsId)
			throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		try {
			String p = postsService.deletePosts(postsId);
			if (p.equals("success")) {
				Posts usp = new Posts();
				String postsFloor = (String) session.getAttribute("postsFloor");
				usp.setPostsFloor(postsFloor);
				List<Posts> pt = null;
				pt = postsService.loadPosts(usp, first, number);
				page = postsService.getPage(usp);
				if (page % 15 > 0) {
					pagenumber = page / 15 + 1;
				} else {
					pagenumber = page / 15;
				}
				boolean flag = false;
				if(pt.size() > 0) {
					flag = true;
				}
				
				for(int i = 0; i<pt.size(); i++) {
					Posts up = pt.get(i);
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String dateString = formatter.format(up.getPostsDate());
					up.setDate(dateString);
				}
				
				Map<String, Object> map = new HashMap<String, Object>();
		        map.put("flag", flag);
		        map.put("posts", pt);
		        map.put("pagenumber", pagenumber);
		        map.put("now", first / 15 + 1);
		        map.put("level", level);
		        String json = JSONObject.fromObject(map).toString();
		        //将数据返回
		        response.setCharacterEncoding("UTF-8");
		        response.flushBuffer();
		        response.getWriter().write(json);
		        response.getWriter().flush();
		        response.getWriter().close();
		        return null;
			} else {
				out.print("<script>alert('error!')</script>");
				out.flush();
				return null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "/addPosts", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
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
			Date date=new Date();
			posts.setPostsDate(date);
			String text = posts.getPostsText();
			text = text.replace("\r\n", "<br/>");
			posts.setPostsText(text);

			String p = postsService.addPosts(posts);
			if (p.equals("success")) {
				return "posts/Floor";
			} else {
				out.print("<script>alert('error!')</script>");
				out.flush();
				return "posts/Posts";
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "posts/Posts";
		}
	}

	@RequestMapping(value = "/getPosts", produces = "text/plain;charset=UTF-8")
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

	@RequestMapping(value = "/replyPosts", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
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
