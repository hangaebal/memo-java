<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="request"/>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, user-scalable=no">
	<title><tiles:getAsString name="title" /></title>
	<link rel="shortcut icon" href="${contextPath}/memo.ico">
	<link rel="stylesheet" href="${contextPath}/css/normalize.css">
	<link rel="stylesheet" href="${contextPath}/css/main.css">
	<script src="${contextPath}/js/jquery-3.1.1.min.js"></script>
</head>
<body>
	<header>
		<a href="${contextPath}/">
			<img id="iconImg" alt="아이콘" src="${contextPath}/img/icon.png">
			<p id="headerTitle">제목 없음 - 메모장</p>
		</a>

		<nav>
			<ul id="mainMenuUl">
				<c:forEach items="${menuList}" var="menu">
					<li class="mainMenu">
						<p>${menu.title}</p>
						<ul class="subMenuUl ${menu.hasYear == 'Y'?'widthShortCut':''}">
							<c:forEach items="${postList}" var="post">
								<c:if test="${menu.id == post.menuId}">
									<li class="subMenu">
										<a href="${contextPath}/post/${post.id}">
											<p>${post.title} <span>${post.year}</span></p>
										</a>
									</li>
								</c:if>
							</c:forEach>
						</ul>
					</li>
				</c:forEach>
			</ul>
		</nav>
	</header>

	<section>
		<tiles:insertAttribute name="body" />
	</section>

	<div id="modalBg" class="modal"></div>
	<img id="modalImg" class="modal">

<script type="text/javascript">
	// 메인 메뉴 클릭시 서브 메뉴 오픈
	$('.mainMenu').click(function(e){
		// body click 으로 전파되는것을 막는다.
		e.stopPropagation();

		$('.mainMenu').removeClass('selected');
		// 선택된 메뉴 보더 진하게
		// 하위 메뉴 노출
		$(this).addClass('selected');
	});
	// 열려있는 서브메뉴 닫기
	$('body').click(function(e){
		$('.mainMenu').removeClass('selected');
	});
</script>
</body>
</html>
