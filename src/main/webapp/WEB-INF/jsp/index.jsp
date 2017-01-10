<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<meta charset="UTF-8">
	<title>...</title>
	<link rel="stylesheet" href="css/normalize.css">
	<link rel="stylesheet" href="css/main.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
</head>
<body>
<section>
	<header>
		<img id="iconImg" alt="아이콘" src="img/icon.png">
		<p>제목 없음 - 메모장</p>
	</header>
	<nav>

		<ul id="mainMenuUl">
			<c:forEach items="${mainMenuList}" var="mainMenu">
				<li class="mainMenu">
					<p>${mainMenu.name}</p>
					<ul class="subMenuUl ${mainMenu.hasShortcut == 'y'?'widthShortCut':''}">
						<c:forEach items="${subMenuList}" var="subMenu">
							<c:if test="${mainMenu.id == subMenu.parentId}">
								<c:choose>
									<c:when test="${subMenu.lineYn == 'y'}">
										<li class="line">
									</c:when>
									<c:otherwise>
										<li class="subMenu">
											<a href="${subMenu.url}">
												<p>${subMenu.name} <span>${subMenu.shortcut}</span></p>
											</a>

										</li>
									</c:otherwise>
								</c:choose>
							</c:if>
						</c:forEach>
					</ul>
				</li>
			</c:forEach>
		</ul>
	</nav>
	<article>
		${mainMenuList.size()}<br>
		${subMenuList.size()}<br>
	</article>
</section>
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