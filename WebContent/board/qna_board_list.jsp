<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="net.board.db.*"%>

<%
	List<BoardBean> boardList = (List<BoardBean>)request.getAttribute("boardList");
	int listCount = ((Integer)request.getAttribute("listCount")).intValue();
	int nowPage = ((Integer)request.getAttribute("page")).intValue();
	int maxPage = ((Integer)request.getAttribute("maxPage")).intValue();
	int startPage = ((Integer)request.getAttribute("startPage")).intValue();
	int endPage = ((Integer)request.getAttribute("endPage")).intValue();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>MVC �Խ���</title>
</head>
<body>
<div align="center">
	<!-- �Խ��� ����Ʈ -->
	<table width="50%" border="0" cellpadding="0" cellspacing="0">
		<%
	      if(listCount>=0){
        %>
		<tr align="center" valign="middle">
			<th colspan="4">MVC �Խ���</th>
			<td align="right"><font size="2">�� ���� : ${listCount }</font></td>
		</tr>

		<tr align="center" valign="middle" bordercolor="#333">
			<td style="font-family: Tahoma; font-size: 8pt;" width="8%"
				height="26">
				<div align="center">��ȣ</div>
			</td>
			<td style="font-family: Tahoma; font-size: 8pt;" width="50%"
				height="26">
				<div align="center">����</div>
			</td>
			<td style="font-family: Tahoma; font-size: 8pt;" width="14%"
				height="26">
				<div align="center">�ۼ���</div>
			</td>
			<td style="font-family: Tahoma; font-size: 8pt;" width="17%"
				height="26">
				<div align="center">��¥</div>
			</td>
			<td style="font-family: Tahoma; font-size: 8pt;" width="11%"
				height="26">
				<div align="center">��ȸ��</div>
			</td>
		</tr>

		<%
		for(int i =0; i<boardList.size(); i++){
			BoardBean bl = boardList.get(i);
	    %>

		<tr align="center" valign="middle" bordercolor="#333"
			onmouseover="this.style.backgroundColor='F8F8F8'"
			onmouseout="this.style.backgroundColor=''">
			<td height="23" style="font-family: Tahoma; font-size: 10pt;"><%=bl.getBOARD_NUM() %>
			</td>
			<td height="23" style="font-family: Tahoma; font-size: 10pt;">
				<div align="left">
					<% if(bl.getBOARD_RE_LEV()!=0){	%>
					<%for(int a=0; a<=bl.getBOARD_RE_LEV()*2;a++){ %>
					&nbsp;
					<%} %>
					��
					<%}else{ %>
					��
					<%} %>
					<a href="./BoardDetailAction.bo?num=<%=bl.getBOARD_NUM() %>"> <%=bl.getBOARD_SUBJECT() %>
					</a>
				</div>
			</td>
			<td height="23" style="font-family: Tahoma; font-size: 10pt;">
				<div align="center"><%=bl.getBOARD_NAME() %></div>
			</td>
			<td height="23" style="font-family: Tahoma; font-size: 10pt;">
				<div align="center"><%=bl.getBOARD_DATE() %></div>
			</td>
			<td height="23" style="font-family: Tahoma; font-size: 10pt;">
				<div align="center"><%=bl.getBOARD_READCOUNT() %></div>
			</td>
		</tr>
		<%} %>
		<tr align="center" height="20">
			<td colspan="7" style="font-family: Tahoma; font-size: 10pt;">
				<%if(nowPage<=1){ %> 
					[����]&nbsp; 
				<%}else{ %> 
					<a href="./BoardListAction.bo?page=<%=nowPage-1 %>">[����]</a>&nbsp; 
				<%} %> 
				
				<%for(int a=startPage; a<=endPage; a++){
					if(a==nowPage){%> 
					[<%=a %>] 
					<%}else{ %> 
					<a href="./BoardListAction.bo?page=<%=a %>">[<%=a %>]</a>
					&nbsp; 
					<%} %> 
				<%} %> 
				
				<%if(nowPage>=maxPage){ %> 
				[����] 
				<%}else{ %> 
				<a href="./BoardListAction.bo?page=<%=nowPage+1 %>">[����]</a> 
				<%} %>
			</td>
		</tr>
		
		<%}else{ %>
		<tr align="center" valign="middle">
			<th colspan="4">MVC�Խ���</th>
			<td align="right"><font size="2">��ϵ� ���� �����ϴ�.</font></td>
		</tr>
		<%} %>
		<tr align="right">
			<td colspan="5"><a href="./BoardWrite.bo">[�۾���]</a></td>
		</tr>
	</table>
</div>
</body>
</html>