<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="request"/>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="_csrf" content="${_csrf.token}"/>
	<meta name="_csrf_header" content="${_csrf.headerName}"/>
	<title><tiles:getAsString name="title" /></title>
	<link rel="stylesheet" href="${contextPath}/css/normalize.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	<script src="${contextPath}/js/jquery-3.1.1.min.js"></script>
	<script src="${contextPath}/js/jquery-ui.min.js"></script>
</head>
<body>
<section class="container">

	<nav class="navbar navbar-inverse">
		<ul class="nav navbar-nav">
			<li><a href="${contextPath}/admin/menu">메뉴관리</a></li>
			<li><a href="${contextPath}/admin/post">포스트 관리</a></li>
		</ul>
	</nav>

	<article>
		<tiles:insertAttribute name="body" />
	</article>

</section>
<script type="text/javascript">

</script>
</body>
</html>
