<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
	.cursor {cursor: pointer; text-align: center;}
	.cursor .glyphicon {font-size: 20px; line-height: 33px;}
</style>
<div>
<form id="menuForm" action="/admin/menu" method="post">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	<table id="menuTable" class="table table-bordered">
		<colgroup>
			<col width="10%">
			<col width="">
			<col width="">
			<col width="">
		</colgroup>
		<thead>
		<tr>
			<th>드래그</th>
			<th>제목</th>
			<th>경로(영문)</th>
			<th>삭제</th>
		</tr>
		</thead>
		<tbody id="menuTbody">
		<c:forEach items="${menuList}" var="menu">
			<tr>
				<td class="cursor">
					<input class="form-control" type="hidden" name="id" value="${menu.id}">
					<span class="glyphicon glyphicon-resize-vertical" aria-hidden="true"></span>
				</td>
				<td><input class="form-control" type="text" name="title" value="${menu.title}" required></td>
				<td><input class="form-control" type="text" name="path" value="${menu.path}" required></td>
				<td><button type="button" class="btn btn-danger" onclick="deleteRow(event, ${menu.id})">삭제</button></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="text-right">
		<button type="button" class="btn btn-info" onclick="addRow()">행 추가</button>
		<button type="button" class="btn btn-primary" onclick="save()">저장</button>
	</div>

</form>

</div>

<script>
	$(function(){
		$('#menuTbody').sortable();
	});

	function deleteRow(e, id) {
		if (confirm('정말 삭제할까요?')) {
			if (id == null) {
				$(e.target).parents('tr').remove();
			} else {
				var token = $("meta[name='_csrf']").attr("content");
				var header = $("meta[name='_csrf_header']").attr("content");

				$.ajax({
					url: '/admin/menu/' + id
					,method: 'delete'
					,beforeSend: function(xhr) {
						xhr.setRequestHeader(header, token);
					}
				}).done(function() {
					$(e.target).parents('tr').remove();
				}).fail(function() {
					alert('오류가 발생했습니다');
				});
			}
		}
	}

	function addRow() {
		var rowTag = '<tr>'
			+ '<td class="cursor"><span class="glyphicon glyphicon-resize-vertical" aria-hidden="true"></span></td>'
			+ '<td><input class="form-control" type="text" name="title" required></td>'
			+ '<td><input class="form-control" type="text" name="path" required></td>'
			+ '<td><button type="button" class="btn btn-danger" onclick="deleteRow(event)">삭제</button></td>'
			+ '</tr>';
		$('#menuTable').append(rowTag);
	}

	function save() {
		var isValid = true;
		$('#menuTbody tr').each(function(){
			var $input = $(this).find('input[name="title"]');
			if ($.trim($input.val()) == "") {
				$input.focus();
				alert('필수 항목이 비어있습니다.');
				isValid = false;
				return false;
			}
			$input = $(this).find('input[name="path"]');
			if ($.trim($input.val()) == "") {
				$input.focus();
				alert('필수 항목이 비어있습니다.');
				isValid = false;
				return false;
			}

		});

		if (isValid) {
			$('#menuForm').submit();
		}

	}



</script>