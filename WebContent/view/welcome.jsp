<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>Welcome Page</title>
</head>

<jsp:useBean id="name" scope="session" class="java.lang.String" />

<body>
<table width="650" border="0" >
  <tr>
   	<td>
   		<font color="green"><%=name%>,Welcome!</font><br>
   	</td>
  </tr>
</table>



</body>
</html>


