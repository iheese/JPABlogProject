<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog 블로그 메인</title>
<script type="text/javascript">
	function popup() {
		window.name= "home";
		window.open('/blog/removal/request/${blog.blogId}', 'popup', 'width=800, height=300, location=no, status=no, menubar=no, scrollbars=no, resizale=no, left=350, top=150');
	}
</script>
</head>
<body background="/images/kubrickbgcolor.jpg">
	<center>
		<table background="/images/kubrickheader.jpg" width="760" height="200" border="0" cellpadding="0" cellspacing="0">
			<tr><td height="60">&nbsp;</td></tr>
			<!-- 블로그 제목과 태그 시작 -->
			<tr><td height="60" align="center"><h1><font color="white">${blog.title}</font></h1></td></tr>
			<tr><td height="20" align="center"><h3><font color="white">${blog.tag}</font></h3></td></tr>
			<!-- 블로그 제목과 태그 끝 -->
			<tr align="right">
				<td>
					<c:if test="${empty user}">
					<a href="/login">로그인</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</c:if>
					<c:if test="${!empty user}">
						<a href="/logout">로그아웃</a>&nbsp;&nbsp;&nbsp;
						<c:if test="${user.userId eq blog.blogId}">
							<a href="/blog/${blog.blogId}/setting">블로그관리</a>&nbsp;&nbsp;&nbsp;
							<a href="/blogmain/${blog.blogId}">블로그메인</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</c:if>
					</c:if>
				</td>
			</tr>
		</table>