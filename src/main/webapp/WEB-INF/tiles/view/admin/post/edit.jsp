<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="${contextPath}/js/jquery.form.min.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.2/summernote.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.2/summernote.js"></script>
<style>
	#previewDiv {min-height: 100px;}
	#previewDiv:after {content:"";display:block; clear: both;}
	.previewItem {float: left;}
	.previewItem img {width: 200px;}
	.previewItem .delImg {cursor: pointer;}

</style>

<div>
<form id="postForm" action="${contextPath}/admin/post/edit" method="post">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	<input type="hidden" name="id" value="${post.id}"/>

	<div class="form-horizontal">
		<div class="form-group">
			<label class="col-sm-1 control-label">메뉴</label>
			<div class="col-sm-4">
				<select class="form-control" name="menuId">
					<c:forEach items="${menuList}" var="menu">
						<option value="${menu.id}" ${menu.id == post.menuId?'selected':''}>${menu.title}</option>
					</c:forEach>
				</select>
			</div>
		</div>
	</div>

	<div class="form-horizontal">
		<div class="form-group">
			<label class="col-sm-1 control-label">제목</label>
			<div class="col-sm-7">
				<input class="form-control" type="text" name="title" required value="${post.title}">
			</div>
			<label class="col-sm-1 control-label">연도</label>
			<div class="col-sm-3">
				<input class="form-control" type="text" name="year" required value="${post.year}">
			</div>
		</div>
	</div>

	<hr>
	<c:if test="${post.type == 'text'}">
		<div class="form-group typeText">
			<label class="control-label">글</label>
			<textarea class="form-control" name="contents" rows="10">${post.contents}</textarea>
		</div>
	</c:if>

	<c:if test="${post.type == 'editor'}">
		<textarea name="contents" style="display: none"></textarea>
		<div id="summernote">
		</div>
		<script>
			$('#summernote').summernote({
				minHeight: 500
			});

			$('#summernote').summernote('code', '${post.contents}');
		</script>
	</c:if>

	<c:if test="${post.type == 'image' or post.type == 'video'}">
		<div class="typeImage typeVideo">
			<label class="control-label">미리보기</label>
			<div id="previewDiv">
				<c:forEach items="${imageList}" var="image">
					<c:if test="${post.type == 'image'}">
						<div class="previewItem">
							<input type="hidden" name="imgId" value="${image.id}">
							<p><span class="glyphicon glyphicon-remove  delImg" onclick="delImg(event, ${image.id})"></span> ${image.title}</p>
							<img src="${contextPath}/upload/${image.thumbPath}">
						</div>
					</c:if>
					<c:if test="${post.type == 'video'}">
						<div class="previewItem">
							<input type="hidden" name="imgId" value="${image.id}">
							<p><span class="glyphicon glyphicon-remove  delImg" onclick="delImg(event, ${image.id})"></span></p>
							<video src="${contextPath}/upload/${image.path}" controls width="300"/>
						</div>
					</c:if>
				</c:forEach>

			</div>
		</div>
	</c:if>
</form>

<hr>
<c:if test="${post.type == 'image'}">
	<form id="imageForm" class="typeImage form-inline" action="${contextPath}/admin/post/image" method="post" enctype="multipart/form-data">
		<p><strong>이미지 등록</strong> <small>등록 후 드래그로 순서 변경 가능</small></p>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		<input type="hidden" name="type" value="image"/>
		<input id="imgTitle" class="form-control" type="text" name="imgTitle" placeholder="이미지 제목">
		<input id="imgFile" type="file" name="mFile" accept="image/*">
		<button class="btn btn-info btn-sm" type="submit">이미지 등록</button>
	</form>
</c:if>

<c:if test="${post.type == 'video'}">
	<form id="videoForm" class="typeVideo" action="${contextPath}/admin/post/image" method="post" enctype="multipart/form-data">
		<p><strong>동영상 등록</strong></p>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		<input type="hidden" name="type" value="video"/>
		<input id="videoFile" type="file" name="mFile" accept="video/*">
		<button class="btn btn-info btn-sm" type="submit">동영상 등록</button>
	</form>
