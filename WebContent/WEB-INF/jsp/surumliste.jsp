<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.0/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>
  <script>
  $( function() {  
	  $('#gelistirmeTarihi1').datepicker({ dateFormat: 'yy-mm-dd' }).val();
	  $('#gelistirmeTarihi2').datepicker({ dateFormat: 'yy-mm-dd' }).val();
	  } );
  </script>
<title>XDSL Version Management</title>
</head>
<body style="background-color: #F2F2F2">
	<div class="navbar navbar-inverse "
		style="color: #fff; background-color: #669999; text-align: center; font-weight: bold; font-size: 25px; padding-top: 5px;">XDSL
		Version Management</div>
	<form:form method="get" modelAttribute="surum"
		action="/VersionManagement/searchSurum">
		<table style="margin-left: 20px; margin-top: 20px;">
			<tr>
				<td width="163" height="40"><strong>Version </strong></td>
				<td width="163" height="50px;"><input type="text"
					class="form-control" name="surum" id="surum"></td>
				<td width="61">&nbsp;</td>
			</tr>
			<tr>
				<td width="163" height="40"><strong>Date of Go-live </strong></td>
				<td width="163"><input type="text" class="form-control" style="background-color:#fff" readonly
					name="canliAlimTarihi1" id="gelistirmeTarihi1"></td>
					<td width="163"><input type="text" class="form-control" style="background-color:#fff" readonly
					name="canliAlimTarihi2" id="gelistirmeTarihi2"></td>
				<td width="61">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="3"><input class="btn btn-danger btn-md" style="font-size:12px;margin-left:500px;"
					 type="submit" value="Search" /></td>
			</tr>

		</table>
	</form:form>


	<h1 style="color: #000000; text-align: center">Version List</h1>
	<button class="btn btn-danger btn-md" style="margin-left: 20px;"
		onclick="window.location.href='http://localhost:8080/VersionManagement/add'">Add</button>



	<br>

	<display:table class="table"  requestURI="${actionUrl}" name="lists" pagesize="10" id="txt" style="margin-left:20px;">
		<display:column class="active" property="surum" title="Version"></display:column>
		<display:column class="warning" property="surumTipi"
			title="Version Type"></display:column>
		<display:column class="active" property="analizTarihi"
			title="Date of Analysis"></display:column>
		<display:column class="warning" property="gelistirmeTarihi"
			title="Date of Developing"></display:column>
		<display:column class="active" property="testeTeslimTarihi"
			title="Date of Testing"></display:column>
		<display:column class="warning" property="canliAlimTarihi"
			title="Date of Go-live"></display:column>
		<display:column class="active" property="durum" title="Status"></display:column>
		<display:column>
			<td><a class="btn btn-danger btn-md" style="font-size:12px;"
				href="/VersionManagement/update?id=${txt.id}">Update</a></td>
		</display:column>
		<display:column>
			<td><a class="btn btn-danger btn-md" id="delete" style="font-size:12px; width:60px"
				onclick="return confirm('Are you sure that you want to delete it ?');"
				href="/VersionManagement/deleteSurum?id=${txt.id}">Delete</a></td>
		</display:column>
		<display:column>
			<td><a class="btn btn-danger btn-md" style="font-size:12px;"
				href="/VersionManagement/detail?ID=${txt.id}">Detail</a></td>
		</display:column>
	</display:table>

	<div class="navbar navbar-inverse "
		style="color: #fff; background-color: #669999;">
		<p style="font-size: 16px; font-weight: bold;">This project has been made by intern of Etiya!</p>
		<p style="font-size: 14px; font-weight: bold;"> Cem Bilgin</p>
	</div>

</body>
</html>
