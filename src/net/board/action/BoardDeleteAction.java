package net.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardDAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BoardDeleteAction implements Action {
	static Logger logger = LoggerFactory.getLogger(BoardDeleteAction.class);
	
	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("euc-kr");
		
		boolean result = false;
		boolean userCheck = false;
		int num = Integer.parseInt(request.getParameter("num"));
		
		BoardDAO boardDao = new BoardDAO();
		userCheck = boardDao.isBoardWriter(num, request.getParameter("BOARD_PASS"));
		
		if(userCheck == false) {
			response.setContentType("text/html;charset=euc-kr");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('삭제할 권한이 없습니다.');");
			out.println("location.href='./BoardListAction.bo'");
			out.println("</script>");
			out.close();
			return null;			
		}
		
		result = boardDao.boardDelete(num);
		if(result == false) {
			logger.info("게시판 삭제 실패");
			return null;
		}
		
		logger.info("게시판 삭제 성공");
		forward.setRedirect(true);
		forward.setPath("./BoardListAction.bo");
		
		return forward;
	}

}
