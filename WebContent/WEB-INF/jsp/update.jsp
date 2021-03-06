<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
	  $('#analizTarihi').datepicker({ dateFormat: 'yy-mm-dd' }).val();
	  $('#gelistirmeTarihi').datepicker({ dateFormat: 'yy-mm-dd' }).val();
	  $('#testeTeslimTarihi').datepicker({ dateFormat: 'yy-mm-dd' }).val();
	  $('#canliAlimTarihi').datepicker({ dateFormat: 'yy-mm-dd' }).val();
	  } );
  </script>
  <script type="text/javascript">
  function goBack(){
	  
  }
  </script>
</head>
<title>Update</title>
<body style="background-color: #E6E6E6">
	<div class="navbar navbar-inverse"
		style="color: #fff; background-color: #669999; text-align: center; font-weight: bold; font-size: 25px; padding-top: 5px;">XDSL
		Version Manegement</div>
	<form:form method="get" modelAttribute="surum"
		action="/VersionManagement/updateSurum">
		<table style="margin-left: 10px; margin-top: 20px" >
			<tbody>
				
				<tr>
					<td width="163" height="40"><strong>Version </strong></td>
					<td width="81"><input type="text" class="form-control" style="width:150px;height:30px;" value="${surum}"
						 name="surum" id="surum"></td>
					<td width="61">&nbsp;</td>
				</tr>

				<tr>
					<td><strong>Version Type</strong></td>
					 <td><select id="surumTipi" name="surumTipi"  style="width:150px;height:30px;">
					  <option>${surumTipi}</option>
						<c:forEach var="listValue" items="${surumlist}">
                                                  <option>${listValue}</option>  
                                               </c:forEach>

         
       
        </select> 
        </td>
				<tr>
					<td><strong>Date of Analysis  </strong></td>
					<td><input type="text" class="form-control" 
						name="analizTarihi" value="${analizTarihi}" id="analizTarihi" style="background-color:#fff; width:150px;height:30px;" readonly></td>
				</tr>
				<tr>
					<td><strong>Date of Developing</strong></td>
					<td><input type="text" class="form-control"
						name="gelistirmeTarihi" value="${gelistirmeTarihi}"
						id="gelistirmeTarihi"style="background-color:#fff; width:150px;height:30px;" readonly></td>
				</tr>
				<tr>
					<td><strong>Date of Testing </strong></td>
					<td><input type="text" class="form-control"
						name="testeTeslimTarihi" value="${testeTeslimTarihi}"
						id="testeTeslimTarihi"style="background-color:#fff; width:150px;height:30px;" readonly></td>
				</tr>
				<tr>
					<td><strong>Date of Go-live </strong></td>
					<td><input type="text" class="form-control"
						name="canliAlimTarihi" value="${canliAlimTarihi}"
						id="canliAlimTarihi" style="background-color:#fff; width:150px;height:30px;" readonly></td>
				</tr>
				<tr>
					<td><strong>Status </strong></td>
					<td><select id="durum" name="durum"  style="width:150px;height:30px;">
					  <option>${durum}</option>
						<c:forEach var="listValue" items="${surumlist2}">
                                                  <option>${listValue}</option>  
                                               </c:forEach>

         
       
        </select> 
        </td>
				</tr>
				<tr>
					<td colspan="2"><input class="btn btn-danger btn-md" style="margin-top: 50px;"
						type="submit" value="Update" /></td>
				</tr>
				
				
			</tbody>
		</table>
	</form:form>
	<div class="navbar navbar-inverse navbar-fixed-bottom "
		style="color: #fff; background-color: #669999;">
		<p style="font-size: 16px; font-weight: bold;">This project has been made by intern of Etiya!</p>
		<p style="font-size: 14px; font-weight: bold;">Cem Bilgin</p>
	</div>

</body>
</html>