</c:if>


<div class="text-right">
	<button type="button" class="btn btn-danger" onclick="deletePost(${post.id})">삭제</button> |
	<button type="button" class="btn btn-primary" onclick="save()">저장</button>
</div>

</div>

<script>
$(function(){

	$('#previewDiv').sortable();

	$('#imageForm').ajaxForm({
		beforeSubmit: function (data, frm, opt) {
			if ($('#imgTitle').val() == '') {
				alert('이미지 제목을 입력하세요.');
				$('#imgTitle').focus();
				return false;
			}
			if ($('#imgFile').val() == '') {
				alert('파일을 선택하세요.');
				$('#imgFile').focus();
				return false;
			}
			return true;
		},
		success: function(data, statusText){
			var previewTag = '<div class="previewItem">'
					+'<input type="hidden" name="imgId" value="'+data.id+'">'
					+'<p><span class="glyphicon glyphicon-remove  delImg" onclick="delImg(event, '+data.id+')"></span> '+data.title+'</p>'
					+'<img src="${contextPath}/upload/'+data.thumbPath+'">'
					+'</div>';
			$('#previewDiv').append(previewTag);

			// 이미지 업로드 폼 초기화
			$('#imageForm')[0].reset();
		},
		error: function(e){
			alert("오류가 발생했습니다.");
			console.log(e);
		}
	});

	$('#videoForm').ajaxForm({
		beforeSubmit: function (data, frm, opt) {
			if ($('#videoFile').val() == '') {
				alert('파일을 선택하세요.');
				$('#videoFile').focus();
				return false;
			}
			return true;
		},
		success: function(data, statusText){
			var previewTag = '<div class="previewItem">'
				+'<input type="hidden" name="imgId" value="'+data.id+'">'
				+'<p><span class="glyphicon glyphicon-remove delImg" onclick="delImg(event, '+data.id+')"></span></p>'
				+'<video src="${contextPath}/upload/'+data.path+'" controls width="300">'
				+'</div>';
			$('#previewDiv').html(previewTag);

			// 이미지 업로드 폼 초기화
			$('#videoForm')[0].reset();
		},
		error: function(e){
			alert("오류가 발생했습니다.");
			console.log(e);
		}
	});
});

function delImg(e, imageId) {
	if (confirm('정말 삭제할까요?')) {
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");

		$.ajax({
			url: '${contextPath}/admin/post/image/' + imageId
			,method: 'delete'
			,beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			}
		}).done(function() {
			$(e.target).parents('.previewItem').remove();
		}).fail(function() {
			alert('오류가 발생했습니다.');
		});
	}
}

function save() {
	var isValid = true;

	var $input = $('input[name="title"]');
	if ($.trim($input.val()) == '') {
		$input.focus();
		alert('제목은 필수입니다.');
		isValid = false;
		return false;
	}

	//var type = $('#typeSelect').val();
	var type = '${post.type}';
	if (type == 'text') {
		$input = $('textarea[name="contents"]');
		if ($.trim($input.val()) == '') {
			$input.focus();
			alert('글은 필수입니다.');
			isValid = false;
			return false;
		}
	} else if (type == 'editor') {
		if ($('#summernote').summernote('isEmpty') || $.trim($('#summernote').summernote('code')) == '') {
			$('#summernote').summernote('focus');
			alert('글은 필수입니다.');
			isValid = false;
			return false;
		}
		$('textarea[name="contents"]').val($('#summernote').summernote('code'));
	} else {
		if ($('input[name="imgId"]').length == 0) {
			alert('미디어 파일은 필수입니다.');
			isValid = false;
			return false;
		}
	}

	if (isValid) {
		$('#postForm').submit();
	}
}

function deletePost(id) {
	if (confirm('정말 삭제할까요?')) {

		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");

		$.ajax({
			url: '${contextPath}/admin/post/' + id
			,method: 'delete'
			,beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			}
		}).done(function(data) {
			alert('삭제되었습니다.');
			location.href = '${contextPath}/'+data;
		}).fail(function() {
			alert('오류가 발생했습니다.');
		});
	}
}

</script>