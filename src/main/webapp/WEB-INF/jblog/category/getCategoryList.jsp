<%@page contentType="text/html; charset=UTF-8" %>

<%@include file="../layout/header.jsp" %>

<table background="/images/kubrickbg.jpg" width="760" height="40" border="0" cellpadding="0" cellspacing="0">
	<tr><td height="10" colspan="10">&nbsp;</td></tr>
	<tr><td height="10" width="20">&nbsp;</td>
		<td width="530" valign="top">
			<!-- 메뉴 시작 --> 
			<a href="/blog/${blog.blogId}/setting"><b>기본설정</b></a>&nbsp;&nbsp;
			<b>카테고리</b>&nbsp;&nbsp;
			<a href="/post/${blog.blogId}"><b>글작성</b></a>&nbsp;&nbsp; 
			<a href="javascript:popup();"><b>블로그삭제</b></a>&nbsp;&nbsp;
			<!-- 메뉴 끝 -->
		</td>
	</tr>
	<tr><td height="5">&nbsp;</td></tr>
	<tr><td height="10">&nbsp;</td><td>
		<!-- 작업 화면  시작 -->
		<table width="720" border="0" cellpadding="1" cellspacing="1">
			<tr bgcolor="#9DCFFF">
				<th width="50"><font color="white">번호</font></th>
				<th width="120"><font color="white">카테고리명</font></th>
				<th width="100"><font color="white">보이기 유형</font></th>
				<th width="350"><font color="white">설명</font></th>
				<th width="100"><font color="white">삭제</font></th>
			</tr>
			<c:forEach var="category" items="${categoryList}" varStatus="status">
			<c:if test="${!status.last}">
			<tr>
				<td align="center">${categoryList.size()-status.index}</td>
				<td><a href="/categories/category/${category.categoryId}">${category.categoryName}</a></td>
				<td align="center">${category.displayType}</td>
				<td>${category.description}</td>
				<td align="center">
					<a href="/category?blogId=${blog.blogId}&categoryId=${category.categoryId}">
						<img height="9" src="/images/delete.jpg" border="0"/>
				    </a>
				</td>
			</tr>
			</c:if>
			</c:forEach>
			<tr>
				<td align="center">1</td>
				<td>미분류</td>
				<td align="center">제목 + 내용</td>
				<td>기본으로 제공되는 카테고리입니다.</td>
				<td align="center">&nbsp;삭제불가</td>
			</tr>
			</table> 
			<!-- 카테고리 등록화면 시작 -->
			<c:if test="${!empty user}">
			<c:if test = "${empty updateCategory}">
			<form action="/categories/${blog.blogId}" method="post">
			<table width="720" border="0" cellpadding="1" cellspacing="1">
				<tr><td height="5">&nbsp;</td></tr>
				<tr><td height="5">&nbsp;</td></tr>
				<tr><td height="5"><b>카테고리 등록</b></td>	</tr>
				<tr><td height="5">&nbsp;</td></tr>
				<tr>
					<td width="150">카테고리명 :</td>
					<td><input type="text" size="40" name="categoryName"></td>
				</tr>
				<tr>
					<td width="150">보이기 유형 :</td>
					<td><input type="radio"	name="displayType" value="TITLE" checked>제목&nbsp;&nbsp;
						<input type="radio" name="displayType" value="MIXED">제목 + 내용&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
					<td width="150">설명 :</td>
					<td><input type="text" size="50" name="description"></td>
				</tr>
				<tr>
					<td colspan="10" align="center">&nbsp;<input type="submit" value="카테고리 추가"></td>
				</tr>
			</table>
			</form>
			</c:if>
			</c:if>
			<!-- 카테고리 등록화면 종료 -->
			
			<!-- 카테고리 수정화면 시작 -->
			<c:if test="${!empty updateCategory}">
			<form action="/categories/category" method="post">
			<table width="720" border="0" cellpadding="1" cellspacing="1">
				<tr><td height="5">&nbsp;</td></tr>
				<tr><td height="5">&nbsp;</td></tr>
				<tr><td height="5"><b>카테고리 수정</b></td></tr>
				<tr><td height="5">&nbsp;</td></tr>
				<tr>
					<td width="150">카테고리명 :</td>
					<td><input type="text" size="40" name="categoryName" value="${updateCategory.categoryName}"></td>
				</tr>
				<tr>
					<td width="150">보이기 유형 :</td>
					<td><input type="radio"	name="displayType" value="TITLE" <c:if test="${updateCategory.displayType eq '제목'}">checked</c:if>>제목&nbsp;&nbsp;
						<input type="radio" name="displayType" value="MIXED" <c:if test="${updateCategory.displayType eq '제목 + 내용'}">checked</c:if>>제목 + 내용&nbsp;&nbsp;</td>
				</tr>
				<tr>
					<td width="150">설명 :</td>
					<td><input type="text" size="50" name="description" value="${updateCategory.description}"></td>
				</tr>
				<tr>
					<input type="hidden" name="blogId" value="${updateCategory.blogId}"/>
					<input type="hidden" name="categoryId" value="${updateCategory.categoryId}"/>
					<td colspan="10" align="center">&nbsp;<input type="submit" value="카테고리 수정"/>
					<input type="button" value="카테고리 등록 화면 이동" onClick="location.href=`/categories/${blog.blogId}`"/>
					</td>
				</tr>
			</table>
			</form> 
			</c:if>
			<!-- 카테고리 수정화면 종료 -->
		</td>
	</tr>
	<tr><td height="10" colspan="10">&nbsp;</td></tr>
</table>
		
<%@include file="../layout/footer.jsp" %>