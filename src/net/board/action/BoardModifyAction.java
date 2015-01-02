package net.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardBean;
import net.board.db.BoardDAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BoardModifyAction implements Action {
	static Logger logger = LoggerFactory.getLogger(BoardModifyAction.class);
	
	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("euc-kr");
		ActionForward forward = new ActionForward();
		boolean result = false;
		
		int num = Integer.parseInt(request.getParameter("BOARD_NUM"));
		
		BoardDAO boardDao = new BoardDAO();
		BoardBean boardBean = new BoardBean();
		
		boolean userCheck = boardDao.isBoardWriter(num, request.getParameter("BOARD_PASS"));
		
		if(userCheck == false) {
			response.setContentType("text/html;charset=euc-kr");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('수정할 권한이 없습니다.');");
			out.println("location.href='./BoardListAction.bo'");
			out.println("</script>");
			out.close();
			return null;
		}
		
		try{
			boardBean.setBOARD_NUM(num);
			boardBean.setBOARD_SUBJECT(request.getParameter("BOARD_SUBJECT"));
			boardBean.setBOARD_CONTENT(request.getParameter("BOARD_CONTENT"));
			
			result = boardDao.boardModify(boardBean);
			
			if(result == false) {
				logger.info("게시판 수정 실패");
				return null;
			}			
			logger.info("게시판 수정 성공");
			
			forward.setRedirect(true);
			forward.setPath("./BoardDetailAction.bo?num="+boardBean.getBOARD_NUM());
			
			return forward;
		}catch(Exception e) {
			logger.info("게시판 수정 실패",e);
		}
		return null;
	}

}
