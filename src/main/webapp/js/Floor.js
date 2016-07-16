function getRootPath() {
	var pathName = window.location.pathname.substring(1);
	var webName = pathName == '' ? '' : pathName.substring(0, pathName
			.indexOf('/'));
	return window.location.protocol + '//' + window.location.host + '/'
			+ webName + '/';
}

function backPage() {
	$.ajax({ // 一个Ajax过程
		type : "post", // 以post方式与后台沟通
		url : getRootPath() + "/posts/backPage", // 与此页面沟通
		dataType : 'json',// 返回的值以JSON方式解释
		success : function(json) {// 如果调用成功
			if(json.level == "user_level1") {
				common(json);
			} else {
				adminCommon(json);
			}
		}
	});
}

function nextPage() {
	$.ajax({ // 一个Ajax过程
		type : "post", // 以post方式与后台沟通
		url : getRootPath() + "/posts/nextPage", // 与此页面沟通
		dataType : 'json',// 返回的值以JSON方式解释
		success : function(json) {// 如果调用成功
			if(json.level == "user_level1") {
				common(json);
			} else {
				adminCommon(json);
			}
		}
	});
}

function somePage(obj) {
	$.ajax({ // 一个Ajax过程
		type : "post", // 以post方式与后台沟通
		url : getRootPath() + "/posts/somePage", // 与此页面沟通
		dataType : 'json',// 返回的值以JSON方式解释
		data : 'somePage=' + obj, // 发给的数据
		success : function(json) {// 如果调用成功
			if(json.level == "user_level1") {
				common(json);
			} else {
				adminCommon(json);
			}
		}
	});
}

function floor(obj) {
	$.ajax({ // 一个Ajax过程
		type : "post", // 以post方式与后台沟通
		url : getRootPath() + "/posts/floor", // 与此页面沟通
		dataType : 'json',// 返回的值以JSON方式解释
		data : 'postsFloor=' + obj, // 发给的数据
		success : function(json) {// 如果调用成功
			if(json.level == "user_level1") {
				common(json);
			} else {
				adminCommon(json);
			}
		}
	});
}

function deletePosts(obj) {
	$.ajax({ // 一个Ajax过程
		type : "post", // 以post方式与后台沟通
		url : getRootPath() + "/posts/deletePosts", // 与此页面沟通
		dataType : 'json',// 返回的值以JSON方式解释
		data : 'postsId=' + obj, // 发给的数据
		success : function(json) {// 如果调用成功
			if(json.level == "user_level1") {
				common(json);
			} else {
				adminCommon(json);
			}
		}
	});
}

function common(json) {
	var posts;
	var page;
	var title;
	title = "<tr><th class='table-title'>标题</th>"
		+ "<th class='table-author am-hide-sm-only'>作者</th>"
		+ "<th class='table-date am-hide-sm-only'>发布时间</th></tr>";
	if (json.flag) {
		$.each(json.posts, function(idx, f) {
			posts = posts + "<tr><td class='am-hide-sm-only'><a href='"
					+ getRootPath() + "/posts/getPosts?postsId="
					+ f.postsId + "'>" + f.postsTitle + "</td>"
					+ "<td class='am-hide-sm-only'>" + f.userName
					+ "</td>" + "<td class='am-hide-sm-only'>"
					+ f.date + "</td></tr>";
		});
		var pagenumber = json.pagenumber;
		var now = json.now;
		if (now == 1) {
			page = "<li class='am-disabled'><a href='#' onclick='backPage()'>«</a></li>";
		} else {
			page = "<li><a href='#' onclick='backPage()'>«</a></li>";
		}
		var a = 1;
		if (pagenumber > 5 & now > 2) {
			if (pagenumber - now >= 2) {
				a = now - 2;
				pagenumber = now + 2;
			} else {
				a = pagenumber - 4;
			}
		} else if (pagenumber > 5) {
			pagenumber = 5;
		}
		for (var i = a; i <= pagenumber; i++) {
			if (i == now) {
				page = page + "<li class='am-active'><a href='#' onclick='somePage(" + i + ")' >" + i + "</a></li>";
			} else {
				page = page + "<li><a href='#' onclick='somePage(" + i + ")' >" + i
						+ "</a></li>";
			}
		}
		if (now == pagenumber) {
			page = page + "<li class='am-disabled'><a href='#' onclick='nextPage()'>»</a></li>";
		} else {
			page = page + "<li><a href='#' onclick='nextPage()'>»</a></li>";
		}
	} else {
		posts = "<tr><td class='am-hide-sm-only'>你将是第一个</td>"
				+ "<td class='am-hide-sm-only'>你将是第一个</td>"
				+ "<td class='am-hide-sm-only'>你将是第一个</td></tr>";
	}
	$('#title').html(title);
	$('#posts').html(posts);
	$('#page').html(page);
}

function adminCommon(json) {
	var posts;
	var page;
	var title;
	title = "<tr><th class='table-title'>标题</th>"
		+ "<th class='table-author am-hide-sm-only'>作者</th>"
		+ "<th class='table-date am-hide-sm-only'>发布时间</th>"
		+ "<th class='table-set'>操作</th></tr>";
	if (json.flag) {
		$.each(json.posts, function(idx, f) {
			posts = posts + "<tr><td class='am-hide-sm-only'><a href='"
					+ getRootPath() + "/posts/getPosts?postsId="
					+ f.postsId + "'>" + f.postsTitle + "</td>"
					+ "<td class='am-hide-sm-only'>" + f.userName
					+ "</td>" + "<td class='am-hide-sm-only'>"
					+ f.date + "</td>"
					+ "<td><div class='am-btn-toolbar'><div class='am-btn-group am-btn-group-xs'>"
					+ "<button onclick='deletePosts("+f.postsId+")' class='am-btn am-btn-default am-btn-xs am-text-secondary'>"
					+ "<span class='am-icon-trash-o'></span> 删除</button>"
					+ "</div></div></tr>";
		});
		var pagenumber = json.pagenumber;
		var now = json.now;
		if (now == 1) {
			page = "<li class='am-disabled'><a href='#' onclick='backPage()'>«</a></li>";
		} else {
			page = "<li><a href='#' onclick='backPage()'>«</a></li>";
		}
		var a = 1;
		if (pagenumber > 5 & now > 2) {
			if (pagenumber - now >= 2) {
				a = now - 2;
				pagenumber = now + 2;
			} else {
				a = pagenumber - 4;
			}
		} else if (pagenumber > 5) {
			pagenumber = 5;
		}
		for (var i = a; i <= pagenumber; i++) {
			if (i == now) {
				page = page + "<li class='am-active'><a href='#' onclick='somePage(" + i + ")' >" + i + "</a></li>";
			} else {
				page = page + "<li><a href='#' onclick='somePage(" + i + ")' >" + i
						+ "</a></li>";
			}
		}
		if (now == pagenumber) {
			page = page + "<li class='am-disabled'><a href='#' onclick='nextPage()'>»</a></li>";
		} else {
			page = page + "<li><a href='#' onclick='nextPage()'>»</a></li>";
		}
	} else {
		posts = "<tr><td class='am-hide-sm-only'>你将是第一个</td>"
				+ "<td class='am-hide-sm-only'>你将是第一个</td>"
				+ "<td class='am-hide-sm-only'>你将是第一个</td></tr>";
	}
	$('#title').html(title);
	$('#posts').html(posts);
	$('#page').html(page);
}

function post() {
	document.forms['posts'].action = getRootPath() + "jsp/posts/Posts.jsp";
	document.forms['posts'].submit();
}
