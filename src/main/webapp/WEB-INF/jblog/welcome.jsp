<%@page language="java" contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>JBlog</title>
</head>
<body>
<center>

<!-- 검색 화면 시작 -->
<form action="/" method="post">
	<table width="550" height=200 border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td width="100%" colspan="10" align="center">
				<img src="/images/logo.jpg" border="0">
			</td>
		</tr>	
		<tr>
		<td width="70%">
			<c:if test="${empty user}">
				<a href="/login"><b>로그인</b></a>&nbsp;&nbsp;
			</c:if>
			<c:if test="${!empty user}">
				<c:if test="${blog.blogId ne user.userId}">
   					<a href="/blog"><b>블로그등록</b></a>&nbsp;&nbsp;
   				</c:if>
   				<c:if test="${blog.blogId eq user.userId}">
    				<a href="/blogmain/${blog.blogId}"><b>블로그바로가기</b></a>&nbsp;&nbsp;
    			</c:if>
    			<a href="/logout"><b>로그아웃</b></a>&nbsp;&nbsp;
    		</c:if>
      	</tr>
      	<tr>
      		<td width="70%" colspan="2" align="left">
				<input type="text" name="searchKeyword"	size="50">
				<input type="submit" value="검색">
			</td>
		</tr>
		<tr>
			<td align="center">
				<input type="radio" name="searchCondition" value="TITLE" <c:if test="${empty searchCondition || searchCondition eq 'TITLE'}"> checked="checked"</c:if>>블로그 제목&nbsp;&nbsp;
				<input type="radio"	name="searchCondition" value="TAG" <c:if test="${searchCondition eq 'TAG'}"> checked="checked"</c:if>>태그
			</td>
		</tr>	
	</table>
</form>
<!-- 검색 화면 종료 -->

<!-- 블로그 목록 시작 -->
<br><br>
<c:if test="${empty searchBlogList && !empty blogList}">
<table width="550" border="0" cellpadding="1" cellspacing="1">
	<tr bgcolor="#9DCFFF">
		<th height="30"><font color="white">블로그 제목</font></th>
		<th width="100"><font color="white">상태</font></th>
		<c:if test="${user.role eq 'ADMIN'}">
			<th width="100"><font color="white">삭제</font></th>
		</c:if>
	</tr>
	
	<c:forEach var="blog" items="${blogList}">
	<tr>
		<td align="left"><a href="/blogmain/${blog.blogId}">${blog.title}</a></td>
		<td align="center">${blog.status}</td>
		<c:if test="${user.role eq 'ADMIN'}">
		<c:if test="${blog.status eq '삭제요청' }">
			<td align="center"><a href="/blog/removal/${blog.blogId}"><img height="9" src="/images/delete.jpg" border="0"></a></td>
		</c:if>
		<c:if test="${blog.status eq '운영' }">
			<td align="center">-</td>
		</c:if>
		</c:if>
	</tr>
	</c:forEach>
	</table>
</c:if>
<c:if test="${!empty searchBlogList}">
<table width="550" border="0" cellpadding="1" cellspacing="1">
	<tr bgcolor="#9DCFFF">
		<th height="30"><font color="white">블로그 제목</font></th>
		<th width="100"><font color="white">상태</font></th>
		<c:if test="${user.role eq 'ADMIN'}">
			<th width="100"><font color="white">삭제</font></th>
		</c:if>
	</tr>
	
	<c:forEach var="blog" items="${searchBlogList}">
	<tr>
		<td align="left"><a href="/blogmain/${blog.blogId}">${blog.title}</a></td>
		<td align="center">${blog.status}</td>
		<c:if test="${user.role eq 'ADMIN'}">
		<c:if test="${blog.status eq '삭제요청' }">
			<td align="center"><a href="/blog/removal/${blog.blogId}"><img height="9" src="/images/delete.jpg" border="0"></a></td>
		</c:if>
		<c:if test="${blog.status eq '운영' }">
			<td align="center">-</td>
		</c:if>
		</c:if>
	</tr>
	</c:forEach>
	</table>
</c:if>
<!-- 블로그 목록 종료 -->

</center>
</body>
</html>