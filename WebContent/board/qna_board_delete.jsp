<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	int num = Integer.parseInt(request.getParameter("num"));
	request.setAttribute("num", num);
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>MVC �Խ���</title>
</head>
<body>
	<div align="center">
		<form action="./BoardDeleteAction.bo?num=<%=num %>" name="deleteForm" method="post">
			<table border="1">
				<tr>
					<td>
						<font size="2">�� ��й�ȣ</font>
					</td>
					<td>
						<input type="password" name="BOARD_PASS"/>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<a href="javascript:deleteForm.submit()">[����]</a>&nbsp;&nbsp;
						<a href="javascript:history.go(-1)">[���ư���]</a>
					</td>
					<td><%=num %></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
