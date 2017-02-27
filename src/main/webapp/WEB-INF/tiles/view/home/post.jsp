<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="newline" value="<%='\n'%>" />
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
				<img src="/upload/${image.path}" class="modalOpen">
			</div>
		</c:forEach>
	</c:when>
</c:choose>
</article>

<script>
	$(function(){
		$('.modalOpen').click(function(e){
			e.preventDefault();

			$('#modalImg').attr('src', $(e.target).attr('src'));
			$('#modalImg').one('load', function() {
				modalToCenter();

				$('#modalBg').show();
				$('#modalImg').show();
			});
		});

		$('.modal').click(function(){
			$('#modalImg').hide();
			$('#modalBg').hide();
			$('#modalImg').removeAttr('src');
		});

		$(window).on('resize', function() {
			modalToCenter();
		});
	});

	function modalToCenter() {
		var width = window.innerWidth ? window.innerWidth : document.documentElement.clientWidth ? document.documentElement.clientWidth : screen.width;
		var height = window.innerHeight ? window.innerHeight : document.documentElement.clientHeight ? document.documentElement.clientHeight : screen.height;

		$('#modalImg').css('top', (height-$('#modalImg').outerHeight())/2 + 'px');
		$('#modalImg').css('left', (width-$('#modalImg').outerWidth())/2 + 'px');
	}


</script>