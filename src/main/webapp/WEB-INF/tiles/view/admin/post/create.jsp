<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="http://malsup.github.com/jquery.form.js"></script>
<style>
	#typeImage:after {content:"";display:block; clear: both;}
	.imgPreview {float: left;}
	.imgPreview img {width: 200px;}
	.imgPreview .delImg {cursor: pointer;}
</style>

<div>
<form id="postForm" action="/admin/post" method="post">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

	<div class="form-horizontal">
		<div class="form-group">
			<label class="col-sm-1 control-label">메뉴</label>
			<div class="col-sm-4">
				<select class="form-control" name="menuId">
					<c:forEach items="${menuList}" var="menu">
						<option value="${menu.id}">${menu.title}</option>
					</c:forEach>
				</select>
			</div>
		</div>
	</div>

	<div class="form-horizontal">
		<div class="form-group">
			<label class="col-sm-1 control-label">형식</label>
			<div class="col-sm-4">
				<select class="form-control" name="type">
					<option value="text">글</option>
					<option value="video">동영상</option>
					<option value="video">이미지</option>
				</select>
			</div>
		</div>
	</div>

	<div class="form-horizontal">
		<div class="form-group">
			<label class="col-sm-1 control-label">제목</label>
			<div class="col-sm-7">
				<input class="form-control" type="text" name="title" required>
			</div>
			<label class="col-sm-1 control-label">연도</label>
			<div class="col-sm-3">
				<input class="form-control" type="text" name="year" required>
			</div>
		</div>
	</div>

	<hr>

	<div class="form-group">
		<label class="control-label">글</label>
		<textarea class="form-control" name="contents" rows="10"></textarea>
	</div>

	<div id="typeImage">
		<div class="imgPreview">
			<p><span class="glyphicon glyphicon-remove delImg" onclick="delImg(event)"></span> 타이틀</p>
			<img src="/upload/image/1d0af860b8134d9b873c9c3e26ef96a4.jpg">
		</div>
		<div class="imgPreview">
			<p><span class="glyphicon glyphicon-remove delImg" onclick="delImg(event)"></span> 타이틀2</p>
			<img src="/upload/image/8a1790f732554e2e9d3bd2f0495f18f6.jpg">
		</div>
	</div>

</form>

<hr>
<form id="imageForm" action="/admin/post/image" method="post" enctype="multipart/form-data">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	<input id="imgTitle" type="text" name="imgTitle">
	<input id="imgFile" type="file" name="imgFile">
	<button type="submit">업로드</button>
</form>


<div class="text-right">
	<button type="button" class="btn btn-primary" onclick="save()">등록</button>
</div>
</div>

<script>
$(function(){
	$('#typeImage').sortable();

	$('#imageForm').ajaxForm({
		beforeSubmit: function (data, frm, opt) {
			if ($('#imgTitle').val() == '') {
				alert('제목을 입력하세요.');
				$('#imgTitle').focus();
				return false;
			}
			if ($('#imgFile').val() == '') {
				alert('이미지를 선택하세요.');
				$('#imgFile').focus();
				return false;
			}
			return true;
		},
		success: function(data, statusText){
			console.log(data); //응답받은 데이터 콘솔로 출력

			var imgPreviewTag = '<div class="imgPreview">'
					+'<input type="hidden" name="imgId" value="'+data.id+'">'
					+'<p><span class="glyphicon glyphicon-remove  delImg" onclick="delImg(event)"></span> '+data.title+'</p>'
					+'<img src="/upload/'+data.path+'">'
					+'</div>';
			$('#typeImage').append(imgPreviewTag);

			// 이미지 업로드 폼 초기화
			$('#imageForm')[0].reset();
		},
		error: function(e){
			alert("에러발생!!");
			console.log(e);
		}
	});
});

function delImg(e) {
	console.log($(e.target).parents('.imgPreview'));
	if (confirm('정말 삭제할까요?')) {

		$(e.target).parents('.imgPreview').remove();
	}
}

</script>