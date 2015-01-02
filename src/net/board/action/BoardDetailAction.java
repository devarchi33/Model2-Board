package net.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardBean;
import net.board.db.BoardDAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BoardDetailAction implements Action {
	static Logger logger = LoggerFactory.getLogger(BoardDetailAction.class);

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("euc-kr");
		
		BoardDAO boardDao = new BoardDAO();
		BoardBean boardBean  = new BoardBean();
		
		int num = Integer.parseInt(request.getParameter("num"));
		boardDao.setReadCountUpdate(num);
		boardBean = boardDao.getDetail(num);
		
		if(boardBean == null) {
			logger.info("상세보기 실패.");
			return null;
		}
		logger.info("상세보기 성공.");
		
		request.setAttribute("boardBean", boardBean);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./board/qna_board_view.jsp");
		
		return forward;
	}

}
