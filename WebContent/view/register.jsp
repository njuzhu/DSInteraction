<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>register page</title>
</head>
<body>
<p>
   <table width=650>
      	<tr>
   			<td>
			   	<s:form action="/dsinteraction/register" method="post">
			      <table align="center" border="0">
			      	<tr>
			          <td><s:textfield name="user.name" label="Name"/></td>
			        </tr>
			        <tr>
			           <td><s:password name="user.password" label="Password"/></td>
			        </tr> 
			        
			        <tr>
			          <td colspan="2" align="center">
			          <s:submit value="Submit"/><br>
			          <s:reset value="Reset"/>
			          </td>
			        </tr>
			      </table>
			    </s:form>
   			</td>
   		</tr>
   </table>
</p>

</body>
</html>