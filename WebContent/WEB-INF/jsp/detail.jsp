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
<script
       src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
       src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.0/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>
<title>Detail</title>
<script type="text/javascript">
function ABC(){
	alert("nothing added to be saved...");
}
</script>

 <script>
  $( function() {  
	  $('#gelistirmeTarihi').datepicker({ dateFormat: 'yy-mm-dd' }).val();
	  $('#testeTeslimTarihi').datepicker({ dateFormat: 'yy-mm-dd' }).val();
	  } );
  </script>
</head>
<body style="background-color: #E6E6E6">
       <div class="navbar navbar-inverse "
             style="color: #fff; background-color: #669999; text-align: center; font-weight: bold; font-size: 25px; padding-top: 5px;">XDSL
             Version Management</div>
      
          
 <form:form method="get" modelAttribute="surum" 
             action="/VersionManagement/detayeklesubmit?ID=${kl}">
             <table style="margin-left: 20px;margin-top:20px;" width="650" border="0">
                    <tbody>
                    	 <tr>
                                  <td width="163" height="40"><strong>Defect_Id </strong></td>
                                  <td width="180"><input type="text" class="form-control" style="width:150px;" value="${defectId }"
                                        name="defectId"></td>
                                  <td width="61">&nbsp;</td>
                                  
                                  <td width="500" height="40"><strong>Teste Teslim
                                               Tarihi </strong></td>
                                  <td width="180"><input type="text" class="form-control" value="${testeTeslimTarihi }"
                                        name="testeTeslimTarihi" id="testeTeslimTarihi" style="background-color:#fff; width:150px;" readonly></td>
                                  <td width="61">&nbsp;</td>
                           </tr>
                           <tr>
                                  <td width="163" height="40"><strong>Jira_Link </strong></td>
                                  <td width="180"><input type="text" class="form-control" value="${jiraLink }"  style="width:150px;"
                                        name="jiraLink" id="jiraLink"></td>
                                  <td width="61">&nbsp;</td>
                                  
                                  <td width="163" height="40"><strong>User Testing
                                               </strong></td>
                                  <td width="180"><input type="text" class="form-control" value="${testKullanici }"   style="width:150px;"
                                        name="testKullanici" id="canliAlimTarihi" ></td>
                                  <td width="61">&nbsp;</td>
                           </tr>
                           <tr>
                                  <td width="163" height="40"><strong>Title </strong></td>
                                  <td width="180"><input type="text" class="form-control" style="width:150px;" value="${baslik }"   style="width:150px;"
                                        name="baslik" id="baslik"></td>
                                  <td width="61">&nbsp;</td>
                                  
                                  <td width="163" height="40"><strong>Date of Developing </strong></td>
                                  <td width="180"><input type="text" class="form-control" value="${gelistimeTarihi } " 
                                   name="gelistirmeTarihi" id="gelistirmeTarihi" style="background-color:#fff; width:150px;" readonly></td>
                           </tr>
                           <tr>
                                  <td width="163" height="40"><strong>Explanation
                                               </strong></td>
                                  <td width="180"><input type="text" class="form-control"style="width:150px;" value="${aciklama }"
                                        name="aciklama" id="aciklama" ></td>
                                  <td width="61">&nbsp;</td>
                                  
                                  
                                  <td width="163" height="40"><strong>Current User  </strong></td>
                                  <td width="180"><input type="text" class="form-control" style="width:150px;" value="${guncelKullanici }"
                                        name="guncelKullanici" ></td>
                           </tr>
                           <tr>
                                  <td width="163" height="40"><strong>Cost
                                               </strong></td>
                                  <td width="180"><input type="text" class="form-control " style="width:150px;" value="${maliyet }"
                                        name="maliyet" id="maliyet" ></td>
                                  <td width="61">&nbsp;</td>
                           </tr>

                    </tbody>
                     
                    <tr>
				<td colspan="2"><input class="btn btn-danger btn-md" 
					 type="submit"  value="Add" /></td>
			</tr>
            
             </table>
       </form:form>
       <display:table class="table" name="lists" pagesize="10"  style="margin-left:20px;" id="txt">
         		
            <display:column class="active" property="surumId"
             		 title="Version Id"></display:column>
             <display:column class="active" property="defectId"
             		 title="Defect Id"></display:column>
             <display:column class="active" property="jiraLink"
             		 title="Jira Link"></display:column>
             <display:column class="warning" property="baslik"
                    title="Title"></display:column>
             <display:column class="active" property="aciklama"
                    title="Explanation"></display:column>
             <display:column class="warning" property="maliyet"
                    title="Cost"></display:column>
             <display:column class="active" property="testeTeslimTarihi"
                    title="Date of Testing"></display:column>
             <display:column class="warning" property="testKullanici"
                    title="User Testing"></display:column>
             <display:column class="active" property="gelistirmeTarihi" 
             		title="Date of Developing"></display:column>
             <display:column>
             <display:column class="warning" property="guncelKullanici"
                    title="Current User"></display:column>
                    <td><a class="btn btn-danger btn-md "style="width:80px;"
                           href="/VersionManagement/updateDetail?ID=${txt.id}&cn=${txt.count}">Update</a></td>
            </display:column>
             <display:column>
                    <td><a class="btn btn-danger btn-md" id="delete" style="width:80px;"
                           onclick="return confirm('Are you sure that you want to delete it ?');"
                           href="/VersionManagement/detaysil?ID=${txt.id}&cn=${txt.count}">Delete</a></td>
             </display:column>   
              
       </display:table>
       <c:set var="add" scope="session" value="${NumberofAddition}"/>	
      
       <c:choose>
       <c:when test="${add>0 }">
        <a class="btn btn-danger btn-md"  style="margin-left:20px;"
				href="/VersionManagement/detaykaydet">Save</a>
       </c:when>
       <c:otherwise>
       <p class="btn btn-danger btn-md"  style="margin-left:20px;" onclick="ABC()">Save</p>
       </c:otherwise>
       </c:choose>
       
			<c:set var="Save" scope="session" value="${save}"/>	
			<c:if test="${Save == 1}">
			<script type="text/javascript">
			alert("It is successfully saved !");
			</script>
			</c:if>
			<c:if test="${Save == 0}">
			<script type="text/javascript">
			alert("Saving is failed !");
			</script>
			</c:if>
			<div class="navbar navbar-inverse "
		style="color: #fff; background-color: #669999;">
		<p style="font-size: 16px; font-weight: bold;">This project has been made by intern of Etiya!</p>
		<p style="font-size: 14px; font-weight: bold;">Cem Bilgin</p>
	</div>
				
				
</body>
</html>

