<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>MVC �Խ���</title>
<script language="javascript">
	function addboard(){
		boardform.submit();
	}	
</script>
</head>
<body>
<div align="center">
	<form action="./BoardAddAction.bo" method="post" enctype="multipart/form-data" name="boardform">
		<table cellpadding="0" cellspacing="0">
			<tr align="center" valign="middle">
				<td colspan="5">MVC �Խ���</td>
			</tr>
			<tr>
				<td style="font-family: ����; font-size: 12" height="16">
					<div align="center">�۾���</div>
				</td>
				<td>
					<input name="BOARD_NAME" type="text" size="10" maxlength="10" value=""/>
				</td>			
			</tr>
			<tr>
				<td style="font-family: ����; font-size: 12" height="16">
					<div align="center">��й�ȣ</div>
				</td>
				<td>
					<input name="BOARD_PASS" type="password" size="10" maxlength="10" value=""/>
				</td>			
			</tr>
			<tr>
				<td style="font-family: ����; font-size: 12" height="16">
					<div align="center">����</div>
				</td>
				<td>
					<input name="BOARD_SUBJECT" type="text" size="50" maxlength="100" value=""/>
				</td>			
			</tr>
			<tr>
				<td style="font-family: ����; font-size: 12" height="16">
					<div align="center">����</div>
				</td>
				<td>
					<textarea name="BOARD_CONTENT" cols="67" rows="15" ></textarea>
				</td>			
			</tr>
			<tr>
				<td style="font-family: ����; font-size: 12" height="16">
					<div align="center">���� ÷��</div>
				</td>
				<td>
					<input name="BOARD_FILE" type="file" />
				</td>			
			</tr>
			<tr bgcolor="ccc">
				<td colspan="2" style="height: 1px;"></td>
			</tr>
			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr align="center" valign="middle">
				<td colspan="5">
					<font size="2">
						<a href="javascript:addboard()">[���]</a>&nbsp;&nbsp;
						<a href="javascript:history.go(-1)">[�ڷ�]</a>
					</font>
				</td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>