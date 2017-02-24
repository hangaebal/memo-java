<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="newline" value="<%='\n'%>" />
<script src="/js/jquery.fancybox.min.js"></script>
<style>
	#postArticle {/*display: inline-block; width: 100%;*/}
	#postArticle:after {
		content: " ";
		display: block;
		height: 0;
		clear: both;
	}
	.imageItem {float: left; width: 30%; margin-left: 2.5%;}
	.imageItem p { width: 100%; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;}
	.imageItem img { width: 100%;}
</style>
<article id="postArticle">
<c:choose>
	<c:when test="${post.type eq 'text'}">
		<div>${fn:replace(post.contents, newline, '<br/>')}</div>
	</c:when>
	<c:when test="${post.type eq 'video'}">
		<c:set var="video" value="${imageList[0]}"/>
		<video src="/upload/${video.path}" width="100%" controls></video>
	</c:when>
	<c:when test="${post.type eq 'image'}">
		<c:forEach items="${imageList}" var="image">
			<div class="imageItem">
				<p>${image.title}</p>
				<a href="/upload/${image.path}" data-fancybox="postImages" data-caption="${image.title}">
					<img src="/upload/${image.path}">
				</a>
			</div>
		</c:forEach>
	</c:when>
</c:choose>
</article>

<script>

</script>