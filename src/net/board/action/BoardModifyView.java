package net.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.board.db.BoardBean;
import net.board.db.BoardDAO;

public class BoardModifyView implements Action {
	static Logger logger = LoggerFactory.getLogger(BoardModifyView.class);
	
	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("euc-kr");
		
		BoardDAO boardDao = new BoardDAO();
		BoardBean boardBean = new BoardBean();
		
		int num = Integer.parseInt(request.getParameter("num"));
		boardBean = boardDao.getDetail(num);
		
		if(boardBean == null) {
			logger.info("(수정) 상세보기 실패.");
			return null;
		}
		logger.info("(수정) 상세보기 성공.");
		
		request.setAttribute("boardBean", boardBean);
		forward.setRedirect(false);
		forward.setPath("./board/qna_board_modify.jsp");
		
		return forward;
	}

}
