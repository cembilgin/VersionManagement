<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
	  $('#gelistirmeTarihi').datepicker({ dateFormat: 'yy-mm-dd' }).val();
	  $('#testeTeslimTarihi').datepicker({ dateFormat: 'yy-mm-dd' }).val();
	  } );
  </script>
</head>
<title> Update Detail</title>
<body style="background-color: #E6E6E6">
	<div class="navbar navbar-inverse"
		style="color: #fff; background-color: #669999; text-align: center; font-weight: bold; font-size: 25px; padding-top: 5px;">XDSL
		Version Management</div>
	
	<form:form method="get" modelAttribute="surum"
             action="/VersionManagement/updateDetailSurum">
             <table style="margin-left: 20px;"width="563" border="0">
                    <tbody>
                           <tr>
                                  <td width="163" height="40"><strong>Defect_Id </strong></td>
                                  <td width="81"><input type="text" class="form-control" value="${defectId }"
                                        name="defectId" id="defectId"></td>
                                  <td width="61">&nbsp;</td>
                           </tr>
                           <tr>
                                  <td width="163" height="40"><strong>Jira Link </strong></td>
                                  <td width="81"><input type="text" class="form-control" value="${jiraLink }"
                                        name="jiraLink" id="jiraLink"></td>
                                  <td width="61">&nbsp;</td>
                           </tr>
                           <tr>
                                  <td width="163" height="40"><strong>Title </strong></td>
                                  <td width="81"><input type="text" class="form-control" value="${baslik }"
                                        name="baslik" id="baslik"></td>
                                  <td width="61">&nbsp;</td>
                           </tr>
                           <tr>
                                  <td width="163" height="40"><strong>Explanation
                                               </strong></td>
                                  <td width="81"><input type="text" class="form-control" value="${aciklama }"
                                        name="aciklama" id="aciklama" ></td>
                                  <td width="61">&nbsp;</td>
                           </tr>
                           <tr>
                                  <td width="163" height="40"><strong>Cost
                                               </strong></td>
                                  <td width="81"><input type="text" class="form-control" value="${maliyet }"
                                        name="maliyet" id="maliyet" ></td>
                                  <td width="61">&nbsp;</td>
                           </tr>
                           <tr>
                                  <td width="163" height="40"><strong>Date of Testing </strong></td>
                                  <td width="81"><input type="text" class="form-control" value="${testeTeslimTarihi }"
                                        name="testeTeslimTarihi" id="testeTeslimTarihi" style="background-color:#fff" readonly></td>
                                  <td width="61">&nbsp;</td>
                           </tr>
                           <tr>
                                  <td width="163" height="40"><strong>User Testing
                                               </strong></td>
                                  <td width="81"><input type="text" class="form-control" value="${testKullanici }"
                                        name="testKullanici" id="canliAlimTarihi" ></td>
                                  <td width="61">&nbsp;</td>
                           </tr>
                           <tr>
                                  <td width="163" height="40"><strong>Date of Developing </strong></td>
                                  <td width="81"><input type="text" class="form-control" value="${gelistirmeTarihi }" 
                                   name="gelistirmeTarihi" id="gelistirmeTarihi" style="background-color:#fff" readonly></td>
                           </tr>
                           <tr>
                                  <td width="163" height="40"><strong>Current User  </strong></td>
                                  <td width="81"><input type="text" class="form-control" value="${guncelKullanici }"
                                        name="guncelKullanici" ></td>
                           </tr>
                           <tr>
                                  <td colspan="2"><input class="btn btn-danger btn-md" style="margin-top:20px;"
                                        type="submit" value="Update" /> </td>
                                                     
                           </tr>
                           <tr>
                           <td><a class="btn btn-danger btn-md" style="margin-top:20px;" href="javascript: history.go(-1)">Cancel</a></td>
                           </tr>
                    </tbody>
             </table>
       </form:form>
	<div class="navbar navbar-inverse navbar-fixed-bottom"
		style="color: #fff; background-color: #669999;">                                    
		<p style="font-size: 16px; font-weight: bold;">This project has been made by intern of Etiya!</p>
		<p style="font-size: 14px; font-weight: bold;">Cem Bilgin</p>
	</div>

</body>
</html>
