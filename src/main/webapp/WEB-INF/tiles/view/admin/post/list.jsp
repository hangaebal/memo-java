<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="http://malsup.github.com/jquery.form.js"></script>
<style>
	.cursor {cursor: pointer; text-align: center;}
	.cursor .glyphicon {font-size: 20px; line-height: 33px;}
</style>
<form id="seqForm" action="/admin/post/seq" method="post">
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
<div>
	<div class="form-horizontal">
		<div class="form-group">
			<label class="col-sm-2 control-label">메뉴 선택</label>
			<div class="col-sm-4">
				<select id="menuId" class="form-control" name="menuId" onchange="changeMenu()">
					<c:forEach items="${menuList}" var="menu">
						<option value="${menu.id}" ${param.menuId == menu.id?'selected':''}>${menu.title}</option>
					</c:forEach>
				</select>
			</div>
		</div>
	</div>

	<table id="postTable" class="table table-bordered">
		<colgroup>
			<col width="10%">
			<col width="10%">
			<col width="50%">
			<col width="20%">
			<col width="10%">
		</colgroup>
		<thead>
			<tr>
				<th>드래그</th>
				<th>유형</th>
				<th>제목</th>
				<th>연도</th>
				<th>수정</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${postList}" var="post">
			<tr>

				<td class="cursor">
					<input class="form-control" type="hidden" name="id" value="${post.id}">
					<span class="glyphicon glyphicon-resize-vertical" aria-hidden="true"></span>
				</td>
				<td>${post.type}</td>
				<td>${post.title}</td>
				<td>${post.year}</td>
				<td><a class="btn btn-info" href="/admin/post/edit/${post.id}">수정</a></td>

			</tr>
		</c:forEach>

		</tbody>
	</table>

	<div class="text-right">
		<button type="submit" class="btn btn-success" style="float: left">순서 저장</button>
		<button type="button" class="btn btn-primary" onclick="createPost()">포스트 추가</button>
	</div>
</div>
</form>

<script>
	$(function(){
		$('#postTable tbody').sortable();
	});

	function createPost() {
		location.href = '/admin/post/create?menuId=' + $('#menuId').val();
	}

	function changeMenu() {
		location.href = '/admin/post?menuId=' + $('#menuId').val();
	}

	$('#seqForm').ajaxForm({
		beforeSubmit: function (data, frm, opt) {
			return true;
		},
		success: function(data, statusText){
			if (data == "success") {
				alert('순서가 저장되었습니다.');
			} else {
				alert("오류가 발생했습니다.");
			}
		},
		error: function(e){
			alert("오류가 발생했습니다.");
			console.log(e);
		}
	});
</script>