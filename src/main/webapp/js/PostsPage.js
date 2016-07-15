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
			common(json);
		}
	});

}

function nextPage() {
	$.ajax({ // 一个Ajax过程
		type : "post", // 以post方式与后台沟通
		url : getRootPath() + "/posts/nextPage", // 与此页面沟通
		dataType : 'json',// 返回的值以JSON方式解释
		success : function(json) {// 如果调用成功
			common(json);
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
			common(json);
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
			common(json);
		}
	});

}

function common(json) {
	var posts;
	var page;
	if (json.flag) {
		var now = json.now;
		$.each(json.posts, function(idx, f) {
			/*alert(JSON.stringify(f.postsDate));*/
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
	$('#posts').html(posts);
	$('#page').html(page);
}

function post() {
	document.forms['posts'].action = getRootPath() + "jsp/posts/Posts.jsp";
	document.forms['posts'].submit();
}
