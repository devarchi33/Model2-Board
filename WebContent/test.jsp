<%@page import="java.sql.Date"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="net.board.db.*" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>테스트 페이지</title>
</head>
<body>
	<%
		BoardDAO dao = new BoardDAO();
		int x = 1;
		x = dao.getListCount();
		List<BoardBean> list = dao.getBoardList(1, 1);
		BoardBean bean = list.get(0);
		String name = bean.getBOARD_NAME(); 
		
		BoardBean bean2 = new BoardBean();
		bean2.setBOARD_CONTENT("ds");
		bean2.setBOARD_FILE("ff");
		bean2.setBOARD_NAME("Donghoon");
		bean2.setBOARD_NUM(1);
		bean2.setBOARD_PASS("sd");
		bean2.setBOARD_RE_LEV(1);
		bean2.setBOARD_RE_REF(1);
		bean2.setBOARD_RE_SEQ(1);
		bean2.setBOARD_READCOUNT(1);
		bean2.setBOARD_SUBJECT("ew");
		boolean inserted = dao.boardInsert(bean2);
		
		int h = dao.boardReply(bean);

		bean.setBOARD_CONTENT("test3");
		
		boolean modified = dao.boardModify(bean);
		
		boolean deleted = dao.boardDelete(6);
		dao.setReadCountUpdate(1);
		
		BoardBean getBean = dao.getDetail(1);
		
		boolean isWriter = dao.isBoardWriter(1, "board");
	%>
	<h1><%=x %></h1>
	<h1><%=name %></h1>
	<h1><%=inserted %></h1>
	<h1><%=h %></h1>
	<h1><%=modified %></h1>
	<h1><%=deleted %></h1>
	<h1><%=getBean.getBOARD_READCOUNT() %></h1>
	<h1><%=isWriter %></h1>
	<p><a href="/BoardWrite.bo">리스트</a></p>
</body>
</html>