<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="net.board.db.*" %>    
<%
	BoardBean boardBean = (BoardBean)request.getAttribute("boardBean");
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>MVC 게시판 </title>
</head>
<body>
	<div align="center">
		<table cellpadding="0" cellspacing="0">
			<tr align="center" valign="middle">
				<td colspan="5">MVC 게시판</td>
			</tr>
			<tr>
				<td style="font-family: 돋움; font-size: 12" height="16">
					<div align="center">제목&nbsp;&nbsp;</div>
				</td>
				<td style="font-family: 돋움;font-size: 12">
					<%=boardBean.getBOARD_SUBJECT() %>
				</td>
			</tr>
			<tr bgcolor="ccc">
				<td colspan="2" style="height: 1px;"></td>
			</tr>
			<tr>
				<td style="font-family: 돋움; font-size: 12">
					<div align="center">내용</div>
				</td>
				<td style="font-family: 돋움; font-size: 12">
					<table border="0" width="490" height="250" style="table-layout: fixed;">
						<tr>
							<td valign="top" style="font-family: 돋움; font-size: 12">
								<%=boardBean.getBOARD_CONTENT() %>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td style="font-family: 돋움; font-size: 12">
					<div align="center">첨부파일</div>
				</td>&nbsp;
				<td style="font-family: 돋움; font-size: 12">
					<%if(!(boardBean.getBOARD_FILE()==null)){ %>
					<a href="./boardupload/<%=boardBean.getBOARD_FILE()%>">
						<%=boardBean.getBOARD_FILE() %>
					</a>
					<%} %>
				</td>
			</tr>
			<tr bgcolor="ccc">
				<td colspan="2" style="height: 1px;"></td>
			</tr>
			<tr><td colspan="2">&nbsp;</td></tr>
			<tr align="center" valign="middle">
				<td colspan="5">
					<font size="2">
						<a href="./BoardReplyView.bo?num=<%=boardBean.getBOARD_NUM()%>">
							[답변]
						</a>&nbsp;&nbsp;
						<a href="./BoardModify.bo?num=<%=boardBean.getBOARD_NUM()%>">
							[수정]
						</a>&nbsp;&nbsp;
						<a href="./BoardDelete.bo?num=<%=boardBean.getBOARD_NUM()%>">
							[삭제]
						</a>&nbsp;&nbsp;
						<a href="./BoardListAction.bo">[목록]</a>&nbsp;&nbsp;
					</font>
				</td> 	
			</tr>
		</table>
	</div>
</body>
</html>