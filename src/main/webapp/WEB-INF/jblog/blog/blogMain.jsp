<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<%@include file="../layout/header.jsp" %>

<table background="/images/kubrickbg.jpg" width="760" height="300" border="0" cellpadding="0" cellspacing="0">
	<tr valign="top"><td height="10">&nbsp;</td></tr>
	<tr valign="top"><td width="20">&nbsp;</td>
		<td width="530">
		
		<!-- 포스트 목록 시작 --> 
		<!-- 모든 포스트 목록 -->
		<c:if test="${empty postListByCategory}">
		<c:forEach var="post" items="${postList}">
		<table border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td width="420">
				<h3><font color="green">${post.title}</font></h3>
				${post.content}<br>
				</td>&nbsp;&nbsp;
				<c:if test="${user.userId eq blog.blogId && !empty user}">
				<td align="right"><a href="/posts/post/${post.postId}">수정</a> / <a href="/posts/${post.blogId}?postId=${post.postId}">삭제</a></td>
				</c:if>
			</tr>
			<tr>
				<td colspan="2" align="right">
				<c:if test="${!empty post.createdDate}">
				<font color="gray">${post.createdDate.toLocalDate()}</font><br>	
				</c:if>	
				<c:if test="${empty post.createdDate}">
				<font color="gray">${post.modifiedDate.toLocalDate()}</font><br>	
				</c:if>	
				</td>
			</tr>
		</table><br>
	    <br>
	    </c:forEach>
	    </c:if>
	    <!-- 카테고리별 포스트 목록 -->
	    <c:if test="${!empty postListByCategory}">
		<c:forEach var="post" items="${postListByCategory}">
		<table border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td width="420">
				<h3><font color="green">${post.title}</font></h3>
				${post.content}<br>
				</td>&nbsp;&nbsp;
				<c:if test="${user.userId eq blog.blogId && !empty user}">
				<td align="right"><a href="/posts/post/${post.postId}">수정</a> / <a href="/posts/${post.blogId}?postId=${post.postId}">삭제</a></td>
				</c:if>
			</tr>
			<tr>
				<td colspan="2" align="right">
				<c:if test="${!empty post.createdDate}">
				<font color="gray">${post.createdDate.toLocalDate()}</font><br>	
				</c:if>	
				<c:if test="${empty post.createdDate}">
				<font color="gray">${post.modifiedDate.toLocalDate()}</font><br>	
				</c:if>	
				</td>
			</tr>
		</table><br>
	    <br>
	    </c:forEach>
	    </c:if>
		<!-- 포스트 목록 끝-->
		
		</td>
		<td width="20">&nbsp;</td>
		<td width="190" valign="top">
		<!-- 로그인, 관리자 메뉴, 로고, 카테고리 시작 -->
		<table cellpadding="0" cellspacing="0">
			<tr><td height="5">&nbsp;</td></tr>
			<tr><td><img height="80" src="/images/j2eelogo.jpg" border="0"></td></tr>
			<tr><td height="5">&nbsp;</td></tr>
			<tr><td><b>카테고리</b></td></tr>
			<c:forEach var="category" items="${categoryList}">
			<tr>
				<td><a href="/blogmain/${blog.blogId}/${category.categoryId}"><b>${category.categoryName}</b></a></td>
			</tr>
			</c:forEach>
			<tr><td height="5">&nbsp;</td></tr>
			<tr><td align="center"><a href="/"><img width="80" src="/images/logo.jpg" border="0"></a></td></tr>
		</table> 
		<!-- 로그인, 관리자 메뉴, 로고, 카테고리 끝 -->
		</td>
	</tr>
</table>

<%@include file="../layout/footer.jsp" %>
